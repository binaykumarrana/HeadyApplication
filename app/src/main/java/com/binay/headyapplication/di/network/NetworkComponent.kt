package com.binay.headyapplication.di.network

import com.binay.headyapplication.di.ApiInteractor
import dagger.Component
import javax.inject.Singleton

/**
 * Created by binay on 02/08/18.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface NetworkComponent {
    fun apiInteractor(): ApiInteractor
}