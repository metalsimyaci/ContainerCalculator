package com.hasanural.containercalculator.DataAccess.Repositories;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.hasanural.containercalculator.DataAccess.DataContract;
import com.hasanural.containercalculator.DataAccess.Entity.Settings;
import com.hasanural.containercalculator.R;

import java.util.ArrayList;

public class Setting_Repository {
    private ContentResolver contentResolver;
    private Context context;
    private String[] projections={
            DataContract.Setting_Entry._ID,
            DataContract.Setting_Entry.COLUMN_KEY,
            DataContract.Setting_Entry.COLUMN_VALUE
    };
    public Setting_Repository(Context context)
    {
        this.context=context;
        contentResolver=context.getContentResolver();
    }
    public ArrayList<Settings> Get_All(){

        ArrayList<Settings> settings=new ArrayList<>();
        String sortOrder=DataContract.Setting_Entry._ID+" DESC ";

        Cursor cr=contentResolver.query(DataContract.Setting_Entry.CONTENT_URI,projections,null,
                null,sortOrder,null);
        if(cr!=null) {
            while (cr.moveToNext()) {
                Settings setting = new Settings(
                        cr.getInt(cr.getColumnIndex(DataContract.Setting_Entry._ID)),
                        cr.getString(cr.getColumnIndex(DataContract.Setting_Entry.COLUMN_KEY)),
                        cr.getString(cr.getColumnIndex(DataContract.Setting_Entry.COLUMN_VALUE)));
                settings.add(setting);
            }
            if (cr.isClosed())
                cr.close();
        }
        return settings;
    }
    public Settings Get_By_Id(int id){
        Settings setting=null;
        String selection=""+DataContract.Setting_Entry._ID+"=?";
        String[] selectionArgs={Integer.toString(id)};
        Cursor cr=contentResolver.query(DataContract.Setting_Entry.CONTENT_URI,projections,selection,
                selectionArgs,null,null);
        if(cr!=null) {
            while (cr.moveToNext()) {
                setting = new Settings(
                        cr.getInt(cr.getColumnIndex(DataContract.Setting_Entry._ID)),
                        cr.getString(cr.getColumnIndex(DataContract.Setting_Entry.COLUMN_KEY)),
                        cr.getString(cr.getColumnIndex(DataContract.Setting_Entry.COLUMN_VALUE)));

            }
            if (cr.isClosed())
                cr.close();
        }
        return setting;
    }
    public Settings Get_By_Key(String key){
        Settings setting=null;
        String selection=""+DataContract.Setting_Entry.COLUMN_KEY+"=?";
        String[] selectionArgs={key};
        Cursor cr=contentResolver.query(DataContract.Setting_Entry.CONTENT_URI,projections,selection,
                selectionArgs,null,null);
        if(cr!=null) {
            while (cr.moveToNext()) {
                setting = new Settings(
                        cr.getInt(cr.getColumnIndex(DataContract.Setting_Entry._ID)),
                        cr.getString(cr.getColumnIndex(DataContract.Setting_Entry.COLUMN_KEY)),
                        cr.getString(cr.getColumnIndex(DataContract.Setting_Entry.COLUMN_VALUE)));

            }
            if (cr.isClosed())
                cr.close();
        }
        return setting;
    }
    public boolean Update(Settings entity)
    {
        try{
            Settings temp=null;
            temp=Get_By_Key(entity.key);
            if(temp!=null)
            {
                ContentValues values=new ContentValues();
                values.put(DataContract.Setting_Entry._ID,temp.id);
                values.put(DataContract.Setting_Entry.COLUMN_KEY,temp.key);
                values.put(DataContract.Setting_Entry.COLUMN_VALUE,entity.value);

                String selection=""+DataContract.Setting_Entry._ID+"=?";
                String[] selectionArgs={Integer.toString(temp.id)};
                int result=contentResolver.update(DataContract.Setting_Entry.CONTENT_URI,values,selection,selectionArgs);
                return result>0;
            }
            else{
                Toast.makeText(context,context.getResources().getString(R.string.errormessage_not_found_db),Toast.LENGTH_LONG).show();

                return false;
            }
        }
        catch (Exception e)
        {
            Log.e("Setting_Repository",e.getMessage());
            Toast.makeText(context,context.getResources().getString(R.string.errormessage_unexpected_error),Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
