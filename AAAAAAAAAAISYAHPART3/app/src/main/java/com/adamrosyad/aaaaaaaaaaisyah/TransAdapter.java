package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TransAdapter extends ArrayAdapter<trans> {
    Context mctk;
    int listLayoutRes;
    private List<trans> transList;
    SQLiteDatabase db;
    String kdtrans;

    public TransAdapter(Context mctk, int listLayoutRes, List<trans> transList, SQLiteDatabase db, String kdtrans) {
        super(mctk, listLayoutRes, transList);
        this.mctk = mctk;
        this.listLayoutRes = listLayoutRes;
        this.transList = transList;
        this.db = db;
        this.kdtrans = kdtrans;
        reloadDataBeli();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mctk);
        View view = inflater.inflate(listLayoutRes, null);

        final trans isi= transList.get(position);

        TextView kdtrans = view.findViewById(R.id.kdtrans);
        TextView kdbar = view.findViewById(R.id.kdbarang);
        TextView nambar = view.findViewById(R.id.namabrg);
        TextView satbar = view.findViewById(R.id.satuanbrg);
        TextView hrgbrg = view.findViewById(R.id.hrgbrg);
        TextView jml = view.findViewById(R.id.jmlbrg);
        TextView tot = view.findViewById(R.id.tothrg);



        kdtrans.setText(isi.getKdtrans());
        kdbar.setText(isi.getKdbar());
        nambar.setText(isi.getNambar());
        satbar.setText(isi.getSatbar());
        hrgbrg.setText(String.valueOf(isi.getHrgbar()));
        jml.setText(String.valueOf(isi.getJml()));
        tot.setText(String.valueOf(isi.getTot()));




        return view;
    }

    void reloadDataBeli(){
        Cursor cursor =db.rawQuery("SELECT KDBELI, KDBARANG, NAMABRG, SATUAN, HARGA, JUMLAH, HARGA*JUMLAH AS TOTAL FROM DETAILBELI1 WHERE KDBELI ='" + kdtrans + "'",null);
        if(cursor.moveToFirst()){
            transList.clear();
            do {
                transList.add(new trans(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
