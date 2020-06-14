package com.binay.headyapplication.ui;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

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

public class HomeActivity extends AppCompatActivity implements ProductView, SwipeRefreshLayout.OnRefreshListener {
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
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.orange),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.green),
                getResources().getColor(R.color.colorAccent));
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
        if (productResponse == null) {
            Toast.makeText(this, getResources().getString(R.string.some_issue), Toast.LENGTH_SHORT).show();
            return;
        }
        if (rankings == null) {
            Toast.makeText(this, getResources().getString(R.string.some_issue), Toast.LENGTH_SHORT).show();
            return;
        }
        List<ProductCategory> list = new ArrayList<>();
        for (Map.Entry m : productResponse.entrySet()) {
            list.add((ProductCategory) m.getKey());
        }
        ExpandableListAdapter expandableListAdapter = new ProductAdapter(this, list, productResponse,rankings);
        expandableListView.setAdapter(expandableListAdapter);
    }

    @Override
    public void onFailure(boolean isFailed) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
