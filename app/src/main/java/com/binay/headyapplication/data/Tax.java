package com.binay.headyapplication.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by binay on 04/08/18.
 */

public class Tax extends RealmObject {
    @SerializedName("name")
    String name;
    @SerializedName("value")
    Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
