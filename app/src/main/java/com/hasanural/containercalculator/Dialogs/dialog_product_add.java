package com.hasanural.containercalculator.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hasanural.containercalculator.Adapters.SA_Color;
import com.hasanural.containercalculator.DataAccess.Entity.Product;
import com.hasanural.containercalculator.DataAccess.Model.SingletonOrderModel;
import com.hasanural.containercalculator.DataAccess.Repositories.Product_Repository;
import com.hasanural.containercalculator.Events.DialogResultEventListener;
import com.hasanural.containercalculator.R;
import com.hasanural.containercalculator.Utilities.Helper;

@SuppressLint("ValidFragment")
public class dialog_product_add extends AppCompatDialogFragment {

    Spinner sp_color;
    Button b_cancel,b_add;
    Context context;
    TextView tw_id;
    EditText et_name,et_length,et_width,et_height;
    private DialogResultEventListener resultEventListener;

    @SuppressLint("ValidFragment")
    public dialog_product_add(Context context, DialogResultEventListener event){
        this.context=context;
        resultEventListener=event;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(context, R.style.WideDialog);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.dialog_product_add,container,false);
        Product pro= SingletonOrderModel.getInstance().productModel;
        b_cancel=v.findViewById(R.id.dialog_product_button_cancel);
        b_add=v.findViewById(R.id.dialog_product_button_add);
        tw_id=v.findViewById(R.id.dialog_product_tw_id);
        et_name=v.findViewById(R.id.dialog_product_te_name);
        et_length=v.findViewById(R.id.dialog_product_te_length);
        et_width=v.findViewById(R.id.dialog_product_te_width);
        et_height =v.findViewById(R.id.dialog_product_te_height);

        sp_color=v.findViewById(R.id.dialog_product_sp_color);
        sp_color.setAdapter(new SA_Color(context));

        if(pro!=null)
        {
            tw_id.setText(Helper.toString(pro.id));
            et_name.setText(pro.definition);
            et_length.setText(Helper.toString(pro.length));
            et_width.setText(Helper.toString(pro.width));
            et_height.setText(Helper.toString(pro.height));
            if(pro.color.length()>0)
                sp_color.setSelection(Integer.parseInt(pro.color));
        }

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name,color;
                int id,length,width,height,t_length,t_width,t_height;
                id=Helper.tryParse(tw_id.getText().toString());
                name=et_name.getText().toString();
                length=Helper.tryParse(et_length.getText().toString());
                width=Helper.tryParse(et_width.getText().toString());
                height=Helper.tryParse(et_height.getText().toString());
                color=Helper.toString(sp_color.getSelectedItemPosition());


                if(!Helper.isEmptyOrWhitespace(name)  && length>0 && width>0 && height>0)
                {
                    et_name.setError(null);
                    et_length.setError(null);
                    et_width.setError(null);
                    et_height.setError(null);

                    Product prod=new Product();
                    prod.setId(id);
                    prod.setDefinition(name);
                    prod.setLength(length);
                    prod.setWidth(width);
                    prod.setHeight(height);
                    prod.setColor(color);

                    Product_Repository repo=new Product_Repository(context);
                    boolean rest=repo.Add(prod);
                    if(rest) {
                        resultEventListener.OkResultEvent(9999);
                        dismiss();
                    }
                    else{
                        Toast.makeText(context,getResources().getString(R.string.errormessage_add_error),Toast.LENGTH_LONG).show();
                    }
                }

                if(Helper.isEmptyOrWhitespace(name))
                    et_name.setError(getResources().getString(R.string.errormessage_product_name_is_required));
                else
                    et_name.setError(null);

                if(length<=0)
                    et_length.setError(getResources().getString(R.string.errormessage_product_length_is_required));
                else
                    et_length.setError(null);

                if(width<=0)
                    et_width.setError(getResources().getString(R.string.errormessage_product_width_is_required));
                else
                    et_width.setError(null);

                if(height<=0)
                    et_height.setError(getResources().getString(R.string.errormessage_product_height_is_required));
                else
                    et_height.setError(null);
            }
        });
        return v;
    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
