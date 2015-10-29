package com.slk.asistes.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.slk.asistes.Data.AsistesDataBaseContract;
import com.slk.asistes.R;

/**
 * Created by VIS on 24.10.2015.
 */
public class ModelsCursorAdapter extends CursorAdapter {

    public ModelsCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.drop_down_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvBody = (TextView) view.findViewById(R.id.drop_down_title);
        tvBody.setText(cursor.getString(cursor.getColumnIndex(AsistesDataBaseContract.ModelEntry.COLUMN_NAME_TITLE)));
    }
}
