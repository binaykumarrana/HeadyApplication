package com.binay.headyapplication.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by binay on 04/08/18.
 */

public class Variants extends RealmObject {
    @SerializedName("id")
    private Integer id;
    @SerializedName("color")
    private String color;
    @SerializedName("size")
    private Integer size;
    @SerializedName("price")
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
