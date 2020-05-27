package com.hasanural.containercalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hasanural.containercalculator.Utilities.LanguageHelper;

public class ActivityMain extends BaseActivity {

    CardView cvCalculation,cvContainers,cvProducts,cvOrders,cvSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        setContentView(R.layout.activity_main);
        loadCardView();
    }
    private void loadCardView(){
        cvCalculation= findViewById(R.id.cv_calculate);
        cvCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCardViewDialog(v);
            }
        });
        cvContainers= findViewById(R.id.cv_container);
        cvContainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCardViewDialog(v);
            }
        });
        cvProducts= findViewById(R.id.cv_product);
        cvProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCardViewDialog(v);
            }
        });
        cvOrders= findViewById(R.id.cv_orders);
        cvOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCardViewDialog(v);
            }
        });
        cvSettings= findViewById(R.id.cv_setting);
        cvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCardViewDialog(v);
            }
        });
        SetBackground();
    }

    private void OpenCardViewDialog(View v) {
        Intent i=null;

        switch (v.getId())
        {
            case R.id.cv_container:
                i=new Intent(this,ContainerActivity.class);
                break;
            case R.id.cv_product:
                i=new Intent(this,ProductActivity.class);
                break;
            case R.id.cv_orders:
                //i=new Intent(this,orderListActivity.class);
                break;
            case R.id.cv_setting:
                i=new Intent(this,SettingActivity.class);
                break;
            case R.id.cv_calculate:
                i=new Intent(this,CalculationActivity.class);
                break;
            default:
                throw new IllegalArgumentException(getString(R.string.errormessage_cardview_not_found));
        }
        if (i!=null)
            startActivity(i);
    }
    private void SetBackground() {
        ImageView background=findViewById(R.id.iv_logo);
        Glide.with(this)
                .load(R.drawable.ic_logo)
                .apply(new RequestOptions().centerCrop())
                .into(background);
    }
}
