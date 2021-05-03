package com.example.sqliteconnection.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteconnection.R;
import com.example.sqliteconnection.database.Teman;

import java.util.ArrayList;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {

    private ArrayList<Teman> ListData;
//  construktor
    public TemanAdapter(ArrayList<Teman> listData) {
        ListData = listData;
    }

//    memanggil tampilan/layout dari adapternya  menggunakan Inflater #1
    @Override
    public TemanAdapter.TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);
    }

//    untuk menampilkan #3
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(TemanAdapter.TemanViewHolder holder, int position) {
        String nm,tlp;
        nm = ListData.get(position).getNama();
        tlp = ListData.get(position).getTelpon();

        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nm);
        holder.telponTxt.setText(tlp);

    }

//  menghitung ukuran dari arraylist #4
//  bisa ditambahin / biarkan saja
    @Override
    public int getItemCount() {
        return (ListData != null) ? ListData.size() : 0;
    }

//  untuk mendaftarkan terlebih dahulu #2
    public class TemanViewHolder extends RecyclerView.ViewHolder{
        private CardView cardku;
        private TextView namaTxt , telponTxt;
        public TemanViewHolder(View view){
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);
        }
    }
}
