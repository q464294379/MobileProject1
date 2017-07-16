package com.example.a123.mobileproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.a123.mobileproject.Service.MusicService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private MusicReceiver music1 = new MusicReceiver();


    @OnClick(R.id.activity_guestbt)
    public void lag(View v){
        Intent intent = new Intent(getApplicationContext(),  MainActivity.class);
        intent.setAction("playing");
        sendBroadcast(intent);
        startActivity(intent);
    }
    @BindView(R.id.activity_login_tv)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Intent music = new Intent();
        music.putExtra("playing",true);
        sendBroadcast(music);
        music1.onReceive(this,music);

        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
      Shader textShader = new LinearGradient(0,0,0,230, new int[]{Color.WHITE,Color.BLACK},new float[]{0,1}, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);
        textView.setAnimation(rotateAnimation);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent music = new Intent();
        music.putExtra("playing",false);
        music1.onReceive(this,music);
    }

    public class MusicReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {


            if(intent.hasExtra("playing")) {
                if(intent.getBooleanExtra("playing", false)) {
                    Intent service = new Intent(context, MusicService.class);
                    context.startService(service);
                } else {
                    Intent service = new Intent(context, MusicService.class);
                    context.stopService(service);
                }
            }
        }
    }

}
