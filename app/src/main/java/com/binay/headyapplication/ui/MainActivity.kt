package com.binay.headyapplication.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.binay.headyapplication.HeadyApp
import com.binay.headyapplication.R
import com.binay.headyapplication.data.ProductCategory
import com.binay.headyapplication.data.ProductResponse
import com.binay.headyapplication.data.Products
import com.binay.headyapplication.di.DaggerMainActivityComponent
import com.binay.headyapplication.presenter.ProductPresenter
import com.binay.headyapplication.view.ProductView
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ProductView {

    val TAG = MainActivity::class.java.name
    @Inject
    lateinit var presenter: ProductPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDagger()
        initViews()
    }

    private fun initViews() {
        presenter.onAttachView(this, this)
    }

    private fun initDagger() {
        DaggerMainActivityComponent.builder().networkComponent((application as HeadyApp).getNetworkComponent())
                .build().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun onSuccess(productResponse: HashMap<ProductCategory, List<Products>>, ranking: HashMap<String, List<Products>>) {
        var list: ArrayList<ProductCategory> = ArrayList()
        Log.d(TAG, " success" + productResponse.size+"ranking"+ranking)

        for (prodcuct in productResponse) {
            list.add(prodcuct.key)
        }
        val expandableListAdapter = ProductAdapter(this, list, productResponse)
        expandableListView.setAdapter(expandableListAdapter)
//        val realm = Realm.getDefaultInstance()
//        val productList = realm.where(ProductResponse::class.java).findAllAsync()
//        Log.d(TAG, " realm list" + productList.size)
    }

    override fun onFailure(isFailed: Boolean) {
        Log.d(TAG, " failed" + isFailed)
    }
}
