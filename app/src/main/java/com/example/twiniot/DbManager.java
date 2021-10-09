package com.example.twiniot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;







public class DbManager extends SQLiteOpenHelper {
    public static final String dbname = "data.db";

    public DbManager(@Nullable Context context) {
        super(context, dbname, null, 3);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String qry = "create table tbl_student(ID Integer primary key autoincrement,name text, balance interger,email text,ifsc text,account interger)";
        db.execSQL(qry);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_student");
        onCreate(db);

    }

    public String add(String p1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(" name",p1);



        long res = db.insert("tbl_student", null, cv);
        if (res == -1)
            return "false";
        else
            return "true";

    }

    //   we can create method by using cursor because cursor are used to fetch the data

    public Cursor fetch() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_student", null);

        return cursor;
    }



}


