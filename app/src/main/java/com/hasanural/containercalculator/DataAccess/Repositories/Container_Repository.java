package com.hasanural.containercalculator.DataAccess.Repositories;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.hasanural.containercalculator.DataAccess.DataContract;
import com.hasanural.containercalculator.DataAccess.Entity.Container;
import com.hasanural.containercalculator.R;

import java.util.ArrayList;

public  class Container_Repository {
    private ContentResolver contentResolver;
    private Context context;
    private String[] projections={
            DataContract.Container_Entry._ID,
            DataContract.Container_Entry.COLUMN_DEFINITION,
            DataContract.Container_Entry.COLUMN_COLOR,
            DataContract.Container_Entry.COLUMN_LENGTH,
            DataContract.Container_Entry.COLUMN_WIDTH,
            DataContract.Container_Entry.COLUMN_HEIGHT,
            DataContract.Container_Entry.COLUMN_TOLERANCE_LENGTH,
            DataContract.Container_Entry.COLUMN_TOLERANCE_WIDTH,
            DataContract.Container_Entry.COLUMN_TOLERANCE_HEIGHT,
            DataContract.Container_Entry.COLUMN_WEIGHT,
            DataContract.Container_Entry.COLUMN_WEIGHT_EMPTY,
            DataContract.Container_Entry.COLUMN_VOLUME
    };


