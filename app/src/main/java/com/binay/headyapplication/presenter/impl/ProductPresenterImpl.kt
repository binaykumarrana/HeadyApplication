package com.binay.headyapplication.presenter.impl

import android.content.Context
import android.util.Log
import com.binay.headyapplication.data.ProductCategory
import com.binay.headyapplication.data.ProductResponse
import com.binay.headyapplication.data.Products
import com.binay.headyapplication.di.ApiInteractor
import com.binay.headyapplication.presenter.ProductPresenter
import com.binay.headyapplication.view.ProductView
import io.realm.Realm
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by binay on 02/08/18.
 */
class ProductPresenterImpl : ProductPresenter {
    private var view: ProductView? = null
    private var compositeSubscription: CompositeSubscription? = null
    private val interactor: ApiInteractor
    val TAG = ProductPresenterImpl::class.java.name

    constructor(interactor: ApiInteractor) {
        this.interactor = interactor
    }

    override fun onAttachView(view: ProductView, context: Context) {
        this.view = view
        compositeSubscription = CompositeSubscription()
        getProducts()
    }


    override fun onDetachView() {
        if (this.view != null) {
            view = null
            compositeSubscription?.unsubscribe()
            compositeSubscription = null
        }
    }

    override fun getProducts() {
        compositeSubscription?.add(interactor.getAllCategoriesProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    //writeToRealm(result)
                    view?.onSuccess(prepareCategoryList(result))

                }, { throwable: Throwable? ->
                    view?.onFailure(true)
                }))
    }

    private fun prepareCategoryList(productResponse: ProductResponse): HashMap<ProductCategory, List<Products>> {
        var expandableListDetail = HashMap<ProductCategory, List<Products>>()
        for (product in productResponse.categories) {
            expandableListDetail[product] = product.products
        }
        return expandableListDetail
    }

    private fun writeToRealm(productResponse: ProductResponse) {
        /* var realm = Realm.getDefaultInstance()
         realm.beginTransaction()
         realm.copyToRealm(productResponse)
         realm.commitTransaction()*/
    }
}