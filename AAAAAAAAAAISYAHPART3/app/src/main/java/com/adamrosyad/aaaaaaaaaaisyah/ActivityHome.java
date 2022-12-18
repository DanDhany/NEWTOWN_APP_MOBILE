package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityHome extends AppCompatActivity {
    Button btnmaster, btntransaksi, btnlaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnmaster = (Button)findViewById(R.id.btnmaster);
        btntransaksi = (Button)findViewById(R.id.btntransaksi);
        btnlaporan = (Button)findViewById(R.id.btnlaporan);

        btnmaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, ActivityMaster.class);
                startActivity(intent);
            }
        });
        btntransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, ActivityTransaksi.class);
                startActivity(intent);
            }
        });
        btnlaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, ActivityLaporan.class);
                startActivity(intent);
            }
        });


    }
}
