package com.binay.headyapplication.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by binay on 04/08/18.
 */

public class Response extends RealmObject {
    @SerializedName("categories")
    private  RealmList<ProductCategory> category;
    @SerializedName("rankings")
    private RealmList<Ranking> ranking;
    @SerializedName("child_categories")
    private RealmList<ProductCategory> childCategory;

    public RealmList<ProductCategory> getCategory() {
        return category;
    }

    public void setCategory(RealmList<ProductCategory> category) {
        this.category = category;
    }

    public RealmList<Ranking> getRanking() {
        return ranking;
    }

    public void setRanking(RealmList<Ranking> ranking) {
        this.ranking = ranking;
    }

    public RealmList<ProductCategory> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(RealmList<ProductCategory> childCategory) {
        this.childCategory = childCategory;
    }
}
