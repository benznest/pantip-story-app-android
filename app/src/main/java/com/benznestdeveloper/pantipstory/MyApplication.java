package com.benznestdeveloper.pantipstory;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by benznest on 02-Oct-17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        MyContextor.setContext(getApplicationContext());
        MyContextor.setApplication(this);
        MyTheme.init();

        if (MyCache.getLastVersion(this) < 3) {
            MyRoom.setRoomList(null);
            MyCache.setLastVersion(this, BuildConfig.VERSION_CODE);
        }
    }


}
