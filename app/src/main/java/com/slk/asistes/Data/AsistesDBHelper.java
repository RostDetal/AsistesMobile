package com.slk.asistes.Data;

import android.content.Context;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.slk.asistes.Data.AsistesDataBaseContract.BrandEntry;
import com.slk.asistes.Data.AsistesDataBaseContract.ModelEntry;
import com.slk.asistes.Data.AsistesDataBaseContract.ModificationEntry;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Logger;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by VIS on 23.10.2015.
 */
public class AsistesDBHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 12;
    public final static String DATABASE_NAME = "asistes.db";
    public final static String DATABASE_PREPOPUALTED_NAME = "asistes_prepopulated.db";

    private Context context;

    public AsistesDBHelper(Context _context) {

        super(_context, DATABASE_NAME, null, DATABASE_VERSION);
        context = _context;
    }

    public void createDataBase() throws IOException{
        boolean dbExist = checkDataBase();
        if(dbExist){
            //do nothing - database already exist
        }else {
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException ex){
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = "";//DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DATABASE_PREPOPUALTED_NAME);

        // Path to the just created empty db
        String outFileName =""; // DB_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException{

        //Open the database
       // String myPath = DB_PATH + DB_NAME;
       // myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

       // if(myDataBase != null)
          //  myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


//        ApplicationContext.Instance().DataManager().IsDatabaseCreationInProgress = true;
//
//        final String SQL_CREATE_BRANDS_TABLE = "CREATE TABLE " + BrandEntry.TABLE_NAME + " (" +
//                BrandEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                BrandEntry.COLUMN_NAME_TITLE+ " TEXT DEFAULT '' ," +
//                BrandEntry.COLUMN_NAME_CODE+ " TEXT DEFAULT '' ," +
//                BrandEntry.COLUMN_NAME_POPULAR + " INTEGER NOT NULL DEFAULT 0 , " +
//                " UNIQUE (" + BrandEntry._ID + ") ON CONFLICT REPLACE);";
//
//        final String SQL_CREATE_MODELS_TABLE = "CREATE TABLE " + ModelEntry.TABLE_NAME + " (" +
//                ModelEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                ModelEntry.COLUMN_NAME_TITLE+ " TEXT DEFAULT '' ," +
//                ModelEntry.COLUMN_NAME_CODE+ " TEXT DEFAULT '' ," +
//                ModelEntry.COLUMN_NAME_BRAND_ID + " INTEGER NOT NULL ," +
//                " UNIQUE (" + ModelEntry._ID + ") ON CONFLICT REPLACE);";
//
//        final String SQL_CREATE_MODIFICATIONS_TABLE = "CREATE TABLE " + ModificationEntry.TABLE_NAME + " (" +
//                ModificationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                ModificationEntry.COLUMN_NAME_TITLE+ " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_CODE+ " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_CREATED_AT + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_UPDATED_AT + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_MODEL_ID + " INTEGER NOT NULL ," +
//                ModificationEntry.COLUMN_NAME_YEAR_FROM + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_YEAR_TO + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_ENGINE_TYPE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_BODY_TITLE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_BODY_CODE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_BODY_DOORS + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_BODY_PLACE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_BODY_LENGTH + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_BODY_WIDTH + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_BODY_HEIGHT + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_WHEELBASE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_FRONT_TRACK + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_REAR_TRACK + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_ENGINE_CAPACITY + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_ENGINE_POWER + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_ENGINE_REVS + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_TORQUE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_POWER_SYSTEM + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_GAS + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_LOCATION_CYLINDERS + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_NUMBER_CYLINDERS + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_COMPRESSION_RATIO + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_FUEL + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_GEARBOX_TYPE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_NUMBER_GEARS + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_NUMBER_GEARS_MECH + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_DRIVE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_FRONT_BRAKES + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_REAR_BRAKES + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_MAXIMUM_SPEED + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_ACCELERATION_TIME + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_CURB_VEHICLE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_FUEL_TANK + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_TIRES_SIZE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_ENVIRONMENTAL_STANDARD + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_GROUND_CLEARANCE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_NUMBER_VALVES_CYLINDER + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_FRONT_SUSPENSION + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_TYPE_REAR_SUSPENSION + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_NUMBER_GEARS_AUTOMATIC + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_MAX_AMOUNT_TRUNK + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_MIN_AMOUNT_TRUNK + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_TURBOCHARGING + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_CYLINDER_DIAMETER + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_PISTON_STROKE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_GEAR_RATIO_MAIN_PAIR + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_FUEL_CONSUMPTION_TOWN + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_FUEL_CONSUMPTION_HIGHWAY + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_FUEL_CONSUMPTION_AVERAGE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_STEERING_TYPE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_ENGINE_MODEL + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_MAX_WEIGHT + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_MOTOR_POWER + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_TURNING_CIRCLE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_DISK_SIZE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_TOTAL_POWER + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_ENGINE_LOCATION + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_POWER_RESERVE + " TEXT DEFAULT '' ," +
//                ModificationEntry.COLUMN_NAME_FULL_CHARGE + " TEXT DEFAULT '' ," +
//                " UNIQUE (" + ModificationEntry._ID + ") ON CONFLICT REPLACE);";
//
//        db.execSQL(SQL_CREATE_BRANDS_TABLE);
//        db.execSQL(SQL_CREATE_MODELS_TABLE);
//        db.execSQL(SQL_CREATE_MODIFICATIONS_TABLE);
//
//        InsertPreInstalledData(db);
    }

    private void InsertBrands(SQLiteDatabase database)
    {
        String brandsData = ApplicationContext.Instance().DataManager().GetTextFileContentFromAssets(context, "brands.txt").toString();
        final String SQL_BRANDS_FILL_DATA = "INSERT INTO " + AsistesDataBaseContract.BrandEntry.TABLE_NAME + " VALUES " + brandsData;
        TryExecuteQuery(database, SQL_BRANDS_FILL_DATA);
    }

    private void InsertModifications(SQLiteDatabase database)
    {
        String delim = "[|]";
        String[] modificationsData = ApplicationContext.Instance().DataManager().GetTextFileContentFromAssets(context, "modifications.txt").toString().split(delim);

        for (String value : modificationsData) {
            final String SQL_MODIFICATIONS_FILL_DATA = "INSERT INTO " + AsistesDataBaseContract.ModificationEntry.TABLE_NAME + " VALUES " + value.toString()+";";
            TryExecuteQuery(database, SQL_MODIFICATIONS_FILL_DATA);
        }
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
        InsertModifications(database);
        Logger.toConsole("Preloaded Data created successfully!");
        ApplicationContext.Instance().DataManager().IsDatabaseCreationInProgress = false;
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
        String selection = ModificationEntry.COLUMN_NAME_MODEL_ID+" = "+model_id;
        retCursor = database.query(ModificationEntry.TABLE_NAME, null ,selection,null,null,null,null);
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
