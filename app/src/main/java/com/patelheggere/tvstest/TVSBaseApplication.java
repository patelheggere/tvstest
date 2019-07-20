package com.patelheggere.tvstest;

import android.app.Application;

public class TVSBaseApplication extends Application {
    private static TVSBaseApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // ApiClient.intialise();
       /* if(isDeve()) {
            ApiClient.setBaseUrl(AppConstants.appBaseUrlDev);
        }
        else
        {
            ApiClient.setBaseUrl(AppConstants.appBaseUrl);

        }*/

    }
    public static synchronized TVSBaseApplication getInstance() {
        return mInstance;
    }
}
