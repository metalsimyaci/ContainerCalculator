package com.hasanural.containercalculator.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hasanural.containercalculator.Utilities.Helper;
import com.hasanural.containercalculator.Utilities.ViewHolders.ContainerVH;
import com.hasanural.containercalculator.DataAccess.Entity.Container;
import com.hasanural.containercalculator.R;
import com.hasanural.containercalculator.Events.RV_ItemClickListener;

import java.util.ArrayList;

public class RA_Container extends RecyclerView.Adapter<ContainerVH>
        implements View.OnClickListener{

    LayoutInflater lInflater;
    ArrayList<Container> containers;
    Context context;
    private RV_ItemClickListener clickListener;

    public RA_Container(Context context, ArrayList<Container> containers){
        this.context=context;
        lInflater=LayoutInflater.from(context);
        this.containers=containers;

    }

    public void SetClickListener(RV_ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ContainerVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=lInflater.inflate(R.layout.adapter_item_container,viewGroup,false);
        ContainerVH holder=new ContainerVH(context,view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContainerVH containerViewHolder, int i) {
        Container cn=containers.get(i);

        containerViewHolder.cai_id.setText(Integer.toString(cn.id));
        containerViewHolder.cai_name.setText(cn.definition);
        String measurement=context.getResources().getString(R.string.calculation_container_definition_short_length)+":"+Helper.toString(cn.length)+" "+
                context.getResources().getString(R.string.calculation_container_definition_short_width)+":"+Helper.toString(cn.width)+" "+
                context.getResources().getString(R.string.calculation_container_definition_short_height)+":"+Helper.toString(cn.height);
        containerViewHolder.cai_measurement.setText(measurement);
        String tolerance=context.getResources().getString(R.string.calculation_container_definition_short_length)+":"+Helper.toString(cn.tolerance_length)+" "+
                context.getResources().getString(R.string.calculation_container_definition_short_width)+":"+Helper.toString(cn.tolerance_width)+" "+
                context.getResources().getString(R.string.calculation_container_definition_short_height)+":"+Helper.toString(cn.tolerance_height);
        containerViewHolder.cai_tolerance.setText(tolerance);
        containerViewHolder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return containers==null?0:containers.size();
    }


    @Override
    public void onClick(View v) {
        TextView tv=v.findViewById(R.id.cai_tw_id);
        int id=Integer.parseInt(tv.getText().toString());
        if(clickListener!=null)clickListener.onClick(v,id);
    }
}
