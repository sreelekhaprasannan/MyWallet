package com.palle.lekha.mywallet.Data;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 29-09-2016.
 */

public class MyWalletContract {

        public MyWalletContract() {
        }


    public static final class LoginDetailsEntry implements BaseColumns {
        public static final String TABLE_NAME = "login_details";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }


    public static class IncomeEntry implements BaseColumns {        //inner class

            public static final String TABLE_NAME= "income_details";
            public static final String COLUMN_INCOME_ID= "_id";
            public static final String COLUMN_INCOME_DATE= "date";
            public static final String COLUMN_INCOME_CATEGORY= "category";
            public static final String COLUMN_INCOME_DESCRIPTION="description";
            public static final String COLUMN_INCOME_AMOUNT= "amount";
        }


        public static class ExpenseEntry implements BaseColumns{
            public static final String TABLE_NAME= "expense_details";
            public static final String COLUMN_EXPENSE_ID= "_id";
            public static final String COLUMN_EXPENSE_DATE= "date";
            public static final String COLUMN_EXPENSE_CATEGORY= "category";
            public static final String COLUMN_EXPENSE_DESCRIPTION="description";
            public static final String COLUMN_EXPENSE_AMOUNT= "amount";
        }

    public static class CategoryEntry implements BaseColumns{
        public static final String TABLE_NAME= "category_details";
        public static final String COLUMN_CATEGORY_ID= "_id";
        public static final String COLUMN_CATEGORY_CATEGORYNAME= "category_name";
        public static final String COLUMN_CATEGORY_TYPE= "category_type";

    }


}
