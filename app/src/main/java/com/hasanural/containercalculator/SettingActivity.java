package com.hasanural.containercalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hasanural.containercalculator.Adapters.SA_Languages;
import com.hasanural.containercalculator.DataAccess.Entity.Settings;
import com.hasanural.containercalculator.DataAccess.Repositories.Setting_Repository;
import com.hasanural.containercalculator.Utilities.Constants;
import com.hasanural.containercalculator.Utilities.Helper;
import com.hasanural.containercalculator.Utilities.LanguageHelper;

import java.util.ArrayList;

public class SettingActivity extends BaseActivity {

    Button b_save;
    EditText et_api_url;
    Spinner sp_lang;
    Context context;
    Setting_Repository repository;
    ArrayList<Settings> settings;
    SA_Languages saLanguages;
    ArrayList<String> languages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.title_activity_setting));

        setContentView(R.layout.activity_setting);
        this.context=this;
        settings=new ArrayList<>();
        repository=new Setting_Repository(context);
        settings=repository.Get_All();

        languages=new ArrayList<>();
        String langs[]=context.getResources().getStringArray(R.array.setting_languages);
        for (String lang:langs)
            languages.add(lang);

        b_save=findViewById(R.id.setting_button_save);
        et_api_url=findViewById(R.id.setting_et_api_url);
        sp_lang=findViewById(R.id.setting_sp_lang);
        saLanguages=new SA_Languages(context);
        sp_lang.setAdapter(saLanguages);
        if(!settings.isEmpty())
        {
            for (Settings st:settings)
            {
                switch (st.key)
                {
                    case Constants.Setting_Language_Key:
                        sp_lang.setSelection(Helper.tryParse(st.value));
                        break;
                    case Constants.Setting_API_URL_Key:
                        et_api_url.setText(st.value);
                        break;
                }

            }
        }

        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String api_url,lang;
                lang= Helper.toString(sp_lang.getSelectedItemPosition());
                api_url=et_api_url.getText().toString();
                if(!Helper.isEmptyOrWhitespace(lang) && !Helper.isNullOrEmpty(api_url))
                {
                    et_api_url.setError(null);
                    try {
                        Settings st_lang= new Settings(0,Constants.Setting_Language_Key,lang);
                        repository.Update(st_lang);

                        Settings st_api= new Settings(0,Constants.Setting_API_URL_Key,api_url);
                        repository.Update(st_api);
                        switch (lang)
                        {
                            case "1":
                                LanguageHelper.SetLocale("tr",getBaseContext());
                                recreate();
                                break;
                            case "2":
                                LanguageHelper.SetLocale("en",getBaseContext());
                                recreate();
                                break;
                            default:
                                LanguageHelper.SetLocale("",getBaseContext());
                                recreate();
                                break;
                        }
                        Toast.makeText(context,context.getResources().getString(R.string.setting_result_success),Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(context,context.getResources().getString(R.string.errormessage_could_not_be_saved),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
