package com.appdoptame.appdoptame;

import android.app.Application;
import android.content.Context;

public class AppDoptameApp  extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getContext(){
        return appContext;
    }
}