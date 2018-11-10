package com.example.admin.myapplication.utils;

import android.content.Context;

public class ScreenUtils {

    public static  int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
