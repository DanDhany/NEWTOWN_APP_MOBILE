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

public class BarangAdapter extends ArrayAdapter<barang> {
    Context mctx;
    int listLayoutRes;
    private List<barang> barangList;
    SQLiteDatabase db;

    public BarangAdapter(Context mctx, int listLayoutRes, List<barang> barangList, SQLiteDatabase db) {
        super(mctx, listLayoutRes, barangList);
        this.mctx = mctx;
        this.listLayoutRes = listLayoutRes;
        this.barangList = barangList;
        this.db = db;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(listLayoutRes, null);

        barang Barang = barangList.get(position);

        TextView kdbar = view.findViewById(R.id.kdbar);
        TextView nambar = view.findViewById(R.id.nambar);
        TextView satbar = view.findViewById(R.id.satbar);
        TextView stokbar = view.findViewById(R.id.stokbar);
        TextView hrgbeli = view.findViewById(R.id.hrgbeli);
        TextView hrgjual = view.findViewById(R.id.hrgjual);


        kdbar.setText(Barang.getKdbar());
        nambar.setText(Barang.getNambar());
        satbar.setText(Barang.getSatbar());
        stokbar.setText(Barang.getStok());
        hrgbeli.setText(Barang.hrgbeli);
        hrgjual.setText(Barang.hrgjual);

        return view;
    }
}
