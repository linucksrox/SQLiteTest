package com.example.android.sqlitetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.android.sqlitetest.data.ProductContract.*;
import com.example.android.sqlitetest.data.ProductHelper;

public class MainActivity extends AppCompatActivity {

    ProductHelper mDbHelper = new ProductHelper(this);
    TextView mProductList;
    Button mAddProductButton;
    Button mDeleteProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProductList = (TextView) findViewById(R.id.tv_product_list);
        mAddProductButton = (Button) findViewById(R.id.btn_add_product);
        mDeleteProductButton = (Button) findViewById(R.id.btn_delete_product);

        mAddProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
                printDatabase();
            }
        });

        mDeleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct();
                printDatabase();
            }
        });

        printDatabase();
    }

    public long addProduct() {
        // get the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // create map of values
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_NAME_NAME, "sample product");

        // insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ProductEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    public void deleteProduct() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String selection = ProductEntry.COLUMN_NAME_NAME + " LIKE ?";
        String[] selectionArgs = { "sample product" };
        db.delete(ProductEntry.TABLE_NAME, selection, selectionArgs);
    }

    public void printDatabase() {
        // clear current list
        mProductList.setText("");

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_NAME_NAME
        };

        String sortOrder = ProductEntry.COLUMN_NAME_NAME + " ASC";

        Cursor cursor = db.query(ProductEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);

        while (cursor.moveToNext()) {
            mProductList.append(cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_NAME)) + "\n");
        }
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        // close that connection!
        mDbHelper.close();

        super.onDestroy();
    }
}
