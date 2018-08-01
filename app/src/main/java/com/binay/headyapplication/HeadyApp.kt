package com.binay.headyapplication

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by binay on 01/08/18.
 */
class HeadyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        var c = RealmConfiguration.Builder()
        c.name("heady")
        c.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(c.build())
    }
}