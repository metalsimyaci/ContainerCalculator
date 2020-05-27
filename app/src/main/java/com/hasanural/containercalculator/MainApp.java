package com.hasanural.containercalculator;

import android.app.Application;
import android.content.Context;

import com.hasanural.containercalculator.Utilities.LanguageHelper;

public class MainApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base));
    }
}
