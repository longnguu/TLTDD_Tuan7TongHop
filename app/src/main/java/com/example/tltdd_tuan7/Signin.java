package com.example.tltdd_tuan7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tltdd_tuan7.Adapter.Database;
import com.example.tltdd_tuan7.Class.BTP;
import com.example.tltdd_tuan7.Class.User;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.Executor;

public class Signin extends AppCompatActivity {
    boolean kt=true;
    public static Database database;
    BiometricPrompt biometricPrompt;
    ConstraintLayout mMainLayout;
    BiometricPrompt.PromptInfo promptInfo;
    ImageView fingerprint ;
    EditText passLogin,usernameLogin;
    SharedPreferences sharedPreferences;
    CheckBox checkBox;
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
        sharedPreferences = getSharedPreferences("Datalogin",MODE_PRIVATE);
        fingerprint = (ImageView) findViewById(R.id.fingerprint);
        usernameLogin = (EditText) findViewById(R.id.usernamelogin);
        passLogin = (EditText) findViewById(R.id.passLogin);
        checkBox = (CheckBox) findViewById(R.id.rememberCB);
        if (sharedPreferences.getBoolean("checked",false)==true){
            usernameLogin.setText(sharedPreferences.getString("taikhoan",""));
            passLogin.setText(sharedPreferences.getString("matkhau",""));
            checkBox.setChecked(true);
        }else checkBox.setChecked(false);

        ImageView imageView = (ImageView) findViewById(R.id.avtsi);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] hinhAnh = byteArrayOutputStream.toByteArray();
        imagemTratada(hinhAnh);


        fingerprint.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"Range", "SuspiciousIndentation"})
            @Override
            public void onClick(View view) {
                if (usernameLogin.getText().toString().trim()!=""){
                    Cursor cursor = database.GetData("Select checkfinger from user where tk = '"+usernameLogin.getText().toString().trim()+"' limit 1");
                    if (cursor.getCount()>0)
                    while (cursor.moveToNext())
                    { if (cursor.getLong(cursor.getColumnIndex("checkfinger"))==1)
                            fingerPrintCheck();
                        else
                    {
                        Dialog dialog = new Dialog(Signin.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_custom2);
                        dialog.show();
                        Button btok = (Button) dialog.findViewById(R.id.okfingercheck);
                        Button btcancel = (Button) dialog.findViewById(R.id.cancelfingercheck);
                        EditText passfingercheck = (EditText) dialog.findViewById(R.id.passfingercheck);
                        btcancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        btok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Cursor cursor = database.GetData("Select checkfinger from user where tk = '"+usernameLogin.getText().toString().trim()+"' and mk='"+passfingercheck.getText().toString().trim()+ "' limit 1");
                                System.out.println(cursor.getColumnIndex("ten")+"\n"+cursor.getColumnIndex("sdt")+"\n"+cursor.getColumnIndex("tk")+"\n"+cursor.getColumnIndex("mk")+"\n"+cursor.getColumnIndex("diachi")+"\n");
                                if (cursor.getCount()>0)
                                    while (cursor.moveToNext()) {
                                        database.QueryData("update user set checkfinger = 1 where tk = '" + usernameLogin.getText().toString().trim() + "' and mk='" + passfingercheck.getText().toString().trim() + "'");
                                    fingerPrintCheck();
                                    }
                                    else
                                    Toast.makeText(getApplicationContext(),"Sai thông tin",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Không tìm thấy username",Toast.LENGTH_SHORT).show();
                }

                else
                Toast.makeText(getApplicationContext(),"Vui lòng nhập username",Toast.LENGTH_SHORT).show();
            }
        });




        database = new Database(this,"DemoSQL.sqlite",null,1);
        //database.QueryData("drop table user");
        database.QueryData("CREATE TABLE IF NOT EXISTS User(id integer primary key autoincrement,ten varchar(100),ngaysinh varchar(100),sdt varchar(20),email varchar(100),cmnd varchar(20),tk varchar(100),mk varchar(100),diachi varchar(100),avata blob,checkfinger int)");
        //database.QueryData("drop table sothic");
        database.QueryData("create table if not exists sothic(id integer primary key autoincrement,idus int, idst int, ten varchar(100),mota varchar(200),img blob)");
        User duser = new User("Long","29/06/02","0123456789","levanlong@gmail.com","012345678","admin","admin");
//        duser.setImage(hinhAnh);
        BTP.userList.add(duser);
        Button bttonLogin = (Button) findViewById(R.id.btlogin);
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
                        checkRemember();
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
                    {   kt=true;
                        Toast.makeText(Signin.this, "Đăng nhập thành công vào tài khoản :" + BTP.user.getUsername(), Toast.LENGTH_SHORT).show();

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

    private void fingerPrintCheck() {
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(getApplicationContext(),"Device Doesn't have fingerprint",Toast.LENGTH_SHORT).show();

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(getApplicationContext(),"Not working",Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(getApplicationContext(),"No finger",Toast.LENGTH_SHORT).show();
                break;
        }
        Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt= new BiometricPrompt(Signin.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);


            }

            @SuppressLint("Range")
            @Override
            public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Cursor cursor = database.GetData("Select * from user where tk = '"+usernameLogin.getText().toString().trim()+"' limit 1");
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
                if(kt==true)
                Toast.makeText(getApplicationContext(),"Sai username",Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(Signin.this, "Đăng nhập thành công vào tài khoản :" + BTP.user.getUsername(), Toast.LENGTH_SHORT).show();
                    kt=true;
                    checkRemember();
                }
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(),"Vân tay không hợp lệ",Toast.LENGTH_SHORT).show();
            }
        });
        promptInfo= new BiometricPrompt.PromptInfo.Builder().setTitle("Đăng nhập bằng vân tay").setDescription("Use fingerPrint to Login").setDeviceCredentialAllowed(true).build();
        biometricPrompt.authenticate(promptInfo);
    }

    private void checkRemember() {

        String us=usernameLogin.getText().toString().trim()+"";
        String pas= passLogin.getText().toString().trim()+"";
        if (checkBox.isChecked()) {
         //   Toast.makeText(Signin.this, "Đã lưu", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("taikhoan", us);
            editor.putString("matkhau", pas);
            editor.putBoolean("checked", true);
            editor.commit();
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
           // Toast.makeText(Signin.this, "không lưu", Toast.LENGTH_SHORT).show();
            editor.putString("taikhoan", us);
            editor.putString("matkhau", pas);
            editor.putBoolean("checked", false);
            editor.commit();
        }

    }
}