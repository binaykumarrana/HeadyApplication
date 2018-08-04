package com.binay.headyapplication.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by binay on 04/08/18.
 */

public class ProductCategory extends RealmObject {
    @PrimaryKey
    @SerializedName("id")
    Integer id;
    @SerializedName("name")
    String name;
    @SerializedName("products")
    RealmList<Products> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Products> getProducts() {
        return products;
    }

    public void setProducts(RealmList<Products> products) {
        this.products = products;
    }
}
