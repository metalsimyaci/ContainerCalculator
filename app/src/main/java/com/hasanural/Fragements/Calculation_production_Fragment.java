package com.hasanural.Fragements;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.hasanural.containercalculator.Adapters.RA_Product_Editable;
import com.hasanural.containercalculator.CalculationActivity;
import com.hasanural.containercalculator.DataAccess.Entity.OrderInProduct;
import com.hasanural.containercalculator.DataAccess.Model.SingletonOrderModel;
import com.hasanural.containercalculator.DataAccess.Repositories.Product_Repository;
import com.hasanural.containercalculator.Dialogs.dialog_order_in_product_select;
import com.hasanural.containercalculator.Dialogs.dialog_product_list;
import com.hasanural.containercalculator.Events.DialogResultEventListener;
import com.hasanural.containercalculator.Events.FragmentValidateEvent;
import com.hasanural.containercalculator.Events.RV_ItemClickListener;
import com.hasanural.containercalculator.R;
import com.hasanural.containercalculator.Utilities.Helper;

import java.util.ArrayList;

public class Calculation_production_Fragment extends android.support.v4.app.Fragment implements FragmentValidateEvent {
    Context context;
    Product_Repository repository;
    RecyclerView rv;
    RA_Product_Editable ra;
    Button btnClearProduct,btnSelectProduct;
    ArrayList<OrderInProduct> products;

    public Calculation_production_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_calculation_production, container, false);
        context=getActivity();

        repository=new Product_Repository(context);
        rv=v.findViewById(R.id.cal_prd_rv);
        LinearLayoutManager ll_manager=new LinearLayoutManager(context);
        rv.setLayoutManager(ll_manager);
        LoadRecycleView();

        btnSelectProduct=v.findViewById(R.id.btn_select_product);
        btnSelectProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_product_list dialog=new dialog_product_list(getActivity(), new DialogResultEventListener() {
                    @Override
                    public void OkResultEvent(int id) {
                        LoadRecycleView();
                    }
                });
                dialog.show(getActivity().getSupportFragmentManager(),"PRODUCT_LIST_DIALOG");
            }
        });

        btnClearProduct=v.findViewById(R.id.btn_clear_product);
        btnClearProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CalculationActivity)getActivity()).calculate_order.products=new ArrayList<OrderInProduct>();
                LoadRecycleView();
            }
        });

        return v;
    }
    private void LoadRecycleView(){
        products=((CalculationActivity)getActivity()).calculate_order.products;
        if(!products.isEmpty()) {
            ra = new RA_Product_Editable(context, products);
            rv.setAdapter(ra);
            ra.SetClickListener(new RV_ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    SingletonOrderModel.getInstance().product = products.get(position);
                    dialog_order_in_product_select dialog = new dialog_order_in_product_select();
                    dialog.show(getActivity().getSupportFragmentManager(), "PRODUCT_EDIT_DIALOG");
                }
            });
        }
    }

    @Override
    public boolean Validate() {
        return true;
    }
}
