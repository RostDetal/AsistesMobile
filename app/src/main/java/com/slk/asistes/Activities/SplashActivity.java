package com.slk.asistes.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import com.slk.asistes.Data.AsistesDBHelper;
import com.slk.asistes.Data.AsistesDataBaseContract;
import com.slk.asistes.R;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Logger;
import com.slk.asistes.Tasks.FillDataBaseDataTask;

public class SplashActivity extends Activity{
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ApplicationInitialize();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Logger.toConsole("Starting Lobby");
                Intent i = new Intent(SplashActivity.this, LobbyPagerActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void ApplicationInitialize()
    {
        ApplicationContext.Instance().Initialize(getApplicationContext());
        InitDatabase();
    }


    private void InitDatabase() {
        AsistesDBHelper dbHelper = new AsistesDBHelper(getApplicationContext());
        dbHelper.getReadableDatabase();
    }

}
