package com.hasanural.containercalculator.Utilities.ViewHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.hasanural.containercalculator.R;

public class ContainerVH   extends RecyclerView.ViewHolder{
    private Context context;
    public TextView cai_id,cai_measurement,cai_tolerance,cai_name;

    public ContainerVH(Context context,@NonNull View itemView) {
        super(itemView);
        this.context=context;
        cai_id=itemView.findViewById(R.id.cai_tw_id);
        cai_name=itemView.findViewById(R.id.cai_tw_name);
        cai_measurement=itemView.findViewById(R.id.cai_tw_measurement);
        cai_tolerance=itemView.findViewById(R.id.cai_tw_tolerance);
//        cai_color=itemView.findViewById(R.id.cai_sp_container_color);
//        cai_length=itemView.findViewById(R.id.cai_te_length);
//        cai_width=itemView.findViewById(R.id.cai_te_width);
//        cai_height=itemView.findViewById(R.id.cai_te_height);
//        cai_t_length=itemView.findViewById(R.id.cai_te_tolerance_length);
//        cai_t_width=itemView.findViewById(R.id.cai_te_tolerance_width);
//        cai_t_height=itemView.findViewById(R.id.cai_te_tolerance_height);
    }
}
