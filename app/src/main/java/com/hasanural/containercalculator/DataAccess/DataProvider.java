package com.hasanural.containercalculator.DataAccess;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class DataProvider extends ContentProvider {

    private SQLiteDatabase db;
    private DataHelper dbHelper;
    private static  final UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
    private static final int URICODE_PRODUCT=1;
    private static final int URICODE_CONTAINER=2;
    private static final int URICODE_ORDER=3;
    private static final int URICODE_ORDER_IN_CONTAINER=4;
    private static final int URICODE_ORDER_IN_PRODUCT=5;
    private static final int URICODE_ORDER_RESULT=6;
    private static final int URICODE_SETTING=7;

    static {
        matcher.addURI(DataContract.CONTENT_AUTHORITY,DataContract.PATH_PRODUCT,URICODE_PRODUCT);
        matcher.addURI(DataContract.CONTENT_AUTHORITY,DataContract.PATH_CONTAINER, URICODE_CONTAINER);
        matcher.addURI(DataContract.CONTENT_AUTHORITY,DataContract.PATH_ORDER,URICODE_ORDER);
        matcher.addURI(DataContract.CONTENT_AUTHORITY,DataContract.PATH_ORDER_IN_CONTAINER,URICODE_ORDER_IN_CONTAINER);
        matcher.addURI(DataContract.CONTENT_AUTHORITY,DataContract.PATH_ORDER_IN_PRODUCT,URICODE_ORDER_IN_PRODUCT);
        matcher.addURI(DataContract.CONTENT_AUTHORITY,DataContract.PATH_ORDER_RESULT,URICODE_ORDER_RESULT);
        matcher.addURI(DataContract.CONTENT_AUTHORITY,DataContract.PATH_SETTING,URICODE_SETTING);
    }

    @Override
    public boolean onCreate() {
        dbHelper=new DataHelper(getContext());
        db= dbHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;
        SQLiteQueryBuilder builder;
        String queery="";
        switch (matcher.match(uri))
        {
            case URICODE_PRODUCT:
                cursor= db.query(DataContract.Product_Entry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case URICODE_CONTAINER:
              /*  builder = new SQLiteQueryBuilder();
                builder.setTables(queery);
                cursor = builder.query(db,projection,selection,selectionArgs,sortOrder,null,null);
                */
                cursor= db.query(DataContract.Container_Entry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case URICODE_ORDER:
                cursor= db.query(DataContract.Order_Entry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case URICODE_ORDER_IN_CONTAINER:
                cursor= db.query(DataContract.Order_In_Container_Entry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case URICODE_ORDER_IN_PRODUCT:
                cursor= db.query(DataContract.Order_In_Product_Entry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case URICODE_ORDER_RESULT:
                cursor= db.query(DataContract.Order_Result_Entry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case URICODE_SETTING:
                cursor= db.query(DataContract.Setting_Entry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw  new IllegalArgumentException("Unknown Request Type");

        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        switch (matcher.match(uri))
        {
            case URICODE_PRODUCT:
               return AddRecord(uri,values,DataContract.Product_Entry.TABLE_NAME);

            case URICODE_CONTAINER:
                return AddRecord(uri,values,DataContract.Container_Entry.TABLE_NAME);

            case URICODE_ORDER:
                return AddRecord(uri,values,DataContract.Order_Entry.TABLE_NAME);

            case URICODE_ORDER_IN_CONTAINER:
                return AddRecord(uri,values,DataContract.Order_In_Container_Entry.TABLE_NAME);

            case URICODE_ORDER_IN_PRODUCT:
                return AddRecord(uri,values,DataContract.Order_In_Product_Entry.TABLE_NAME);

            case URICODE_ORDER_RESULT:
                return AddRecord(uri,values,DataContract.Order_Result_Entry.TABLE_NAME);
            case URICODE_SETTING:
                return AddRecord(uri,values,DataContract.Setting_Entry.TABLE_NAME);

            default:
                throw  new IllegalArgumentException("Unknown Request Type");
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (matcher.match(uri))
        {
            case URICODE_PRODUCT:
                return RemoveRecord(uri,selection,selectionArgs,DataContract.Product_Entry.TABLE_NAME);

            case URICODE_CONTAINER:
                return RemoveRecord(uri,selection,selectionArgs,DataContract.Container_Entry.TABLE_NAME);

            case URICODE_ORDER:
                return RemoveRecord(uri,selection,selectionArgs,DataContract.Order_Entry.TABLE_NAME);

            case URICODE_ORDER_IN_CONTAINER:
                return RemoveRecord(uri,selection,selectionArgs,DataContract.Order_In_Container_Entry.TABLE_NAME);

            case URICODE_ORDER_IN_PRODUCT:
                return RemoveRecord(uri,selection,selectionArgs,DataContract.Order_In_Product_Entry.TABLE_NAME);

            case URICODE_ORDER_RESULT:
                return RemoveRecord(uri,selection,selectionArgs,DataContract.Order_Result_Entry.TABLE_NAME);
            case URICODE_SETTING:
                return RemoveRecord(uri,selection,selectionArgs,DataContract.Setting_Entry.TABLE_NAME);

            default:
                throw  new IllegalArgumentException("Unknown Request Type");

        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        switch (matcher.match(uri))
        {
            case URICODE_PRODUCT:
                return UpdateRecord(uri,values,selection,selectionArgs,DataContract.Product_Entry.TABLE_NAME);

            case URICODE_CONTAINER:
                return UpdateRecord(uri,values,selection,selectionArgs,DataContract.Container_Entry.TABLE_NAME);

            case URICODE_ORDER:
                return UpdateRecord(uri,values,selection,selectionArgs,DataContract.Order_Entry.TABLE_NAME);

            case URICODE_ORDER_IN_CONTAINER:
                return UpdateRecord(uri,values,selection,selectionArgs,DataContract.Order_In_Container_Entry.TABLE_NAME);

            case URICODE_ORDER_IN_PRODUCT:
                return UpdateRecord(uri,values,selection,selectionArgs,DataContract.Order_In_Product_Entry.TABLE_NAME);

            case URICODE_ORDER_RESULT:
                return UpdateRecord(uri,values,selection,selectionArgs,DataContract.Order_Result_Entry.TABLE_NAME);
            case URICODE_SETTING:
                return UpdateRecord(uri,values,selection,selectionArgs,DataContract.Setting_Entry.TABLE_NAME);
            default:
                    throw  new IllegalArgumentException("Unknown Request Type");

        }
    }

    private Uri AddRecord(Uri uri, ContentValues values, String tableName){
        long id=db.insert(tableName,null,values);
        if(id<=0) {
            Log.e("DataProvider", "Insert Error!" + uri);
            return null;
        }
        return ContentUris.withAppendedId(uri,id);
    }
    private int UpdateRecord( Uri uri, ContentValues values, String selection, String[] selectionArgs,String tableName){
        int id=db.update(tableName,values,selection,selectionArgs);
        if(id<=0){
            Log.e("DataProvider", "Update Error!" + uri);
            return -1;
        }
        return id;
    }
    private int RemoveRecord( Uri uri, String selection, String[] selectionArgs,String tableName){
        int id=db.delete(tableName,selection,selectionArgs);
        if(id<=0){
            Log.e("DataProvider", "Delete Error!" + uri);
            return -1;
        }
        return id;
    }
}
