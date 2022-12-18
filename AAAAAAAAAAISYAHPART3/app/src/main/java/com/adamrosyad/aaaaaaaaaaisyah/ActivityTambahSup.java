package com.adamrosyad.aaaaaaaaaaisyah;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityTambahSup extends AppCompatActivity {
    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    EditText inputid, inputnama, inputalamat, inputtelp;
    Button btnsimpandata, btnubahdata, btnhapusdata;
    CardView inputuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_sup);

        inputuser = (CardView) findViewById(R.id.inputuser);
        inputid = (EditText)findViewById(R.id.inputidsup);
        inputnama = (EditText)findViewById(R.id.inputnamasup);
        inputalamat = (EditText)findViewById(R.id.inputalamatsup);
        inputtelp = (EditText)findViewById(R.id.inputtelpsup);
        btnsimpandata = (Button)findViewById(R.id.btnsimpandata);
        btnubahdata = (Button)findViewById(R.id.btnubahdata);
        btnhapusdata = (Button)findViewById(R.id.btnhapusdata);

        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS ISISUP(supid VARCHAR(10),nama VARCHAR(150),alamat VARCHAR(250),telp number(12));");


        btnsimpandata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cek = db.rawQuery("SELECT * FROM ISISUP WHERE supid ='" + inputid.getText().toString() + "'", null);
                if (cek.getCount() > 0) {
                    db.execSQL("UPDATE ISISUP SET nama = '" + inputnama.getText().toString() + "', alamat = '" + inputalamat.getText().toString() + "', telp = '" + inputtelp.getText().toString()+ "' WHERE supid ='" + inputid.getText().toString() + "';");
                    Toast.makeText(ActivityTambahSup.this, "Data Dengan ID "+inputid.getText()+" Berhasil di Update, Coba Gunakan ID lain", Toast.LENGTH_LONG).show();
                }
                else {
                    db.execSQL("INSERT INTO ISISUP VALUES('" + inputid.getText().toString() + "','" + inputnama.getText().toString() + "','" + inputalamat.getText().toString() + "','" + inputtelp.getText().toString()+ "');");
                    Toast.makeText(ActivityTambahSup.this, "Data Berhasil Ditambah", Toast.LENGTH_LONG).show();
                }
                inputid.setText("");
                inputnama.setText("");
                inputalamat.setText("");
                inputtelp.setText("");
            }
        });

        btnubahdata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Cursor cek = db.rawQuery("SELECT * FROM ISISUP WHERE supid ='"+inputid.getText().toString()+"'", null);
                if (cek.getCount() > 0) {
                    Cursor c = db.rawQuery("SELECT * FROM ISISUP WHERE supid ='" + inputid.getText().toString() + "'", null);
                    c.moveToFirst();
                    inputid.setText("");
                    inputnama.setText("");
                    inputalamat.setText("");
                    inputtelp.setText("");
                    inputid.setText(c.getString(c.getColumnIndex("supid")));
                    inputnama.setText(c.getString(c.getColumnIndex("nama")));
                    inputalamat.setText(c.getString(c.getColumnIndex("alamat")));
                    inputtelp.setText(c.getString(c.getColumnIndex("telp")));
                    Toast.makeText(getApplicationContext(), "Data tersedia, dan berhasil diambil!", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(), "Data tidak ada!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnhapusdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("DELETE FROM ISISUP WHERE supid ='"+inputid.getText().toString()+"';");
                inputid.setText("");inputnama.setText("");inputalamat.setText("");inputtelp.setText("");
                Toast.makeText(ActivityTambahSup.this, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
            }
        });
    }
}
