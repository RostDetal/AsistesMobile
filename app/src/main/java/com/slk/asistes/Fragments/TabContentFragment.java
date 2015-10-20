package com.slk.asistes.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slk.asistes.R;

/**
 * Created by ViS on 20.10.15.
 */
public class TabContentFragment  extends Fragment{

    public enum FragmentListTypes {
        SEARCH,
        ABOUT,
        CONTACTS
    }

    public interface TabSelectedCallback {
        void onItemSelected(long id);
    }

    public static TabContentFragment newInstance(FragmentListTypes fragmentListType) {
        TabContentFragment moviesListFragment = new TabContentFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("", fragmentListType.ordinal());
        moviesListFragment.setArguments(bundle);

        return moviesListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            this.listType = FragmentListTypes.values()[ bundle.getInt("", 0) ];
//        }
//        final View rootView = inflater.inflate(R.layout.fragment_grid_movies, container, false);
//        final GridView gridLayout = (GridView) rootView.findViewById(R.id.gridview);
//        moviesAdapter = new MoviesAdapter(getActivity(), null, 0);
//        gridLayout.setAdapter(moviesAdapter);
//        gridLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Cursor cursor = moviesAdapter.getCursor();
//                if (cursor != null && cursor.moveToPosition(position)) {
//                    ((MovieSelectedCallback) getActivity()).onItemSelected(cursor.getLong(COL_MOVIEDB_ID));
//                }
//            }
//        });

        final View rootView = inflater.inflate(R.layout.fragment_tab_content, container, false);

        return rootView;
    }


}
