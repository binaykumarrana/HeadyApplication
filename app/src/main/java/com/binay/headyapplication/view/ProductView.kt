package com.binay.headyapplication.view

import com.binay.headyapplication.data.ProductCategory
import com.binay.headyapplication.data.ProductResponse
import com.binay.headyapplication.data.Products
import com.binay.headyapplication.data.Rankings

/**
 * Created by binay on 02/08/18.
 */
interface ProductView {
    fun onSuccess(productResponse: HashMap<ProductCategory, List<Products>>, rankings: HashMap<String, List<Products>>)
    fun onFailure(isFailed: Boolean)
}