package com.binay.headyapplication.presenter

import android.content.Context
import com.binay.headyapplication.view.ProductView

/**
 * Created by binay on 02/08/18.
 */
interface ProductPresenter {
    fun onAttachView(view: ProductView, context: Context)
    fun onDetachView()
    fun getProducts()
}