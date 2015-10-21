package com.slk.asistes.Fragments;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.slk.asistes.Activities.LobbyActivity;
import com.slk.asistes.Activities.ResultsDataActivity;
import com.slk.asistes.R;
import com.slk.asistes.Tasks.SearchProductsTask;

/**
 * Created by ViS on 21.10.15.
 */
public class SearchContentFragment extends Fragment {

    public interface SearchContentCallback {
        void onButtonSearchClick();
    }

    private TabFragment.TabContentType contentType;

    public static SearchContentFragment newInstance(TabFragment.TabContentType fragmentListType) {
        SearchContentFragment tabContentFragment = new SearchContentFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("", fragmentListType.ordinal());
        tabContentFragment.setArguments(bundle);

        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.contentType = TabFragment.TabContentType.values()[ bundle.getInt("", 0) ];
        }

        final View rootView = inflater.inflate(R.layout.fragment_search_tab_content, container, false);

        Button btn = (Button)rootView.findViewById(R.id.button_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SearchContentCallback)getActivity()).onButtonSearchClick();
            }
        });
        return rootView;
    }

}
