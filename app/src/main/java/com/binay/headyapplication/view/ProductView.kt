package com.binay.headyapplication.view

import com.binay.headyapplication.data.ProductCategory
import com.binay.headyapplication.data.ProductResponse
import com.binay.headyapplication.data.Products

/**
 * Created by binay on 02/08/18.
 */
interface ProductView {
    fun onSuccess(productResponse: HashMap<ProductCategory, List<Products>>)
    fun onFailure(isFailed: Boolean)
}