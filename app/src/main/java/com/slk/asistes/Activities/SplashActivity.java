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

public class SplashActivity extends Activity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ApplicationInitialize();

        new Handler().postDelayed(new Runnable() {

        /*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, LobbyPagerActivity.class);
                startActivity(i);

                // close this activity
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
        AsistesDBHelper dbHelper = new AsistesDBHelper(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
