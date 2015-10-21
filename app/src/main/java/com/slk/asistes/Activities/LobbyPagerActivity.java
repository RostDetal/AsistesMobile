package com.slk.asistes.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.slk.asistes.Fragments.SearchTabContentFragment;
import com.slk.asistes.R;
import com.slk.asistes.Tasks.SearchProductsTask;

/**
 * Created by ViS on 20.10.15.
 */
public class LobbyPagerActivity  extends AppCompatActivity implements SearchTabContentFragment.SearchContentCallback {

    public interface ProductLoadedCallback {
        public void onProductsLoadingDone(String result);
    }

    @Override
    public void onButtonSearchClick() {
        SearchProductsTask task = new SearchProductsTask(new ProductLoadedCallback() {
            @Override
            public void onProductsLoadingDone(String result) {
                Intent intent = new Intent(LobbyPagerActivity.this, ResultsDataActivity.class);
                startActivity(intent);
            }
        });
        task.ExecuteWithData("");
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


