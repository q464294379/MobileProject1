package com.example.a123.mobileproject.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.a123.mobileproject.R;
import com.example.a123.mobileproject.adapter.ListNormalAdapter;

import java.util.ArrayList;


public class RankFragment extends Fragment {
    private final ArrayList<String> contentList;
    private final Context context;
    private ListView listView;


    public RankFragment() {
        context = getContext();
        contentList = new ArrayList<String>();
        contentList.add("1. Weng");
        contentList.add("2. John");
        contentList.add("3. Jack");
        contentList.add("4. Xin");
        contentList.add("5. Sherry");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_rank_lv);
        ListNormalAdapter adapter = new ListNormalAdapter(this.getContext(), contentList);
        listView.setAdapter(adapter);
        return view;
    }


}
