package com.slk.asistes.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.slk.asistes.Adapters.BrandsCursorAdapter;
import com.slk.asistes.Adapters.ModelsCursorAdapter;
import com.slk.asistes.Data.AsistesDBHelper;
import com.slk.asistes.Data.AsistesDataBaseContract;
import com.slk.asistes.R;
import com.slk.asistes.Static.Logger;

import javax.security.auth.login.LoginException;

/**
 * Created by ViS on 21.10.15.
 */
public class SearchTabContentFragment extends Fragment{

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



        AsistesDBHelper sbHelper = new AsistesDBHelper(getActivity());
        Cursor brandsCursor = sbHelper.GetBrands();
        final Spinner brands = (Spinner)rootView.findViewById(R.id.spinner_brands);
        BrandsCursorAdapter cursor = new BrandsCursorAdapter(getActivity(),brandsCursor,0);
        brands.setAdapter(cursor);
        brands.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SQLiteCursor cursor = (SQLiteCursor) brands.getSelectedItem();
                int brandId = cursor.getInt(cursor.getColumnIndex(AsistesDataBaseContract.BrandEntry._ID));

                UpdateModels(brandId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sbHelper.close();

        return rootView;
    }


    private void UpdateModels(int brand_id)
    {

        AsistesDBHelper sbHelper = new AsistesDBHelper(getActivity());
        Cursor modelsByBrandCursor = sbHelper.GetModelsByBrand(brand_id);
        ModelsCursorAdapter modelsAdapter = new ModelsCursorAdapter(getActivity(),modelsByBrandCursor,0);
        final Spinner modelsSpinner = (Spinner)getActivity().findViewById(R.id.spinner_models);
        modelsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SQLiteCursor cursor = (SQLiteCursor) modelsSpinner.getSelectedItem();
                int modelId = cursor.getInt(cursor.getColumnIndex(AsistesDataBaseContract.ModelEntry._ID));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        modelsSpinner.setAdapter(modelsAdapter);
        sbHelper.close();
    }

}
