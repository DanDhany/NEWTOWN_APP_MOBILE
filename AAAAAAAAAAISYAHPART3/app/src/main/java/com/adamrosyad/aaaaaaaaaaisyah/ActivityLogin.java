package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    private static String DBNAME="dbkasir";
    SQLiteDatabase db;
    EditText txtusername, txtpass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusername = (EditText)findViewById(R.id.txtusername);
        txtpass = (EditText)findViewById(R.id.txtpass);
        login = (Button) findViewById(R.id.login);

        db=openOrCreateDatabase(DBNAME,MODE_PRIVATE,null);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cek = db.rawQuery("SELECT userid, password FROM ISIUSER WHERE userid ='" + txtusername.getText().toString() + "' AND password = '"+txtpass.getText().toString()+"'", null);
                if (cek.getCount() > 0) {
                    Cursor c = db.rawQuery("SELECT * FROM ISIUSER WHERE userid ='" + txtusername.getText().toString() + "'AND password = '"+txtpass+"'", null);
                    String akses = c.getColumnName(5);
                    Toast.makeText(ActivityLogin.this,"Selamat Datang "+txtusername.getText(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ActivityLogin.this, ActivityHome.class);
                    intent.putExtra("akses", akses);
                    startActivity(intent);
                }else if(txtusername.getText().toString().equals("admin")&&txtpass.getText().toString().equals("admin")){
                    Toast.makeText(ActivityLogin.this,"Selamat Datang "+txtusername.getText(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ActivityLogin.this, ActivityHome.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ActivityLogin.this,"Username atau Password Salah Mohon Periksa Kembali", Toast.LENGTH_LONG).show();
                }
                txtusername.setText("");
                txtpass.setText("");
            }
        });
    }
}
