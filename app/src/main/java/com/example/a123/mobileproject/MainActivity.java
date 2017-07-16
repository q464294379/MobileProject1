package com.example.a123.mobileproject;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a123.mobileproject.Service.MusicService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {




    @OnClick(R.id.main_rank)
    public void rank(View v){
        Intent intent = new Intent(getApplicationContext(),  RankActivity.class);
        startActivity(intent);

    }
    @OnClick(R.id.main_story)
    public void story(View v){
        Intent intent = new Intent(getApplicationContext(),  StoryModeActivity.class);
        startActivity(intent);

    }
    @OnClick(R.id.main_setting)
    public void setting(View v){
        Intent intent = new Intent(getApplicationContext(),  SettingActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }




}
