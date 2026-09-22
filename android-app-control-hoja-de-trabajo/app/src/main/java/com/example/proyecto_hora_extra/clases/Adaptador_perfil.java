package com.example.proyecto_hora_extra.clases;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_hora_extra.R;

import java.util.ArrayList;

public class Adaptador_perfil extends RecyclerView.Adapter<Adaptador_perfil.MyHolder> {
    public ArrayList<objeto_lista_perfil> datos;
    public Activity activity;
    public LayoutInflater layoutInflater;
    public Adaptador_perfil(ArrayList<objeto_lista_perfil>datos, Activity activity)
    {
        this.datos=datos;
        this.activity=activity;
        this.layoutInflater = LayoutInflater.from(activity);
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(R.layout.cards_perfil,parent,false);
        MyHolder myHolder= new MyHolder(v);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.id.setText(datos.get(position).getId());
        holder.estado.setText(datos.get(position).getestado());
        holder.fecha.setText(datos.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView id,estado,fecha;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.id_peticion_card_perfil);
            estado=(TextView)itemView.findViewById(R.id.estado_card_perfil);
            fecha=(TextView)itemView.findViewById(R.id.fecha_card_perfil);
        }
    }
}

