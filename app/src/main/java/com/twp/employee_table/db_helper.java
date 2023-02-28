package com.twp.employee_table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class db_helper extends SQLiteOpenHelper {
    private static String db_name = "employee_db";
    private static String tb_name = "employees";
    private static int ver = 1;


    public db_helper(@Nullable Context context) {

        super(context, db_name, null, ver);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table employees (id PRIMARY KEY , name text,code int,desgination text, salary text)");


    }

    public void insertEmployee(String name, int code, String design, String salary, Context ctx) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("code", code);
        contentValues.put("desgination", design);
        contentValues.put("salary", salary);
        SQLiteDatabase db2 = getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db2, "employees", "name=?", new String[]{name});
        Log.d("TAG", "insertEmployee: " + count);

        if (count > 0) {
            Toast.makeText(ctx, "RECORD EXISTS", Toast.LENGTH_SHORT).show();
        } else {
            db.insertOrThrow("employees", null, contentValues);
        }
    }

    public void updateEmployee(String old_name, String name, int code, String design, String salary, Context ctx) {

        SQLiteDatabase db2 = getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db2, "employees", "name=?", new String[]{old_name});

        if (count > 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("code", code);
            contentValues.put("desgination", design);
            contentValues.put("salary", salary);
            db.update("employees", contentValues, "name=?", new String[]{old_name});
            Toast.makeText(ctx, "RECORD UPDATED", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ctx, "RECORD DOESN'T EXISTS", Toast.LENGTH_SHORT).show();

        }
    }
    public void initdb(){
        SQLiteDatabase db2 = getWritableDatabase();
    }

    public void delete(String name, Context ctx) {
        SQLiteDatabase db2 = getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db2, "employees", "name=?", new String[]{name});

        if (count > 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("employees", "name=?", new String[]{name});

            Toast.makeText(ctx, "RECORD DELETED", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ctx, "RECORD DOESN'T EXISTS", Toast.LENGTH_SHORT).show();

        }


    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from employees", null);

        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tb_name);
        onCreate(sqLiteDatabase);
    }
}
