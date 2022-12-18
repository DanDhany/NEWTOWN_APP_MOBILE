package com.adamrosyad.aaaaaaaaaaisyah;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityTampilUser extends AppCompatActivity {
    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    ListView lvtampil;
    UserAdapter useradapter;
    List<user> userlist;
    Button btntambahdata, btnrefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_user);

        btntambahdata = (Button)findViewById(R.id.btntambahdata);
        btnrefresh = (Button)findViewById(R.id.btnrefresh);

        lvtampil = (ListView)findViewById(R.id.lvtampil);

        userlist = new ArrayList<user>();

        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);
        useradapter = new UserAdapter(this,R.layout.isilvuser, userlist, db);
        lvtampil.setAdapter(useradapter);


        db.execSQL("CREATE TABLE IF NOT EXISTS ISIUSER(userid VARCHAR(10),nama VARCHAR(100),alamat VARCHAR(250),telp number(12),akses VARCHAR(15), password VARCHAR(100));");
        Cursor cursor =db.rawQuery("SELECT * FROM ISIUSER ORDER BY 1",null);
        if(cursor.moveToFirst()){
        userlist.clear();
        do {
            userlist.add(new user(
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
                Intent intent = new Intent(ActivityTampilUser.this, ActivityTambahUser.class);
                startActivity(intent);

            }
        });
        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor =db.rawQuery("SELECT * FROM ISIUSER ORDER BY 1",null);
                if(cursor.moveToFirst()){
                    userlist.clear();
                    do {
                        userlist.add(new user(
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

                lvtampil.setAdapter(useradapter);


            }
        });

        lvtampil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user users = userlist.get(position);
                Intent intent = new Intent(getApplicationContext(), ActivityEditUser.class);
                intent.putExtra("sendid", users.getId());
                intent.putExtra("sendnama", users.getNama());
                intent.putExtra("sendalamat", users.getAlamat());
                intent.putExtra("sendtlp", users.getTlp());
                intent.putExtra("sendpass", users.getPass());
                startActivity(intent);
            }
        });



    }

}
