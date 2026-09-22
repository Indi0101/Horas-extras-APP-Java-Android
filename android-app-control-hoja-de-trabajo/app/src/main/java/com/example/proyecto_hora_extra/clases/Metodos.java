package com.example.proyecto_hora_extra.clases;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_hora_extra.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Metodos {
    public Metodos()
    {}

    public RequestQueue requestQueue;
    public void insertar_datos_sql(Map<String,String> datos,String URL,Context context)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("PANINI  Insertar Mostrar:","Se guardo CON EXITO");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("PANINI  Insertar Mostrar:","Error al insertar");
            }
        }
        ){
            protected Map<String ,String> getParams(){
               // Map<String,String> parametros=new HashMap<>();
                //parametros.put("estado","En proceso");
                return datos;
            }
        };
        requestQueue=Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public String[] select_arrayString_una_fila(int i, String[] lista_columnas, String URL, Context context)
    {
        String lista_datos[]=new String[i];

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                    for(int x=0 ;x<lista_datos.length;x++)
                    {
                        lista_datos[x]=jsonObject.getString(lista_columnas[x]);
                    }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.v("PANINI","error conexion: "+error);
            }

        }
        );
        requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);

        return lista_datos;
    }

    public void ventana_Alerta(String msj,Context context)
    {
        AlertDialog.Builder aler=new AlertDialog.Builder(context);
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
}
