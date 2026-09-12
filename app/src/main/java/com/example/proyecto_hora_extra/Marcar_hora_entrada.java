package com.example.proyecto_hora_extra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_hora_extra.clases.Cordenadas;
import com.example.proyecto_hora_extra.clases.Ubicacion;
import com.example.proyecto_hora_extra.clases.objeto_lista_perfil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;

import com.example.proyecto_hora_extra.config.ApiConfig;

import static android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

public class Marcar_hora_entrada extends AppCompatActivity {

    private Button entrar;
    private TextView email,pass;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private RequestQueue requestQueue;
    private String id_empleado,valores_g[]=new String[2];
    private String nombre_empleado="",v="";
    private BiometricPrompt.PromptInfo promptInfo;
    private ImageView errEmail,errPass;
    public double[] d=new double[3];
    final Handler handler = new Handler();

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_hora_entrada);

        email=(TextView)findViewById(R.id.correo_login);
        pass=(TextView)findViewById(R.id.contra_login);
        entrar= (Button) findViewById(R.id.img_login);
        errEmail=(ImageView) findViewById(R.id.img_error1_login);
        errPass=(ImageView) findViewById(R.id.img_error2_login);

        errEmail.setImageAlpha(200);
        errPass.setImageAlpha(200);

        executor= ContextCompat.getMainExecutor(this);
        biometricPrompt=new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext()," Algo salio mal :"+errString,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                ///registrar_entrada("http://192.168.0.6/conexion_hora_extra/registrar_entrada_salida.php");
                Log.v("PANINI resultado aut",ApiConfig.endpoint("registrar_entrada_salida.php"));
                llamar_actividad_nueva(0);
            }
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext()," No dr reconoce la hueya dactilar ",Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo=new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Pantalla de autenticacion")
                .setSubtitle("Verifique su identidad")
                .setNegativeButtonText("Atras")
                .build();
        entrar.setOnClickListener(view -> {
            String v[]=trear_valores_txt();
                        if(v[0].isEmpty()||v[1].isEmpty()) {
                            alerta("Llene los campos vacíos.");
                        }else
                        {
                            traer_usuarios( ApiConfig.BASE_URL
                                    + "traer_empleados_infa.php?email="
                                    + v[0]
                                    + "&con="
                                    + v[1]);
                        }
                });

        final Runnable r = new Runnable() {
            public void run() {
                verificar_campos_vacios();
                handler.postDelayed(this, 1000); 
            }
        };
        handler.postDelayed(r, 1000);
        verificar_sesion_anterior();
    }
    private void registrar_entrada(String URL){

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
               SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  , HH:mm:ss", Locale.getDefault());
               Date date = new Date();
               String en="entrada";
               String fecha_hora_inicio=dateFormat.format(date);
               String f_h[]=fecha_hora_inicio.split(",");
               Map<String,String> parametros=new HashMap<>();
               parametros.put("estado",en);
               parametros.put("hora",f_h[1]);
               parametros.put("fecha",f_h[0]);
               parametros.put("emple_id",valores_g[1]);
               parametros.put("ubi",d[0]+":"+d[1]+":"+d[2]);
               return parametros;
           }
       };
           requestQueue=Volley.newRequestQueue(this);
           requestQueue.add(stringRequest);

    }
    private void traer_usuarios(String URL){
            JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jsonObject = null;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            id_empleado=jsonObject.getString("id_empleado");
                            nombre_empleado=jsonObject.getString("nombre_completo_empleado");
                            Log.v("PANINI traer_datos", "id empleado:"+id_empleado+" nombre:"+nombre_empleado);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    guardar_datos_emple_S(nombre_empleado+":"+id_empleado);
                    biometricPrompt.authenticate(promptInfo);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    alerta("El correo o la contraseña son inválidos");
                    Log.v("PANINI","error conexion: "+error);
                }
            }
            );
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(jsonArrayRequest);
    }
    public void verificar_sesion_anterior(){
        Log.e("PANINI verificar 10 ","verificar_sesion_anterior");
        valores_g=obtener_datos_emple_S().split(":");
        if(!valores_g[0].equalsIgnoreCase("nulo"))
        {
            Log.e("PANINI verificar 11 ","metodo if");
            llamar_actividad_nueva(1);
           // biometricPrompt.authenticate(promptInfo);
        }
    }
    public void verificar_campos_vacios(){
        String v[]=trear_valores_txt();
        if (v[0].isEmpty())
        {
            this.errEmail.setImageAlpha(200);
        }else{
            this.errEmail.setImageAlpha(0);
        }

        if (v[1].isEmpty())
        {
            this.errPass.setImageAlpha(200);
        }else{
            this.errPass.setImageAlpha(0);
        }
    }
    public String[] trear_valores_txt(){
            String  v[]= {email.getText().toString(),pass.getText().toString()};
           return v;
        }
    public AlertDialog alerta(String msj){
            AlertDialog.Builder builderAler =new AlertDialog.Builder(this);
            builderAler.setTitle("Alerta");
            builderAler.setIcon(R.drawable.ic_baseline_error_outline_24);
            builderAler.setMessage(msj);
            builderAler.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builderAler.show();
            return builderAler.create();
        }
   /* public void verificar_marca_entrada(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  , HH:mm:ss a", Locale.getDefault());
        Date date = new Date();
        String fecha_hora_inicio=dateFormat.format(date);
        String f_h[]=fecha_hora_inicio.split(",");
        String URL="http://192.168.0.6/conexion_hora_extra/verificar_existencia.php?n="+valores_g[1]+"&&t_m=entrada&&f="+f_h[0]+"";
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
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (!f.isEmpty())
                {
                    llamar_actividad_nueva(1);
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
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }*/
    public void guardar_datos_emple_S(String dato){
        SharedPreferences sharedPreferences=getSharedPreferences("user_datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("KEY",dato);
        editor.commit();
    }
    public String obtener_datos_emple_S(){
        SharedPreferences sharedPreferences=getSharedPreferences("user_datos",Context.MODE_PRIVATE);
        String dato=sharedPreferences.getString("KEY","NULO");
        return  dato;
    }
    public void llamar_actividad_nueva(int v){
        Log.e("PANINI llamar_actividad_nueva 13","metodo");
        Log.e("PANINI Biometric 15","metodo");
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        if(obtener_datos_emple_S().equalsIgnoreCase("nulo"))
        {
            Log.e("PANINI LOGIN",d[0]+":"+d[1]);
            intent.putExtra("N_EMP",nombre_empleado+":"+id_empleado);
            intent.putExtra("UBI",d);
            intent.putExtra("RRRR",v);
        }else
        {
            Log.e("PANINI LOGIN",d[0]+":"+d[1]);
            intent.putExtra("N_EMP",obtener_datos_emple_S());
            intent.putExtra("UBI",d);
            intent.putExtra("RRRR",v);
        }
        startActivity(intent);
    }
}