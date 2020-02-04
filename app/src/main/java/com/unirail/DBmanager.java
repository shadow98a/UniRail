package com.unirail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBmanager extends SQLiteOpenHelper {

    public static volatile DBmanager db;

    public DBmanager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBmanager(@Nullable Context context) {
        super(context,"routestation.db", null, 1);
    }

    public static DBmanager getInstance(Context context) {
        if (db == null) {
            synchronized (DBmanager.class) {
                if (db == null) {
                    db = new DBmanager(context);
                }
            }
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ROUTESTATION("+"TIME STRING" +
                ",STARTSTATION STRING" +
                ",FINALSTATION STRING" +
                ",STARTLINENUM STRING" +
                ",STARTRAILLINKLISTNUM STRING" +
                ",MIDDLESTATION0 STRING" +
                ",MIDDLESTATION1 STRING" +
                ",MIDDLESTATION2 STRING" +
                ",MIDDLELINENUM0 STRING" +
                ",MIDDLELINENUM1 STRING" +
                ",MIDDLELINENUM2 STRING" +
                ",MIDDLERAILLINKLISTNUM0 STRING" +
                ",MIDDLERAILLINKLISTNUM1 STRING" +
                ",MIDDLERAILLINKLISTNUM2 STRING" +
                " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(String data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("STARTSTATION",data);
        db.insert("ROUTESTATION",null,values);
        db.close();
    }

    public String getData(){
        String s = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT STARTSTATION FROM ROUTESTATION", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            s = cursor.getString(0);
            System.out.println(s);
            cursor.moveToNext();
        }
        cursor.close();
        return s;
    }

}
