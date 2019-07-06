package com.example.employee.Util;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by tenzin on 22/4/16.
 */
public class DefaultRealmConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("employee.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);

    }
}
