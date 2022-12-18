package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityTambahUser extends AppCompatActivity {
    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    EditText inputid, inputnama, inputalamat, inputtelp, inputpass;
    Button btnsimpandata;
    Spinner akses;
    CardView inputuser;
    ListView lvtampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_user);

        inputuser = (CardView) findViewById(R.id.inputuser);
        inputid = (EditText)findViewById(R.id.inputid);
        inputnama = (EditText)findViewById(R.id.inputnama);
        inputalamat = (EditText)findViewById(R.id.inputalamat);
        inputtelp = (EditText)findViewById(R.id.inputtelp);
        inputpass = (EditText)findViewById(R.id.inputpass);
        akses = (Spinner)findViewById(R.id.spinakses);
        btnsimpandata = (Button)findViewById(R.id.btnsimpandata);

        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS ISIUSER(userid VARCHAR(10),nama VARCHAR(100),alamat VARCHAR(250),telp number(12),akses VARCHAR(15), password VARCHAR(100));");


        btnsimpandata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setError();
                Cursor cek = db.rawQuery("SELECT * FROM ISIUSER WHERE userid ='" + inputid.getText().toString() + "'", null);
                if (cek.getCount() > 0) {
                    Toast.makeText(ActivityTambahUser.this, "Data Dengan ID "+inputid.getText()+" Sudah Ada, Coba Gunakan ID Lain", Toast.LENGTH_LONG).show();
                } else {
                    db.execSQL("INSERT INTO ISIUSER VALUES('" + inputid.getText().toString() + "','" + inputnama.getText().toString() + "','" + inputalamat.getText().toString() + "','" + inputtelp.getText().toString() + "','" + akses.getSelectedItem().toString() + "','" + inputpass.getText().toString() + "');");
                    Toast.makeText(ActivityTambahUser.this, "Data Berhasil Ditambah", Toast.LENGTH_LONG).show();
                }
                inputid.setText("");
                inputnama.setText("");
                inputalamat.setText("");
                inputtelp.setText("");
                inputpass.setText("");
            }
        });


        //INI BUAT NAMPILIN DATA BERDASARKAN INPUT KODE MELALUI BUTTON EDIT

        /*btnubahdata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Cursor cek = db.rawQuery("SELECT * FROM ISIUSER WHERE userid ='" + inputid.getText().toString() + "'", null);
                if (cek.getCount() > 0) {
                    Cursor c = db.rawQuery("SELECT * FROM ISIUSER WHERE userid ='" + inputid.getText().toString() + "'", null);
                    c.moveToFirst();
                    inputid.setText("");
                    inputnama.setText("");
                    inputalamat.setText("");
                    inputtelp.setText("");
                    inputpass.setText("");
                    inputid.setText(c.getString(c.getColumnIndex("userid")));
                    inputnama.setText(c.getString(c.getColumnIndex("nama")));
                    inputalamat.setText(c.getString(c.getColumnIndex("alamat")));
                    inputtelp.setText(c.getString(c.getColumnIndex("telp")));
                    inputpass.setText(c.getString(c.getColumnIndex("password")));
                    Toast.makeText(getApplicationContext(), "Data tersedia, dan berhasil diambil!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(), "Data tidak ada!", Toast.LENGTH_SHORT).show();

                }
            }
        });*/


    }

    //INI GAK JALAN

    void setError(){
        if(inputid.getText().toString().trim().equalsIgnoreCase("")){
            inputid.setError("Kolom Harus Di Isi");
        } else if(inputnama.getText().toString().trim().equalsIgnoreCase("")){
            inputnama.setError("Kolom Harus Di Isi");
        }else if(inputalamat.getText().toString().trim().equalsIgnoreCase("")){
            inputalamat.setError("Kolom Harus Di Isi");
        }else if(inputtelp.getText().toString().trim().equalsIgnoreCase("")){
            inputtelp.setError("Kolom Harus Di Isi");
        }else if(inputpass.getText().toString().trim().equalsIgnoreCase("")){
            inputpass.setError("Kolom Harus Di Isi");
        }

    }
}
