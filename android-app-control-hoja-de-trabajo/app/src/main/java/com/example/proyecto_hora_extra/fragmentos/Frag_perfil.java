package com.example.proyecto_hora_extra.fragmentos;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.example.proyecto_hora_extra.R;
import com.example.proyecto_hora_extra.clases.Adaptador_lista;
import com.example.proyecto_hora_extra.clases.Adaptador_perfil;
import com.example.proyecto_hora_extra.clases.Cordenadas;
import com.example.proyecto_hora_extra.clases.Ubicacion;
import com.example.proyecto_hora_extra.clases.objeto_info_card_view;
import com.example.proyecto_hora_extra.clases.objeto_lista_perfil;
import com.example.proyecto_hora_extra.config.ApiConfig;
import com.google.android.material.bottomnavigation.BottomNavigationPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;

public class Frag_perfil extends Fragment {
    @Nullable
    TextView t;
    double ubi[]=new double[3];
    public RecyclerView recyclerView;
    public Adaptador_perfil adapter;
    public LinearLayoutManager linearLayoutManager;
    public ArrayList<objeto_lista_perfil> datos;
    public TextView nombre_emple,hora_entrada;
    public Button btn;
    public String n_emple;
    public RequestQueue requestQueue;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private Executor executor;
    private HashMap<String,String> item_litsa=new HashMap<>();
    private ListView listView;
    private ArrayList<String> arrayList=new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    public double ubicacion[]=new double[3];
    public String datos_emple[]=null;
    int x=0;
    private SwipeRefreshLayout swipeRefreshLayout_recycleView;
    private SwipeRefreshLayout swipeRefreshLayout_listview;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragmento_perfil,container,false);

            swipeRefreshLayout_recycleView = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout_recycleView);
            swipeRefreshLayout_recycleView.setColorSchemeResources(R.color.rojo_infa);
            swipeRefreshLayout_recycleView.setProgressBackgroundColorSchemeResource(R.color.nigga);

            swipeRefreshLayout_listview = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout_listView);
            swipeRefreshLayout_listview.setColorSchemeResources(R.color.rojo_infa);
            swipeRefreshLayout_listview.setProgressBackgroundColorSchemeResource(R.color.nigga);

            datos=new ArrayList<>();
            recyclerView=(RecyclerView)v.findViewById(R.id.recicle_view_perfil);
            recyclerView.setHasFixedSize(true);
            linearLayoutManager=new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter =new Adaptador_perfil(datos,getActivity());
            recyclerView.setAdapter(adapter);

            listView= (ListView) v.findViewById(R.id.lista_perfil);
            arrayAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_activated_1,arrayList);
            listView.setAdapter(arrayAdapter);
            nombre_emple=(TextView)v.findViewById(R.id.nombre_emple_perfil);
            hora_entrada=(TextView)v.findViewById(R.id.hora_entrada_perfil);
            btn=(Button) v.findViewById(R.id.btn_salida_perfil);
            Log.wtf("PANINI Frag_perfil","onCREATE");
            String valores=getArguments().getString("KEY");
            ubi=getArguments().getDoubleArray("UBI");

            Bundle args = getArguments();

            if (args != null) {n_emple = args.getString("KEY", "");
            ubi = args.getDoubleArray("UBI");}
            if (n_emple != null && !n_emple.isEmpty()) {
                guardar_datos_emple_S(n_emple);}

            Log.e("PANINI P_PERFIL Ubi",valores+"   "+ubi[0]+":"+ubi[1]);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verificar_marca_entrada();
                }
            });
        datos_emple=obtener_datos_emple_S().split(":");
        llenar_listView(ApiConfig.endpoint("traer_horas_extra_aprobadas.php")+"?n_emple="+datos_emple[0]+"");
        cargar_reciclyView(ApiConfig.endpoint("seleccionar_tareas_finalizadas.php")+"?id="+datos_emple[1]+"");
        nombre_emple.setText(datos_emple[0]);
        traer_hora_entrada(ApiConfig.endpoint("verificar_existencia.php")+"?n="+datos_emple[1]+"&&t_m=entrada&&f="+fecha_hora("f")+"");

        executor= ContextCompat.getMainExecutor(getContext());
        biometricPrompt=new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                Log.v("PANINI Perfil","Autenticacion perfil");
                registrar_salida();
            }
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getContext()," No se reconoce la huella dactilar ",Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo=new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Pantalla de autenticacion")
                .setSubtitle("Verifique su identidad")
                .setNegativeButtonText("Atras")
                .build();

        swipeRefreshLayout_recycleView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                datos.clear();
                cargar_reciclyView(ApiConfig.endpoint("seleccionar_tareas_finalizadas.php")+"?id="+datos_emple[1]+"");
                swipeRefreshLayout_recycleView.setRefreshing(false);
            }
        });
        swipeRefreshLayout_listview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                llenar_listView(ApiConfig.endpoint("traer_horas_extra_aprobadas.php")+"?n_emple="+datos_emple[0]+"");
                swipeRefreshLayout_listview.setRefreshing(false);
            }
        });
        return  v;
    }
    public static Frag_perfil frag_perfil(String n_empleado,double[] d) {
        Frag_perfil frag_perfil=new Frag_perfil();
        Bundle b=new Bundle();
        b.putString("KEY",n_empleado);
        b.putDoubleArray("UBI",d);
        frag_perfil.setArguments(b);
        return frag_perfil;
    }
    public void cargar_reciclyView(String URL) {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jsonObject = null;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            datos.add(new objeto_lista_perfil(jsonObject.getString("id_peticiones"),jsonObject.getString("estado_peticion"),jsonObject.getString("fecha_final_peticion")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    recyclerView.getAdapter().notifyDataSetChanged();
                    swipeRefreshLayout_recycleView.setRefreshing(false);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("PANINI FRAG_Lista cargar_reciclyView","error conexion: "+error);
                }

            }
            );
            requestQueue= Volley.newRequestQueue(getContext());
            requestQueue.add(jsonArrayRequest);
    }
    public void llenar_listView(String URL) {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        arrayList.add("    "+jsonObject.getString("id_ingreso_hora_extra")+"                     "+jsonObject.getString("estado_ingreso_hora_extra")+"                    "+jsonObject.getString("pago_total_ingreso_hora_extra")+"Lps");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                listView.setAdapter(arrayAdapter);
                swipeRefreshLayout_listview.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PANINI FRAG_Lista ListView","error conexion: "+error);
            }
        }
        );
        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
    public void guardar_datos_emple_S(String dato) {
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("user_datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("KEY",dato);
        editor.commit();
    }
    public String obtener_datos_emple_S() {
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("user_datos",Context.MODE_PRIVATE);
        String dato=sharedPreferences.getString("KEY","NULO");
        return  dato;
    }
    public void traer_hora_entrada(String URL) {
        final String[] hora = {""};
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        hora[0] =jsonObject.getString("hora_marca_de_entrada_salida");
                        Log.e("PANINI PERFIL",hora[0]+"");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                hora_entrada.setText(hora[0]);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PANINI FRAG_PERFIL TREAR hora","error conexion: "+error);
            }
        }
        );
        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);

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
    public AlertDialog ventana_alerta(String msj) {
        AlertDialog.Builder alerta =new AlertDialog.Builder(getContext());
        alerta.setIcon(R.drawable.images);
        alerta.setTitle("Confirmado");
        alerta.setMessage(msj+"\n¿Desea salir de la app?");
        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alerta.show();
        return  alerta.create();
    }
    private void registrar_salida(){
        String URL =ApiConfig.endpoint("registrar_entrada_salida.php");
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("PANINI  Insertar Hora Salida:","Se guardo CON EXITO");
                ventana_alerta("Se marco corecctamente la hora de salida");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PANINI  Insertar Hora Salida:","Error al insertar");
                ventana_alerta("Error al marcar hora de salida");
            }
        }
        ){
            protected Map<String ,String> getParams(){
                String emple[]=obtener_datos_emple_S().split(":");

               String f=fecha_hora("f");
               String h=fecha_hora("h");
               String en="salida";

                Map<String,String> parametros=new HashMap<>();
                parametros.put("estado",en);
                parametros.put("hora",h);
                parametros.put("fecha",f);
                parametros.put("emple_id",emple[1]);
                parametros.put("ubi",ubi[0]+":"+ubi[1]+":"+ubi[2]);
                return parametros;
            }
        };

        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public void verificar_marca_entrada(){
       String f=fecha_hora("f");
       Log.e("panini  perfil","fecha:"+f);
       String emple[]=obtener_datos_emple_S().split(":");
        String URL=ApiConfig.endpoint("verificar_existencia.php")+"?n="+emple[1]+"&&t_m=salida&&f="+f+"";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                String f="";
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        f=jsonObject.getString("id_marca_de_entrada_salida");
                        Log.e("PANINI : Verificar>",f+"");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (!f.isEmpty())
                {
                   ventana_alerta("Usted ya a marcado hora de salida");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PANINI MARCAR ENTRADA","No existe este dato: "+error);
                biometricPrompt.authenticate(promptInfo);
            }
        }
        );
        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

}
