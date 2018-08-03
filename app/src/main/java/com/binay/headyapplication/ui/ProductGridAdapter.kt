package com.binay.headyapplication.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.binay.headyapplication.R
import com.binay.headyapplication.data.Products

/**
 * Created by binay on 03/08/18.
 */
class ProductGridAdapter(val mContext: Context, val products: List<Products>) : RecyclerView.Adapter<CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val product = products[position]
        holder.bindViews(mContext, product)
    }
}