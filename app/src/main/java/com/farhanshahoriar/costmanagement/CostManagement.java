package com.farhanshahoriar.costmanagement;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class CostManagement extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
