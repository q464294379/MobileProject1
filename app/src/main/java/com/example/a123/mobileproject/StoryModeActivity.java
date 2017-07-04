package com.example.a123.mobileproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a123.mobileproject.Service.MusicService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoryModeActivity extends AppCompatActivity {
    @OnClick(R.id.activity_story_bt1)
    public void bt1(View v){
        Intent intent = new Intent(getApplicationContext(),  GameActivity.class);
        startActivity(intent);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_mode);

        ButterKnife.bind(this);


    }
}
