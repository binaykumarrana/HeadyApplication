package com.binay.headyapplication.api

import retrofit2.http.GET
import rx.Single

/**
 * Created by binay on 02/08/18.
 */
interface ApiService {
    @GET("json")
    fun getProducts(): Single<String>
}