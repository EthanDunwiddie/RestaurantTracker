package com.example.assessment_app;

import android.provider.BaseColumns;

public class UserContract {

    private UserContract() {}

    public static final class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_FULLNAME = "fullname";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PWD = "pwd";
    }

    public static final class MealEntry implements BaseColumns{
        public static final String TABLE_NAME1 = "pastMeals";
        public static final String COLUMN_MEAL = "meal";
        public static final String COLUMN_FLAVOUR = "flavourScore";
        public static final String COLUMN_PRICE = "priceScore";
        public static final String COLUMN_AVERAGE = "averageScore";
    }

}
