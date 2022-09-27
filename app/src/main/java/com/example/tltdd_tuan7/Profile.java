package com.example.tltdd_tuan7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tltdd_tuan7.Class.BTP;

public class Profile extends AppCompatActivity {
    TextView namepf,sdtpf,usernamepf,emailpf,passpf,nspf;
    ImageView showpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AnhXa();
        namepf.setText(BTP.user.getTen());
        usernamepf.setText(BTP.user.getUsername());
        sdtpf.setText(BTP.user.getSdt());
        passpf.setText(BTP.user.getPassword());
        nspf.setText(BTP.user.getNgaysinh());
        emailpf.setText(BTP.user.getEmail());

    }
    private void AnhXa(){
        namepf = (TextView) findViewById(R.id.namepf);
        sdtpf = (TextView) findViewById(R.id.sdtpf);
        usernamepf = (TextView) findViewById(R.id.usernamepf);
        passpf = (TextView) findViewById(R.id.passpf);
        nspf = (TextView) findViewById(R.id.nspf);
        emailpf = (TextView) findViewById(R.id.emailpf);
    }
}