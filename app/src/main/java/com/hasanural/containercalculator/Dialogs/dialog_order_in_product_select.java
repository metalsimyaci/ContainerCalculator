package com.hasanural.containercalculator.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.hasanural.containercalculator.Adapters.SA_Color;
import com.hasanural.containercalculator.CalculationActivity;
import com.hasanural.containercalculator.DataAccess.Entity.OrderInProduct;
import com.hasanural.containercalculator.DataAccess.Model.SingletonOrderModel;
import com.hasanural.containercalculator.R;
import com.hasanural.containercalculator.Utilities.Helper;

public class dialog_order_in_product_select extends DialogFragment {
    private Button mCancelBtn,mAddBtn;
    Context context;
    public TextView pai_id;
    public Spinner pai_color;
    public EditText pai_name,pai_length,pai_width,pai_height,pai_quantity;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), R.style.WideDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.dialog_order_in_product_select,container,false);
        OrderInProduct p= SingletonOrderModel.getInstance().product;
        mCancelBtn=v.findViewById(R.id.cal_prod_dialog_cancel);
        mAddBtn=v.findViewById(R.id.cal_prod_dialog_add);
        pai_id=v.findViewById(R.id.cal_prd_tw_id);
        pai_name=v.findViewById(R.id.cal_prd_te_name);
        pai_length=v.findViewById(R.id.cal_prd_te_length);
        pai_width=v.findViewById(R.id.cal_prd_te_width);
        pai_height=v.findViewById(R.id.cal_prd_te_height);
        pai_quantity=v.findViewById(R.id.cal_prd_te_quantity);
        pai_color=v.findViewById(R.id.cal_prd_sp_color);

        if(p!=null) {
            pai_id.setText(Helper.toString(p.id));
            pai_name.setText(p.definition);
            pai_length.setText(Helper.toString(p.length));
            pai_width.setText(Helper.toString(p.width));
            pai_height.setText(Helper.toString(p.height));
            pai_quantity.setText(Helper.toString(p.quantity));
            pai_color.setAdapter(new SA_Color(getActivity()));
        }
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,color;
                int length,width,height,quantity;
                name=pai_name.getText().toString();
                length=Helper.tryParse(pai_length.getText().toString());
                width=Helper.tryParse(pai_width.getText().toString());
                quantity=Helper.tryParse(pai_quantity.getText().toString());
                height=Helper.tryParse(pai_height.getText().toString());
                color=Helper.toString(pai_color.getSelectedItemPosition());

                if(!Helper.isEmptyOrWhitespace(name)  && length>0 && width>0 && height>0 && quantity>0)
                {
                    OrderInProduct prod=new OrderInProduct();
                    prod.setDefinition(name);
                    prod.setLength(length);
                    prod.setWidth(width);
                    prod.setHeight(height);
                    prod.setColor(color);
                    prod.setQuantity(quantity);

                    ((CalculationActivity)getActivity()).calculate_order.products.add(prod);
                    dismiss();
                }
                if(Helper.isEmptyOrWhitespace(name))
                    pai_name.setError(getResources().getString(R.string.errormessage_product_name_is_required));
                if(length<=0)
                    pai_length.setError(getResources().getString(R.string.errormessage_product_length_is_required));
                if(width<=0)
                    pai_width.setError(getResources().getString(R.string.errormessage_product_width_is_required));
                if(height<=0)
                    pai_height.setError(getResources().getString(R.string.errormessage_product_height_is_required));
                if(quantity<=0)
                    pai_quantity.setError(getResources().getString(R.string.errormessage_product_quantity_is_required));
            }
        });
        return v;
    }


}
