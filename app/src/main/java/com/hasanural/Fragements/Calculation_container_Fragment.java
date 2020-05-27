package com.hasanural.Fragements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hasanural.containercalculator.Adapters.SA_Color;
import com.hasanural.containercalculator.CalculationActivity;
import com.hasanural.containercalculator.DataAccess.Repositories.Container_Repository;
import com.hasanural.containercalculator.DataAccess.Entity.Container;
import com.hasanural.containercalculator.DataAccess.Entity.OrderInContainer;
import com.hasanural.containercalculator.Dialogs.dialog_container_list;
import com.hasanural.containercalculator.Events.DialogResultEventListener;
import com.hasanural.containercalculator.Events.FragmentValidateEvent;
import com.hasanural.containercalculator.R;
import com.hasanural.containercalculator.Utilities.Helper;

import java.util.ArrayList;


public class Calculation_container_Fragment extends android.support.v4.app.Fragment implements FragmentValidateEvent {

    Container_Repository repContainer;
    TextView container_name,container_measurement,container_tolerance;
    Button btnClearContainer,btnSelectContainer;
    LinearLayout ll_message,ll_content;
    ArrayList<Integer> colors;
    public Calculation_container_Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CalculationActivity)getActivity()).setActivityListener(Calculation_container_Fragment.this);
        int retrieve[]=this.getResources().getIntArray(R.array.spinner_colors);
        colors=new ArrayList<Integer>();
        for (int re:retrieve)
            colors.add(re);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_calculation_container, container, false);
        repContainer=new Container_Repository(getActivity());
        container_name=v.findViewById(R.id.calculation_container_tw_name);
        container_measurement=v.findViewById(R.id.calculation_container_tw_measurement);
        container_tolerance=v.findViewById(R.id.calculation_container_tw_tolerance);

        ll_message=v.findViewById(R.id.calculation_container_layout_empty);
        ll_content=v.findViewById(R.id.calculation_container_layout_content);

        btnSelectContainer=v.findViewById(R.id.btn_select_container);
        btnSelectContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_container_list dialog=new dialog_container_list(getActivity(),
                        new DialogResultEventListener() {
                    @Override
                    public void OkResultEvent(int id) {
                        LoadContent();
                    }
                });
                dialog.show(getActivity().getSupportFragmentManager(),"CONTAINER_LIST_DIALOG");
            }
        });

        return v;
    }
    private void LoadContent(){
        OrderInContainer container=((CalculationActivity)getActivity()).calculate_order.container;
        if(container!=null) {
            ll_message.setVisibility(View.GONE);
            ll_content.setVisibility(View.VISIBLE);
            container_name.setTextColor(colors.get(Helper.tryParse(container.color)));
            container_name.setText(container.definition);
            String measurement=getResources().getString(R.string.calculation_container_definition_short_length)+":"+Helper.toString(container.length)+" "+
                    getResources().getString(R.string.calculation_container_definition_short_width)+":"+Helper.toString(container.width)+" "+
                    getResources().getString(R.string.calculation_container_definition_short_height)+":"+Helper.toString(container.height);
            container_measurement.setText(measurement);
            String tolerance=getResources().getString(R.string.calculation_container_definition_short_length)+":"+Helper.toString(container.tolerance_length)+" "+
                    getResources().getString(R.string.calculation_container_definition_short_width)+":"+Helper.toString(container.tolerance_width)+" "+
                    getResources().getString(R.string.calculation_container_definition_short_height)+":"+Helper.toString(container.tolerance_height);
            container_tolerance.setText(tolerance);
        }
        else{
            ll_message.setVisibility(View.VISIBLE);
            ll_content.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean Validate() {
        return true;


    }
}
