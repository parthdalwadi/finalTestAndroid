package com.example.parth_c0766346_feandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "PHONEBOOK";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "CONTACTS";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FNAME = "FIRST_NAME";
    public static final String COLUMN_LNAME = "LAST_NAME";
    public static final String COLUMN_PHONE = "PHONE";
    public static final String COLUMN_ADDRESS = "ADDRESS" ;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT contact_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FNAME + " varchar(200) NOT NULL, " +
                COLUMN_LNAME + " varchar(200) NOT NULL, " +
                COLUMN_PHONE + " varchar(200) NOT NULL, " +
                COLUMN_ADDRESS + " varchar(200) NOT NULL);";

        db.execSQL(sql);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }


    boolean addContact(String fname, String lname, String phone, String address){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FNAME, fname);
        cv.put(COLUMN_LNAME, lname);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_ADDRESS, address);
        return sqLiteDatabase.insert(TABLE_NAME, null, cv) != -1;
    }

    Cursor getAllContacts(){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * from " + TABLE_NAME, null);
    }


    boolean editContact(int id, String fname, String lname, String phone, String address){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FNAME, fname);
        cv.put(COLUMN_LNAME, lname);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_ADDRESS, address);

        return sqLiteDatabase.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0 ;

    }


    boolean deleteContact(int id){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0 ;

    }
}
