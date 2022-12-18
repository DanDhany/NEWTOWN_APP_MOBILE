package com.adamrosyad.aaaaaaaaaaisyah;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActivityTampilTransJual extends AppCompatActivity {

    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    ListView lvtampiltrans;
    EditText diskon, bayar, kembali;
    Button btndiskon, btnbayar, btnsimpan, btnbatal;
    TextView kdtrans, dateView;
    EditText total, gtotal, pembayar;
    JualAdapter jualadapter;
    List<trans> transList = new ArrayList<trans>();
    //String tot;
    int  intotal,randomNumber, inbayar = 0;
    double subtotal, indiskon, ingrandtot, kembalian = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_trans_jual);

        total = (EditText) findViewById(R.id.total);
        bayar = (EditText)findViewById(R.id.pembayar);
        gtotal = (EditText) findViewById(R.id.gtotal);
        kembali = (EditText) findViewById(R.id.kembali);
        pembayar = (EditText) findViewById(R.id.pembayar);
        diskon = (EditText)findViewById(R.id.diskon);

        btndiskon = (Button)findViewById(R.id.btndiskon);
        btnbayar = (Button)findViewById(R.id.btnbayar);
        btnsimpan = (Button)findViewById(R.id.btnSimpan);
        btnbatal = (Button)findViewById(R.id.btnBatal);

        kdtrans = (TextView)findViewById(R.id.kdtrans);
        dateView = (TextView) findViewById(R.id.textView3);
        lvtampiltrans = (ListView)findViewById(R.id.lvtampilbaranag);

        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS TRANSJUAL(KDJUAL VARCHAR(10),TGL_JUAL DATE,TOTAL INT(12),DISKON INT(5),GRTOTAL DOUBLE(20), BAYAR INT(20), KEMBALI INT(20));");


        Intent i = getIntent();
        kdtrans.setText(i.getStringExtra("sendid"));
        dateView.setText(i.getStringExtra("sendtgl"));
        total.setText(i.getStringExtra("totol"));


        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        jualadapter = new JualAdapter(this,R.layout.isilvbeli, transList, db, i.getStringExtra("sendid"));
        lvtampiltrans.setAdapter(jualadapter);

//        Cursor tot = db.rawQuery("SELECT SUM(HARGA*JUMLAH) AS TOTAL FROM DETAILBELI1 WHERE KDBELI ='" + kdtrans.getText().toString() + "'",null);
//        tot.moveToFirst();
//        total.setText("");
//        total.setText(tot.getInt(tot.getColumnIndex("TOTAL")));

        btndiskon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indiskon = Double.parseDouble(diskon.getText().toString());
                intotal = Integer.parseInt(total.getText().toString());
                subtotal = intotal-(intotal*(indiskon/100));
                gtotal.setText(String.valueOf(subtotal));
            }
        });
        btnbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingrandtot = Double.parseDouble(gtotal.getText().toString());
                inbayar = Integer.parseInt(pembayar.getText().toString());
                kembalian = inbayar-ingrandtot;
                kembali.setText(String.valueOf(kembalian));
            }
        });
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO TRANSJUAL  VALUES('"+ kdtrans.getText().toString() +"','"
                        + dateView.getText() + "','"
                        + total.getText().toString() + "','"
                        + diskon.getText().toString() + "','"
                        + gtotal.getText() + "','"
                        + pembayar.getText() + "','"
                        + kembali.getText() +"');");
                finish();
                Toast.makeText(ActivityTampilTransJual.this, "Transaksi Berhasil Disimpan", Toast.LENGTH_LONG).show();
            }
        });
        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("DELETE FROM DETAILJUAL1 WHERE KDJUAL ='" + kdtrans.getText().toString() + "';");
                db.execSQL("DELETE FROM TRANSJUAL WHERE KDJUAL ='" + kdtrans.getText().toString() + "';");
                finish();
                Toast.makeText(ActivityTampilTransJual.this, "Transaksi Dibatalkan!", Toast.LENGTH_LONG).show();
            }
        });


//        Cursor cursor =db.rawQuery("SELECT KDBELI, KDBARANG, NAMABRG, SATUAN, HARGA, JUMLAH, HARGA*JUMLAH AS TOTAL FROM DETAILBELI1 WHERE KDBELI ='" + kdtrans.getText().toString() + "'",null);
//        if(cursor.moveToFirst()){
//            transList.clear();
//            do {
//                transList.add(new trans(
//                        cursor.getString(0),
//                        cursor.getString(1),
//                        cursor.getString(2),
//                        cursor.getString(3),
//                        cursor.getString(4),
//                        cursor.getString(5),
//                        cursor.getString(6)
//                ));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
    }
}
