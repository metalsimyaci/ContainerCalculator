package com.hasanural.containercalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hasanural.containercalculator.Adapters.RA_Container;
import com.hasanural.containercalculator.DataAccess.Entity.Container;
import com.hasanural.containercalculator.DataAccess.Model.SingletonOrderModel;
import com.hasanural.containercalculator.DataAccess.Repositories.Container_Repository;
import com.hasanural.containercalculator.Dialogs.dialog_container_add;
import com.hasanural.containercalculator.Dialogs.dialog_container_edit;
import com.hasanural.containercalculator.Events.DialogResultEventListener;
import com.hasanural.containercalculator.Events.RV_ItemClickListener;
import com.hasanural.containercalculator.Utilities.LanguageHelper;

import java.util.ArrayList;

public class ContainerActivity extends BaseActivity {

    RecyclerView rv;
    RA_Container rvAdapter;
    Container_Repository repository;
    ArrayList<Container> containers;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageHelper.LoadLocale(getBaseContext());
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.title_activity_container));
        this.context=this;
        setContentView(R.layout.activity_container);
        rv=findViewById(R.id.container_rv);
        repository = new Container_Repository(this);
        LinearLayoutManager ll_manager = new LinearLayoutManager(this);
        rv.setLayoutManager(ll_manager);

        loadContent();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingletonOrderModel.getInstance().containerModel=null;
                dialog_container_add dialog = new dialog_container_add(context, new DialogResultEventListener() {
                    @Override
                    public void OkResultEvent(int id) {
                        if(id>0)
                        {
                            loadContent();
                        }
                    }
                });
                dialog.show(getSupportFragmentManager(), "CONTAINER_DIALOG_ADD");
            }
        });

    }
    public void loadContent(){

        if(containers!=null)
            containers.clear();
        containers = repository.Get_All_Containers();
        rvAdapter = new RA_Container(this, containers);
        rv.setAdapter(rvAdapter);
        rvAdapter.SetClickListener(new RV_ItemClickListener() {
            @Override
            public void onClick(View view, final int position) {
                TextView tw_id= view.findViewById(R.id.cai_tw_id);
                int id=Integer.parseInt(tw_id.getText().toString());
                Container c= repository.Get_By_Id_Container(id);

                if(c!=null)
                    SingletonOrderModel.getInstance().containerModel = new Container(c.id, c.definition, c.length, c.width, c.height,
                            c.tolerance_length,c.tolerance_width,c.tolerance_height, c.weight,c.weight_Empty,c.volume, c.color);
                dialog_container_edit dialog = new dialog_container_edit(context, new DialogResultEventListener() {
                    @Override
                    public void OkResultEvent(int id) {
                        if(id>0)
                        {
                            loadContent();
                        }
                    }
                });
                dialog.show(getSupportFragmentManager(), "CONTAINER_DIALOG_ADD");
            }
        });
        rvAdapter.notifyDataSetChanged();

    }
}
