package com.example.tltdd_tuan7.Adapter;

import android.content.ContentValues;
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
        String sql = "INSERT INTO USER VALUES(null,?,?,?,?,?,?,?,?,?,?)";
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
        statement.bindLong(10,0);

        statement.executeInsert();
    }
    public Boolean update_img(User us){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sdt",us.getSdt());
        contentValues.put("email",us.getEmail());
        contentValues.put("cmnd",us.getCmnd());
        contentValues.put("ngaysinh",us.getNgaysinh());
        contentValues.put("diachi",us.getDiachi());
        contentValues.put("avata",us.getImage());
        contentValues.put("mk",us.getPassword());
        database.update("user",contentValues,"tk = ?",new String[]{us.getUsername()});
        return true;

//        String sql = "update user set sdt = ? , email = ? , cmnd = ? ,ngaysinh = ?, diachi = ?, avata = ? where tk = ?";
//        SQLiteStatement statement = database.compileStatement(sql);
//        statement.clearBindings();
//        statement.bindString(1,us.getSdt());
//        statement.bindString(2,us.getEmail());
//        statement.bindString(3,us.getNgaysinh());
//        statement.bindString(4,us.getDiachi());
//        statement.bindBlob(5,us.getImage());
//        statement.bindString(6,us.getUsername());


//        statement.executeUpdateDelete();
    }
    public Cursor GetData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String sql = "CREATE TABLE user(id text primary key, name text not null)";
//        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
