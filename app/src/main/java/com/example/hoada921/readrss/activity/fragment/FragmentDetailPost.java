package com.example.hoada921.readrss.activity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoada921.readrss.R;
import com.example.hoada921.readrss.models.Data;

public class FragmentDetailPost extends Fragment {
    Data item = null;

    public FragmentDetailPost(Data item) {
        this.item = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_post, container, false);

        return view;
    }
}