package com.hasanural.containercalculator.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hasanural.containercalculator.DataAccess.Entity.OrderInProduct;
import com.hasanural.containercalculator.Utilities.Helper;
import com.hasanural.containercalculator.Utilities.ViewHolders.ProductEditableVH;
import com.hasanural.containercalculator.R;
import com.hasanural.containercalculator.Events.RV_ItemClickListener;

import java.util.ArrayList;

public class RA_Product_Editable extends RecyclerView.Adapter<ProductEditableVH>
        implements View.OnClickListener {
    LayoutInflater lInflater;
    ArrayList<OrderInProduct> products;
    Context context;
    ArrayList<Integer> colors;
    private RV_ItemClickListener clickListener;

    public RA_Product_Editable(Context context,ArrayList<OrderInProduct> products)
    {
        this.context=context;
        colors=new ArrayList<Integer>();
        int retrieve[]=context.getResources().getIntArray(R.array.spinner_colors);
        for (int re:retrieve)
            colors.add(re);
        lInflater=LayoutInflater.from(context);
        this.products=products;
    }
    public void SetClickListener(RV_ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    @NonNull
    @Override
    public ProductEditableVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=lInflater.inflate(R.layout.adapter_item_product_order,viewGroup,false);
        ProductEditableVH holder=new ProductEditableVH(context,view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductEditableVH productEditableVH, int i) {
        OrderInProduct pd=products.get(i);
        if(pd!=null) {
            productEditableVH.pai_id.setText(Helper.toString(pd.id));
            productEditableVH.pai_name.setText(pd.definition);
            productEditableVH.pai_measurement.setText(Helper.toString(pd.length) + " X " + Helper.toString(pd.width) + " X " + Helper.toString(pd.height));
            productEditableVH.pai_quantity.setText(Helper.toString(pd.quantity));
            productEditableVH.pai_name.setTextColor(colors.get(Helper.tryParse(pd.color)));
        }
        productEditableVH.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return products==null?0: products.size();
    }

    @Override
    public void onClick(View v) {
        TextView tv=v.findViewById(R.id.pai_tw_id);
        int id=Integer.parseInt(tv.getText().toString());
        if(clickListener!=null)clickListener.onClick(v,id);
    }
}
