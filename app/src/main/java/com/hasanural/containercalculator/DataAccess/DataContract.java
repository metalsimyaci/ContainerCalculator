package com.hasanural.containercalculator.DataAccess;

import android.net.Uri;
import android.provider.BaseColumns;

public class DataContract {
    static final String CONTENT_AUTHORITY="com.hasanural.containercalculator.dataprovider";

    static final Uri BASE_CONTENT_URI=Uri.parse("content://"+CONTENT_AUTHORITY);

    static final String PATH_PRODUCT="product";
    static final String PATH_SETTING="setting";
    static final String PATH_CONTAINER="container";
    static final String PATH_ORDER="order";
    static final String PATH_ORDER_IN_PRODUCT="order_in_product";
    static final String PATH_ORDER_IN_CONTAINER="order_in_container";
    static final String PATH_ORDER_RESULT="order_result";

    public static final class Product_Entry implements BaseColumns{
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_PRODUCT);

        public static final String TABLE_NAME="Products";

        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_DEFINITION="Definition";
        public static final String COLUMN_LENGTH="Length";
        public static final String COLUMN_WIDTH="Width";
        public static final String COLUMN_HEIGHT="Height";
        public static final String COLUMN_WEIGHT="Weight";
        public static final String COLUMN_COLOR="Color";

    }
    public static final class Container_Entry implements BaseColumns{
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_CONTAINER);

        public static final String TABLE_NAME="Containers";

        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_DEFINITION="Definition";
        public static final String COLUMN_LENGTH="Length";
        public static final String COLUMN_WIDTH="Width";
        public static final String COLUMN_HEIGHT="Height";
        public static final String COLUMN_WEIGHT="Weight";
        public static final String COLUMN_WEIGHT_EMPTY="Weight_Empty";
        public static final String COLUMN_TOLERANCE_LENGTH="Tolerance_Length";
        public static final String COLUMN_TOLERANCE_WIDTH="Tolerance_Width";
        public static final String COLUMN_TOLERANCE_HEIGHT="Tolerance_Height";
        public static final String COLUMN_VOLUME="Volume";
        public static final String COLUMN_COLOR="Color";

    }
    public static final class Order_Entry implements BaseColumns{
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_ORDER);

        public static final String TABLE_NAME="Order";

        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_ORDERNO="OrderNo";
        public static final String COLUMN_DESCRIPTION="Description";

    }
    public static final class Order_In_Product_Entry implements BaseColumns{
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_ORDER_IN_PRODUCT);

        public static final String TABLE_NAME="Order_In_Product";

        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_ORDER_ID="Order_ID";
        public static final String COLUMN_DEFINITION="Definition";
        public static final String COLUMN_LENGTH="Length";
        public static final String COLUMN_WIDTH="Width";
        public static final String COLUMN_HEIGHT="Height";
        public static final String COLUMN_WEIGHT="Weight";
        public static final String COLUMN_QUANTITY="Quantity";
        public static final String COLUMN_COLOR="Color";

    }
    public static final class Order_In_Container_Entry implements BaseColumns{
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_ORDER_IN_CONTAINER);

        public static final String TABLE_NAME="Order_In_Container";

        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_ORDER_ID="Order_ID";
        public static final String COLUMN_DEFINITION="Definition";
        public static final String COLUMN_LENGTH="Length";
        public static final String COLUMN_WIDTH="Width";
        public static final String COLUMN_HEIGHT="Height";
        public static final String COLUMN_TOLERANCE_LENGTH="Tolerance_Length";
        public static final String COLUMN_TOLERANCE_WIDTH="Tolerance_Width";
        public static final String COLUMN_TOLERANCE_HEIGHT="Tolerance_Height";
        public static final String COLUMN_WEIGHT="Weight";
        public static final String COLUMN_WEIGHT_EMPTY="WeightEmpty";
        public static final String COLUMN_VOLUME="Volume";
        public static final String COLUMN_COLOR="Color";

    }
    public static final class Order_Result_Entry implements BaseColumns{
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_ORDER_RESULT);

        public static final String TABLE_NAME="Order_Result";

        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_ORDER_ID="Order_ID";
        public static final String COLUMN_TOTAL_CONTAINER_COUNT="Total_Container_Count";
        public static final String COLUMN_IS_COMPLATE_PACK="Is_Complate_Pack";
        public static final String COLUMN_PACK_TIME_IN_MILLISECOND="Pack_Time_In_Millisecond";
        public static final String COLUMN_PERCENT_CONTAINER_VOLUME_PACKED="Percent_Container_Volume_Packed";
        public static final String COLUMN_PERCENT_ITEM_VOLUME_PACKED="Percent_Item_Volume_Packed";
        public static final String COLUMN_TOTAL_PACKAGES="Total_Packages";
        public static final String COLUMN_PACKED_ITEMS_COUNT="Packed_Items_Count";
        public static final String COLUMN_PACKED_ITEMS_VOLUME="Packed_Items_Volume";
        public static final String COLUMN_UNPACKED_ITEMS_COUNT="Unpacked_Items_Count";
        public static final String COLUMN_IS_ERROR="Is_Error";
        public static final String COLUMN_MESSAGE="Message";
    }

    public static final class Setting_Entry implements BaseColumns{
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_SETTING);

        public static final String TABLE_NAME="Settings";

        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_KEY="Key";
        public static final String COLUMN_VALUE="Value";

    }
}
