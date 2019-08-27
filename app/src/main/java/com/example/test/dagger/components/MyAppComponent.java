package com.example.test.dagger.components;

import android.app.Application;

import com.example.test.dagger.modules.MyAppModule;

import dagger.Component;

@Component (modules = { MyAppModule.class })
public interface MyAppComponent {
    Application app();
}
