package com.binay.headyapplication.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by binay on 02/08/18.
 */
open class ProductResponse(
        open var categories: ArrayList<ProductCategory> = ArrayList(),
        open var rankings: ArrayList<Rankings> = ArrayList(),
        open var child_categories: ArrayList<Int> = ArrayList()
) : java.io.Serializable

open class Rankings(
        open var ranking: String? = null,
        open var products: ArrayList<Product> = ArrayList()
) : java.io.Serializable

open class Product(
        open var id: Int? = null,
        open var view_count: Long? = null,
        open var order_count: Long? = null,
        open var shares: Long? = null
) : java.io.Serializable

open class ProductCategory(
        @PrimaryKey open var id: Int? = null,
        open var name: String? = null,
        open var products: ArrayList<Products> = ArrayList()
) : java.io.Serializable


open class Products(
        open var id: Int? = null,
        open var name: String? = null,
        open var date_added: String? = null,
        open var variants: ArrayList<Variants> = ArrayList(),
        open var tax: Tax? = null
) : java.io.Serializable

open class Tax(
        open var name: String? = null,
        open var value: Double? = null
) : java.io.Serializable


open class Variants(
        open var id: Int? = null,
        open var color: String? = null,
        open var size: Int? = null,
        open var price: Double? = null
) : java.io.Serializable