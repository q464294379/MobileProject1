package com.example.a123.mobileproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.TextView;


import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoryModeActivity extends AppCompatActivity {
    int lv=1;
    @OnClick(R.id.activity_story_bt1)
    public void bt1(View v){
        lv=1;
        Intent intent = new Intent(getApplicationContext(),  GameActivity.class);
        Bundle lvBundle = new Bundle();
        lvBundle.putInt("Level",lv);
        intent.putExtras(lvBundle);
        startActivity(intent);
    }
    @OnClick(R.id.activity_story_bt2)
    public void bt2(View v){
        lv=2;
        Intent intent = new Intent(getApplicationContext(),  GameActivity.class);
        Bundle lvBundle = new Bundle();
        lvBundle.putInt("Level",lv);
        intent.putExtras(lvBundle);
        startActivity(intent);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_mode);

        ButterKnife.bind(this);


    }
}
