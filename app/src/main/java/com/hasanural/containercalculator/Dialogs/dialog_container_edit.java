package com.hasanural.containercalculator.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.hasanural.containercalculator.Adapters.SA_Color;
import com.hasanural.containercalculator.DataAccess.Entity.Container;
import com.hasanural.containercalculator.DataAccess.Model.SingletonOrderModel;
import com.hasanural.containercalculator.DataAccess.Repositories.Container_Repository;
import com.hasanural.containercalculator.Events.DialogResultEventListener;
import com.hasanural.containercalculator.R;
import com.hasanural.containercalculator.Utilities.Helper;

@SuppressLint("ValidFragment")
public class dialog_container_edit extends DialogFragment {

    Spinner sp_color;
    Button b_cancel,b_edit,b_delete;
    Context context;
    TextView tw_id;
    EditText et_name,et_length,et_width, et_height,et_t_length,et_t_width,et_t_height;
    private DialogResultEventListener resultEventListener;

    @SuppressLint("ValidFragment")
    public dialog_container_edit(Context context, DialogResultEventListener event){
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
        View v= inflater.inflate(R.layout.dialog_container_edit,container,false);
        Container cont= SingletonOrderModel.getInstance().containerModel;
        b_cancel=v.findViewById(R.id.container_dialog_button_cancel);
        b_edit=v.findViewById(R.id.container_dialog_button_edit);
        b_delete=v.findViewById(R.id.container_dialog_button_delete);
        tw_id=v.findViewById(R.id.cal_con_tw_id);
        et_name=v.findViewById(R.id.cal_con_te_container_name);
        et_length=v.findViewById(R.id.cal_con_te_length);
        et_width=v.findViewById(R.id.cal_con_te_width);
        et_height =v.findViewById(R.id.cal_con_te_height);
        et_t_length=v.findViewById(R.id.cal_con_te_tolerance_length);
        et_t_width=v.findViewById(R.id.cal_con_te_tolerance_width);
        et_t_height=v.findViewById(R.id.cal_con_te_tolerance_height);
        sp_color=v.findViewById(R.id.cal_con_sp_container_color);
        sp_color.setAdapter(new SA_Color(context));

        if(cont!=null)
        {
            tw_id.setText(Helper.toString(cont.id));
            et_name.setText(cont.definition);
            et_length.setText(Helper.toString(cont.length));
            et_width.setText(Helper.toString(cont.width));
            et_height.setText(Helper.toString(cont.height));
            et_t_length.setText(Helper.toString(cont.tolerance_length));
            et_t_width.setText(Helper.toString(cont.tolerance_width));
            et_t_height.setText(Helper.toString(cont.tolerance_height));
            if(cont.color.length()>0)
                sp_color.setSelection(Integer.parseInt(cont.color));
        }
        else{
            String message=context.getResources().getString(R.string.errormessage_container_selected_container_not_found);
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        }

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        b_edit.setOnClickListener(new View.OnClickListener() {
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
                t_length=Helper.tryParse(et_t_length.getText().toString());
                t_width=Helper.tryParse(et_t_width.getText().toString());
                t_height=Helper.tryParse(et_t_height.getText().toString());

                if(!Helper.isEmptyOrWhitespace(name)&& id>0  && length>0 && width>0 && height>0)
                {
                    et_name.setError(null);
                    et_length.setError(null);
                    et_width.setError(null);
                    et_height.setError(null);

                    Container cont=new Container();
                    cont.setId(id);
                    cont.setDefinition(name);
                    cont.setLength(length);
                    cont.setWidth(width);
                    cont.setHeight(height);
                    cont.setColor(color);
                    cont.setToleranceLength(t_length);
                    cont.setToleranceWidth(t_width);
                    cont.setToleramceHeight(t_height);

                    Container_Repository repo=new Container_Repository(context);
                    boolean rest=repo.Update(cont);
                    if(rest) {
                        resultEventListener.OkResultEvent(id);
                        dismiss();
                    }
                    else{
                        Toast.makeText(context,getResources().getString(R.string.errormessage_update_error),Toast.LENGTH_LONG).show();
                    }
                }

                if(Helper.isEmptyOrWhitespace(name))
                    et_name.setError(getResources().getString(R.string.errormessage_container_name_is_required));
                else
                    et_name.setError(null);

                if(length<=0)
                    et_length.setError(getResources().getString(R.string.errormessage_container_length_is_required));
                else
                    et_length.setError(null);

                if(width<=0)
                    et_width.setError(getResources().getString(R.string.errormessage_container_width_is_required));
                else
                    et_width.setError(null);

                if(height<=0)
                    et_height.setError(getResources().getString(R.string.errormessage_container_height_is_required));
                else
                    et_height.setError(null);
            }
        });
        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                id=Helper.tryParse(tw_id.getText().toString());

                if(id>0)
                {
                    Container_Repository repo=new Container_Repository(context);
                    boolean rest=repo.Delete(id);
                    if(rest) {
                        resultEventListener.OkResultEvent(id);
                        dismiss();
                    }
                    else{
                        Toast.makeText(context,getResources().getString(R.string.errormessage_delete_error),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return v;
    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
