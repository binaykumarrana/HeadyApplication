package com.binay.headyapplication.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.binay.headyapplication.R;
import com.binay.headyapplication.data.ProductCategory;
import com.binay.headyapplication.data.Products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public View getChildView(int headPos, int childPos, boolean b, View convertView, ViewGroup viewGroup) {
        Products item = getChild(headPos, childPos);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_subcategory, viewGroup, false);
        }
        ((TextView) convertView.findViewById(R.id.tvProdName)).setText(item.getName());
        TextView priceText = convertView.findViewById(R.id.tvProductPrice);
        LinearLayout colorLayout = convertView.findViewById(R.id.llProductColor);
        colorLayout.removeAllViews();
        setProductColor(item, colorLayout);
        setProductSizeAndPrice(convertView, item, priceText);
        return convertView;
    }

    private void setProductSizeAndPrice(View convertView, Products item, TextView priceText) {
        Set<Integer> size = new HashSet<>();
        Set<Double> price = new HashSet<>();
        for (int i = 0; i < item.getVarients().size(); i++) {
            if (item.getVarients().get(i).getSize() != null) {
                size.add(item.getVarients().get(i).getSize());
            }
            price.add(item.getVarients().get(i).getPrice());
        }
        Iterator<Integer> sizeIterator = size.iterator();
        Iterator<Double> proceIterator = price.iterator();
        List<Integer> productSize = new ArrayList<>();
        List<Double> productPrice = new ArrayList<>();
        while (sizeIterator.hasNext()) {
            productSize.add(sizeIterator.next());
        }
        while (proceIterator.hasNext()) {
            productPrice.add(proceIterator.next());
        }
        Collections.sort(productSize);
        Collections.sort(productPrice);
        setSizeSelection(convertView, priceText, productSize, productPrice);
    }

    private void setSizeSelection(View convertView, TextView priceText, List<Integer> productSize, List<Double> productPrice) {
        Spinner spinner = convertView.findViewById(R.id.spinnerSize);
        if ((productSize != null) && (productSize.size() > 0)) {
            spinner.setVisibility(View.VISIBLE);
        } else {
            spinner.setVisibility(View.GONE);
        }
        priceText.setText("Price: " + productPrice.get(0));
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, productSize);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                Integer item = (Integer) adapterView.getItemAtPosition(position);
                priceText.setText("Price: " + productPrice.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setProductColor(Products item, LinearLayout colorLayout) {
        Set<String> colors = new HashSet<>();
        for (int i = 0; i < item.getVarients().size(); i++) {
            colors.add(item.getVarients().get(i).getColor());
        }

        Iterator<String> colorIterator = colors.iterator();
        while (colorIterator.hasNext()) {
            TextView textView = new TextView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
            params.setMargins(8, 8, 8, 8);
            textView.setLayoutParams(params);
            textView.setPadding(4, 4, 4, 4);
            textView.setBackgroundResource(R.drawable.color_bg);
            GradientDrawable textViewBg = (GradientDrawable) textView.getBackground();
            colorSelectionOfProduct(colorIterator, textViewBg);
            colorLayout.addView(textView);
        }
    }

    private void colorSelectionOfProduct(Iterator<String> colorIterator, GradientDrawable textViewBg) {
        switch (colorIterator.next()) {
            case "Red":
                textViewBg.setColor(Color.RED);
                break;
            case "Blue":
                textViewBg.setColor(Color.BLUE);
                break;
            case "Black":
                textViewBg.setColor(Color.BLACK);
                break;
            case "White":
                textViewBg.setColor(Color.WHITE);
                break;
            case "Green":
                textViewBg.setColor(Color.GREEN);
                break;
            case "Silver":
                textViewBg.setColor(Color.parseColor("#C0C0C0"));
                break;
            case "Brown":
                textViewBg.setColor(Color.parseColor("#A52A2A"));
                break;
            default:
                textViewBg.setColor(Color.parseColor("#FF4081"));
                break;
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


}
