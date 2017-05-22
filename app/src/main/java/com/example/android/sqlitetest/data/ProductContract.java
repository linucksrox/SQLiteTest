package com.example.android.sqlitetest.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by edaly on 5/15/2017.
 */

public final class ProductContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.sqlitetest";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PRODUCT = "product";

    // private constructor prevents accidental instantiation of the contract class
    private ProductContract() {
        // empty constructor
    }

    // inner class defines the table
    public static class ProductEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH_PRODUCT);
        public static final String PRODUCT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCT;
        public static final String PRODUCT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCT;
        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME_NAME = "name";
    }
}
