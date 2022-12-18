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

public class ActivityTambahBarang extends AppCompatActivity {
    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    EditText inputkdbar, inputnambar, inputsatuan, inputstok, inputhrgbeli, inputhrgjual;
    Button btnsimpandata, btnubahdata, btnhapusdata;
    CardView inputbarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);

        inputbarang = (CardView) findViewById(R.id.inputbarang);
        inputkdbar = (EditText)findViewById(R.id.inputkdbar);
        inputnambar = (EditText)findViewById(R.id.inputnambar);
        inputsatuan = (EditText)findViewById(R.id.inputsatuan);
        inputstok = (EditText)findViewById(R.id.inputstok);
        inputhrgbeli = (EditText)findViewById(R.id.inputhrgbeli);
        inputhrgjual = (EditText)findViewById(R.id.inputhrgjual);

        btnsimpandata = (Button)findViewById(R.id.btnsimpandata);
        btnubahdata = (Button)findViewById(R.id.btnubahdata);
        btnhapusdata = (Button)findViewById(R.id.btnhapusdata);

        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS ISIBARANG(KDBAR VARCHAR(10),NAMBAR VARCHAR(250),SATUAN VARCHAR(25),STOK VARCHAR(10),HRGBELI VARCHAR(10), HRGJUAL VARCHAR(10));");


        btnsimpandata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cek = db.rawQuery("SELECT * FROM ISIBARANG WHERE KDBAR ='" + inputkdbar.getText().toString() + "'", null);
                if (cek.getCount() > 0) {
                    db.execSQL("UPDATE ISIBARANG SET NAMBAR = '" + inputnambar.getText().toString() + "', SATUAN = '" + inputsatuan.getText().toString() + "', stok = '" + inputstok.getText().toString() + "', hrgbeli = '" + inputhrgbeli.getText().toString() + "',hrgjual = '" + inputhrgjual.getText().toString() + "' WHERE kdbar='" + inputkdbar.getText().toString() + "';");
                    Toast.makeText(ActivityTambahBarang.this, "Data Dengan ID "+ inputkdbar.getText()+" Berhasil di Update, Coba Gunakan ID lain", Toast.LENGTH_LONG).show();
                } else {
                    db.execSQL("INSERT INTO ISIBARANG VALUES('" + inputkdbar.getText().toString() + "','" + inputnambar.getText().toString() + "','" + inputsatuan.getText().toString() + "','" + inputstok.getText().toString() + "','" + inputhrgbeli.getText().toString() + "','" + inputhrgjual.getText().toString() + "');");
                    Toast.makeText(ActivityTambahBarang.this, "Data Berhasil Ditambah", Toast.LENGTH_LONG).show();
                }
                inputkdbar.setText("");
                inputnambar.setText("");
                inputsatuan.setText("");
                inputstok.setText("");
                inputhrgbeli.setText("");
                inputhrgjual.setText("");
            }
        });


        btnubahdata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Cursor cek = db.rawQuery("SELECT * FROM ISIBARANG WHERE KDBAR ='" + inputkdbar.getText().toString() + "'", null);
                if (cek.getCount() > 0) {
                    Cursor c = db.rawQuery("SELECT * FROM ISIBARANG WHERE KDBAR ='" + inputkdbar.getText().toString() + "'", null);
                    c.moveToFirst();
                    inputkdbar.setText("");
                    inputnambar.setText("");
                    inputsatuan.setText("");
                    inputstok.setText("");
                    inputhrgbeli.setText("");
                    inputhrgjual.setText("");
                    inputkdbar.setText(c.getString(c.getColumnIndex("KDBAR")));
                    inputnambar.setText(c.getString(c.getColumnIndex("NAMBAR")));
                    inputsatuan.setText(c.getString(c.getColumnIndex("SATUAN")));
                    inputstok.setText(c.getString(c.getColumnIndex("STOK")));
                    inputhrgbeli.setText(c.getString(c.getColumnIndex("HRGBELI")));
                    inputhrgjual.setText(c.getString(c.getColumnIndex("HRGJUAL")));
                    Toast.makeText(getApplicationContext(), "Data tersedia, dan berhasil diambil!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(), "Data tidak ada!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnhapusdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("DELETE FROM isibarang WHERE kdbar ='"+inputkdbar.getText().toString()+"';");
                inputkdbar.setText("");
                inputnambar.setText("");
                inputsatuan.setText("");
                inputstok.setText("");
                inputhrgbeli.setText("");
                inputhrgjual.setText("");
                Toast.makeText(ActivityTambahBarang.this, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
            }
        });
    }
}
