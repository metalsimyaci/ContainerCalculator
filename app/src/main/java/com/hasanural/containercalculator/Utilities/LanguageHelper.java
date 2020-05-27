package com.hasanural.containercalculator.Utilities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.StringDef;

import com.hasanural.containercalculator.DataAccess.Entity.Settings;
import com.hasanural.containercalculator.DataAccess.Repositories.Setting_Repository;

import java.util.Locale;

public class LanguageHelper {
    private static final String LANGKEY="CONCALLANG";

    public static Context onAttach(Context c)
    {
        String lang=GetLocaleLang(c,Locale.getDefault().getLanguage());
        return SetLocale(lang,c);

    }
    public static Context onAttach(Context c,String defaultLang)
    {
        String lang=GetLocaleLang(c,defaultLang);
        return SetLocale(lang,c);
    }
    public static Context SetLocale(String lang, Context context){
        persist(context,lang);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return updateResources(context, lang);
        }

        return updateResourcesLegacy(context, lang);

    }
    public static void LoadLocale(Context context){
        String lang=GetLocaleLang(context,"en");
        SetLocale(lang,context);
    }

    public static String GetLocaleLang(Context context, String defaultLang) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String lang=prefs.getString(LANGKEY,defaultLang);
        return lang;
    }
    private static void persist(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(LANGKEY, language);
        editor.apply();
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }
}
