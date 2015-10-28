package com.slk.asistes.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.slk.asistes.Adapters.BrandsCursorAdapter;
import com.slk.asistes.Fragments.SearchTabContentFragment;
import com.slk.asistes.R;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Logger;
import com.slk.asistes.Static.Utils;
import com.slk.asistes.Tasks.SearchProductsTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ViS on 20.10.15.
 */
public class LobbyPagerActivity  extends AppCompatActivity implements SearchTabContentFragment.SearchContentCallback{

    GoogleAnalytics analytics = GoogleAnalytics.getInstance(ApplicationContext.Instance().getAndroidContext());
    Tracker tracker = analytics.newTracker(R.xml.app_tracker);


    @Bind(R.id.toolbar_left_item)
    ImageView leftButton;

    @Override
    public void onButtonSearchClick(String _search_text) {

        if(ApplicationContext.Instance().getSocialManager().hasInternet()) {
            Intent intent = new Intent(LobbyPagerActivity.this, ResultsDataActivity.class);
            intent.putExtra(Utils.INTENT_SEARCH_QUERY_EXTRA_ID, _search_text);
            startActivity(intent);


            tracker.send(new HitBuilders.EventBuilder()
                    .setCategory("Search")
                    .setAction("Click For Search: ")
                    .setLabel(_search_text)
                    .build());
        }else{
            Toast.makeText(this, "Невозможно загрузить товары. Проверьте интернет соединение.", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        leftButton.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setVisibility(View.GONE);
    }

}


