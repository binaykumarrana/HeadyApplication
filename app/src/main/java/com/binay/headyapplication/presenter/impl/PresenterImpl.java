package com.binay.headyapplication.presenter.impl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.binay.headyapplication.R;
import com.binay.headyapplication.data.Product;
import com.binay.headyapplication.data.ProductCategory;
import com.binay.headyapplication.data.Products;
import com.binay.headyapplication.data.Ranking;
import com.binay.headyapplication.data.Response;
import com.binay.headyapplication.di.ApiInteractor;
import com.binay.headyapplication.presenter.ProductPresenter;
import com.binay.headyapplication.util.HeadyUtilsKt;
import com.binay.headyapplication.view.ProductView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
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
                }, throwable -> {
                    productView.onFailure(true);
                }));
    }

    private void saveToLocalStorage(Response response) {
        Realm realm = Realm.getDefaultInstance();
        for (ProductCategory productCategory : response.getCategory()) {
            for (Products products : productCategory.getProducts()) {
                products.setShareCount(getShareCount(response.getRanking(), products.getId()));
                products.setViewCount(getViewCount(response.getRanking(), products.getId()));
                products.setOrderCount(getOrderCount(response.getRanking(), products.getId()));
            }
        }
        realm.executeTransactionAsync(realm1 -> {
                    realm1.copyToRealm(response);
                },
                () -> {
                    productView.onSuccess(prepareCategoryList(), getProductRanking());
                },
                error -> {
                    Log.d("PresenterImpl", " reaml onError" + error.getMessage());
                });

    }

    private RealmResults<Response> fetchLocalData() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Response> result = realm.where(Response.class).findAllAsync();
        realm.commitTransaction();
        return result;
    }

    /**
     * This method will give direct ranking based list of products
     * So if UI need to display product based on Ranking we can use directly this map and
     * for each rank all list of product can be shown
     */
    private Map<String, List<Products>> getProductRanking() {
        RealmResults<Response> result = fetchLocalData();
        if (result == null || result.get(0) == null)
            return null;
        Map<Integer, List<String>> rankingGroup = new HashMap<>();
        Map<String, List<Products>> finalProductHashMap = new HashMap<>();
        for (Ranking rank : result.get(0).getRanking()) {
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
        for (ProductCategory category : result.get(0).getCategory()) {
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

    /**
     * This method ll return map which will have category and product list of each categry
     * This map can be directly used to show data if UI display based on Expandable listview
     * In any other UI case above ranking method map can use
     */
    private Map<ProductCategory, List<Products>> prepareCategoryList() {
        RealmResults<Response> productResponse = fetchLocalData();
        if (productResponse == null || productResponse.get(0) == null)
            return null;
        Map<ProductCategory, List<Products>> expandableListDetail = new HashMap<>();
        for (ProductCategory productCategory : productResponse.get(0).getCategory()) {
            expandableListDetail.put(productCategory, productCategory.getProducts());
        }
        return expandableListDetail;
    }


    /**
     * Method to return view count of each product // just fix position passing to pick rnking list it souldn't
     */
    private Long getViewCount(RealmList<Ranking> ranking, Integer id) {
        if (ranking.get(0) == null || ranking.get(0).getProduct().isEmpty())
            return 0L;
        RealmList<Product> productList = ranking.get(0).getProduct();
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return product.getViewCount();
            }
        }
        return 0L;
    }

    /**
     * Method to return getOrderCount  of each product
     */
    private Long getOrderCount(RealmList<Ranking> ranking, Integer id) {
        if (ranking.get(1) == null || ranking.get(1).getProduct().isEmpty())
            return 0L;
        RealmList<Product> productList = ranking.get(1).getProduct();
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return product.getOrderCount();
            }
        }
        return 0L;
    }

    /**
     * Method to return getShareCount of each product
     */
    private Long getShareCount(RealmList<Ranking> ranking, Integer id) {
        if (ranking.get(2) == null || ranking.get(2).getProduct().isEmpty())
            return 0L;
        RealmList<Product> productList = ranking.get(2).getProduct();
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return product.getShares();
            }
        }
        return 0L;
    }


}
