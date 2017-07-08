package com.example.a123.mobileproject;

import android.animation.Animator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.a123.mobileproject.Service.MusicService;
import com.example.a123.mobileproject.dialog.CustomDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {


    Bitmap bitmap = Bitmap.createBitmap(350,450 , Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);

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
    private Level lv;
    private ImageView imageView;


    @OnClick(R.id.game_exit_bt)
    public void bt(View v) {
        CustomDialog customDialog = new CustomDialog(this, new CustomDialog.ICustomDialogListener() {
            @Override
            public void onOkClick(String msg) {

            }
        });
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.show();
        Intent intent = new Intent(getApplication(), MusicService.class);
        stopService(intent);
    }


    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        imageView =(ImageView) findViewById(R.id.activity_game_iv);
        createBitMap();
        imageView.setImageBitmap(bitmap);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("touch","x"+pxToDp((int) x));
                        Log.d("touch","y"+pxToDp((int) y));
                        return true;
                }
                return false;
            }
        });
        ButterKnife.bind(this);
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
    }


    public void createBitMap(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
         lv = new Level(2);

        Bitmap b= BitmapFactory.decodeResource(getResources(), R.drawable.rsz_1star);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(
                b, 50, 50, false);
        for(int i=0; i<lv.getH_cards_count(); i++){
            for(int j =0; j<lv.getV_cards_count();j++){
                canvas.drawBitmap(bitmap1,50+50*(i),50+50*(j), null);
            }
        }
    }
}
