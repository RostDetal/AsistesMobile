package com.slk.asistes.Fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.slk.asistes.R;

/**
 * Created by ViS on 21.10.15.
 */
public class SearchTabContentFragment extends Fragment {

    public interface SearchContentCallback {
        void onButtonSearchClick(String _search_text);
    }

    private LobbyFragment.TabContentType contentType;



    public static SearchTabContentFragment newInstance(LobbyFragment.TabContentType fragmentListType) {
        SearchTabContentFragment tabContentFragment = new SearchTabContentFragment();

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
            this.contentType = LobbyFragment.TabContentType.values()[ bundle.getInt("", 0) ];
        }

        final View rootView = inflater.inflate(R.layout.fragment_search_tab_content, container, false);

        final Button btn = (Button)rootView.findViewById(R.id.button_search);
        final EditText searchField = (EditText)rootView.findViewById(R.id.search_edittext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SearchContentCallback)getActivity()).onButtonSearchClick(searchField.getText().toString());
            }
        });

        return rootView;
    }

}
