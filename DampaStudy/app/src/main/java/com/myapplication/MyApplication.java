package com.myapplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.myapplication.ormlite.orm.DatabaseHelper;


public class MyApplication extends Application {
    public static DatabaseHelper databaseHelper = null;
    public static Context mContext;

    private static MyApplication me;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("onCreate", "Application");
        databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        mContext = getApplicationContext();
        me = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    public static MyApplication getInstance() {
        return me;
    }


}
