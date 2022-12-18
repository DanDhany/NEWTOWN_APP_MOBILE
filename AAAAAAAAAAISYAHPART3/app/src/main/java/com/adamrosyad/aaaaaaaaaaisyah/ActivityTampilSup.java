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

public class ActivityTampilSup extends AppCompatActivity {
    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    ListView lvtampilsup;
    SupAdapter supadapter;
    List<sup> suplist;
    Button btntambahdata, btnrefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_sup);

        btntambahdata = (Button)findViewById(R.id.btntambahdata);
        btnrefresh = (Button)findViewById(R.id.btnrefresh);
        lvtampilsup = (ListView)findViewById(R.id.lvtampilsup);

        suplist = new ArrayList<sup>();

        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        supadapter = new SupAdapter(this,R.layout.isilvsup, suplist, db);
        lvtampilsup.setAdapter(supadapter);


        db.execSQL("CREATE TABLE IF NOT EXISTS ISISUP(supid VARCHAR(10),nama VARCHAR(150),alamat VARCHAR(250),telp number(12));");
        Cursor cursor =db.rawQuery("SELECT * FROM ISISUP",null);
        if(cursor.moveToFirst()){
            suplist.clear();
            do {
                suplist.add(new sup(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();

        btntambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTampilSup.this, ActivityTambahSup.class);
                startActivity(intent);

            }
        });
        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor =db.rawQuery("SELECT * FROM ISISUP ",null);
                if(cursor.moveToFirst()){
                    suplist.clear();
                    do {
                        suplist.add(new sup(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3)
                        ));
                    } while (cursor.moveToNext());
                }
                cursor.close();

                lvtampilsup.setAdapter(supadapter);


            }
        });
    }
}
