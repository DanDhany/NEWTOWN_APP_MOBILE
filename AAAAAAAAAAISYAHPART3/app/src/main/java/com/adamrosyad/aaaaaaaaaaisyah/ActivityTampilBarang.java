package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ActivityTampilBarang extends AppCompatActivity {
    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    ListView lvtampilbarang;
    BarangAdapter barangAdapter;
    List<barang> barangList;
    Button btntambahdata, btnrefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_barang);

        btntambahdata = (Button)findViewById(R.id.btntambahdata);
        btnrefresh = (Button)findViewById(R.id.btnrefresh);
        lvtampilbarang = (ListView)findViewById(R.id.lvtampilbaranag);
        barangList = new ArrayList<barang>();

        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        barangAdapter = new BarangAdapter(this,R.layout.isilvbarang, barangList, db);
        lvtampilbarang.setAdapter(barangAdapter);

        db.execSQL("CREATE TABLE IF NOT EXISTS ISIBARANG(KDBAR VARCHAR(10),NAMBAR VARCHAR(250),SATUAN VARCHAR(25),STOK INT(10),HRGBELI INT(10), HRGJUAL INT(10));");
        Cursor cursor =db.rawQuery("SELECT * FROM ISIBARANG ",null);
        if(cursor.moveToFirst()){
            barangList.clear();
            do {
                barangList.add(new barang(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();


        btntambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTampilBarang.this, ActivityTambahBarang.class);
                startActivity(intent);

            }
        });
        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor =db.rawQuery("SELECT * FROM ISIBARANG ",null);
                if(cursor.moveToFirst()){
                    barangList.clear();
                    do {
                        barangList.add(new barang(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5)
                        ));
                    } while (cursor.moveToNext());
                }
                cursor.close();

                lvtampilbarang.setAdapter(barangAdapter);
            }
        });
    }
}
