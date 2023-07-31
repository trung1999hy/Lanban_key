package com.example.keyboardtheme;

import android.app.Application;


public class MainApp extends Application {

    private static Preference preference = null;
    private static MainApp application = null;
    public static Preference getInstance() {
        if (preference == null) {
            preference = Preference.buildInstance(application.getApplicationContext());
        }
        preference.isOpenFirst();
        return preference;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
