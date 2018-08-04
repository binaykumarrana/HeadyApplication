package com.binay.headyapplication.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by binay on 04/08/18.
 */

public class Product extends RealmObject {
    @SerializedName("id")
    Integer id;
    @SerializedName("view_count")
    Long viewCount;
    @SerializedName("order_count")
    Long orderCount;
    @SerializedName("shares")
    Long shares;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Long getShares() {
        return shares;
    }

    public void setShares(Long shares) {
        this.shares = shares;
    }
}
