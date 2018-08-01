package com.binay.headyapplication.view

import com.binay.headyapplication.data.ProductResponse

/**
 * Created by binay on 02/08/18.
 */
interface ProductView {
    fun onSuccess(productResponse: ProductResponse)
    fun onFailure(isFailed: Boolean)
}