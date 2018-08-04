package com.binay.headyapplication.di

import com.binay.headyapplication.api.ApiService
import com.binay.headyapplication.data.Response
import rx.Single
import javax.inject.Inject

/**
 * Created by binay on 02/08/18.
 */
class ApiInteractor @Inject constructor(val apiService: ApiService) {
    fun getAllCategoriesProducts(): Single<Response> {
        return apiService.getProducts()
    }
}