package com.binay.headyapplication

import android.app.Application
import com.binay.headyapplication.di.network.DaggerNetworkComponent
import com.binay.headyapplication.di.network.NetworkComponent
import com.binay.headyapplication.di.network.NetworkModule
import com.binay.headyapplication.util.baseUrl
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by binay on 01/08/18.
 */
class HeadyApp : Application() {
    private var networkComponent: NetworkComponent? = null

    companion object {
        private var instance: HeadyApp? = null

    }
    public fun getInstance(): HeadyApp {
        if (instance == null)
            instance = HeadyApp()
        return instance!!
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initRealmConfig()
    }

    private fun initRealmConfig() {
        Realm.init(this)
        var realmConfiguration = RealmConfiguration.Builder()
        realmConfiguration.name("heady")
        realmConfiguration.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(realmConfiguration.build())
    }

    public fun initDagger() {
        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(NetworkModule(baseUrl))
                .build()
    }

    public fun getNetworkComponent(): NetworkComponent? {
        return networkComponent
    }
}