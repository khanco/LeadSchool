package com.example.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.example.test.utils.UtilMethods;

import org.junit.Assert;
import org.junit.Test;

public class UtilMethodTest {

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Assert.assertEquals("com.example.test", appContext.getPackageName());
    }

    /*
    Here you need to manually switch on your internet connection
     */

    @Test
    public void isInternetConnected() {
        boolean isConnected = UtilMethods.getInstance().isNetworkAvailable();
        Assert.assertEquals("Internet Connection Status", true, isConnected);
    }
}
