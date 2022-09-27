package com.example.tltdd_tuan7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tltdd_tuan7.Class.BTP;
import com.example.tltdd_tuan7.Class.User;

public class Signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        User duser = new User("Long","29/06/02","0123456789","levanlong@gmail.com","012345678","admin","admin");
        BTP.userList.add(duser);
        Button bttonLogin = (Button) findViewById(R.id.btlogin);
        EditText usernameLogin = (EditText) findViewById(R.id.usernamelogin);
        EditText passLogin = (EditText) findViewById(R.id.passLogin);
        TextView linkdk = (TextView) findViewById(R.id.linkdk);
        bttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(User us : BTP.userList)
                    if (us.getUsername().equals(usernameLogin.getText().toString().trim()) && us.getPassword().equals(passLogin.getText().toString().trim()))
                    {
                        BTP.user=us;
                        Intent intent = new Intent(Signin.this, MainActivity.class);
                        startActivity(intent);
                    }
                Toast.makeText(Signin.this,"Sai thông tin",Toast.LENGTH_SHORT).show();
            }
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