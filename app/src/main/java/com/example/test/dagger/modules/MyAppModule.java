package com.example.test.dagger.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class MyAppModule {

    private Application application;

    public MyAppModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideMyApp() {
        return application;
    }
}
