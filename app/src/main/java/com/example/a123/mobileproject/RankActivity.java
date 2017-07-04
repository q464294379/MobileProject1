package com.example.a123.mobileproject;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a123.mobileproject.adapter.ViewFragmentStateAdapter;
import com.example.a123.mobileproject.fragment.RankFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RankActivity extends AppCompatActivity {
    private final ArrayList<Pair<String,Fragment>> list = new ArrayList<Pair<String, Fragment>>();
    @BindView(R.id.activity_rank_tab)
    TabLayout tabLayout;

    @BindView(R.id.activity_rank_view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ButterKnife.bind(this);
        list.add(new Pair<String, Fragment>("Challenge Mode", new RankFragment()));
        list.add(new Pair<String, Fragment>("Story Mode", new RankFragment()));
        ViewFragmentStateAdapter adapter = new ViewFragmentStateAdapter(this.getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }
}
