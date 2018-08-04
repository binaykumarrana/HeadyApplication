package com.binay.headyapplication.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.binay.headyapplication.HeadyApp;
import com.binay.headyapplication.R;
import com.binay.headyapplication.data.ProductCategory;
import com.binay.headyapplication.data.Products;
import com.binay.headyapplication.di.DaggerMainActivityComponent;
import com.binay.headyapplication.presenter.ProductPresenter;
import com.binay.headyapplication.view.ProductView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements ProductView {
    @Inject
    ProductPresenter presenter;
    SwipeRefreshLayout swipeRefreshLayout;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDagger();
        initViews();
    }

    private void initViews() {
        expandableListView = findViewById(R.id.expandableListView);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setRefreshing(true);
        presenter.onAttachView(HomeActivity.this, this);
    }

    private void initDagger() {
        DaggerMainActivityComponent.builder().
                networkComponent(((HeadyApp) getApplicationContext()).getNetworkComponent())
                .build().inject(HomeActivity.this);
    }

    @Override
    public void onSuccess(@NotNull Map<ProductCategory, ? extends List<? extends Products>> productResponse, @NotNull Map<String, ? extends List<? extends Products>> rankings) {
        swipeRefreshLayout.setRefreshing(false);
        Log.d("Home", " success" + productResponse.size() + "ranking" + rankings.size());
        List<ProductCategory> list = new ArrayList<>();
        for (Map.Entry m : productResponse.entrySet()) {
            list.add((ProductCategory) m.getKey());
        }
        ExpandableListAdapter expandableListAdapter = new ProductAdapter(this, list, productResponse);
        expandableListView.setAdapter(expandableListAdapter);
    }

    @Override
    public void onFailure(boolean isFailed) {
        swipeRefreshLayout.setRefreshing(false);
    }
}
