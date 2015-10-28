package com.slk.asistes.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.slk.asistes.Adapters.BrandsCursorAdapter;
import com.slk.asistes.Adapters.ModelsCursorAdapter;
import com.slk.asistes.Adapters.ModificationsCursorAdapter;
import com.slk.asistes.Data.AsistesDBHelper;
import com.slk.asistes.Data.AsistesDataBaseContract;
import com.slk.asistes.R;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Logger;

import java.util.ArrayList;
import java.util.Calendar;

import javax.security.auth.login.LoginException;

/**
 * Created by ViS on 21.10.15.
 */
public class SearchTabContentFragment extends Fragment{

    private interface DataPositionType{
        String Brand = "BrandPotition";
        String Model = "ModelPotition";
        String Modification = "ModificationPotition";
        String Year = "YearPotition";
    }

    private interface DataType{
        String Brand = "Brand";
        String Model = "Model";
        String Modification = "Modification";
        String Year = "Year";
    }


    private Spinner brandsSpinner;
    private Spinner modelsSpinner;
    private Spinner yearsSpinner;
    private Spinner modificationsSpinner;

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
        searchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    ((SearchContentCallback) getActivity()).onButtonSearchClick(v.getText().toString());
                }
                return false;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SearchContentCallback) getActivity()).onButtonSearchClick(searchField.getText().toString());
            }
        });

        UpdateBrands(rootView);

        return rootView;
    }

    private void UpdateBrands(View rootView)
    {
        AsistesDBHelper sbHelper = new AsistesDBHelper(getActivity());
        Cursor brandsCursor = sbHelper.GetBrands();
        brandsSpinner = (Spinner)rootView.findViewById(R.id.spinner_brands);
        BrandsCursorAdapter cursor = new BrandsCursorAdapter(getActivity(), brandsCursor, 0);

        brandsSpinner.setAdapter(cursor);
        brandsSpinner.setSelection(GetPosition(brandsSpinner, DataPositionType.Brand));

        brandsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SQLiteCursor cursor = (SQLiteCursor) brandsSpinner.getSelectedItem();
                int brandId = cursor.getInt(cursor.getColumnIndex(AsistesDataBaseContract.BrandEntry._ID));


                ApplicationContext.Instance().DataManager().SetLiveData(DataType.Brand, brandId);
                ApplicationContext.Instance().DataManager().SetLiveData(DataPositionType.Brand, position);

                UpdateModels(brandId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sbHelper.close();
    }


    private void UpdateModels(int brand_id)
    {
        AsistesDBHelper sbHelper = new AsistesDBHelper(getActivity());
        Cursor modelsByBrandCursor = sbHelper.GetModelsByBrand(brand_id);
        ModelsCursorAdapter modelsAdapter = new ModelsCursorAdapter(getActivity(),modelsByBrandCursor,0);

        modelsSpinner = (Spinner)getActivity().findViewById(R.id.spinner_models);

        modelsSpinner.setAdapter(modelsAdapter);
        modelsSpinner.setSelection(GetPosition(modelsSpinner, DataPositionType.Model));

        modelsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SQLiteCursor cursor = (SQLiteCursor) modelsSpinner.getSelectedItem();
                int modelId = cursor.getInt(cursor.getColumnIndex(AsistesDataBaseContract.ModelEntry._ID));

                ApplicationContext.Instance().DataManager().SetLiveData(DataType.Model, modelId);
                ApplicationContext.Instance().DataManager().SetLiveData(DataPositionType.Model, position);

                UpdateYears(modelId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sbHelper.close();
    }


    private void UpdateModification()
    {
        AsistesDBHelper sbHelper = new AsistesDBHelper(getActivity());

        int model_id = (int)ApplicationContext.Instance().DataManager().GetLiveData(DataType.Model);
        int year = (int)ApplicationContext.Instance().DataManager().GetLiveData(DataType.Year);

         Cursor modifCursor = sbHelper.GetModificationsByModelIdAndYears(model_id, year);


        ModificationsCursorAdapter modifAdapter = new ModificationsCursorAdapter(getActivity(),modifCursor,0);
        modificationsSpinner = (Spinner)getActivity().findViewById(R.id.spinner_modifications);

        modificationsSpinner.setAdapter(modifAdapter);
        modificationsSpinner.setSelection(GetPosition(modificationsSpinner, DataPositionType.Modification));

        modificationsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SQLiteCursor cursor = (SQLiteCursor) modificationsSpinner.getSelectedItem();
                int model_id = cursor.getInt(cursor.getColumnIndex(AsistesDataBaseContract.ModificationEntry.COLUMN_NAME_MODEL_ID));
                ApplicationContext.Instance().DataManager().SetLiveData(DataType.Modification, model_id);
                ApplicationContext.Instance().DataManager().SetLiveData(DataPositionType.Modification, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sbHelper.close();
    }


    private void UpdateYears(int model_id)
    {

        AsistesDBHelper sbHelper = new AsistesDBHelper(getActivity());
        Cursor yearsCursor = sbHelper.GetYearsByModelId(model_id);

        int to = yearsCursor.getInt(1);
        int from = yearsCursor.getInt(0);

        ArrayList<String> tempValues = new ArrayList<String>();
        to = to == 0 ? Calendar.getInstance().get(Calendar.YEAR) : to;

        if(from == to)
            tempValues.add(String.valueOf(to));
        else {

            int cnt = 0;
            for (int i = from; i <= to; i++) {
                String year = String.valueOf(from + cnt);
                tempValues.add(year);
                cnt++;
            }
        }

        ArrayAdapter<String> years = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, tempValues);

        yearsSpinner = (Spinner)getActivity().findViewById(R.id.spinner_year);
        yearsSpinner.setAdapter(years);
        yearsSpinner.setSelection(GetPosition(yearsSpinner, DataPositionType.Year));

        yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ApplicationContext.Instance().DataManager().SetLiveData(DataType.Year, Integer.valueOf((String) yearsSpinner.getSelectedItem()));
                ApplicationContext.Instance().DataManager().SetLiveData(DataPositionType.Year, position);
                UpdateModification();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void InitSelectedItems()
    {
        ApplicationContext.Instance().DataManager().SetLiveData("model", 0);
        ApplicationContext.Instance().DataManager().SetLiveData("modification", 0);
        ApplicationContext.Instance().DataManager().SetLiveData("year", 0);
    }

    private int GetPosition(Spinner spinner, String type)
    {
        int position = ApplicationContext.Instance().DataManager().GetLiveData(type) == null ? 0 : (int)ApplicationContext.Instance().DataManager().GetLiveData(type);
        int result = position > (spinner.getCount()-1) ? 0 : position;
        return result;
    }


}


