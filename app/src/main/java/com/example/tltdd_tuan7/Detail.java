package com.example.tltdd_tuan7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tltdd_tuan7.Class.Items;
import com.example.tltdd_tuan7.Class.User;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView tv = (TextView) findViewById(R.id.detailtitle);
        TextView tv1 = (TextView) findViewById(R.id.motadetail);
        ImageView imgv = (ImageView) findViewById(R.id.imgviewdetail);
        Intent intent1= getIntent();
        Items item = (Items) intent1.getSerializableExtra("dulieu");
        if (item.getImg()!=0)
            imgv.setImageResource(item.getImg());
        else{
            InputStream is = new ByteArrayInputStream(item.getAnh());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            imgv.setImageBitmap(bitmap);
        }

        tv.setText(item.getTen());
        tv1.setText(item.getMota());
    }
}