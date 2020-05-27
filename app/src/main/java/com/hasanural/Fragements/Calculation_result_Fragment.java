package com.hasanural.Fragements;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hasanural.containercalculator.CalculationActivity;
import com.hasanural.containercalculator.DataAccess.Entity.OrderResult;
import com.hasanural.containercalculator.Events.FragementFillEvent;
import com.hasanural.containercalculator.R;
import com.hasanural.containercalculator.Utilities.Helper;

public class Calculation_result_Fragment extends android.support.v4.app.Fragment implements FragementFillEvent {


    public Calculation_result_Fragment() {
        // Required empty public constructor
    }

    TextView tw_totalContainerCount,tw_percentContainerVolumePacked,tw_percentItemVolumePacked,
    tw_totalPackages,tw_packedItemsCount,tw_packedItemsVolume,tw_unpackedItemsCount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CalculationActivity)getActivity()).setActivityFragmentFillEvent(Calculation_result_Fragment.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_calculation_result, container, false);

        tw_totalContainerCount=v.findViewById(R.id.cal_res_totalContainerCount);
        tw_percentContainerVolumePacked=v.findViewById(R.id.cal_res_percentContainerVolumePacked);
        tw_percentItemVolumePacked=v.findViewById(R.id.cal_res_percentItemVolumePacked);
        tw_totalPackages=v.findViewById(R.id.cal_res_totalPackages);
        tw_packedItemsCount=v.findViewById(R.id.cal_res_packedItemsCount);
        tw_packedItemsVolume=v.findViewById(R.id.cal_res_packedItemsVolume);
        tw_unpackedItemsCount=v.findViewById(R.id.cal_res_unPackedItemsCount);

        return v;
    }

    @Override
    public void Fill(Object data) {
        OrderResult or=((CalculationActivity)getActivity()).calculate_order.result;
        tw_totalContainerCount.setText(Helper.toString(or.totalContainerCount));
        tw_percentContainerVolumePacked.setText(Helper.toString(or.percentContainerVolumePacked));
        tw_percentItemVolumePacked.setText(Helper.toString(or.percentItemValumePacked));
        tw_totalPackages.setText(Helper.toString(or.totalPacked));
        tw_packedItemsCount.setText(Helper.toString(or.packedItemsCount));
        tw_packedItemsVolume.setText(Helper.toString(or.packedItemsVolume));
        tw_unpackedItemsCount.setText(Helper.toString(or.packedItemsCount));
    }
}
