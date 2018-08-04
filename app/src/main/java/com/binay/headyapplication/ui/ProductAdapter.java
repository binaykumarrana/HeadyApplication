package com.binay.headyapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.binay.headyapplication.R;
import com.binay.headyapplication.data.ProductCategory;
import com.binay.headyapplication.data.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by binay on 03/08/18.
 */

public class ProductAdapter extends BaseExpandableListAdapter {
    private final Map<ProductCategory, ? extends List<? extends Products>> productItems;
    private final Context context;
    private final List<ProductCategory> productHeader;

    public ProductAdapter(Context context, List<ProductCategory> foodHeader, Map<ProductCategory, ? extends List<? extends Products>> footItems) {
        this.context = context;
        this.productHeader = foodHeader;
        this.productItems = footItems;
    }

    @Override
    public int getGroupCount() {
        return productHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {

        return productItems.get(productHeader.get(i)) == null ? 0 : productItems.get(productHeader.get(i)).size();
    }

    @Override
    public String getGroup(int i) {
        return productHeader.get(i).getName();
    }

    @Override
    public Products getChild(int i, int i1) {

        return productItems.get(productHeader.get(i)).get(i1);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_subcategory, viewGroup, false);
        }
        ((TextView) convertView.findViewById(R.id.tvProdName)).setText(item.getName());
//        ((TextView)convertView.findViewById(R.id.tvProductSize)).setText(""+item.getVariants().get(0).getSize());
//        ((TextView) convertView.findViewById(R.id.tvProductColor)).setText(""+item.getVariants().get(0).getColor());
//        ((TextView)convertView.findViewById(R.id.tvProductPrice)).setText(""+item.getVariants().get(0).getPrice());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


}
