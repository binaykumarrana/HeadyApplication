package com.binay.headyapplication.data

/**
 * Created by binay on 02/08/18.
 */
data class ProductResponse(
        val categories: ArrayList<ProductCategory> = ArrayList(),
        val rankings: ArrayList<Rankings> = ArrayList()
) : java.io.Serializable

data class Rankings(
        val ranking: String,
        val products: ArrayList<Product> = ArrayList()
) : java.io.Serializable

data class Product(
        val id: Int,
        val view_count: Long,
        val order_count: Long,
        val shares: Long
) : java.io.Serializable

data class ProductCategory(
        val id: Int,
        val name: String,
        val products: ArrayList<Products> = ArrayList()
//        val child_categories: ArrayList<ChildCategory> = ArrayList()
) : java.io.Serializable

//data class ChildCategory() : java.io.Serializable

data class Products(
        val id: Int,
        val name: String,
        val date_added: String,
        val variants: ArrayList<Variants> = ArrayList(),
        val tax: Tax
) : java.io.Serializable

data class Tax(
        val name: String,
        val value: Double
) : java.io.Serializable


data class Variants(
        val id: Int,
        val color: String,
        val size: Int,
        val price: Double
) : java.io.Serializable