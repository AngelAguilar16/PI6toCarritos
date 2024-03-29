package com.example.usuariocliente.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.usuariocliente.Cliente.Home.AutoList;
import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;
import com.example.usuariocliente.Cliente.Home.AutoSelected;

import java.util.ArrayList;
import java.util.List;

public class AutosAdapter extends RecyclerView.Adapter<AutosAdapter.AutoViewHolder> {
    List<Auto> autos;

    List<Auto> autosList;
    ArrayList<Auto> arrayList;
    Context c;

    public AutosAdapter(List<Auto> autos) {
        this.autos = autos;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(autos);
    }

    @NonNull
    @Override
    public AutosAdapter.AutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.auto_card, parent, false);
        AutosAdapter.AutoViewHolder rvh = new AutosAdapter.AutoViewHolder(v);
        c = v.getContext();
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AutoViewHolder holder, int position) {
        Glide.with(c).load("https://cinderellaride.000webhostapp.com/assets/img/autos/" + autos.get(position).getId_vehiculo() + ".png").asBitmap().into(holder.foto);
        holder.nombre.setText(autos.get(position).getModelo());
        holder.transmision.setText(autos.get(position).getTransmision());
        holder.puertas.setText("4 puertas"); //Insertar un if donde cheque cuantas puertas tiene dependiendo del tipo de auto
        holder.pasajeros.setText("5 pasajeros");
        holder.precio.setText("$ " + autos.get(position).getPrecio());
        //poner foto
        autosList = new ArrayList<>();
        holder.cv.setOnClickListener(v -> {
            Auto auto = autosList.get(position);
            //Toast.makeText(c, auto.getModelo(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(c, AutoSelected.class);
            i.putExtra("auto", auto);
            c.startActivity(i);
            ((AutoList)c).finish();
        });
        autosList = Globals.showList(c);
    }



    @Override
    public int getItemCount() {
        return autos.size();
    }

    public static class AutoViewHolder extends RecyclerView.ViewHolder{
        CardView cv, rentar;
        TextView nombre, transmision, puertas, pasajeros, precio;
        ImageView foto;

        public AutoViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cvAuto);
            nombre = itemView.findViewById(R.id.tvNombre_auto);
            transmision = itemView.findViewById(R.id.transmision);
            puertas = itemView.findViewById(R.id.puertas);
            pasajeros = itemView.findViewById(R.id.pasajeros);
            precio = itemView.findViewById(R.id.tvPrecio);
            foto = itemView.findViewById(R.id.ivAuto);
            rentar = itemView.findViewById(R.id.btnRentar);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
