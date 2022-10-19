package com.example.tltdd_tuan7.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.tltdd_tuan7.Class.User;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //query
    public void QueryData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void insert_img(User us){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO USER VALUES(null,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,us.getTen());
        statement.bindString(2,us.getNgaysinh());
        statement.bindString(3,us.getSdt());
        statement.bindString(4,us.getEmail());
        statement.bindString(5,us.getCmnd());
        statement.bindString(6,us.getUsername());
        statement.bindString(7,us.getPassword());
        statement.bindString(8,us.getDiachi());
        statement.bindBlob(9,us.getImage());

        statement.executeInsert();
    }
    public Cursor GetData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE user(id text primary key, name text not null)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
