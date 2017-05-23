package com.example.android.sqlitetest;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.sqlitetest.data.ProductContract.*;
import com.example.android.sqlitetest.data.ProductCursorAdapter;

public class MainActivity extends AppCompatActivity {

    ListView mProductList;
    Button mAddProductButton;
    Button mDeleteProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProductList = (ListView) findViewById(R.id.product_list);
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

    public void addProduct() {
        // create map of values
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_NAME_NAME, "sample product");

        // insert the new row using the content provider
        Uri newProductUri = getContentResolver().insert(ProductEntry.CONTENT_URI, values);
        Long newRowId = ContentUris.parseId(newProductUri);

        // confirm whether entry was successful or not
        if (newRowId >= 1) {
//            Toast.makeText(this, "Successfully added new product", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Failed to add new product", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProduct() {
        String selection = ProductEntry.COLUMN_NAME_NAME + " LIKE ?";
        String[] selectionArgs = { "sample product" };
        int numRowsDeleted = getContentResolver().delete(ProductEntry.CONTENT_URI, selection, selectionArgs);

        Toast.makeText(this, "Deleted " + numRowsDeleted + " rows", Toast.LENGTH_SHORT).show();
    }

    public void printDatabase() {
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_NAME_NAME
        };

        String sortOrder = ProductEntry.COLUMN_NAME_NAME + " ASC";

        Cursor cursor = getContentResolver().query(ProductEntry.CONTENT_URI, projection, null, null, sortOrder);

        ProductCursorAdapter productCursorAdapter = new ProductCursorAdapter(this, cursor);
        mProductList.setAdapter(productCursorAdapter);
    }
}
