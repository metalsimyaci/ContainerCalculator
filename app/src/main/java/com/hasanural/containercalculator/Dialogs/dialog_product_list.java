package com.hasanural.containercalculator.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hasanural.containercalculator.Adapters.RA_Product;
import com.hasanural.containercalculator.DataAccess.Entity.OrderInProduct;
import com.hasanural.containercalculator.DataAccess.Entity.Product;
import com.hasanural.containercalculator.DataAccess.Model.SingletonOrderModel;
import com.hasanural.containercalculator.DataAccess.Repositories.Product_Repository;
import com.hasanural.containercalculator.Events.DialogResultEventListener;
import com.hasanural.containercalculator.Events.RV_ItemClickListener;
import com.hasanural.containercalculator.R;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class dialog_product_list extends DialogFragment {
    private Button mCancelButton;
    Context context;
    private DialogResultEventListener el_result;
    RecyclerView rv;
    RA_Product rvAdapter;
    Product_Repository repository;
    ArrayList<Product> products;

    @SuppressLint("ValidFragment")
    public dialog_product_list(Context context, DialogResultEventListener event) {
        this.context = context;
        el_result = event;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), R.style.WideDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_product_list, container, false);
        rv = v.findViewById(R.id.dialog_prd_rv);
        repository = new Product_Repository(context);

        LinearLayoutManager ll_manager = new LinearLayoutManager(context);
        products = repository.Get_All();
        rvAdapter = new RA_Product(context, products);

        rv.setLayoutManager(ll_manager);
        rv.setAdapter(rvAdapter);
        rvAdapter.SetClickListener(new RV_ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView tw_id = view.findViewById(R.id.pai_tw_id);
                int id = Integer.parseInt(tw_id.getText().toString());
                Product p = repository.Get_By_Id(id);
                if (p != null)
                    SingletonOrderModel.getInstance().product = new OrderInProduct(p.id, p.definition, p.length, p.width, p.height, 0, 0, p.color);
                dialog_order_in_product_select dialog = new dialog_order_in_product_select();
                dialog.show(getActivity().getSupportFragmentManager(), "PRODUCT_EDIT_DIALOG");
            }
        });

        mCancelButton = v.findViewById(R.id.dialog_prd_btn_cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return v;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        el_result.OkResultEvent(0);
    }
}
