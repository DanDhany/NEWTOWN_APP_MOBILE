package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityEditUser extends AppCompatActivity {

    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    EditText id, nama, alamat, tlp, pass;
    Spinner spinakses;
    Button btnsimpandata, btnhapusdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        id = (EditText)findViewById(R.id.inputid);
        nama = (EditText)findViewById(R.id.inputnama);
        alamat = (EditText)findViewById(R.id.inputalamat);
        tlp = (EditText)findViewById(R.id.inputtelp);
        pass = (EditText)findViewById(R.id.inputpass);
        spinakses = (Spinner)findViewById(R.id.spinakses);
        btnsimpandata = (Button)findViewById(R.id.btnsimpandata);
        btnhapusdata = (Button)findViewById(R.id.btnhapusdata);

        Intent intent = getIntent();
        String idd = intent.getStringExtra("sendid");
        String namaa = intent.getStringExtra("sendnama");
        String alamatt = intent.getStringExtra("sendalamat");
        String tlpp = intent.getStringExtra("sendtlp");
        String passs = intent.getStringExtra("sendpass");

        id.setText(idd);
        nama.setText(namaa);
        alamat.setText(alamatt);
        tlp.setText(tlpp);
        pass.setText(passs);

        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);

        btnhapusdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("DELETE FROM ISIUSER WHERE userid ='"+id.getText().toString()+"';");
                id.setText("");nama.setText("");alamat.setText("");tlp.setText("");pass.setText("");
                Toast.makeText(ActivityEditUser.this, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnsimpandata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setError();
                db.execSQL("UPDATE ISIUSER SET nama = '" + nama.getText().toString() + "', alamat = '" + alamat.getText().toString() + "', telp = '" + tlp.getText().toString() + "', akses = '" + spinakses.getSelectedItem().toString() + "',password = '" + pass.getText().toString() + "' WHERE userid ='" + id.getText().toString() + "';");
                id.setText("");nama.setText("");alamat.setText("");tlp.setText("");pass.setText("");
                Toast.makeText(ActivityEditUser.this, "Data Berhasil Diubah", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    void setError(){
        if(id.getText().toString().trim().equalsIgnoreCase("")){
            id.setError("Kolom Harus Di Isi");
        } else if(nama.getText().toString().trim().equalsIgnoreCase("")){
            nama.setError("Kolom Harus Di Isi");
        }else if(alamat.getText().toString().trim().equalsIgnoreCase("")){
            alamat.setError("Kolom Harus Di Isi");
        }else if(tlp.getText().toString().trim().equalsIgnoreCase("")){
            tlp.setError("Kolom Harus Di Isi");
        }else if(pass.getText().toString().trim().equalsIgnoreCase("")){
            pass.setError("Kolom Harus Di Isi");
        }

    }
}
