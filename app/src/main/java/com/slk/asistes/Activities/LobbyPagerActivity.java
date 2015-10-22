package com.slk.asistes.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.slk.asistes.Fragments.SearchTabContentFragment;
import com.slk.asistes.R;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Utils;
import com.slk.asistes.Tasks.SearchProductsTask;

/**
 * Created by ViS on 20.10.15.
 */
public class LobbyPagerActivity  extends AppCompatActivity implements SearchTabContentFragment.SearchContentCallback {


    public void onButtonSearchClick() {

        if(ApplicationContext.Instance().getSocialManager().hasInternet()) {
            Intent intent = new Intent(LobbyPagerActivity.this, ResultsDataActivity.class);
            intent.putExtra(Utils.INTENT_SEARCH_QUERY_EXTRA_ID, "");
            startActivity(intent);
        }else{
            Toast.makeText(this, "Невозможно загрузить товары. Проверьте интернет соединение.", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}


