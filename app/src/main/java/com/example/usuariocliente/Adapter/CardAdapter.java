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
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Card;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.example.usuariocliente.Models.Globals.id_usuario;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    List<Card> cards;
    ArrayList<Card> arrayList;
    Boolean seleccionar;
    String ubicacion, fecha_inicio, fecha_fin;
    Auto autodata;
    Context c;

    public CardAdapter(List<Card> cards, Boolean seleccionar) {
        this.cards = cards;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(cards);
        this.seleccionar = seleccionar;
    }
    public CardAdapter(List<Card> cards, Boolean seleccionar, String ubicacion, String fecha_inicio, String fecha_fin, Auto autodata) {
        this.cards = cards;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(cards);
        this.seleccionar = seleccionar;
        this.ubicacion = ubicacion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.autodata = autodata;
    }

    @NonNull
    @Override
    public CardAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.credit_card, parent, false);
        CardAdapter.CardViewHolder rvh = new CardAdapter.CardViewHolder(v);
        c = v.getContext();
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        if (!seleccionar){
            holder.delete.setOnClickListener(v -> {
                //eliminar tarjeta de la db
                c = v.getContext();
                new AlertDialog.Builder(c)
                        .setTitle("Confirmar")
                        .setMessage("¿Desea eliminar este método de pago? Esta acción no es reversible")
                        .setNegativeButton(android.R.string.cancel, null) // dismiss
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                            Globals.deleteCard(cards.get(position).getNumero_tarjeta(),c);
                            //Toast.makeText(c, "Espere un momento...", Toast.LENGTH_SHORT).show();
                        })
                        .create()
                        .show();

            });
        }
        if (seleccionar){
            holder.delete.setVisibility(View.GONE);
            holder.cv.setOnClickListener(v -> {
                Globals.rentarAuto(c, ubicacion, fecha_inicio, fecha_fin, autodata);
            });
        }
        holder.n_tarjeta.setText("****    ****    ****    " + cards.get(position).getNumero_tarjeta().substring(cards.get(position).getNumero_tarjeta().length() - 4));
        holder.fecha_vencimiento.setText(cards.get(position).getFecha_vencimiento());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView n_tarjeta, fecha_vencimiento;
        ImageView delete;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cvCard);
            n_tarjeta = itemView.findViewById(R.id.card_number);
            fecha_vencimiento = itemView.findViewById(R.id.fecha_vencimiento);
            delete = itemView.findViewById(R.id.trash);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}