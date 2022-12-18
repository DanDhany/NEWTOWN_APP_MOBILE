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

public class SupAdapter extends ArrayAdapter<sup> {
    Context mctq;
    int listLayoutRes;
    private List<sup> suplist;
    SQLiteDatabase db;

    public SupAdapter(Context mctq, int listLayoutRes, List<sup> suplist, SQLiteDatabase db) {
        super(mctq, listLayoutRes, suplist);
        this.mctq = mctq;
        this.listLayoutRes = listLayoutRes;
        this.suplist = suplist;
        this.db = db;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mctq);
        View view = inflater.inflate(listLayoutRes, null);

        sup Sup = suplist.get(position);

        TextView id = view.findViewById(R.id.idsup);
        TextView namasup = view.findViewById(R.id.namasup);
        TextView alamatsup = view.findViewById(R.id.alamatsup);
        TextView tlp = view.findViewById(R.id.tlpsup);

        id.setText(Sup.getId());
        namasup.setText(Sup.getNama());
        alamatsup.setText(Sup.getAlamat());
        tlp.setText(Sup.getTlp());

        return view;
    }
}
