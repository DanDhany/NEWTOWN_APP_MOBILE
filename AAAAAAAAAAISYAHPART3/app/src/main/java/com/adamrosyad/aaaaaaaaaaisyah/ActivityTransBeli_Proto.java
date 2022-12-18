package com.adamrosyad.aaaaaaaaaaisyah;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.CancellationException;

public class ActivityTransBeli_Proto extends AppCompatActivity {

    private static String DBNAME="dbkasir";
    SQLiteDatabase db;

    private DatePicker datePicker;
    private Button plus, minus, tampil, submit, cek;
    private Calendar calendar;
    private TextView dateView, kdtrans, jml, header;
    private int year, month, day, counter;
    EditText kdbar, nambar, satbar, hrgbar;
    int harga =0, jumlah=0, total = 0, randomNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_beli__proto);
        dateView = (TextView) findViewById(R.id.textView3);
        kdtrans = (TextView)findViewById(R.id.kdtrans);
        header = (TextView)findViewById(R.id.header);

        kdbar = (EditText)findViewById(R.id.kdbar);
        nambar = (EditText)findViewById(R.id.nambar);
        satbar = (EditText)findViewById(R.id.satbar);
        hrgbar = (EditText)findViewById(R.id.hrgbar);

       //tot=(EditText)findViewById(R.id.tot);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        Random r = new Random();
        randomNumber = r.nextInt(6666);
        kdtrans.setText("TB"+String.valueOf(randomNumber));

        counter = 0;
        cek =(Button)findViewById(R.id.btntampildata);
        tampil =(Button)findViewById(R.id.btnTampil);
        plus =(Button)findViewById(R.id.btnPlus);
        submit =(Button)findViewById(R.id.btnSubmit);
        minus =(Button)findViewById(R.id.btnMinus);
        jml = (TextView)findViewById(R.id.jml);

        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS TRANSBELI(KDBELI VARCHAR(10) PRIMARY KEY,NAMSUP VARCHAR(250),TGLBELI DATE);");
        db.execSQL("CREATE TABLE IF NOT EXISTS DETAILBELI1(KDBELI VARCHAR(10)," +
                "KDBARANG VARCHAR(10)," +
                "NAMABRG VARCHAR(200), " +
                "SATUAN VARCHAR(50), " +
                "HARGA INT(12), " +
                "JUMLAH INT(10));");


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adds 1 to the counter
                counter = counter + 1;
                jml.setText(String.valueOf(counter));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Subtract 1 from counter
                if(counter>0) {
                    counter = counter - 1;
                    jml.setText(String.valueOf(counter));
                }else{
                    jml.setText("0");
                }
            }
        });
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adds 1 to the counter
                Random r = new Random();
                randomNumber = r.nextInt(6666);
                kdtrans.setText("TB"+String.valueOf(randomNumber));
            }
        });
        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Cursor cekk = db.rawQuery("SELECT * FROM DETAILBELI1 WHERE KDBARANG ='" + kdbar.getText().toString() + "' AND KDBELI ='" + kdtrans.getText().toString() + "'", null);
//                if (cekk.getCount() > 0) {
//                    Cursor cc = db.rawQuery("SELECT * FROM DETAILBELI1 WHERE KDBARANG ='" + kdbar.getText().toString() + "' AND KDBELI ='" + kdtrans.getText().toString() + "'", null);
//                    cc.moveToFirst();
//                    kdbar.setText("");
//                    nambar.setText("");
//                    satbar.setText("");
//                    hrgbar.setText("");
//                    jml.setText("0");
//                    kdbar.setText(cc.getString(cc.getColumnIndex("KDBARARNG")));
//                    nambar.setText(cc.getString(cc.getColumnIndex("NAMABRG")));
//                    satbar.setText(cc.getString(cc.getColumnIndex("SATUAN")));
//                    hrgbar.setText(cc.getInt(cc.getColumnIndex("HARGA")));
//                    jml.setText(cc.getInt(cc.getColumnIndex("JUMLAH")));
//                    Toast.makeText(getApplicationContext(), "Data tersedia, dan berhasil diambil!", Toast.LENGTH_SHORT).show();
//
//                } else {
                    Cursor cek = db.rawQuery("SELECT * FROM ISIBARANG WHERE KDBAR ='" + kdbar.getText().toString() + "' OR nambar ='"+ nambar.getText() +"'", null);
                    if (cek.getCount() > 0) {
                        Cursor c = db.rawQuery("SELECT * FROM ISIBARANG WHERE KDBAR ='" + kdbar.getText().toString() + "' OR nambar ='"+ nambar.getText() +"'", null);
                        c.moveToFirst();
                        kdbar.setText("");
                        nambar.setText("");
                        satbar.setText("");
                        hrgbar.setText("");
                        kdbar.setText(c.getString(c.getColumnIndex("KDBAR")));
                        nambar.setText(c.getString(c.getColumnIndex("NAMBAR")));
                        satbar.setText(c.getString(c.getColumnIndex("SATUAN")));
                        hrgbar.setText(c.getString(c.getColumnIndex("HRGBELI")));
                        Toast.makeText(getApplicationContext(), "Data tersedia, dan berhasil diambil!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Data Barang Tidak Ada!", Toast.LENGTH_SHORT).show();

                    }
                }
            //}
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                Cursor cekk = db.rawQuery("SELECT * FROM DETAILBELI1 WHERE KDBARANG ='" + kdbar.getText().toString() + "' AND KDBELI ='" + kdtrans.getText().toString() + "'", null);
                if (cekk.getCount()>0) {
                     db.execSQL("UPDATE DETAILBELI1 SET KDBARANG = '"
                              + kdbar.getText().toString() + "', NAMABRG = '"
                              + nambar.getText().toString() + "', SATUAN = '"
                              + satbar.getText().toString() + "', HARGA = '"
                              + hrgbar.getText() + "', JUMLAH = '"
                              + jml.getText() + "' WHERE KDBELI ='"
                              + kdtrans.getText().toString() + "';");
                    Toast.makeText(ActivityTransBeli_Proto.this, "Data Berhasil Diubah", Toast.LENGTH_LONG).show();
                } else {
                    db.execSQL("INSERT INTO DETAILBELI1  VALUES('"+ kdtrans.getText().toString() +"','"
                            + kdbar.getText().toString() + "','"
                            + nambar.getText().toString() + "','"
                            + satbar.getText().toString() + "','"
                            + hrgbar.getText() + "','"
                            + jml.getText() +"');");

                    Toast.makeText(ActivityTransBeli_Proto.this, "Data Berhasil Ditambah", Toast.LENGTH_LONG).show();
                }
                kdbar.setText("");
                nambar.setText("");
                satbar.setText("");
                hrgbar.setText("");
                jml.setText("0");
            }
        });

        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTransBeli_Proto.this, ActivityTampilTransBeli.class);
                Cursor tot = db.rawQuery("SELECT SUM(HARGA*JUMLAH) AS TOTAL FROM DETAILBELI1 WHERE KDBELI ='" + kdtrans.getText().toString() + "'",null);
                tot.moveToFirst();
                String total = tot.getString(tot.getColumnIndex("TOTAL"));
                String sendid = kdtrans.getText().toString();
                String sendtgl = dateView.getText().toString();
                intent.putExtra("sendid",sendid);
                intent.putExtra("sendtgl",sendtgl);
                intent.putExtra("totol",total);
                startActivity(intent);
            }
        });


    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }
}
