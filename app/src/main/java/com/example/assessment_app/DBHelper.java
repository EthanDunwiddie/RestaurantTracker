package com.example.assessment_app;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DBHelper extends SQLiteAssetHelper {
    public static final String DB_NAME = "lunchClubTest2.db";
    public static final int DB_VERSION = 1;

    // no onCreate or onUpgrade methods needed as the database was created in DB browser and added into the assets folder

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

}
