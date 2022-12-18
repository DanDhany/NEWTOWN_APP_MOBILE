package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMaster extends AppCompatActivity {

    Button btnuser, btnsup, btnbarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        btnuser = (Button)findViewById(R.id.btnuser);
        btnsup = (Button)findViewById(R.id.btnsup);
        btnbarang = (Button)findViewById(R.id.btnbarang);

        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(ActivityMaster.this, ActivityTampilUser.class);
                startActivity(myintent);
            }
        });

        btnbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(ActivityMaster.this, ActivityTampilBarang.class);
                startActivity(myintent);
            }
        });
    }
}
