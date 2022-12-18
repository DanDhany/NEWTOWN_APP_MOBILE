package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetailTrans extends AppCompatActivity {
    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    ListView lvtampiltrans;
    TextView kdtrans, dateView;
    BeliAdapter beliadapter;
    List<trans> transList = new ArrayList<trans>();
    //String tot;
    int  intotal,randomNumber, inbayar = 0;
    double subtotal, indiskon, ingrandtot, kembalian = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trans);

        kdtrans = (TextView)findViewById(R.id.kdtrans);
        dateView = (TextView) findViewById(R.id.textView3);
        lvtampiltrans = (ListView)findViewById(R.id.lvtampilbaranag);
        Intent i = getIntent();
        kdtrans.setText(i.getStringExtra("sendid"));
        dateView.setText(i.getStringExtra("sendtgl"));
        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS DETAILBELI1(KDBELI VARCHAR(10)," +
                "KDBARANG VARCHAR(10)," +
                "NAMABRG VARCHAR(200), " +
                "SATUAN VARCHAR(50), " +
                "HARGA INT(12), " +
                "JUMLAH INT(10));");


        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        beliadapter = new BeliAdapter(this,R.layout.isilvbeli, transList, db, i.getStringExtra("sendid"));
        lvtampiltrans.setAdapter(beliadapter);


    }
}
