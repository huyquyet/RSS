package com.example.hoada921.readrss.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hoada921.readrss.R;
import com.example.hoada921.readrss.models.Data;

import java.util.ArrayList;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 17/03/2016.
 */
public class AdapterListPost extends ArrayAdapter {
    Activity context;
    int resoure;
    ArrayList<Data> arrayList;

    public AdapterListPost(Context context, int resource, ArrayList<Data> arrayList) {
        super(context, resource, arrayList);
        this.context = (Activity) context;
        this.resoure = resource;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(resoure, null);
        final Data item = arrayList.get(position);
        final TextView textView_stt = (TextView) convertView.findViewById(R.id.textView_stt);
        final TextView textView_title = (TextView) convertView.findViewById(R.id.textView_title);
        final TextView textView_date = (TextView) convertView.findViewById(R.id.textView_date);
        final TextView textView_category = (TextView) convertView.findViewById(R.id.textView_category);
        String category = "";
        for (String data : item.getarrayListcategory()) {
            category += (data + " - ");
        }
        textView_stt.setText(String.valueOf(position));
        textView_title.setText(item.getTitle());
        textView_date.setText(item.getPubDate());
        textView_category.setText(category);

        return convertView;
    }
}