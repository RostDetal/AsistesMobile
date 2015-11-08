package com.slk.asistes.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.ArraySet;
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
import com.slk.asistes.Managers.DataManager;
import com.slk.asistes.R;
import com.slk.asistes.Static.ApplicationContext;
import com.slk.asistes.Static.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;

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

    public interface DataType{
        String Brand = "Brand";
        String Model = "Model";
        String Modification = "Modification";
        String Year = "Year";
    }


    private Spinner brandsSpinner;
    private Spinner modelsSpinner;
    private Spinner yearsSpinner;
    private Spinner modificationsSpinner;

    private DataManager dataManager = ApplicationContext.Instance().DataManager();

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
        brandsSpinner.setSelection(GetPosition(brandsSpinner, DataType.Brand));

        brandsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SaveData(DataType.Brand, brandsSpinner.getSelectedItem(), position, id);
                UpdateModels(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sbHelper.close();
    }


    private void UpdateModels(long brand_id)
    {
        AsistesDBHelper sbHelper = new AsistesDBHelper(getActivity());
        Cursor modelsByBrandCursor = sbHelper.GetModelsByBrand(brand_id);
        ModelsCursorAdapter modelsAdapter = new ModelsCursorAdapter(getActivity(),modelsByBrandCursor,0);

        modelsSpinner = (Spinner)getActivity().findViewById(R.id.spinner_models);

        modelsSpinner.setAdapter(modelsAdapter);
        modelsSpinner.setSelection(GetPosition(modelsSpinner, DataType.Model));

        modelsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SaveData(DataType.Model, modelsSpinner.getSelectedItem(), position, id);
                UpdateYears(id);
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

        long model_id = (long)dataManager.GetLiveValue(DataType.Model, false);
        int year = Integer.valueOf((String) dataManager.GetLiveValue(DataType.Year, true));

        Cursor modifCursor = sbHelper.GetModificationsByModelIdAndYears(model_id, year);


        ModificationsCursorAdapter modifAdapter = new ModificationsCursorAdapter(getActivity(),modifCursor,0);
        modificationsSpinner = (Spinner)getActivity().findViewById(R.id.spinner_modifications);

        modificationsSpinner.setAdapter(modifAdapter);
        modificationsSpinner.setSelection(GetPosition(modificationsSpinner, DataType.Modification));

        modificationsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SaveData(DataType.Modification, modificationsSpinner.getSelectedItem(), position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sbHelper.close();
    }


    private void UpdateYears(long model_id)
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

        ArrayAdapter<String> years = new ArrayAdapter<String>(getActivity(), R.layout.drop_down_item, R.id.drop_down_title, tempValues);

        yearsSpinner = (Spinner)getActivity().findViewById(R.id.spinner_year);
        yearsSpinner.setAdapter(years);
        yearsSpinner.setSelection(GetPosition(yearsSpinner, DataType.Year));

        yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SaveData(DataType.Year, yearsSpinner.getSelectedItem(), position, id);
                UpdateModification();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private int GetPosition(Spinner spinner, String type)
    {
        int position = 0;
        if(ApplicationContext.Instance().DataManager().GetLiveData(type)!=null)
        {
            HashMap<Integer, String> data = (HashMap<Integer, String>)ApplicationContext.Instance().DataManager().GetLiveData(type);
            position = (int)data.keySet().toArray()[0];
        }

        int result = position > (spinner.getCount()-1) ? 0 : position;
        return result;
    }



    private void SaveData(String type, Object data, int position, long id)
    {
        HashMap<Integer, ArrayList<Object>> savedObject = new HashMap<Integer, ArrayList<Object>>();

        int pos = position;
        String name = "";
        SQLiteCursor cursor;
        switch (type)
        {
            case DataType.Brand:
                cursor = (SQLiteCursor)data;
                name = cursor.getString(cursor.getColumnIndex(AsistesDataBaseContract.BrandEntry.COLUMN_NAME_TITLE));
                break;

            case DataType.Model:
                cursor = (SQLiteCursor)data;
                name = cursor.getString(cursor.getColumnIndex(AsistesDataBaseContract.ModelEntry.COLUMN_NAME_TITLE));
                break;

            case DataType.Modification:
                cursor = (SQLiteCursor)data;
                name = cursor.getString(cursor.getColumnIndex(AsistesDataBaseContract.ModificationEntry.COLUMN_NAME_TITLE));
                break;

            case DataType.Year:
                name = (String) data;
                break;
        }

        ArrayList<Object> values = new ArrayList<Object>();
        values.add(id);
        values.add(name);

        savedObject.put(pos, values);
        ApplicationContext.Instance().DataManager().SetLiveData(type,savedObject);
    }


}


