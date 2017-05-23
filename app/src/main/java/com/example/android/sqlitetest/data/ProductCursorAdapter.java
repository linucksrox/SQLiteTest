package com.example.android.sqlitetest.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.sqlitetest.R;
import com.example.android.sqlitetest.data.ProductContract.*;

/**
 * Created by edaly on 5/23/17.
 */

public class ProductCursorAdapter extends CursorAdapter {

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView productNameTV = (TextView) view.findViewById(R.id.tv_product_name);

        String productNameString = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_NAME));

        productNameTV.setText(productNameString);
    }
}
