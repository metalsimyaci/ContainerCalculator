package com.hasanural.containercalculator.Utilities.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hasanural.containercalculator.R;

public class ProductEditableVH extends RecyclerView.ViewHolder {
    private Context context;
    public TextView pai_id,pai_name,pai_measurement,pai_quantity;
    public ProductEditableVH(Context context, View itemView){
        super(itemView);
        this.context=context;
        pai_id=itemView.findViewById(R.id.pai_tw_id);
        pai_name=itemView.findViewById(R.id.pai_tw_name);
        pai_measurement=itemView.findViewById(R.id.pai_tw_measurement);
        pai_quantity=itemView.findViewById(R.id.pai_tw_quantity);
    }

}
