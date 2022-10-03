package com.example.tltdd_tuan7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tltdd_tuan7.Class.BTP;
import com.example.tltdd_tuan7.Class.User;

public class Signup extends AppCompatActivity {
    private EditText namedk,userdk,passdk,nsdk,cmnddk,sdtdk,emaildk;
    private Button btdk;
    private User us;
    public MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        LinearLayout linkback = (LinearLayout) findViewById(R.id.linkbackdk);
        AnhXa();
        btdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                us=new User(namedk.getText().toString(),nsdk.getText().toString(),sdtdk.getText().toString(),emaildk.getText().toString(),cmnddk.getText().toString(),userdk.getText().toString(),passdk.getText().toString());
                BTP.userList.add(us);
                Intent intent = new Intent(Signup.this,Signin.class);
                startActivity(intent);
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
    private void AnhXa(){
        namedk = (EditText) findViewById(R.id.namedk);
        userdk = (EditText) findViewById(R.id.usernamedk);
        passdk = (EditText) findViewById(R.id.passdk);
        nsdk = (EditText) findViewById(R.id.nsdk);
        cmnddk = (EditText) findViewById(R.id.cmnddk);
        sdtdk = (EditText) findViewById(R.id.sdtdk);
        emaildk = (EditText) findViewById(R.id.emaildk);
        btdk = (Button) findViewById(R.id.btdk);
    }
}