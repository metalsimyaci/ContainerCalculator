package com.hasanural.containercalculator.DataAccess.Repositories;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.hasanural.containercalculator.DataAccess.DataContract;
import com.hasanural.containercalculator.DataAccess.Entity.Product;
import com.hasanural.containercalculator.R;

import java.util.ArrayList;

public class Product_Repository {
    private ContentResolver contentResolver;
    private Context context;
    private String[] projections={
            DataContract.Product_Entry._ID,
            DataContract.Product_Entry.COLUMN_DEFINITION,
            DataContract.Product_Entry.COLUMN_COLOR,
            DataContract.Product_Entry.COLUMN_LENGTH,
            DataContract.Product_Entry.COLUMN_WIDTH,
            DataContract.Product_Entry.COLUMN_HEIGHT,
            DataContract.Product_Entry.COLUMN_WEIGHT
    };
    public Product_Repository(Context context){
        this.context=context;
        contentResolver=context.getContentResolver();
    }
    public ArrayList<Product> Get_All(){
        ArrayList<Product> products=new ArrayList<>();
        String orderBy=DataContract.Product_Entry._ID+" DESC";
        Cursor cr=contentResolver.query(DataContract.Product_Entry.CONTENT_URI,projections,null,null,orderBy,null);
        if(cr!=null)
            while (cr.moveToNext())
            {
                Product product=new Product(
                        cr.getInt(cr.getColumnIndex(DataContract.Product_Entry._ID)),
                        cr.getString(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_DEFINITION)),
                        cr.getInt(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_LENGTH)),
                        cr.getInt(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_WIDTH)),
                        cr.getInt(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_HEIGHT)),
                        cr.getInt(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_WEIGHT)),
                        cr.getString(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_COLOR)));
                products.add(product);
            }
        return products;
    }
    public Product Get_By_Id(int id){
        Product product=null;
        String selection=DataContract.Product_Entry._ID+"=?";
        String[] selectionArgs={Integer.toString(id)};
        Cursor cr=contentResolver.query(DataContract.Product_Entry.CONTENT_URI,projections,selection,selectionArgs,null,null);
        if(cr!=null)
            while (cr.moveToNext())
            {
                product=new Product(
                        cr.getInt(cr.getColumnIndex(DataContract.Product_Entry._ID)),
                        cr.getString(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_DEFINITION)),
                        cr.getInt(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_LENGTH)),
                        cr.getInt(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_WIDTH)),
                        cr.getInt(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_HEIGHT)),
                        cr.getInt(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_WEIGHT)),
                        cr.getString(cr.getColumnIndex(DataContract.Product_Entry.COLUMN_COLOR)));
            }
        return product;
    }
    public Boolean Any_By_Definition(String definition){

        String selection=""+DataContract.Product_Entry.COLUMN_DEFINITION+"=?";
        String[] selectionArgs={definition};
        Cursor cr=contentResolver.query(DataContract.Product_Entry.CONTENT_URI,projections,selection,
                selectionArgs,null,null);
        if(cr!=null) {
            if (cr.getCount() > 0)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    public boolean Add(Product prod)
    {
        try{
            if(!Any_By_Definition(prod.definition)) {
                ContentValues values = new ContentValues();
                values.put(DataContract.Product_Entry.COLUMN_DEFINITION, prod.definition);
                values.put(DataContract.Product_Entry.COLUMN_LENGTH, prod.length);
                values.put(DataContract.Product_Entry.COLUMN_WIDTH, prod.width);
                values.put(DataContract.Product_Entry.COLUMN_HEIGHT, prod.height);
                values.put(DataContract.Product_Entry.COLUMN_WEIGHT, prod.weight);
                values.put(DataContract.Product_Entry.COLUMN_COLOR, prod.color);

                Uri result = contentResolver.insert(DataContract.Product_Entry.CONTENT_URI, values);

                return true;
            }
            else{
                String message=context.getResources().getString(R.string.errormessage_not_exists_db);
                Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                return false;
            }

        }
        catch (Exception e)
        {
            Log.e("Product_Repository",e.getMessage());
            Toast.makeText(context,context.getResources().getString(R.string.errormessage_unexpected_error),Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public boolean Update(Product prod)
    {
        try{
            Product temp=null;
            if(prod.id>0)
                temp=Get_By_Id(prod.id);
            if(temp!=null)
            {
                ContentValues values = new ContentValues();
                values.put(DataContract.Product_Entry._ID,prod.id);
                values.put(DataContract.Product_Entry.COLUMN_DEFINITION, prod.definition);
                values.put(DataContract.Product_Entry.COLUMN_LENGTH, prod.length);
                values.put(DataContract.Product_Entry.COLUMN_WIDTH, prod.width);
                values.put(DataContract.Product_Entry.COLUMN_HEIGHT, prod.height);
                values.put(DataContract.Product_Entry.COLUMN_WEIGHT, prod.weight);
                values.put(DataContract.Product_Entry.COLUMN_COLOR, prod.color);
                String selection=""+DataContract.Product_Entry._ID+"=?";
                String[] selectionArgs={Integer.toString(temp.id)};
                int result = contentResolver.update(DataContract.Product_Entry.CONTENT_URI, values,selection,selectionArgs);

                return result>0;
            }
            else{
                String message=context.getResources().getString(R.string.errormessage_not_exists_db);
                Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                return false;
            }

        }
        catch (Exception e)
        {
            Log.e("Product_Repository",e.getMessage());
            Toast.makeText(context,context.getResources().getString(R.string.errormessage_unexpected_error),Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public boolean Delete(int productId)
    {
        try{
            Product temp=null;
            if(productId>0)
                temp=Get_By_Id(productId);
            if(temp!=null) {
                String selection=""+DataContract.Product_Entry._ID+"=?";
                String[] selectionArgs={Integer.toString(temp.id)};
                int result = contentResolver.delete(DataContract.Product_Entry.CONTENT_URI, selection,selectionArgs);

                return result>0;
            }
            else{
                String message=context.getResources().getString(R.string.errormessage_not_exists_db);
                Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                return false;
            }

        }
        catch (Exception e)
        {
            Log.e("Product_Repository",e.getMessage());
            Toast.makeText(context,context.getResources().getString(R.string.errormessage_unexpected_error),Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
