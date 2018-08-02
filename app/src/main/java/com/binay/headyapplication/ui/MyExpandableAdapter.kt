package com.binay.headyapplication.ui

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseExpandableListAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.binay.headyapplication.R
import com.binay.headyapplication.data.ProductCategory
import com.binay.headyapplication.data.Products

import java.util.ArrayList
import java.util.HashMap

/**
 * Created by binay on 02/08/18.
 */

//class MyExpandableAdapter(private val context: Context,
//                          private val productHeader: ArrayList<String>,
//                          private val productsCategory: HashMap<ProductCategory, List<Products>>) : BaseExpandableListAdapter() {

    /*override fun getGroupCount(): Int {
        return productHeader.size
    }

    override fun getChildrenCount(i: Int): Int {
        return productsCategory[productHeader[i]]!!.size
    }

    override fun getGroup(i: Int): Any {
        return productHeader[i]
    }

    override fun getChild(i: Int, i1: Int): Products {
        return productsCategory[this.productHeader[i]]!![i1]
    }

    override fun getGroupId(i: Int): Long {
        return i.toLong()
    }

    override fun getChildId(i: Int, i1: Int): Long {
        return i1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(listPosition: Int, b: Boolean, convertView: View, viewGroup: ViewGroup): View {

        var convertView = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.group_category_item, null)
        }
        val listTitleTextView = convertView!!
                .findViewById<View>(R.id.tvGroupItem) as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return convertView
    }

    override fun getChildView(listPosition: Int, expandedListPosition: Int,
                              isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_item_subcategory, null)
        }
        val expandedListTextView = convertView!!
                .findViewById<View>(R.id.tvSubCatItem) as TextView
        expandedListTextView.text = expandedListText
        return convertView
    }

    override fun isChildSelectable(i: Int, i1: Int): Boolean {
        return true
    }*/


//}
