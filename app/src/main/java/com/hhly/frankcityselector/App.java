package com.hhly.frankcityselector;

import android.app.Application;

import com.hhly.frankcityselector.db.DBManager;

/**
 * @创建者 frank
 * @时间 2017/2/22 18:25
 * @描述：${TODO}
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DBManager dbManager = new DBManager(this);
        dbManager.openDatabase();


    }
}
