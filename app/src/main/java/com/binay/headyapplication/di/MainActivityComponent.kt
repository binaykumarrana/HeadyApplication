package com.binay.headyapplication.di

import com.binay.headyapplication.di.network.NetworkComponent
import com.binay.headyapplication.ui.HomeActivity
import dagger.Component

/**
 * Created by binay on 02/08/18.
 */
@ActivityScope
@Component(dependencies = arrayOf(NetworkComponent::class), modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(mainActivity: HomeActivity)
}