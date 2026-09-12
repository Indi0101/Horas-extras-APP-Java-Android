package com.example.proyecto_hora_extra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_hora_extra.clases.Ubicacion;
import com.example.proyecto_hora_extra.fragmentos.Frag_lista_tareas;
import com.example.proyecto_hora_extra.fragmentos.Frag_mapa;
import com.example.proyecto_hora_extra.fragmentos.Frag_perfil;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.osmdroid.config.Configuration;


public class MainActivity extends AppCompatActivity {

    public double d[]=new double[3];
    public String n_emple;
    public static ViewPager viewPager;
    private TabLayout tabLayout;
    public LocationManager manager;
    public Ubicacion ubicacion;
    private RequestQueue requestQueue;
    final android.os.Handler handler = new Handler();
    int v=01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("PANINI PANINI", "EJEMPLOOOOOOOOOOOOOOOO");
        Context ctx = getApplicationContext();

        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Configuration.getInstance().setUserAgentValue("ProyectoHoraExtra/1.0 " + "(com.example.proyecto_hora_extra)");

        Log.e("MAPA_OSM", "UserAgent: " + Configuration.getInstance().getUserAgentValue());

        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        viewPager = (ViewPager) findViewById(R.id.mypager);
        tabLayout =( TabLayout) findViewById(R.id.tab_layout);
        viewPager.setOffscreenPageLimit(3);
        Log.wtf("PANINI MainActivity","onCREATE");

        tabLayout.setupWithViewPager(viewPager);
        n_emple=getIntent().getStringExtra("N_EMP");
        if (n_emple!=null&&n_emple.isEmpty())
        {
            guardar_datos_emple_S(n_emple);
        }
        v=getIntent().getIntExtra("RRRR",01);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        }else
        {
            manager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
            ubicacion=new Ubicacion();
            ubicacion.setMainActivity(MainActivity.this);

            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(settingsIntent);
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            }

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, (LocationListener) ubicacion);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) ubicacion);
        }

        cargarViewPager(viewPager);
        final Runnable r = new Runnable() {
            public void run() {
                if (d[0]!=0.0&&d[1]!=0.0&&d[2]!=0.0)
                {
                   /// Log.e("PANINI MAinActivity","hay datos en ubi");
                    //actualizar();
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(r, 1000);
      ////  actualizar_ubi();
    }
    public void cargarViewPager(ViewPager viewPager) {
        Log.e("PANINI cargarViewPager","metodo");
        String s="si";
        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(getSupportFragmentManager(),0);
        myPagerAdapter.addFragment(Frag_lista_tareas.f_lista_tareas(obtener_datos_emple_S(),d),"Tareas pedientes");
        myPagerAdapter.addFragment(Frag_mapa.f_mapa(obtener_datos_emple_S(),d),"Mapa");
        myPagerAdapter.addFragment(Frag_perfil.frag_perfil(obtener_datos_emple_S(),d),"Perfil");
        viewPager.setAdapter(myPagerAdapter);
    }
    private class MyPagerAdapter extends FragmentStatePagerAdapter {
            ArrayList<Fragment> lista_frament=new ArrayList<>();
            ArrayList<String> lista_titulos=new ArrayList<>();

        public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return lista_frament.get(position);
        }
        @Override
        public int getCount() {
            return lista_frament.size();
        }
        public void addFragment(Fragment fragment, String title) {
            lista_frament.add(fragment);
            lista_titulos.add(title);
        }
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return lista_titulos.get(position);
        }
    }
    public String obtener_direccion(double[] corde){
        String de="",ci="",sec="",direccion="";

       Geocoder geoc=new Geocoder(this,Locale.getDefault());
        ArrayList<Address> inf=new ArrayList<>();
        Log.v("Panini Sacara direecio 3",""+corde[1]);
        for (double f:corde)
        {
            if(f==0.0)
            {
                return "no hay datos";
            }
        }
           try {
               inf= (ArrayList<Address>) geoc.getFromLocation(corde[0],corde[1],1);

              ci=inf.get(0).getLocality();
              sec=inf.get(0).getSubLocality();
              de=inf.get(0).getFeatureName();
              direccion=ci+", "+sec+", "+de;
           } catch (IOException e) {
               e.printStackTrace();
           }
        return direccion;
    }
    public void onBackPressed(){
        AlertDialog.Builder aleBuilder = new AlertDialog.Builder(this);
        aleBuilder.setMessage("¿Desea salir de la app?");
        aleBuilder.setTitle("Aviso");
        aleBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        aleBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
           dialog.cancel();
            }
        });
       /// AlertDialog alertDialog =aleBuilder.create();
        aleBuilder.show();
    }
    public void guardar_datos_emple_S(String dato) {
        SharedPreferences sharedPreferences=getSharedPreferences("user_datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("KEY",dato);
        editor.commit();
    }
    public String obtener_datos_emple_S() {
        SharedPreferences sharedPreferences=getSharedPreferences("user_datos",Context.MODE_PRIVATE);
        String dato=sharedPreferences.getString("KEY","NULO");
        return  dato;
    }
    public void setUbicacion(Location location)
    {
        asignar_ubicacion(location);
    }
    private void registrar_entrada(String URL){
        Log.e("PANINI MAinactivity :","registrar_entrada");
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
                double dd[]=ubi_r();
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
                parametros.put("ubi",dd[0]+":"+dd[1]+":"+dd[2]);
                return parametros;
            }
        };
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void asignar_ubicacion(Location location){
        d[0]=location.getLatitude();
        d[1]=location.getLongitude();
        d[2]=location.getAltitude();
        if(obtener_Ubi_S().equalsIgnoreCase("null"))
        {
            if (d[0]!=0.0&&d[1]!=0.0&&d[2]!=0.0)
            {
               /// ubi_guardar_S(d[0]+":"+d[1]+":"+d[2]);
            }
        }
    ///   Log.v("Panini set_Ubucacion Activity ",d[0]+" : "+d[1]+" : "+d[2]);
    }
    public double[] ubi_r() {
        Log.e("Panini return d Activity ",d[0]+" : "+d[1]+" : "+d[2]);
        return d;
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
    public void ubi_guardar_S(String dato) {
        SharedPreferences sharedPreferences=getSharedPreferences("ubi_datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("UBI",dato);
        editor.commit();
    }
    public String obtener_Ubi_S()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("ubi_datos",Context.MODE_PRIVATE);
        String dato=sharedPreferences.getString("UBI","null");
        return  dato;
    }
}