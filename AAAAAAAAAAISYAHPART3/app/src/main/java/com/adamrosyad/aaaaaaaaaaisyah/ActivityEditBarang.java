package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityEditBarang extends AppCompatActivity {

    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    EditText kdbar, nambar, satuan, stok, hrgbeli, hrgjual;
    Button simpan, hapus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        kdbar = (EditText)findViewById(R.id.inputkdbar);
        nambar = (EditText)findViewById(R.id.inputnambar);
        satuan = (EditText)findViewById(R.id.inputsatuan);
        stok = (EditText)findViewById(R.id.inputstok);
        hrgbeli = (EditText)findViewById(R.id.inputhrgbeli);
        hrgjual = (EditText)findViewById(R.id.inputhrgjual);
        simpan = (Button) findViewById(R.id.btnsimpandata);
        hapus = (Button) findViewById(R.id.btnhapusdata);

        Intent intent = getIntent();
        String kd = intent.getStringExtra("sendkd");
        String nama = intent.getStringExtra("sendnama");
        String satu = intent.getStringExtra("sendsatu");
        String stokk = intent.getStringExtra("sendstok");
        String hbeli = intent.getStringExtra("sendhbeli");
        String hjual = intent.getStringExtra("sendhjual");

        kdbar.setText(kd);
        nambar.setText(nama);
        satuan.setText(satu);
        stok.setText(stokk);
        hrgbeli.setText(hbeli);
        hrgjual.setText(hjual);

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("DELETE FROM isibarang WHERE kdbar ='"+kdbar.getText().toString()+"';");
                kdbar.setText("");
                nambar.setText("");
                satuan.setText("");
                stok.setText("");
                hrgbeli.setText("");
                hrgjual.setText("");
                Toast.makeText(ActivityEditBarang.this, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setError();
                db.execSQL("UPDATE ISIBARANG SET NAMBAR = '" + nambar.getText().toString() + "', SATUAN = '" + satuan.getText().toString() + "', stok = '" + stok.getText().toString() + "', hrgbeli = '" + hrgbeli.getText().toString() + "',hrgjual = '" + hrgjual.getText().toString() + "' WHERE kdbar='" + kdbar.getText().toString() + "';");
                kdbar.setText("");nambar.setText("");satuan.setText("");stok.setText("");hrgbeli.setText("");hrgjual.setText("");
                Toast.makeText(ActivityEditBarang.this, "Data Berhasil Diubah", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    void setError(){
        if(kdbar.getText().toString().trim().equalsIgnoreCase("")){
            kdbar.setError("Kolom Harus Di Isi");
        } else if(nambar.getText().toString().trim().equalsIgnoreCase("")){
            nambar .setError("Kolom Harus Di Isi");
        }else if(satuan.getText().toString().trim().equalsIgnoreCase("")){
            satuan.setError("Kolom Harus Di Isi");
        }else if(hrgbeli.getText().toString().trim().equalsIgnoreCase("")){
            hrgbeli.setError("Kolom Harus Di Isi");
        }else if(hrgjual.getText().toString().trim().equalsIgnoreCase("")){
            hrgjual.setError("Kolom Harus Di Isi");
        }else if(stok.getText().toString().trim().equalsIgnoreCase("")) {
            stok.setError("Kolom Harus Di Isi");
        }
    }
}
