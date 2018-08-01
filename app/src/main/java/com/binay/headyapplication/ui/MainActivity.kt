package com.binay.headyapplication.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.binay.headyapplication.HeadyApp
import com.binay.headyapplication.R
import com.binay.headyapplication.di.DaggerMainActivityComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDagger()
    }

    private fun initDagger() {
        DaggerMainActivityComponent.builder().networkComponent((application as HeadyApp).getNetworkComponent())
                .build().inject(this)
    }
}
