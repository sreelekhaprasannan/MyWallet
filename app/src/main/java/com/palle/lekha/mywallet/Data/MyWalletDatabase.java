package com.palle.lekha.mywallet.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.palle.lekha.mywallet.Model.ViewExpense;
import com.palle.lekha.mywallet.Model.ViewIncome;

import java.util.ArrayList;

/**
 * Created by Administrator on 29-09-2016.
 */

public class MyWalletDatabase {
    private SQLiteDatabase database;
    private MyWalletDbHelper helper;

    public MyWalletDatabase(Context context) {
        helper = new MyWalletDbHelper(context);
    }

    private SQLiteDatabase openReadableDatabaseInstance() {
        return helper.getReadableDatabase();
    }

    private SQLiteDatabase openWritableDatabaseInstance() {
        return helper.getWritableDatabase();
    }

    private void closeDatabaseConnection() {
        database.close();
        helper.close();
    }


    public boolean checkIfUsernameAndPasswordExist(String username, String password) {
        database = openReadableDatabaseInstance();

        String selection = MyWalletContract.LoginDetailsEntry.COLUMN_USERNAME + " = ? AND " + MyWalletContract.LoginDetailsEntry.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = database.query(MyWalletContract.LoginDetailsEntry.TABLE_NAME, null, selection,
                selectionArgs, null, null, null);

        boolean userExists = false;

        if (cursor.moveToFirst()) {
            userExists = true;
        } else {
            userExists = false;
        }

        closeDatabaseConnection();

        return userExists;
    }



    public boolean checkIfUsernameExists(String username) {
        database = openReadableDatabaseInstance();

        String selection = MyWalletContract.LoginDetailsEntry.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};
//        Cursor cursor=database.rawQuery("SELECT username FROM login_details",null);

        Cursor cursor = database.query(MyWalletContract.LoginDetailsEntry.TABLE_NAME, null, selection, selectionArgs, null, null, null);

        boolean userExists =false;

        if (cursor.moveToFirst()) {
            userExists = true;
        } else {
            userExists = false;
        }

        closeDatabaseConnection();

