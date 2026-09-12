package com.example.proyecto_hora_extra.fragmentos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_hora_extra.Marcar_hora_entrada;
import com.example.proyecto_hora_extra.Mostrar_info;
import com.example.proyecto_hora_extra.R;
import com.example.proyecto_hora_extra.clases.Adaptador_lista;
import com.example.proyecto_hora_extra.clases.objeto_info_card_view;
import com.example.proyecto_hora_extra.config.ApiConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Frag_lista_tareas extends Fragment {
    @Nullable
    public RecyclerView recyclerView;
    public Adaptador_lista adaptador_lista;
    public ArrayList<objeto_info_card_view> objeto_info_card_viewArrayList;
    public LinearLayoutManager layoutManager;
    public String corde[];
    public String dato="";
    private RequestQueue requestQueue;
    public String n_emple=null;
    public double ubi_entrada[]=new double[3];
    public AlertDialog.Builder aler;
    private SwipeRefreshLayout swipeRefreshLayout;
    TextView t;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);

        View v=inflater.inflate(R.layout.fragmento_lista_tareas,container,false);
        Log.wtf("PANINI Frag_Lista","onCREATE");
        t=(TextView)v.findViewById(R.id.txt_lista);
        aler=new AlertDialog.Builder(getContext());

        n_emple=getArguments().getString("KEY");

        if (n_emple!=null&&!n_emple.isEmpty())
        {
            guardar_datos_emple_S(n_emple);
        }
        ubi_entrada=getArguments().getDoubleArray("UBI");
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.rojo_infa);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.nigga);
        objeto_info_card_viewArrayList=new ArrayList<>();
        recyclerView=(RecyclerView)v.findViewById(R.id.recicle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adaptador_lista=new Adaptador_lista(objeto_info_card_viewArrayList,getActivity());
        recyclerView.setAdapter(adaptador_lista);
       // recyclerView.getAdapter().notifyDataSetChanged();
        adaptador_lista.parada_adaptador_lista(new Adaptador_lista.puente_dato() {
            @Override
            public void onItemClick(String agencia, String estado, String peticion, String descripcion, String direccion,String fecha,String hora,String corde[],String n_emple_r,String problema,String f_final,String h_final,TextView esta) {
                String d[]={agencia,corde[0],corde[1],descripcion,direccion,fecha,hora,peticion,obtener_datos_emple_S(),problema};
                if(estado.equalsIgnoreCase("Finalizada"))
                {
                     ventana_Alerta("Esta tarea ya esta Finalizada");
                     Log.e("PANINI onClick adapter"," Finalizada ");
                }else{
                    Log.e("PANINI onClick adapter n_emple",obtener_datos_emple_S()+"");
                    String v[]=obtener_datos_emple_S().split(":");
                    if (estado.equalsIgnoreCase("En proceso")&&v[1].equalsIgnoreCase(n_emple_r))
                    {
                        esta.setTextColor(Color.GREEN);
                        Log.e("PANINI onClick adapter"," Proceso ");
                        Intent intent=new Intent(getContext(), Mostrar_info.class);
                        intent.putExtra("KEY",d);
                        Log.e("PANINI onClick adapter"," P "+obtener_datos_emple_S());
                        startActivity(intent);
                    }
                    else
                    {
                        if (estado.equalsIgnoreCase("Disponible"))
                        {

                            Log.e("PANINI onClick adapter ESTADO PETI"," Disponible ");
                            Intent intent=new Intent(getContext(), Mostrar_info.class);
                            Log.e("PANINI onClick adapter"," D "+obtener_datos_emple_S());
                            Log.e("PANINI onClick adapter","problema: "+d[9]);
                            intent.putExtra("KEY",d);
                            startActivity(intent);
                            return;
                        }
                        Log.e("PANINI onClick adapter"," Afuera ");
                        ventana_Alerta("Esta tarea ya esta Asignada");
                    }
                }
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                objeto_info_card_viewArrayList.clear();
                traer_datos(ApiConfig.endpoint("agencia_peticion.php"));
            }
        });
        traer_datos(ApiConfig.endpoint("agencia_peticion.php"));
        verificar_marca_entrada();
        return v;
    }
    public static Frag_lista_tareas f_lista_tareas(String txt,double d_ubi[])
    {
        Log.v("PANINI frag_lista",txt+"");
        Frag_lista_tareas lista_tareas = new Frag_lista_tareas();
        Bundle b=new Bundle();
        b.putString("KEY",txt);
        b.putDoubleArray("UBI",d_ubi);
        lista_tareas.setArguments(b);
        return lista_tareas;
    }
    public void ventana_Alerta(String msj)
    {
        aler.setTitle("Aviso");
        aler.setIcon(R.drawable.alto_24);
        aler.setMessage(msj);
        aler.setPositiveButton("Entiendo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        aler.show();
    }
    public void traer_datos(String URL)
    {
        Log.e("PANINI frag_lista ","traer_datos:");

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        String z=jsonObject.getString("cordenada_agencias");
                        corde=z.split(",");
                        objeto_info_card_viewArrayList.add(new objeto_info_card_view(jsonObject.getString("nombre_agencias"),jsonObject.getString("estado_peticion"), jsonObject.getString("id_peticiones"),jsonObject.getString("descripcion_peticion"),jsonObject.getString("direccion_agencias"),jsonObject.getString("fecha_peticion"),jsonObject.getString("hora_peticion"),corde,jsonObject.getString("emple_recibio_peticiones"),jsonObject.getString("nombre_problema"),jsonObject.getString("fecha_final_peticion"),jsonObject.getString("hora_final_peticion")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                Log.e("PANINI frag_lista","traer_datos : recyclerView cargado");
                recyclerView.getAdapter().notifyDataSetChanged();
                   swipeRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error de conexion :(",Toast.LENGTH_SHORT).show();
                Log.v("PANINI","error conexion: "+error);
            }
        }
        );
        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
    public void guardar_datos_emple_S(String dato)
    {
        Log.e("PANINI frag_lista","guardar_datos_emple_S");
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("user_datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("KEY",dato);
        editor.commit();
    }
    public String obtener_datos_emple_S()
    {
        Log.e("PANINI frag_lista","obtener_datos_emple_S");
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("user_datos",Context.MODE_PRIVATE);
        String dato=sharedPreferences.getString("KEY","NULO");
        return  dato;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    public void verificar_marca_entrada(){
        Log.e("PANINI frag_lista ","verificar_marca_entrada");
        final ArrayList<String> id_emple = new ArrayList<>();
        String f = fecha_hora("f");
        Log.e("panini  Frag_lista","fecha:"+ f);
        String emple[]=obtener_datos_emple_S().split(":");///17-04-2021  "+ f +"  
        String URL=ApiConfig.endpoint("verificar_existencia.php")+"?n="+emple[1]+"&&t_m=entrada&&f="+ f +"";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for(int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        id_emple.add(jsonObject.getString("id_marca_de_entrada_salida"));
                        Log.e("PANINI Frag_lista: Verificar>", id_emple.get(i) +": ID");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                    if(id_emple.isEmpty())
                    {
                        Log.e("PANINI frag_lista entrar a guardar","contador_");
                        registrar_entrada(ApiConfig.endpoint("registrar_entrada_salida.php"));}
                    else{
                        Log.e("PANINI frag_lista verificar_marca_entrada","ID_"+id_emple.get(0));
                        actualizar_ubicacion(id_emple.get(0));
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PANINI FragmentLista","No existe este dato: "+error);
                registrar_entrada(ApiConfig.endpoint("registrar_entrada_salida.php"));
            }
        }
        );
        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
    private void actualizar_ubicacion(String id) {
        String URL=ApiConfig.endpoint("actualizar_ubi.php");
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.e("PANINI Frag_lista actualizar Ubi","se actualizo");
        }
    }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PANINI Frag_lista actualizar Ubi",":"+error);
            }
        })
        {
            protected Map<String ,String> getParams(){
                Map<String,String> parametros=new HashMap<>();
                Log.e("PANINI Fragmen_lista actualizar ","Ubi: "+ubi_entrada[0]+":"+ubi_entrada[1]+":"+ubi_entrada[2]);
                parametros.put("ubi",ubi_entrada[0]+":"+ubi_entrada[1]+":"+ubi_entrada[2]);
                parametros.put("id",id);
                return parametros;
            }
        };
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    private void registrar_entrada(String URL){
        Log.e("PANINI Frag_lista :","registrar_entrada");
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("PANINI  Insertar Hora entrada:","Se guardo CON EXITO");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PANINI  Insertar Hora entrada:","Error al insertar");
            }
        }
        ){
            protected Map<String ,String> getParams(){
                String emple[]=obtener_datos_emple_S().split(":");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  , HH:mm:ss", Locale.getDefault());
                Date date = new Date();
                String en="entrada";
                String fecha_hora_inicio=dateFormat.format(date);
                String f_h[]=fecha_hora_inicio.split(",");
                Map<String,String> parametros=new HashMap<>();
                parametros.put("estado",en);
                parametros.put("hora",f_h[1]);
                parametros.put("fecha",f_h[0]);
                parametros.put("emple_id",emple[1]);
                parametros.put("ubi",ubi_entrada[0]+":"+ubi_entrada[1]+":"+ubi_entrada[2]);
                return parametros;
            }
        };
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public String fecha_hora(String s) {
        String d="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  , HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String fecha_hora_inicio=dateFormat.format(date);
        String f_h[]=fecha_hora_inicio.split(",");

        if (s.equalsIgnoreCase("f"))
        {
            d=f_h[0];
        }else
        {
            d=f_h[1];
        }
       return d;
    }

}
