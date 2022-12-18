package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<user>{
    Context mctx;
    int listLayoutRes;
    private List<user> userlist;
    SQLiteDatabase db;

    public UserAdapter(Context mctx, int listLayoutRes, List<user> userlist, SQLiteDatabase db) {
        super(mctx, listLayoutRes, userlist);
        this.mctx = mctx;
        this.listLayoutRes = listLayoutRes;
        this.userlist = userlist;
        this.db = db;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(listLayoutRes, null);

        user User = userlist.get(position);

        TextView id = view.findViewById(R.id.iduser);
        TextView nama = view.findViewById(R.id.namauser);
        TextView alamat = view.findViewById(R.id.alamatuser);
        TextView tlp = view.findViewById(R.id.tlpuser);
        TextView pass = view.findViewById(R.id.passuser);
        TextView akses = view.findViewById(R.id.aksesuser);
        ListView tampil = view.findViewById(R.id.lvtampil);

        id.setText(User.getId());
        nama.setText(User.getNama());
        alamat.setText(User.getAlamat());
        tlp.setText(User.getTlp());
        akses.setText(User.getAkses());
        pass.setText(User.getPass());

//        tampil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                 View item = adapter.get(position);
//                Intent intent = new Intent(mctx, ActivityEditUser.class);
//                String sendid = User.getId();
//                String sendnama = User.getNama();
//                String sendalamat = User.getAlamat();
//                String sendtlp = User.getTlp();
//                String sendpass = User.getPass();
//                intent.putExtra("sendid",sendid);
//                intent.putExtra("sendnama",sendnama);
//                intent.putExtra("sendalamat",sendalamat);
//                intent.putExtra("sendtelp",sendtlp);
//                intent.putExtra("sendpass",sendpass);
//                mctx.startActivity(intent);
//            }
//        });

        return view;
    }

}
