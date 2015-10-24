package com.slk.asistes.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.slk.asistes.Data.AsistesDataBaseContract.BrandEntry;
import com.slk.asistes.Fragments.SearchTabContentFragment;

/**
 * Created by VIS on 24.10.2015.
 */
public class BrandsCursorAdapter extends CursorAdapter
{

    private int brandId;

    public BrandsCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvBody = (TextView) view.findViewById(android.R.id.text1);
        tvBody.setText(cursor.getString(cursor.getColumnIndex(BrandEntry.COLUMN_NAME_TITLE)));
    }

}
