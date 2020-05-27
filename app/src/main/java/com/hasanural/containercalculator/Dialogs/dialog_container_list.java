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

import com.hasanural.containercalculator.Adapters.RA_Container;
import com.hasanural.containercalculator.DataAccess.Entity.OrderInContainer;
import com.hasanural.containercalculator.DataAccess.Model.SingletonOrderModel;
import com.hasanural.containercalculator.DataAccess.Repositories.Container_Repository;
import com.hasanural.containercalculator.DataAccess.Entity.Container;
import com.hasanural.containercalculator.Events.DialogResultEventListener;
import com.hasanural.containercalculator.Events.RV_ItemClickListener;
import com.hasanural.containercalculator.R;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class dialog_container_list extends DialogFragment {

    private Button mCancelButton;
    Context context;
    private DialogResultEventListener resultEventListener;
    RecyclerView rv;
    RA_Container rvAdapter;
    Container_Repository con_repo;
    ArrayList<Container> containers;

    @SuppressLint("ValidFragment")
    public dialog_container_list(Context context, DialogResultEventListener event){
        this.context=context;
        resultEventListener=event;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), R.style.WideDialog);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.dialog_container_list,container,false);

        rv=view.findViewById(R.id.dialog_container_list_rv);
        con_repo=new Container_Repository(context);

        LinearLayoutManager llmanager=new LinearLayoutManager(context);

        containers=con_repo.Get_All_Containers();
        rvAdapter=new RA_Container(context,containers);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(llmanager);
        rv.setAdapter(rvAdapter);
        rvAdapter.SetClickListener(new RV_ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView tw_id= view.findViewById(R.id.cai_tw_id);
                int id=Integer.parseInt(tw_id.getText().toString());
                Container c= con_repo.Get_By_Id_Container(id);

                if(c!=null)
                    SingletonOrderModel.getInstance().container = new OrderInContainer(c.id,0, c.definition, c.length, c.width, c.height,
                           c.tolerance_length,c.tolerance_width,c.tolerance_height, c.weight,c.weight_Empty,c.volume, c.color);
                dialog_container_select dialog = new dialog_container_select(context, new DialogResultEventListener() {
                    @Override
                    public void OkResultEvent(int id) {
                        if(id>0)
                            getDialog().dismiss();
                    }
                });
                dialog.show(getActivity().getSupportFragmentManager(), "CALCULATION_CONTAINER_DIALOG_EDIT");
            }
        });

        mCancelButton=view.findViewById(R.id.dialog_container_btn_cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        resultEventListener.OkResultEvent(0);
    }
}
