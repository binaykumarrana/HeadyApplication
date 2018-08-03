package com.binay.headyapplication.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.binay.headyapplication.data.Products
import kotlinx.android.synthetic.main.grid_item.view.*

/**
 * Created by binay on 03/08/18.
 */
public class CustomViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val productName = itemView!!.tvProductName
    val productSize = itemView!!.tvProductName
    val productColor = itemView!!.tvProductName
    val productPrice = itemView!!.tvProductName
    fun bindViews(context: Context, prodctus: Products) {
        productName.text = prodctus.name
        productSize.text = prodctus.variants[0].size.toString()
        productColor.text = prodctus.variants[0].color
        productPrice.text = prodctus.variants[0].price.toString()
    }
}