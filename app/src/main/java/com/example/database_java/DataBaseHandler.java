package com.example.database_java;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "employeeManager";
    private static final String TABLE_EMPLOYEE = "employee";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_CITY = "city";

    SQLiteDatabase db;

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = " CREATE TABLE "  + TABLE_EMPLOYEE +
                " ( " + KEY_ID + " INTEGER PRIMARY KEY , " +
                 KEY_NAME + " TEXT, " +
                KEY_AGE + " TEXT, " +
                KEY_CITY + " TEXT " + " ) ";

        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "  + TABLE_EMPLOYEE);
        onCreate(db);
    }

    //Add Employee

    public Long addEmployee(String name , String age , String city)
    {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_AGE,age);
        cv.put(KEY_CITY,city);

        return  db.insert(TABLE_EMPLOYEE,null,cv);
    }

    // code to get the single employee

    public String getEmployee()
    {
        db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_EMPLOYEE , new String[]{KEY_ID, KEY_NAME, KEY_AGE, KEY_CITY},null,null,null,null,null);

        int eId = cursor.getColumnIndex(KEY_ID);
        int eName = cursor.getColumnIndex(KEY_NAME);
        int eAge = cursor.getColumnIndex(KEY_AGE);
        int eCity = cursor.getColumnIndex(KEY_CITY);

        String res = "";

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            res = res +
                    "Id: "+cursor.getString(eId) + "\n"+
                    "Name: "+cursor.getString(eName) + "\n"+
                    "Age: "+cursor.getString(eAge) + "\n"+
                    "City "+cursor.getString(eCity)+"\n\n";
        }
        db.close();
        return res;
    }

    //update Employee

    public void updateEmployee(long l , String name , String age , String city)
    {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_AGE,age);
        cv.put(KEY_CITY,city);

        db.update(TABLE_EMPLOYEE , cv , KEY_ID + " = " + l,null);
        db.close();
    }

    //delete Employee

    public void deleteEmployee(long l)
    {
        db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEE , KEY_ID + " = " + l,null);
    }

    //Name

    public String getName(long l1)
    {
        db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_EMPLOYEE , new String[]{KEY_ID,KEY_NAME,KEY_AGE,KEY_CITY},
                KEY_ID+ " = " + l1, null,null,null,null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            String name = cursor.getString(1);
            return name;
        }
        return  null;
    }

    //Age

    public String getAge(long l1)
    {
        db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_EMPLOYEE , new String[]{KEY_ID,KEY_NAME,KEY_AGE,KEY_CITY},
                KEY_ID+ " = " + l1, null,null,null,null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            String age = cursor.getString(2);
            return age;
        }
        return  null;
    }

    //Ciry

    public String getCity(long l1)
    {
        db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_EMPLOYEE , new String[]{KEY_ID,KEY_NAME,KEY_AGE,KEY_CITY},
                KEY_ID + " = " + l1, null,null,null,null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            String city = cursor.getString(3);
            return city;
        }
        return  null;
    }

}
