package com.hasanural.containercalculator.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hasanural.containercalculator.R;

import java.util.ArrayList;

public class SA_Languages extends BaseAdapter {

    ArrayList<String> languages;
    Context context;

    public SA_Languages(Context context) {
        this.context = context;
        languages=new ArrayList<>();
        String langs[]=context.getResources().getStringArray(R.array.setting_languages);
        for (String lang:langs)
            languages.add(lang);

    }

    @Override
    public int getCount() {
        return languages.size();
    }

    @Override
    public Object getItem(int position) {
        return languages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater linflater=LayoutInflater.from(context);
        convertView=linflater.inflate(android.R.layout.simple_spinner_dropdown_item,null);
        TextView tv=convertView.findViewById(android.R.id.text1);
        tv.setText(languages.get(position));
        tv.setTextSize(20f);
        return convertView;
    }
}
