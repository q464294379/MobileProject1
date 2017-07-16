package com.example.a123.mobileproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by 123 on Jul/09/17.
 */

public class picture  {

    private Bitmap bitmap=null;
    private int picid=0;

    public picture(Bitmap bitmap,  int picid) {
        this.bitmap=bitmap;
        this.picid=picid;
    }
    public picture(Bitmap bitmap) {
        this.bitmap=bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }



    public int getPicId(){
        return picid;
    }



}
