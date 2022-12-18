package com.adamrosyad.aaaaaaaaaaisyah;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class lapTransBAdapter extends ArrayAdapter<laptransb> {
    Context mctx;
    int listLayoutRes;
    private List<laptransb> laptransblist;
    SQLiteDatabase db;
    public lapTransBAdapter(Context mctx, int listLayoutRes, List<laptransb> laptransblist, SQLiteDatabase db) {
        super(mctx, listLayoutRes, laptransblist);
        this.mctx = mctx;
        this.listLayoutRes = listLayoutRes;
        this.laptransblist = laptransblist;
        this.db = db;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(listLayoutRes, null);

        laptransb LapTransB = laptransblist.get(position);

        TextView kdtrans = view.findViewById(R.id.kdtrans);
        TextView tgltrans = view.findViewById(R.id.tgltrans);
        TextView total = view.findViewById(R.id.total);
        TextView diskon = view.findViewById(R.id.diskon);
        TextView gtotal = view.findViewById(R.id.gtotal);
        TextView bayar = view.findViewById(R.id.bayar);
        TextView kembali = view.findViewById(R.id.kembali);

        kdtrans.setText("Kode Transaksi: "+LapTransB.getKdtrans());
        tgltrans.setText("Tgl Transaksi: "+LapTransB.getTgl());
        total.setText("Total Belanja: "+String.valueOf(LapTransB.getTotal()));
        diskon.setText("Diskon: "+String.valueOf(LapTransB.getDiskon()));
        gtotal.setText("Grand Total: "+String.valueOf(LapTransB.getGtotal()));
        bayar.setText("Total Bayar: "+String.valueOf(LapTransB.getBayar()));
        kembali.setText("Kembalian: "+String.valueOf(LapTransB.getKembali()));


        return view;
    }
}
