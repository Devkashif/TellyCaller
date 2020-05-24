package com.example.tellycaller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "customer.db";
    public static final String TABLE_NAME = "NCustomer";
    public static final String COL1 ="ID";
    public static final String COL2 = "Cname";
    public static final String COL3 = "Cmobile";
    public static final String COL4 = "Cemail";
    public static final String COL5 = "Caddress";


    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL1 + " INTEGER PRIMARY KEY," + COL2
                + " TEXT," + COL3 + " TEXT," + COL4 + " TEXT," +COL5 + " TEXT " + ");";

        sqLiteDatabase.execSQL(CREATE_CUSTOMER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
//    public boolean addData(String item){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL2,item);
//        contentValues.put(COL3,item);
//        contentValues.put(COL4,item);
//        contentValues.put(COL5,item);
//
//        Log.e("DATA","AddDAta: AddingDAta"+item+"to"+TABLE_NAME);
//
//        long result = db.insert(TABLE_NAME,null,contentValues);
//        if (result == -1){
//            return false;
//        }else {
//            return true;
//        }
//    }


}
