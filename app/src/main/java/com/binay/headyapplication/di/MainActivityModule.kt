package com.binay.headyapplication.di

import com.binay.headyapplication.presenter.ProductPresenter
import com.binay.headyapplication.presenter.impl.ProductPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by binay on 02/08/18.
 */
@Module
class MainActivityModule {
    @Provides
    @ActivityScope
    fun providePresenter(apiInteractor: ApiInteractor): ProductPresenter{
        return ProductPresenterImpl(apiInteractor)
    }
}