package com.example.test.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.test.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilMethods {

    private static UtilMethods instance;

    private UtilMethods() {
        // private constructor
    }

    synchronized public static UtilMethods getInstance() {
        if (instance == null) {
            instance = new UtilMethods();
        }
        return instance;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarGradient(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.background_gradient_status_bar);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    public String getDateTime(long timeInMilliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        return formatter.format(new Date(timeInMilliSeconds));
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
