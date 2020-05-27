package com.hasanural.containercalculator.DataAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="ContainerCalculator.db";
    private static final int DATABASE_VERSION=2;

    //Region Database Queries
    private static  final String DATABASE_CREATE_PRODUCT=
            " CREATE TABLE IF NOT EXISTS `"+DataContract.Product_Entry.TABLE_NAME+"` ( " +
            " `"+DataContract.Product_Entry._ID+"` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            " `"+DataContract.Product_Entry.COLUMN_DEFINITION+"` TEXT NOT NULL UNIQUE, " +
            " `"+DataContract.Product_Entry.COLUMN_LENGTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Product_Entry.COLUMN_WIDTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Product_Entry.COLUMN_HEIGHT+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Product_Entry.COLUMN_WEIGHT+"` INTEGER DEFAULT 0, " +
            " `"+DataContract.Product_Entry.COLUMN_COLOR+"` TEXT NOT NULL DEFAULT '0' " +
            "); ";
    private static final String DATABASE_CREATE_CONTAINER=
            "CREATE TABLE IF NOT EXISTS `"+DataContract.Container_Entry.TABLE_NAME+"` ( " +
            " `"+DataContract.Container_Entry._ID+"` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            " `"+DataContract.Container_Entry.COLUMN_DEFINITION+"` TEXT NOT NULL UNIQUE, " +
            " `"+DataContract.Container_Entry.COLUMN_LENGTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Container_Entry.COLUMN_WIDTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Container_Entry.COLUMN_HEIGHT+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Container_Entry.COLUMN_TOLERANCE_LENGTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Container_Entry.COLUMN_TOLERANCE_WIDTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Container_Entry.COLUMN_TOLERANCE_HEIGHT+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Container_Entry.COLUMN_WEIGHT+"` INTEGER DEFAULT 0, " +
            " `"+DataContract.Container_Entry.COLUMN_WEIGHT_EMPTY+"` INTEGER DEFAULT 0, " +
            " `"+DataContract.Container_Entry.COLUMN_VOLUME+"` NUMERIC DEFAULT 0, " +
            " `"+DataContract.Container_Entry.COLUMN_COLOR+"` TEXT NOT NULL DEFAULT '0' " +
            "); ";
    private static final String DATABASE_CREATE_ORDER=
            "CREATE TABLE IF NOT EXISTS `"+DataContract.Order_Entry.TABLE_NAME+"` ( " +
            " `"+DataContract.Order_Entry._ID+"` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            " `"+DataContract.Order_Entry.COLUMN_ORDERNO+"` TEXT NOT NULL, " +
            " `"+DataContract.Order_Entry.COLUMN_DESCRIPTION+"` TEXT " +
            "); ";
    private static final String DATABASE_CREATE_ORDER_IN_CONTAINER=
            "CREATE TABLE IF NOT EXISTS `"+DataContract.Order_In_Container_Entry.TABLE_NAME+"` ( " +
            " `"+DataContract.Order_In_Container_Entry._ID+"` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_ORDER_ID+"` INTEGER NOT NULL, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_DEFINITION+"` TEXT NOT NULL, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_LENGTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_WIDTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_HEIGHT+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_TOLERANCE_LENGTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_TOLERANCE_WIDTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_TOLERANCE_HEIGHT+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_WEIGHT+"` INTEGER DEFAULT 0, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_WEIGHT_EMPTY+"` INTEGER DEFAULT 0, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_VOLUME+"` NUMERIC DEFAULT 0, " +
            " `"+DataContract.Order_In_Container_Entry.COLUMN_COLOR+"` TEXT NOT NULL DEFAULT '0', " +
            " FOREIGN KEY(`"+DataContract.Order_In_Container_Entry.COLUMN_ORDER_ID+"`) REFERENCES `"+DataContract.Order_Entry.TABLE_NAME+"`(`"+DataContract.Order_Entry._ID+"`)"+
            "); ";
    private static  final String DATABASE_CREATE_ORDER_IN_PRODUCT=
            " CREATE TABLE IF NOT EXISTS `"+DataContract.Order_In_Product_Entry.TABLE_NAME+"` ( " +
            " `"+DataContract.Order_In_Product_Entry._ID+"` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            " `"+DataContract.Order_In_Product_Entry.COLUMN_ORDER_ID+"` INTEGER NOT NULL, " +
            " `"+DataContract.Order_In_Product_Entry.COLUMN_DEFINITION+"` TEXT NOT NULL, " +
            " `"+DataContract.Order_In_Product_Entry.COLUMN_LENGTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_In_Product_Entry.COLUMN_WIDTH+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_In_Product_Entry.COLUMN_HEIGHT+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_In_Product_Entry.COLUMN_QUANTITY+"` INTEGER DEFAULT 0, " +
            " `"+DataContract.Order_In_Product_Entry.COLUMN_WEIGHT+"` INTEGER DEFAULT 0, " +
            " `"+DataContract.Order_In_Product_Entry.COLUMN_COLOR+"` TEXT NOT NULL DEFAULT '0', " +
            " FOREIGN KEY(`"+DataContract.Order_In_Product_Entry.COLUMN_ORDER_ID+"`) REFERENCES `"+DataContract.Order_Entry.TABLE_NAME+"`(`"+DataContract.Order_Entry._ID+"`)"+
            "); ";
    private static final String DATABASE_CREATE_ORDER_RESULT=
            " CREATE TABLE IF NOT EXISTS `"+DataContract.Order_Result_Entry.TABLE_NAME+"` ( " +
            " `"+DataContract.Order_Result_Entry._ID+"` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_ORDER_ID+"` INTEGER NOT NULL, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_TOTAL_CONTAINER_COUNT+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_IS_COMPLATE_PACK+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_PACK_TIME_IN_MILLISECOND+"` NUMERIC NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_PERCENT_CONTAINER_VOLUME_PACKED+"` NUMERIC NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_PERCENT_ITEM_VOLUME_PACKED+"` NUMERIC DEFAULT 0, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_TOTAL_PACKAGES+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_PACKED_ITEMS_COUNT+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_PACKED_ITEMS_VOLUME+"` NUMERIC NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_UNPACKED_ITEMS_COUNT+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_IS_ERROR+"` INTEGER NOT NULL DEFAULT 0, " +
            " `"+DataContract.Order_Result_Entry.COLUMN_MESSAGE+"` TEXT, " +
            " FOREIGN KEY(`"+DataContract.Order_Result_Entry.COLUMN_ORDER_ID+"`) REFERENCES `"+DataContract.Order_Entry.TABLE_NAME+"`(`"+DataContract.Order_Entry._ID+"`)"+
            "); ";
    private static  final String DATABASE_CREATE_SETTING=
            " CREATE TABLE IF NOT EXISTS `"+DataContract.Setting_Entry.TABLE_NAME+"` ( " +
                    " `"+DataContract.Setting_Entry._ID+"` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    " `"+DataContract.Setting_Entry.COLUMN_KEY+"` TEXT NOT NULL UNIQUE, " +
                    " `"+DataContract.Setting_Entry.COLUMN_VALUE+"` TEXT" +
                    "); ";

    private static final String DATABASE_INSERT_PRODUCT=
            "INSERT INTO `"+DataContract.Product_Entry.TABLE_NAME+
            "` ("+DataContract.Container_Entry._ID+","+DataContract.Container_Entry.COLUMN_DEFINITION+
            ","+DataContract.Container_Entry.COLUMN_LENGTH+","+DataContract.Container_Entry.COLUMN_WIDTH+
            ","+DataContract.Container_Entry.COLUMN_HEIGHT+","+DataContract.Container_Entry.COLUMN_WEIGHT+
            ","+DataContract.Container_Entry.COLUMN_COLOR+") "+
            " VALUES (1,'Cargo3',2000,600,440,60,'0'), " +
            " (2,'Cargo1',200,400,355,30,'0'), " +
            " (3,'Cargo2',600,400,200,50,'0'); ";
    private static final String DATABASE_INSERT_CONTAINER=
            "INSERT INTO `"+DataContract.Container_Entry.TABLE_NAME+
            "` ("+DataContract.Container_Entry._ID+","+DataContract.Container_Entry.COLUMN_DEFINITION+
            ","+DataContract.Container_Entry.COLUMN_LENGTH+","+DataContract.Container_Entry.COLUMN_WIDTH+
            ","+DataContract.Container_Entry.COLUMN_HEIGHT+","+DataContract.Container_Entry.COLUMN_TOLERANCE_LENGTH+
            ","+DataContract.Container_Entry.COLUMN_TOLERANCE_WIDTH+","+DataContract.Container_Entry.COLUMN_TOLERANCE_HEIGHT+
            ","+DataContract.Container_Entry.COLUMN_WEIGHT+","+DataContract.Container_Entry.COLUMN_WEIGHT_EMPTY+
            ","+DataContract.Container_Entry.COLUMN_VOLUME+","+DataContract.Container_Entry.COLUMN_COLOR+") " +
            "VALUES (1,'20'' DC',5900,2350,2370,0,0,0,21770,2300,33,'0'), " +
            " (2,'40'' DC',11980,2350,2350,0,0,0,26780,3700,66,'0'), " +
            " (3,'40'' HC',11980,2350,2690,0,0,0,26780,3970,76,'0'), " +
            " (4,'45'' HC',13500,2350,2690,0,0,0,27900,4590,86,'0'), " +
            " (5,'20'' REEFER',5420,2230,2260,0,0,0,20800,3200,28,'0'), " +
            " (6,'40 HC REEFER',11560,2290,2500,0,0,0,25980,4500,'66,6','0'), " +
            " (7,'SWAPBODY',13400,2420,2980,0,0,0,0,0,'96,5','0'), " +
            " (8,'20'' FLATRACT',5600,2200,2200,0,0,0,0,2530,0,'0'), " +
            " (9,'40'' FLATRACT',12080,2350,2100,0,0,0,0,5480,0,'0'); ";


    private static final String DATABASE_INSERT_SETTING=
            "INSERT INTO `"+DataContract.Setting_Entry.TABLE_NAME+
                    "` ("+DataContract.Setting_Entry._ID+","+DataContract.Setting_Entry.COLUMN_KEY+
                    ","+DataContract.Setting_Entry.COLUMN_VALUE+") "+
                    " VALUES (1,'API_URL','http://api.hasanural.com/v1/containercalculator/calculate'),"+
                    " (2,'LANG','0'); ";
    //EndRegion

    public DataHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL(DATABASE_CREATE_PRODUCT);
        db.execSQL(DATABASE_INSERT_PRODUCT);
        db.execSQL(DATABASE_CREATE_CONTAINER );
        db.execSQL(DATABASE_INSERT_CONTAINER);
        db.execSQL(DATABASE_CREATE_ORDER);
        db.execSQL(DATABASE_CREATE_ORDER_IN_CONTAINER);
        db.execSQL(DATABASE_CREATE_ORDER_IN_PRODUCT);
        db.execSQL(DATABASE_CREATE_ORDER_RESULT);
        db.execSQL(DATABASE_CREATE_SETTING);
        db.execSQL(DATABASE_INSERT_SETTING);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS `"+DataContract.Product_Entry.TABLE_NAME+"`");
        db.execSQL("DROP TABLE IF EXISTS `"+DataContract.Container_Entry.TABLE_NAME+"`");
        db.execSQL("DROP TABLE IF EXISTS `"+DataContract.Order_Entry.TABLE_NAME+"`");
        db.execSQL("DROP TABLE IF EXISTS `"+DataContract.Order_In_Container_Entry.TABLE_NAME+"`");
        db.execSQL("DROP TABLE IF EXISTS `"+DataContract.Order_In_Product_Entry.TABLE_NAME+"`");
        db.execSQL("DROP TABLE IF EXISTS `"+DataContract.Order_Result_Entry.TABLE_NAME+"`");
        db.execSQL("DROP TABLE IF EXISTS `"+DataContract.Setting_Entry.TABLE_NAME+"`");
        onCreate(db);
    }
}
