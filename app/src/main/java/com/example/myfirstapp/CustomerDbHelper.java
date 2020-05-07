package com.example.myfirstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomerDbHelper extends SQLiteOpenHelper {
    public CustomerDbHelper(Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        int rows = db.delete("customer", null, null);
//        System.out.println("number of rows deleted in CustomerDbHelper onCreate " + rows);
//        db.execSQL("drop table if exists customer");
        db.execSQL("create table if not exists customer (email text, password text, firstName text, lastName text, gender text, dateOfBirth text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists customer");
    }
}
