package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TampilTransLapJ extends AppCompatActivity {
    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    ListView lvtampiltranslapb;
    lapTransBAdapter laptransbadapter;
    List<laptransb> laptransblist;
    Button btntambahdata, btnrefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_trans_lap_j);

        lvtampiltranslapb= (ListView)findViewById(R.id.lvtampiltranslapb);

        laptransblist = new ArrayList<laptransb>();

        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        laptransbadapter = new lapTransBAdapter(this,R.layout.isilvlaptransb, laptransblist, db);
        lvtampiltranslapb.setAdapter(laptransbadapter);

        db.execSQL("CREATE TABLE IF NOT EXISTS TRANSJUAL(KDJUAL VARCHAR(10),TGL_BELI DATE,TOTAL INT(12),DISKON INT(5),GRTOTAL DOUBLE(20), BAYAR INT(20), KEMBALI INT(20));");
        Cursor cursor =db.rawQuery("SELECT * FROM TRANSJUAL ORDER BY 1",null);
        if(cursor.moveToFirst()){
            laptransblist.clear();
            do {
                laptransblist.add(new laptransb(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getDouble(4),
                        cursor.getInt(5),
                        cursor.getInt(6)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        lvtampiltranslapb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                laptransb laptransbs = laptransblist.get(position);
                Intent intent = new Intent(getApplicationContext(), ActivityDetailTranJ.class);
                intent.putExtra("sendid", laptransbs.getKdtrans());
                intent.putExtra("sendtgl", laptransbs.getTgl());

                startActivity(intent);
            }
        });
    }
}
