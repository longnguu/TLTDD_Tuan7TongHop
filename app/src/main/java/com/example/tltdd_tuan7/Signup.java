package com.example.tltdd_tuan7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tltdd_tuan7.Class.BTP;
import com.example.tltdd_tuan7.Class.User;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Signup extends AppCompatActivity {
    private EditText namedk,userdk,passdk,nsdk,cmnddk,sdtdk,emaildk,dcdk;
    private Button btdk;
    private User us;
    public MainActivity mainActivity;
    private ImageView imageView;
    Uri uriimgt;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        LinearLayout linkback = (LinearLayout) findViewById(R.id.linkbackdk);
        Button btchonanh = (Button) findViewById(R.id.btchonanhsu);
        imageView = (ImageView) findViewById(R.id.imgthemsu);
        btchonanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CapQuyenCA();
            }
        });
        AnhXa();
        btdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                us=new User(namedk.getText().toString(),nsdk.getText().toString(),sdtdk.getText().toString(),emaildk.getText().toString(),cmnddk.getText().toString(),userdk.getText().toString(),passdk.getText().toString());
                Cursor cursorr = Signin.database.GetData("Select tk from user where tk ='"+us.getUsername()+"'");
                if (cursorr.getCount()==0){
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                    byte[] hinhAnh = byteArrayOutputStream.toByteArray();
                    hinhAnh=imagemTratada(hinhAnh);
                    us.setImage(hinhAnh);
                    us.setDiachi(dcdk.getText().toString());
                    Signin.database.insert_img(us);
                    BTP.userList.add(us);
                    Intent intent = new Intent(Signup.this,Signin.class);
                    startActivity(intent);
                }else Toast.makeText(Signup.this,"Đã tồn tại",Toast.LENGTH_SHORT).show();

            }
        });
        linkback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this,Signin.class);
                startActivity(intent);
            }
        });
    }

    private void CapQuyenCA() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                OpenImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(Signup.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private void OpenImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Title"),1);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            uriimgt=data.getData();
            bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriimgt);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);


        }
    }
    private void AnhXa(){
        namedk = (EditText) findViewById(R.id.namedk);
        userdk = (EditText) findViewById(R.id.usernamedk);
        passdk = (EditText) findViewById(R.id.passdk);
        nsdk = (EditText) findViewById(R.id.nsdk);
        cmnddk = (EditText) findViewById(R.id.cmnddk);
        sdtdk = (EditText) findViewById(R.id.sdtdk);
        emaildk = (EditText) findViewById(R.id.emaildk);
        btdk = (Button) findViewById(R.id.btdk);
        dcdk =(EditText) findViewById(R.id.diachidk);
    }
    private byte[] imagemTratada(@NonNull byte[] imagem_img){

        while (imagem_img.length > 500000){
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem_img, 0, imagem_img.length);
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imagem_img = stream.toByteArray();
        }
        return imagem_img;

    }

}