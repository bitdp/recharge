package com.example.dongpeng.myapplication;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by dongpeng on 2016/12/29.
 */

public class ToastUtil {
    static Toast toast;
    public static void show(Context context, String msg){
        if (toast==null){
            toast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    };
}
