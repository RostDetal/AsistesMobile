package com.slk.asistes.Tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.slk.asistes.Data.AsistesDBHelper;
import com.slk.asistes.Static.ApplicationContext;

/**
 * Created by VIS on 25.10.2015.
 */
public class FillDataBaseDataTask extends AsyncTask<String, Void, String> {

    public interface TaskCallback{
        public void onTaskComplete();
    }

    private Context globalContext;

    public FillDataBaseDataTask(Context context)
    {
        globalContext = context;
    }

    private void InitDatabase() {
        AsistesDBHelper dbHelper = new AsistesDBHelper(globalContext);
        dbHelper.getReadableDatabase();
    }


    @Override
    protected String doInBackground(String... params) {
        InitDatabase();
        return "";
    }

    @Override
    protected void onPostExecute(String content) {

        ((TaskCallback)globalContext).onTaskComplete();
    }



}
