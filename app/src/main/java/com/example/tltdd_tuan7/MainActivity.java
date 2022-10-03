package com.example.tltdd_tuan7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tltdd_tuan7.Class.BTP;

public class MainActivity extends AppCompatActivity {
    Button btl,btpr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nameh = (TextView) findViewById(R.id.nameh);
        nameh.setText(BTP.user.getTen());
        btl = (Button) findViewById(R.id.btchuyenlist);
        btpr = (Button) findViewById(R.id.btchuyenprofile);
        btl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,List.class);
                startActivity(intent);
            }
        });
        btpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Profile.class);
                startActivity(intent);
            }
        });

    }
}