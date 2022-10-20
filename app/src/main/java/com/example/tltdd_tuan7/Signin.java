package com.example.tltdd_tuan7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tltdd_tuan7.Adapter.Database;
import com.example.tltdd_tuan7.Class.BTP;
import com.example.tltdd_tuan7.Class.User;
import com.example.tltdd_tuan7.MainFragment.HomeFragment;
import com.example.tltdd_tuan7.MainFragment.ListFragment;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Signin extends AppCompatActivity {
    boolean kt=true;
    public static Database database;
    private byte[] imagemTratada(byte[] imagem_img){

        while (imagem_img.length > 500000){
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem_img, 0, imagem_img.length);
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imagem_img = stream.toByteArray();
        }
        return imagem_img;

    }

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ImageView imageView = (ImageView) findViewById(R.id.avtsi);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] hinhAnh = byteArrayOutputStream.toByteArray();
        imagemTratada(hinhAnh);

        database = new Database(this,"DemoSQL.sqlite",null,1);
        //database.QueryData("drop table user");
        database.QueryData("CREATE TABLE IF NOT EXISTS User(id integer primary key autoincrement,ten varchar(100),ngaysinh varchar(100),sdt varchar(20),email varchar(100),cmnd varchar(20),tk varchar(100),mk varchar(100),diachi varchar(100),avata blob)");
        //database.QueryData("drop table sothic");
        database.QueryData("create table if not exists sothic(id integer primary key autoincrement,idus int, idst int, ten varchar(100),mota varchar(200),img blob)");
        User duser = new User("Long","29/06/02","0123456789","levanlong@gmail.com","012345678","admin","admin");
//        duser.setImage(hinhAnh);
        BTP.userList.add(duser);
        Button bttonLogin = (Button) findViewById(R.id.btlogin);
        EditText usernameLogin = (EditText) findViewById(R.id.usernamelogin);
        EditText passLogin = (EditText) findViewById(R.id.passLogin);
        TextView linkdk = (TextView) findViewById(R.id.linkdk);
        bttonLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                Cursor cursor = database.GetData("Select * from user where tk = '"+usernameLogin.getText().toString().trim()+"' and mk='"+passLogin.getText().toString().trim()+"' limit 1");
                if (cursor.getCount()>0){
                    while (cursor.moveToNext()){
                        BTP.user.setTen(cursor.getString(cursor.getColumnIndex("ten")));
                        BTP.user.setCmnd(cursor.getString(cursor.getColumnIndex("cmnd")));
                        BTP.user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                        BTP.user.setUsername(cursor.getString(cursor.getColumnIndex("tk")));
                        BTP.user.setNgaysinh(cursor.getString(cursor.getColumnIndex("ngaysinh")));
                        BTP.user.setPassword(cursor.getString(cursor.getColumnIndex("mk")));
                        BTP.user.setSdt(cursor.getString(cursor.getColumnIndex("sdt")));
                        BTP.user.setImage(cursor.getBlob(cursor.getColumnIndex("avata")));
                        BTP.user.setDiachi(cursor.getString(cursor.getColumnIndex("diachi")));
                        kt=false;
                        Intent intent = new Intent(Signin.this, MainActivity.class);
                        startActivity(intent);
                }
                }
//                for(User us : BTP.userList)
//                    if (us.getUsername().equals(usernameLogin.getText().toString().trim()) && us.getPassword().equals(passLogin.getText().toString().trim()))
//                    {
//
//                    }

                    if (kt)
                        Toast.makeText(Signin.this, "Sai thông tin", Toast.LENGTH_SHORT).show();
                    else
                    {kt=true;
                        Toast.makeText(Signin.this, "Đăng nhập thành công vào tài khoản :" + BTP.user.getUsername(), Toast.LENGTH_SHORT).show();;

            }}
        });
        linkdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signin.this,Signup.class);
                startActivity(intent);
            }
        });
    }
}