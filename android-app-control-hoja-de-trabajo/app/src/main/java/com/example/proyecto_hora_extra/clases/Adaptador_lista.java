package com.example.proyecto_hora_extra.clases;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_hora_extra.R;

import java.util.ArrayList;

public class Adaptador_lista extends RecyclerView.Adapter<Adaptador_lista.MyHolder> {
    public Activity activity;
    public ArrayList<objeto_info_card_view> datos;
    public static puente_dato puente_dato;
    public LayoutInflater layoutInflater;
    public Adaptador_lista(ArrayList<objeto_info_card_view> datos,Activity activity)
    {
    this.datos=datos;
    this.activity=activity;
    this.layoutInflater= LayoutInflater.from(activity);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.card_view,parent,false);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String agencia,estado,peticion,descripcion,direccion,fecha,hora,problema,fecha_final,hora_final;
        String corde[];
        agencia=datos.get(position).getAgencia();
        estado=datos.get(position).getEstado();
        peticion=datos.get(position).getPeticion();
        descripcion=datos.get(position).getDescripcion();
        direccion=datos.get(position).getDireccion();
        fecha=datos.get(position).getFecha();
        corde=datos.get(position).getCordenadas();
        hora=datos.get(position).getHora();
        problema=datos.get(position).getProblema();
        fecha_final=datos.get(position).getFecha_final();
        hora_final=datos.get(position).getHora_final();
        holder.agencia=agencia;
        if (estado.equalsIgnoreCase("en Proceso"))
        {
            holder.esta.setText(estado);
            holder.esta.setTextColor(Color.BLUE);
        }else
        {

            holder.esta.setText(estado);
            holder.esta.setTextColor(Color.GREEN);
        }
        holder.peticion.setText(peticion);
        holder.descripcion=descripcion;
        holder.direccion=direccion;
        holder.fecha=fecha;
        holder.hora=hora;
        holder.corde=corde;
        holder.n_emple_r=datos.get(position).getN_emple_r();
        holder.problema=problema;
        holder.fecha_final=fecha_final;
        holder.hora_final=hora_final;
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public TextView  esta,peticion;
        String hora,fecha,descripcion,direccion,n_emple_r,agencia,problema,fecha_final,hora_final;
        String corde[];

        public MyHolder(@NonNull View itemView) {
            super(itemView);


            this.esta=(TextView)itemView.findViewById(R.id.card_estdo);
            this.peticion=(TextView)itemView.findViewById(R.id.card_peticion);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            String estado=this.esta.getText().toString();
            String peticion=this.peticion.getText().toString();
            puente_dato.onItemClick(agencia,estado,peticion,descripcion,direccion,fecha,hora,corde,n_emple_r,problema,fecha_final,hora_final,esta);
        }
    }
    public void parada_adaptador_lista(puente_dato puente_dato)
    {
        Adaptador_lista.puente_dato=puente_dato;
    }

    public interface puente_dato{
        void  onItemClick(String agencia,String estado, String peticion,String descripcion,String direccion,String fecha,String hora,String corde[],String n_emple_r,String problema,String fecha_final,String hora_final,TextView esta);
    }

}
