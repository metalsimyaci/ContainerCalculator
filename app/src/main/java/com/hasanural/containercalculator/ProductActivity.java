package com.hasanural.containercalculator;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hasanural.containercalculator.Adapters.RA_Container;
import com.hasanural.containercalculator.Adapters.RA_Product;
import com.hasanural.containercalculator.DataAccess.Entity.Container;
import com.hasanural.containercalculator.DataAccess.Entity.Product;
import com.hasanural.containercalculator.DataAccess.Model.SingletonOrderModel;
import com.hasanural.containercalculator.DataAccess.Repositories.Container_Repository;
import com.hasanural.containercalculator.DataAccess.Repositories.Product_Repository;
import com.hasanural.containercalculator.Dialogs.dialog_container_add;
import com.hasanural.containercalculator.Dialogs.dialog_container_edit;
import com.hasanural.containercalculator.Dialogs.dialog_product_add;
import com.hasanural.containercalculator.Dialogs.dialog_product_edit;
import com.hasanural.containercalculator.Events.DialogResultEventListener;
import com.hasanural.containercalculator.Events.RV_ItemClickListener;
import com.hasanural.containercalculator.Utilities.LanguageHelper;

import java.util.ArrayList;

public class ProductActivity extends BaseActivity {

    RecyclerView rv;
    RA_Product rvAdapter;
    Product_Repository repository;
    ArrayList<Product> products;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageHelper.LoadLocale(getBaseContext());
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.title_activity_product));
        setContentView(R.layout.activity_product);
        this.context=this;
               rv=findViewById(R.id.product_rv);
        repository = new Product_Repository(this);
        LinearLayoutManager ll_manager = new LinearLayoutManager(this);
        rv.setLayoutManager(ll_manager);

        loadContent();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingletonOrderModel.getInstance().productModel=null;
                dialog_product_add dialog = new dialog_product_add(context, new DialogResultEventListener() {
                    @Override
                    public void OkResultEvent(int id) {
                        if(id>0)
                        {
                            loadContent();
                        }
                    }
                });
                dialog.show(getSupportFragmentManager(), "PRODUCT_DIALOG_ADD");
            }
        });
    }
    public void loadContent(){

        if(products!=null)
            products.clear();
        products = repository.Get_All();
        rvAdapter = new RA_Product(this, products);
        rv.setAdapter(rvAdapter);
        rvAdapter.SetClickListener(new RV_ItemClickListener() {
            @Override
            public void onClick(View view, final int position) {
                TextView tw_id= view.findViewById(R.id.pai_tw_id);
                int id=Integer.parseInt(tw_id.getText().toString());
                Product p= repository.Get_By_Id(id);

                if(p!=null)
                    SingletonOrderModel.getInstance().productModel = new Product(p.id, p.definition, p.length, p.width, p.height,
                             p.weight, p.color);
                dialog_product_edit dialog = new dialog_product_edit(context, new DialogResultEventListener() {
                    @Override
                    public void OkResultEvent(int id) {
                        if(id>0)
                        {
                            loadContent();
                        }
                    }
                });
                dialog.show(getSupportFragmentManager(), "PRODUCT_DIALOG_EDIT");
            }
        });
        rvAdapter.notifyDataSetChanged();

    }
}
