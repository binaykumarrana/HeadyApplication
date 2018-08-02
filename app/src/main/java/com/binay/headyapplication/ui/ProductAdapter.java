package com.binay.headyapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.binay.headyapplication.R;
import com.binay.headyapplication.data.Product;
import com.binay.headyapplication.data.ProductCategory;
import com.binay.headyapplication.data.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by binay on 03/08/18.
 */

public class ProductAdapter extends BaseExpandableListAdapter {
    private final HashMap<ProductCategory, List<Products>> foodItems;
    private final Context context;
    private final ArrayList<String> foodHeader;

    public ProductAdapter(Context context, ArrayList<String> foodHeader, HashMap<ProductCategory, List<Products>> footItems) {
        this.context = context;
        this.foodHeader = foodHeader;
        this.foodItems = footItems;
    }

    @Override
    public int getGroupCount() {
        return foodHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {

        return foodItems.get(foodHeader.get(i)) == null ? 0 : foodItems.get(foodHeader.get(i)).size();
    }

    @Override
    public String getGroup(int i) {
        return foodHeader.get(i);
    }

    @Override
    public Products getChild(int i, int i1) {
        return foodItems.get(foodHeader.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
        String item = getGroup(i);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.group_category_item, viewGroup, false);
        }
        TextView textView = convertView.findViewById(R.id.tvGroupItem);
        textView.setText(item);
        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
        Products item = getChild(i, i1);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.group_category_item, viewGroup, false);
        }
        TextView textView = convertView.findViewById(R.id.tvGroupItem);
        textView.setText(item.getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


}
