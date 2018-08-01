package com.binay.headyapplication.di

import com.binay.headyapplication.api.ApiService
import com.binay.headyapplication.data.ProductResponse
import rx.Single
import javax.inject.Inject

/**
 * Created by binay on 02/08/18.
 */
class ApiInteractor @Inject constructor(val apiService: ApiService) {
    fun getAllCategoriesProducts(): Single<ProductResponse> {
        return apiService.getProducts()
    }
}