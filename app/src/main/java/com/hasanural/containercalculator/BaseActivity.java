package com.hasanural.containercalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.hasanural.containercalculator.Utilities.LanguageHelper;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageHelper.onAttach(newBase));
    }
}
