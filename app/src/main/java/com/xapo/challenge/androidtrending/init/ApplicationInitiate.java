package com.xapo.challenge.androidtrending.init;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class ApplicationInitiate extends Application {

    private static Context APP_CONTEXT=null;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInitiate.APP_CONTEXT = getApplicationContext();
    }

    public static Context getAppContext() {
        return APP_CONTEXT;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}

