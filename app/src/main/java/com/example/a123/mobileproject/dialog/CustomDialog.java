package com.example.a123.mobileproject.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.a123.mobileproject.MainActivity;
import com.example.a123.mobileproject.R;
import com.example.a123.mobileproject.RankActivity;
import com.example.a123.mobileproject.StoryModeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * Created by 123 on Jun/21/17.
 */

public class CustomDialog extends Dialog {


    private final ICustomDialogListener listener;



    public interface  ICustomDialogListener{
        public void onOkClick(String msg);
    }

    @OnClick(R.id.dialog_continue)
    public void cont(View v){
        listener.onOkClick("continue");
        cancel();
    }

    @OnClick(R.id.dialog_exit)
    public void ex(View v){
        listener.onOkClick("exit");
        System.exit(0);
        cancel();
    }

    public CustomDialog(@NonNull Context context, ICustomDialogListener listener) {
        super(context, R.style.dialog);
        setContentView(R.layout.dialog_xbt);
        ButterKnife.bind(this);
        this.listener=listener;

    }


}