        return userExists;
    }


    public long createLoginDetails(String username, String password) {

        database = openWritableDatabaseInstance();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyWalletContract.LoginDetailsEntry.COLUMN_USERNAME, username);
        contentValues.put(MyWalletContract.LoginDetailsEntry.COLUMN_PASSWORD, password);


        long value = database.insert(MyWalletContract.LoginDetailsEntry.TABLE_NAME, null, contentValues);

        closeDatabaseConnection();

        return value;

    }

    public long createIncomeDetails(String date, String category,String description,int amount) {

        database = openWritableDatabaseInstance();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyWalletContract.IncomeEntry.COLUMN_INCOME_DATE,""+date);
        contentValues.put(MyWalletContract.IncomeEntry.COLUMN_INCOME_CATEGORY,""+category);
        contentValues.put(MyWalletContract.IncomeEntry.COLUMN_INCOME_DESCRIPTION,""+description);
        contentValues.put(MyWalletContract.IncomeEntry.COLUMN_INCOME_AMOUNT,""+amount);


        long value = database.insert(MyWalletContract.IncomeEntry.TABLE_NAME, null, contentValues);

        closeDatabaseConnection();

        return value;

    }


    public long createExpenseDetails(String date, String category,String description,int amount) {

        database = openWritableDatabaseInstance();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_DATE,""+date);
        contentValues.put(MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_CATEGORY,""+category);
        contentValues.put(MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_DESCRIPTION,""+description);
        contentValues.put(MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_AMOUNT,""+amount);


        long value = database.insert(MyWalletContract.ExpenseEntry.TABLE_NAME, null, contentValues);

        closeDatabaseConnection();

        return value;

    }



    public ArrayList<ViewIncome> getAllIncomeDetails() {

        database = openReadableDatabaseInstance();

        Cursor c = database.query(MyWalletContract.IncomeEntry.TABLE_NAME, null, null, null, null, null, null);

        ArrayList<ViewIncome> arrayList = new ArrayList<>();

        if(c.moveToFirst()){
            do{
                int id = c.getInt(c.getColumnIndex(MyWalletContract.IncomeEntry._ID));
                String date = c.getString(c.getColumnIndex(MyWalletContract.IncomeEntry.COLUMN_INCOME_DATE));
                String category = c.getString(c.getColumnIndex(MyWalletContract.IncomeEntry.COLUMN_INCOME_CATEGORY));
                String description = c.getString(c.getColumnIndex(MyWalletContract.IncomeEntry.COLUMN_INCOME_DESCRIPTION));
                double amount = c.getDouble(c.getColumnIndex(MyWalletContract.IncomeEntry.COLUMN_INCOME_AMOUNT));

                ViewIncome viewIncome = new ViewIncome(id,date,category, description, amount);
                arrayList.add(viewIncome);

            }while (c.moveToNext());
        }

        return arrayList;
    }





    public ArrayList<ViewExpense> getAllExpenseDetails() {

        database = openReadableDatabaseInstance();

        Cursor c = database.query(MyWalletContract.ExpenseEntry.TABLE_NAME, null, null, null, null, null, null);

        ArrayList<ViewExpense> arrayList = new ArrayList<>();

        if(c.moveToFirst()){
            do{
                int id = c.getInt(c.getColumnIndex(MyWalletContract.ExpenseEntry._ID));
                String date = c.getString(c.getColumnIndex(MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_DATE));
                String category = c.getString(c.getColumnIndex(MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_CATEGORY));
                String description = c.getString(c.getColumnIndex(MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_DESCRIPTION));
                double amount = c.getDouble(c.getColumnIndex(MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_AMOUNT));

                ViewExpense viewExpense = new ViewExpense(id,date,category, description, amount);
                arrayList.add(viewExpense);

            }while (c.moveToNext());
        }

        return arrayList;
    }

    public long createCategoryDetails(String category,String type) {
        database= openWritableDatabaseInstance();

        ContentValues contentValues= new ContentValues();
        contentValues.put(MyWalletContract.CategoryEntry.COLUMN_CATEGORY_CATEGORYNAME,""+category);
        contentValues.put(MyWalletContract.CategoryEntry.COLUMN_CATEGORY_TYPE,""+type);

        long value =database.insert(MyWalletContract.CategoryEntry.TABLE_NAME,null,contentValues);

        closeDatabaseConnection();

        return value;
    }

    public ArrayList<String> getAllExpenseCategory() {
        database = openReadableDatabaseInstance();
        ArrayList<String> categoryList= new ArrayList<>();

        String[] projection={MyWalletContract.CategoryEntry.COLUMN_CATEGORY_CATEGORYNAME};
        //String selection=MyWalletContract.CategoryEntry.COLUMN_CATEGORY_TYPE+">?";
       // String[] selectionArgs={"Expense"};
        String selctQuery="SELECT "+ MyWalletContract.CategoryEntry.COLUMN_CATEGORY_CATEGORYNAME+" FROM "
                + MyWalletContract.CategoryEntry.TABLE_NAME+" WHERE "+ MyWalletContract.CategoryEntry.COLUMN_CATEGORY_TYPE+"='Expense';";

        Cursor c=database.rawQuery(selctQuery,null);

        if (c.moveToFirst()){
            do{
                String category=c.getString(0);
                categoryList.add(category);

            }while (c.moveToNext());

            closeDatabaseConnection();
        }

        return categoryList;
    }

    public ArrayList<String> getAllIncomeCategory() {
        database = openReadableDatabaseInstance();
        ArrayList<String> categoryList= new ArrayList<>();


        String selctQuery="SELECT "+ MyWalletContract.CategoryEntry.COLUMN_CATEGORY_CATEGORYNAME+" FROM "
                + MyWalletContract.CategoryEntry.TABLE_NAME+" WHERE "+ MyWalletContract.CategoryEntry.COLUMN_CATEGORY_TYPE+"='Income';";

        Cursor c=database.rawQuery(selctQuery,null);

        if (c.moveToFirst()){
            do{
                String category=c.getString(0);
                categoryList.add(category);

            }while (c.moveToNext());

            closeDatabaseConnection();
        }

        return categoryList;
    }


    private class MyWalletDbHelper extends SQLiteOpenHelper {

        //region SQL Statements
        private static final String SQL_CREATE_INCOME_DETAILS_TABLE = "CREATE TABLE " + MyWalletContract.IncomeEntry.TABLE_NAME + "("
                + MyWalletContract.IncomeEntry._ID + " INTEGER PRIMARY KEY,"
                + MyWalletContract.IncomeEntry.COLUMN_INCOME_DATE + " TEXT NOT NULL,"
                + MyWalletContract.IncomeEntry.COLUMN_INCOME_CATEGORY + " TEXT NOT NULL,"
                + MyWalletContract.IncomeEntry.COLUMN_INCOME_DESCRIPTION + " TEXT,"
                + MyWalletContract.IncomeEntry.COLUMN_INCOME_AMOUNT + " INTEGER NOT NULL);";

        private static final String SQL_DROP_INCOME_DETAILS_TABLE = "DROP TABLE " + MyWalletContract.IncomeEntry.TABLE_NAME + ";";


        private static final String SQL_CREATE_LOGIN_DETAILS_TABLE = "CREATE TABLE " + MyWalletContract.LoginDetailsEntry.TABLE_NAME + "("
                + MyWalletContract.LoginDetailsEntry.COLUMN_USERNAME + " TEXT NOT NULL,"
                + MyWalletContract.LoginDetailsEntry.COLUMN_PASSWORD + " TEXT NOT NULL);";

        private static final String SQL_DROP_LOGIN_DETAILS_TABLE = "DROP TABLE " + MyWalletContract.LoginDetailsEntry.TABLE_NAME + ";";



        private static final String SQL_CREATE_EXPENSE_DETAILS_TABLE = "CREATE TABLE " + MyWalletContract.ExpenseEntry.TABLE_NAME + "("
                + MyWalletContract.ExpenseEntry._ID+" INTEGER PRIMARY KEY,"
                + MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_DATE+" TEXT NOT NULL,"
                + MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_CATEGORY+ " TEXT NOT NULL,"
                + MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_DESCRIPTION+ " TEXT,"
                + MyWalletContract.ExpenseEntry.COLUMN_EXPENSE_AMOUNT+ " INTEGER NOT NULL);";

        private static final String SQL_DROP_EXPENSE_DETAILS_TABLE = "DROP TABLE " + MyWalletContract.ExpenseEntry.TABLE_NAME + ";";





        private static final String SQL_CREATE_CATEGORY_DETAILS_TABLE = "CREATE TABLE " + MyWalletContract.CategoryEntry.TABLE_NAME + "("
                + MyWalletContract.CategoryEntry._ID + " INTEGER PRIMARY KEY,"
                + MyWalletContract.CategoryEntry.COLUMN_CATEGORY_CATEGORYNAME + " TEXT NOT NULL,"
                + MyWalletContract.CategoryEntry.COLUMN_CATEGORY_TYPE + " TEXT NOT NULL);";


        private static final String SQL_DROP_category_DETAILS_TABLE = "DROP TABLE " + MyWalletContract.CategoryEntry.TABLE_NAME + ";";





        //endregion

        private static final String DATABASE_NAME = "MyWallet.db";

        private static final int DATABASE_VERSION = 1;

        public MyWalletDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }





        @Override
        public void onCreate(SQLiteDatabase db) {

//
            db.execSQL(SQL_CREATE_LOGIN_DETAILS_TABLE);
            db.execSQL(SQL_CREATE_INCOME_DETAILS_TABLE);
            db.execSQL(SQL_CREATE_EXPENSE_DETAILS_TABLE);
            db.execSQL(SQL_CREATE_CATEGORY_DETAILS_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


            if (newVersion > oldVersion) {
                db.execSQL(SQL_DROP_LOGIN_DETAILS_TABLE);
                db.execSQL(SQL_DROP_INCOME_DETAILS_TABLE);
                db.execSQL(SQL_DROP_EXPENSE_DETAILS_TABLE);
                db.execSQL(SQL_DROP_category_DETAILS_TABLE);
            }
                onCreate(db);
        }
    }

}
