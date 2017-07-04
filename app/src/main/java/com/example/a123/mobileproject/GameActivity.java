package com.example.a123.mobileproject;

import android.animation.Animator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.a123.mobileproject.Service.MusicService;
import com.example.a123.mobileproject.dialog.CustomDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {

    private MusicService mServ;
    private ServiceConnection Scon = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    @OnClick(R.id.game_exit_bt)
    public void bt(View v) {
        CustomDialog customDialog = new CustomDialog(this, new CustomDialog.ICustomDialogListener() {
            @Override
            public void onOkClick(String msg) {

            }
        });
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.show();
        Intent intent = new Intent(getApplication(),MusicService.class);
        stopService(intent);
    }

    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        Intent music = new Intent();
        music.setClass(this,MusicService.class);

    }
}
