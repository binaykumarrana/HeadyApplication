package com.binay.headyapplication.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by binay on 04/08/18.
 */

public class Products extends RealmObject{
    @SerializedName("id")
    Integer id;
    @SerializedName("name")
    String name;
    @SerializedName("date_added")
    String dateAdded;
    @SerializedName("variants")
    RealmList<Variants> varients;
    @SerializedName("tax")
    Tax tax;

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

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public RealmList<Variants> getVarients() {
        return varients;
    }

    public void setVarients(RealmList<Variants> varients) {
        this.varients = varients;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
}
