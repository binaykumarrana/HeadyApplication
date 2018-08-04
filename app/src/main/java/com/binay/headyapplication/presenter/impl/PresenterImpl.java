package com.binay.headyapplication.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.binay.headyapplication.data.Product;
import com.binay.headyapplication.data.ProductCategory;
import com.binay.headyapplication.data.Products;
import com.binay.headyapplication.data.Ranking;
import com.binay.headyapplication.data.Response;
import com.binay.headyapplication.di.ApiInteractor;
import com.binay.headyapplication.presenter.ProductPresenter;
import com.binay.headyapplication.view.ProductView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by binay on 04/08/18.
 */

public class PresenterImpl implements ProductPresenter {

    private ProductView productView;
    private CompositeSubscription compositeSubscription;
    private ApiInteractor interactor;

    public PresenterImpl(ApiInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onAttachView(@NotNull ProductView view, @NotNull Context context) {
        this.productView = view;
        compositeSubscription = new CompositeSubscription();
        getProducts();
    }

    @Override
    public void onDetachView() {
        if (this.productView != null) {
            productView = null;
            compositeSubscription.unsubscribe();
            compositeSubscription = null;
        }
    }

    @Override
    public void getProducts() {
        compositeSubscription.add(interactor.getAllCategoriesProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    saveToLocalStorage(response);
                    productView.onSuccess(prepareCategoryList(response), getProductRanking(response));
                }, throwable -> {
                    productView.onFailure(true);
                }));
    }

    private void saveToLocalStorage(Response response) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.createObject(Response.class);
        realm.commitTransaction();
        Log.d("PresenterImpl"," reaml store");
    }

    private Map<String, List<Products>> getProductRanking(Response result) {
        Map<Integer, List<String>> rankingGroup = new HashMap<>();
        Map<String, List<Products>> finalProductHashMap = new HashMap<>();
        for (Ranking rank : result.getRanking()) {
            finalProductHashMap.put(rank.getRanking(), new ArrayList<>());
            for (Product product : rank.getProduct()) {
                if (!rankingGroup.containsKey(product.getId())) {
                    List<String> rankList = new ArrayList<>();
                    rankList.add(rank.getRanking());
                    rankingGroup.put(product.getId(), rankList);
                } else {
                    List<String> rankList = rankingGroup.get(product.getId());
                    rankList.add(rank.getRanking());
                }
            }
        }
        for (ProductCategory category : result.getCategory()) {
            for (Products prod : category.getProducts()) {
                for (String rankingStr : rankingGroup.get(prod.getId())) {
                    List<Products> productList = finalProductHashMap.get(rankingStr);
                    productList.add(prod);
                    finalProductHashMap.put(rankingStr, productList);
                }

            }
        }
        return finalProductHashMap;
    }

    private Map<ProductCategory, List<Products>> prepareCategoryList(Response productResponse) {
        Map<ProductCategory, List<Products>> expandableListDetail = new HashMap<>();
        for (ProductCategory product : productResponse.getCategory()) {
            expandableListDetail.put(product, product.getProducts());
        }
        return expandableListDetail;
    }
}
