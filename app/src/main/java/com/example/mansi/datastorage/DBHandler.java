package com.example.mansi.datastorage;

/**
 * Created by MANSI on 02-10-2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.camera2.params.StreamConfigurationMap;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentRecords";
    private static final String TABLE_RECORDS = "records";
    private static final String KEY_ID = "id";
    private static final String KEY_ROLL = "roll";
    private static final String KEY_NAME = "name";
    private static final String KEY_STREAM = "stream";
    private static final String KEY_CGPA = "cgpa";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECORDS_TABLE = "CREATE TABLE " + TABLE_RECORDS + "("
        + KEY_ROLL + " TEXT PRIMARY KEY" + KEY_NAME + " TEXT,"
        + KEY_STREAM + " TEXT," + KEY_CGPA + "TEXT" + ")";
        db.execSQL(CREATE_RECORDS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        onCreate(db);
    }

    public void addRecord(Records record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ROLL, record.getRoll());
        values.put(KEY_NAME, record.getName());
        values.put(KEY_STREAM, record.getStream());
        values.put(KEY_CGPA, record.getCgpa());
        db.insert(TABLE_RECORDS, null, values);
        db.close();
    }

    public Records getRecord(String roll) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RECORDS, new String[] { KEY_ROLL,
                        KEY_NAME, KEY_STREAM, KEY_CGPA}, KEY_ROLL + "=?",
                new String[] { roll }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Records record = new Records(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return record;
    }

    public int updateRecord(Records record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CGPA, record.getCgpa());
        values.put(KEY_NAME, record.getName());
        values.put(KEY_STREAM, record.getStream());
        return db.update(TABLE_RECORDS, values, KEY_ROLL + " = ?",
                new String[]{String.valueOf(record.getRoll())});
    }

    public void deleteRecord(Records record) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECORDS, KEY_ROLL + " = ?",
                new String[] { String.valueOf(record.getRoll()) });
        db.close();
    }


}

