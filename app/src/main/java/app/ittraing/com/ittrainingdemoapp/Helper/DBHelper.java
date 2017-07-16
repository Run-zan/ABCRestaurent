package app.ittraing.com.ittrainingdemoapp.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by ranja_000 on 6/26/2017.
 */

//https://www.tutorialspoint.com/android/android_sqlite_database.htm

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myDatabase.db";
    public static final String TABLE_NAME = "personal_info";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    private HashMap hashMap;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.hashMap = hashMap;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table personal_info"+("id integer primary key, name text, email text "));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table personal_info");
        onCreate(db);
    }

    public boolean insertInfo(String name, String email){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        sqLiteDatabase.insert("personal_info", null, contentValues);
        return true;
    }

    public Cursor getData(int id){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select *from personal_info where id = "+id, null);
        return cursor;
    }
}
