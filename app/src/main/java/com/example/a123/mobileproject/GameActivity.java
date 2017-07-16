package com.example.a123.mobileproject;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a123.mobileproject.Service.MusicService;
import com.example.a123.mobileproject.dialog.CustomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {


    Bitmap bitmap = Bitmap.createBitmap(350,450 , Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    public picture pic1, pic2, pic3,pic4,pic5,pic6;
    private Level lv;
    public game g;
    private ImageView imageView;
    private int[][] index;
    private int l2;

    Handler mHandler = new Handler();

    private int time1=15;
    @BindView(R.id.game_stage)
    TextView stage;

    @BindView(R.id.game_time)
    TextView time;
    

    Runnable runnable= new Runnable(){
        @Override
        public void run(){
            time1--;
            if(time1>=0){
                time.setText("Time:" +String.valueOf(time1));
                mHandler.postDelayed(runnable,1000);
            }
            if(time1==0){
                mHandler.removeCallbacks(runnable);
                exitDialog("Game End", "Start a new game?",0);
            }
        }
    };

    private  void exitDialog(String title, String msg, final int f){
        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setTitle(title);
        exit.setMessage(msg);
        exit.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(f==0){
                    System.exit(0);//exit game
                }
                else {
                    mHandler.postDelayed(runnable, 1000);//resume game
                    time.setText("Time: " + time1);
                    dialog.dismiss();
                }
            }
        });
        exit.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(f==0){

                    Intent intent = new Intent(getApplicationContext(),  GameActivity.class);
                    intent.putExtra("Level",""+lv.getLv());
                    startActivity(intent);
                }
                else{
                    System.exit(0);//exit game
                }

                dialog.dismiss();
            }
        });
        AlertDialog alert11 = exit.create();
        alert11.show();
    }



    @Override
    public void onBackPressed() {
        mHandler.removeCallbacks(runnable);
        exitDialog("Exit Game", "Do you want exit now?",1);

    }


    @OnClick(R.id.game_exit_bt)
    public void bt(View v) {
        CustomDialog customDialog = new CustomDialog(this, new CustomDialog.ICustomDialogListener() {
            @Override
            public void onOkClick(String msg) {
                if(msg.equals("continue")){
                    mHandler.postDelayed(runnable,1000);
                    time.setText("Time: "+ time1);
                }
            }
        });
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.show();
        mHandler.removeCallbacks(runnable);
    }


    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        Bundle lvBundle = this.getIntent().getExtras();
        l2 = lvBundle.getInt("Level");


        imageView =(ImageView) findViewById(R.id.activity_game_iv);
        createBitMap();

        imageView.setImageBitmap(bitmap);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = pxToDp((int) event.getX());
                int y = pxToDp((int) event.getY());
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        int r=x/50;
                        int c=y/50;
                        Log.d("touch","x"+c);
                        Log.d("touch","y"+r);
                        reDraw(r,c,pic5);
                       imageView.setImageBitmap(bitmap);
                        return true;
                }
                return false;
            }
        });
        stage.setText("Stage: " +lv.getLv());
        mHandler.postDelayed(runnable,1000);
        time1=lv.getMaxTime();
        time.setText("Time: "+ time1);
//        Intent music = new Intent();
//        music.setClass(this, MusicService.class);
    }

    public void reDraw(int r, int c, picture rpic) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);


        picture pic[]=generatePicArray();
        for (int i = 0; i < lv.getH_cards_count(); i++) {
            for (int j = 0; j < lv.getV_cards_count(); j++) {
                int x = index[i][j] % lv.getV_cards_count();
                if (i == (r-1) & j == (c-1)) {
                    canvas.drawBitmap(rpic.getBitmap(),  50 + 50 * (i), 50 + 50 * (j), null);
                } else {
                    canvas.drawBitmap(pic[x].getBitmap(), 50 + 50 * (i), 50 + 50 * (j), null);
                }
            }
        }
    }

    public void createBitMap(){

        lv = new Level(l2);
        g=new game(lv);
         index =g.getG();
        picture pic[]=generatePicArray();
        for(int i=0; i<lv.getH_cards_count(); i++){
            for(int j =0; j<lv.getV_cards_count();j++){
                 int x= index[i][j]%lv.getV_cards_count();
                  canvas.drawBitmap(pic[x].getBitmap(),50+50*(i),50+50*(j), null);
            }
        }
    }

    private picture[] generatePicArray() {
        Bitmap b1= BitmapFactory.decodeResource(getResources(), R.drawable.icon1);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(b1, 45, 45, false);
        Bitmap b2= BitmapFactory.decodeResource(getResources(), R.drawable.icon2);
        Bitmap bitmap2 = Bitmap.createScaledBitmap(b2, 50, 50, false);
        Bitmap b3= BitmapFactory.decodeResource(getResources(), R.drawable.icon3);
        Bitmap bitmap3 = Bitmap.createScaledBitmap(b3, 50, 50, false);
        Bitmap b4= BitmapFactory.decodeResource(getResources(), R.drawable.icon4);
        Bitmap bitmap4 = Bitmap.createScaledBitmap(b4, 50, 50, false);
        Bitmap b5= BitmapFactory.decodeResource(getResources(), R.drawable.icon5);
        Bitmap bitmap5 = Bitmap.createScaledBitmap(b5, 50, 50, false);
        Bitmap b6= BitmapFactory.decodeResource(getResources(), R.drawable.icon6);
        Bitmap bitmap6 = Bitmap.createScaledBitmap(b6, 50, 50, false);
        pic1= new picture(bitmap1);
        pic2= new picture(bitmap2);
        pic3= new picture(bitmap3);
        pic4= new picture(bitmap4);
        pic5= new picture(bitmap5);
        pic6= new picture(bitmap6);
        picture pic[]={pic1, pic2, pic3,pic4,pic5,pic6} ;
        return pic;
    }


}
