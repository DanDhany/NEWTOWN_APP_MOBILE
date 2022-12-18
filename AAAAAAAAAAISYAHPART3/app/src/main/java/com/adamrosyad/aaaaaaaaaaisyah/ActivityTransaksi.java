package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityTransaksi extends AppCompatActivity {
    Button btnbeli, btnjual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        btnbeli = (Button)findViewById(R.id.btnbeli);
        btnjual = (Button)findViewById(R.id.btnjual);


        btnbeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTransaksi.this, ActivityTransBeli_Proto.class);
                startActivity(intent);
            }
        });
        btnjual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTransaksi.this, ActivityTransJual_Proto.class);
                startActivity(intent);
            }
        });
    }
}
