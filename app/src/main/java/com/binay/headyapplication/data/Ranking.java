package com.binay.headyapplication.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by binay on 04/08/18.
 */

public class Ranking extends RealmObject {
    @SerializedName("ranking")
    private String ranking;
    @SerializedName("products")
    private RealmList<Product> Product;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public RealmList<com.binay.headyapplication.data.Product> getProduct() {
        return Product;
    }

    public void setProduct(RealmList<com.binay.headyapplication.data.Product> product) {
        Product = product;
    }
}
