package com.slk.asistes.Data;

import android.provider.BaseColumns;

/**
 * Created by VIS on 23.10.2015.
 */
public final class AsistesDataBaseContract {
    public AsistesDataBaseContract(){}


    public static abstract class BrandEntry implements BaseColumns {
        public static final String TABLE_NAME = "brands";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_POPULAR = "popular";
    }

    public static abstract class ModelEntry implements BaseColumns {
        public static final String TABLE_NAME = "models";
        public static final String COLUMN_NAME_BRAND_ID = "brand_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CODE = "code";
    }


    public static abstract class ModificationEntry implements BaseColumns {
        public static final String TABLE_NAME = "modifications";
        public static final String COLUMN_NAME_BRANDS_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_POPULAR = "brand_id";
    }
}
