package com.example.test.utils;

import android.app.Application;
import android.content.Context;

import com.example.test.dagger.components.DaggerMyAppComponent;
import com.example.test.dagger.components.MyAppComponent;
import com.example.test.dagger.modules.MyAppModule;

public class MyApp extends Application {

    public static Context context;
    public static MyAppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initDagger();
    }

    void initDagger() {
        mAppComponent = DaggerMyAppComponent.builder()
                .myAppModule(new MyAppModule(this))
                .build();
    }

    public MyAppComponent getApplicationComponent() {
        return mAppComponent;
    }

    public static Context getAppContext() {
        return context;
    }
}
