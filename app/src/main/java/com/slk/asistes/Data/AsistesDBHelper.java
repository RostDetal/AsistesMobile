package com.slk.asistes.Data;

import android.content.Context;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.slk.asistes.Data.AsistesDataBaseContract.BrandEntry;
import com.slk.asistes.Data.AsistesDataBaseContract.ModelEntry;
import com.slk.asistes.Data.AsistesDataBaseContract.ModificationEntry;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by VIS on 23.10.2015.
 */
public class AsistesDBHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "asistes.db";

    private Context context;

    public AsistesDBHelper(Context _context) {

        super(_context, DATABASE_NAME, null, DATABASE_VERSION);
        context = _context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_BRANDS_TABLE = "CREATE TABLE " + BrandEntry.TABLE_NAME + " (" +
                BrandEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                BrandEntry.COLUMN_NAME_TITLE+ " TEXT DEFAULT '' ," +
                BrandEntry.COLUMN_NAME_CODE+ " TEXT DEFAULT '' ," +
                BrandEntry.COLUMN_NAME_POPULAR + " INTEGER NOT NULL DEFAULT 0 , " +
                " UNIQUE (" + BrandEntry._ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_MODELS_TABLE = "CREATE TABLE " + ModelEntry.TABLE_NAME + " (" +
                ModelEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ModelEntry.COLUMN_NAME_TITLE+ " TEXT DEFAULT '' ," +
                ModelEntry.COLUMN_NAME_CODE+ " TEXT DEFAULT '' ," +
                ModelEntry.COLUMN_NAME_BRAND_ID + " INTEGER NOT NULL ," +
                " UNIQUE (" + ModelEntry._ID + ") ON CONFLICT REPLACE);";



        db.execSQL(SQL_CREATE_BRANDS_TABLE);
        db.execSQL(SQL_CREATE_MODELS_TABLE);

        InsertPreInstalledData(db);
    }

    private void InsertBrands(SQLiteDatabase database)
    {
        String brandsData = ApplicationContext.Instance().DataManager().GetTextFileContentFromAssets(context, "brands.txt").toString();
        final String SQL_BRANDS_FILL_DATA = "INSERT INTO " + AsistesDataBaseContract.BrandEntry.TABLE_NAME + " VALUES " + brandsData;

        TryExecuteQuery(database, SQL_BRANDS_FILL_DATA);

    }

    private void InsertModels(SQLiteDatabase database)
    {
        String delim = "[|]";
        String[] modelsData = ApplicationContext.Instance().DataManager().GetTextFileContentFromAssets(context, "models.txt").toString().split(delim);

        for (String value : modelsData) {
            final String SQL_MODELS_FILL_DATA = "INSERT INTO " + AsistesDataBaseContract.ModelEntry.TABLE_NAME + " VALUES " + value.toString()+";";
            TryExecuteQuery(database, SQL_MODELS_FILL_DATA);
        }
    }

    private void TryExecuteQuery(SQLiteDatabase database, String query)
    {
        database.beginTransaction();
        database.execSQL(query);
        database.setTransactionSuccessful();
        database.endTransaction();
    }


    private void InsertPreInstalledData(SQLiteDatabase database)
    {

        InsertBrands(database);
        InsertModels(database);
        Logger.toConsole("Preloaded Data created successfully!");
    }



    public Cursor GetBrands()
    {
        Cursor retCursor;
        SQLiteDatabase database = getReadableDatabase();
        retCursor = database.query(BrandEntry.TABLE_NAME, null ,null,null,null,null,null);
        return retCursor;
    }

    public Cursor GetModelsByBrand(int brand_id)
    {
        Cursor retCursor;
        SQLiteDatabase database = getReadableDatabase();
        String selection = ModelEntry.COLUMN_NAME_BRAND_ID+" = "+brand_id;
        retCursor = database.query(ModelEntry.TABLE_NAME, null ,selection,null,null,null,null);
        return retCursor;
    }


    public Cursor GetModificationsByModelId(int model_id)
    {
        Cursor retCursor;
        SQLiteDatabase database = getReadableDatabase();
        String selection = ModelEntry.COLUMN_NAME_BRAND_ID+" = "+model_id;
        retCursor = database.query(ModelEntry.TABLE_NAME, null ,selection,null,null,null,null);
        return retCursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BrandEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ModelEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ModificationEntry.TABLE_NAME);
        onCreate(db);
    }
}
