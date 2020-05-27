package com.hasanural.containercalculator.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hasanural.containercalculator.DataAccess.Entity.Product;
import com.hasanural.containercalculator.Utilities.ViewHolders.ProductVH;
import com.hasanural.containercalculator.R;
import com.hasanural.containercalculator.Events.RV_ItemClickListener;

import java.util.ArrayList;

public class RA_Product extends RecyclerView.Adapter<ProductVH> implements View.OnClickListener{

    LayoutInflater lInflater;
    ArrayList<Product> products;
    Context context;
    private RV_ItemClickListener clickListener;

    public RA_Product(Context context,ArrayList<Product> products){
        this.context=context;
        lInflater=LayoutInflater.from(context);
        this.products=products;
    }
    public void SetClickListener(RV_ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=lInflater.inflate(R.layout.adapter_item_product,viewGroup,false);
        ProductVH holder=new ProductVH(context,view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH productVH, int i) {
        Product pd=products.get(i);

        productVH.pai_id.setText(Integer.toString(pd.id));
        productVH.pai_name.setText(pd.definition);
        productVH.pai_measurement.setText(Integer.toString(pd.length)+" X "+Integer.toString(pd.width)+" X "+Integer.toString(pd.height));
        productVH.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return products==null?0:products.size();
    }

    @Override
    public void onClick(View v) {
        TextView tv=v.findViewById(R.id.pai_tw_id);
        int id=Integer.parseInt(tv.getText().toString());
        if(clickListener!=null)clickListener.onClick(v,id);

    }
}