    public Container_Repository(Context context)
    {
        this.context=context;
        contentResolver=context.getContentResolver();
    }
    public ArrayList<Container> Get_All_Containers(){

        ArrayList<Container> containers=new ArrayList<>();
        String sortOrder=DataContract.Container_Entry._ID+" DESC ";

        Cursor cr=contentResolver.query(DataContract.Container_Entry.CONTENT_URI,projections,null,
                null,sortOrder,null);
        if(cr!=null)
            while (cr.moveToNext())
            {
                Container container=new Container(
                        cr.getInt(cr.getColumnIndex(DataContract.Container_Entry._ID)),
                        cr.getString(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_DEFINITION)),
                        cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_LENGTH)),
                        cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_WIDTH)),
                        cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_HEIGHT)),
                        cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_TOLERANCE_LENGTH)),
                        cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_TOLERANCE_WIDTH)),
                        cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_TOLERANCE_HEIGHT)),
                        cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_WEIGHT)),
                        cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_WEIGHT_EMPTY)),
                        cr.getDouble(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_VOLUME)),
                        cr.getString(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_COLOR)));
                containers.add(container);
            }
        return containers;
    }
    public Container Get_By_Id_Container(int id){

        String selection=""+DataContract.Container_Entry._ID+"=?";
        String[] selectionArgs={Integer.toString(id)};
        Cursor cr=contentResolver.query(DataContract.Container_Entry.CONTENT_URI,projections,selection,
                selectionArgs,null,null);
        Container container;
        if(cr!=null)
            cr.moveToNext();
            container=new Container(
                    cr.getInt(cr.getColumnIndex(DataContract.Container_Entry._ID)),
                    cr.getString(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_DEFINITION)),
                    cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_LENGTH)),
                    cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_WIDTH)),
                    cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_HEIGHT)),
                    cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_TOLERANCE_LENGTH)),
                    cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_TOLERANCE_WIDTH)),
                    cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_TOLERANCE_HEIGHT)),
                    cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_WEIGHT)),
                    cr.getInt(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_WEIGHT_EMPTY)),
                    cr.getDouble(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_VOLUME)),
                    cr.getString(cr.getColumnIndex(DataContract.Container_Entry.COLUMN_COLOR)));
        return container;
    }
    public Boolean Any_By_Definition(String definition){

        String selection=""+DataContract.Container_Entry.COLUMN_DEFINITION+"=?";
        String[] selectionArgs={definition};
        Cursor cr=contentResolver.query(DataContract.Container_Entry.CONTENT_URI,projections,selection,
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
    public boolean Add_or_Update(Container con)
    {
        try{
            Container temp=null;
            if(con.id>0)
                temp=Get_By_Id_Container(con.id);

            ContentValues values=new ContentValues();
            values.put(DataContract.Container_Entry.COLUMN_DEFINITION,con.definition);
            values.put(DataContract.Container_Entry.COLUMN_LENGTH,con.length);
            values.put(DataContract.Container_Entry.COLUMN_WIDTH,con.width);
            values.put(DataContract.Container_Entry.COLUMN_HEIGHT,con.height);
            values.put(DataContract.Container_Entry.COLUMN_TOLERANCE_LENGTH,con.tolerance_length);
            values.put(DataContract.Container_Entry.COLUMN_TOLERANCE_WIDTH,con.tolerance_width);
            values.put(DataContract.Container_Entry.COLUMN_TOLERANCE_HEIGHT,con.tolerance_height);
            values.put(DataContract.Container_Entry.COLUMN_WEIGHT,con.weight);
            values.put(DataContract.Container_Entry.COLUMN_WEIGHT_EMPTY,con.weight_Empty);
            values.put(DataContract.Container_Entry.COLUMN_VOLUME,con.volume);
            values.put(DataContract.Container_Entry.COLUMN_COLOR,con.color);

            if(temp!=null)
            {

                values.put(DataContract.Container_Entry._ID,temp.id);
                String selection=""+DataContract.Container_Entry._ID+"=?";
                String[] selectionArgs={Integer.toString(temp.id)};
                int result=contentResolver.update(DataContract.Container_Entry.CONTENT_URI,values,selection,selectionArgs);
                return result>0;
            }
            else{
                Uri result=contentResolver.insert(DataContract.Container_Entry.CONTENT_URI,values);

                return true;
            }
        }
        catch (Exception e)
        {
            Log.e("Container_Repository",e.getMessage());
            Toast.makeText(context,context.getResources().getString(R.string.errormessage_unexpected_error),Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public boolean Add(Container con)
    {
        try{
            Container temp=null;
            if(!Any_By_Definition(con.definition)) {
                ContentValues values = new ContentValues();
                values.put(DataContract.Container_Entry.COLUMN_DEFINITION, con.definition);
                values.put(DataContract.Container_Entry.COLUMN_LENGTH, con.length);
                values.put(DataContract.Container_Entry.COLUMN_WIDTH, con.width);
                values.put(DataContract.Container_Entry.COLUMN_HEIGHT, con.height);
                values.put(DataContract.Container_Entry.COLUMN_TOLERANCE_LENGTH, con.tolerance_length);
                values.put(DataContract.Container_Entry.COLUMN_TOLERANCE_WIDTH, con.tolerance_width);
                values.put(DataContract.Container_Entry.COLUMN_TOLERANCE_HEIGHT, con.tolerance_height);
                values.put(DataContract.Container_Entry.COLUMN_WEIGHT, con.weight);
                values.put(DataContract.Container_Entry.COLUMN_WEIGHT_EMPTY, con.weight_Empty);
                values.put(DataContract.Container_Entry.COLUMN_VOLUME, con.volume);
                values.put(DataContract.Container_Entry.COLUMN_COLOR, con.color);

                Uri result = contentResolver.insert(DataContract.Container_Entry.CONTENT_URI, values);

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
            Log.e("Container_Repository",e.getMessage());
            Toast.makeText(context,context.getResources().getString(R.string.errormessage_unexpected_error),Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public boolean Update(Container con)
    {
        try{
            Container temp=null;
            if(con.id>0)
                temp=Get_By_Id_Container(con.id);



            if(temp!=null)
            {
                ContentValues values=new ContentValues();
                values.put(DataContract.Container_Entry._ID,con.id);
                values.put(DataContract.Container_Entry.COLUMN_DEFINITION,con.definition);
                values.put(DataContract.Container_Entry.COLUMN_LENGTH,con.length);
                values.put(DataContract.Container_Entry.COLUMN_WIDTH,con.width);
                values.put(DataContract.Container_Entry.COLUMN_HEIGHT,con.height);
                values.put(DataContract.Container_Entry.COLUMN_TOLERANCE_LENGTH,con.tolerance_length);
                values.put(DataContract.Container_Entry.COLUMN_TOLERANCE_WIDTH,con.tolerance_width);
                values.put(DataContract.Container_Entry.COLUMN_TOLERANCE_HEIGHT,con.tolerance_height);
                values.put(DataContract.Container_Entry.COLUMN_WEIGHT,con.weight);
                values.put(DataContract.Container_Entry.COLUMN_WEIGHT_EMPTY,con.weight_Empty);
                values.put(DataContract.Container_Entry.COLUMN_VOLUME,con.volume);
                values.put(DataContract.Container_Entry.COLUMN_COLOR,con.color);

                String selection=""+DataContract.Container_Entry._ID+"=?";
                String[] selectionArgs={Integer.toString(temp.id)};
                int result=contentResolver.update(DataContract.Container_Entry.CONTENT_URI,values,selection,selectionArgs);
                return result>0;
            }
            else{
                Toast.makeText(context,context.getResources().getString(R.string.errormessage_not_found_db),Toast.LENGTH_LONG).show();

                return false;
            }
        }
        catch (Exception e)
        {
            Log.e("Container_Repository",e.getMessage());
            Toast.makeText(context,context.getResources().getString(R.string.errormessage_unexpected_error),Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public boolean Delete(int deletedId)
    {
        try{
            Container temp=null;
            if(deletedId>0)
                temp=Get_By_Id_Container(deletedId);

            if(temp!=null)
            {
                String selection=""+DataContract.Container_Entry._ID+"=?";
                String[] selectionArgs={Integer.toString(temp.id)};
                int result=contentResolver.delete(DataContract.Container_Entry.CONTENT_URI,selection,selectionArgs);
                return result>0;
            }
            else{
                Toast.makeText(context,context.getResources().getString(R.string.errormessage_not_found_db),Toast.LENGTH_LONG).show();

                return false;
            }
        }
        catch (Exception e)
        {
            Log.e("Container_Repository",e.getMessage());
            Toast.makeText(context,context.getResources().getString(R.string.errormessage_unexpected_error),Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
