package com.example.ditenun;

import android.app.Application;
import android.content.Context;

import com.example.ditenun.utility.UserConfiguration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    public static final int SCHEMA_VERSION = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        UserConfiguration.getInstance().initSharedPreferences(this);

        //configure default configuration for realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}
