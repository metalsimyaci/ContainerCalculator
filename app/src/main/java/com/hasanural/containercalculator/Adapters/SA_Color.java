package com.hasanural.containercalculator.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hasanural.containercalculator.R;

import java.util.ArrayList;

public class SA_Color extends BaseAdapter {

    ArrayList<Integer> colors;
    ArrayList<String> colorTitles;
    Context context;
    public SA_Color(Context context){
        this.context=context;
        colors=new ArrayList<Integer>();
        colorTitles=new ArrayList<String>();
        int retrieve[]=context.getResources().getIntArray(R.array.spinner_colors);
        String retrieveTitle[]=context.getResources().getStringArray(R.array.colors_strings);
        for (int re:retrieve)
            colors.add(re);
        for (String ret:retrieveTitle)
            colorTitles.add(ret);
    }

    @Override
    public int getCount() {
        return colors.size();
    }

    @Override
    public Object getItem(int position) {
        return colors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(android.R.layout.simple_spinner_dropdown_item,null);
        TextView tv=(TextView)convertView.findViewById(android.R.id.text1);
        tv.setBackgroundColor(context.getResources().getColor(R.color.white));
        tv.setTextColor(colors.get(position));
        tv.setTextSize(20f);
        tv.setText(colorTitles.get(position));
        return convertView;
    }
}
