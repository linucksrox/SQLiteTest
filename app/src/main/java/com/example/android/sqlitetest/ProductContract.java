package com.example.android.sqlitetest;

import android.provider.BaseColumns;

/**
 * Created by edaly on 5/15/2017.
 */

public final class ProductContract {

    // private constructor prevents accidental instantiation of the contract class
    private ProductContract() {
        // empty constructor
    }

    // inner class defines the table
    public static class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME_NAME = "name";
    }
}
