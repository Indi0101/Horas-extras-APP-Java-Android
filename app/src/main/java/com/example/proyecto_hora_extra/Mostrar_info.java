package com.example.proyecto_hora_extra;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.ExecutorDelivery;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.proyecto_hora_extra.config.ApiConfig;
import com.example.proyecto_hora_extra.firma_clase.CaptureBitmapView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import static org.opencv.imgproc.Imgproc.INTER_CUBIC;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.resize;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;

import java.util.ArrayList;
import java.util.List;

public class Mostrar_info extends AppCompatActivity {
    int STOP_HILO_SYSAID_DATOS = 0;
    int STOP_HILO_SYSAID_NOMBRE = 0;
    int STOP_HILO_SYSAID_GASTOS = 0;
    int STOP_HILO_SYSAID_CATE = 0;
    Button aceptar;
    TextView agencia;
    Button atras;
    String corde1;
    String corde2;
    String[] d;
    TextView descrip, problema;
    Dialog dialog_personalizado;
    Dialog dialog_firma = null;
    TextView direccion;
    String empleado_id = "";
    TextView fecha;
    String fecha_hora_inicio = "";
    Button finalizar;
    TextView hora;
    String[] id_nom = null;
    String id_peticion;
    String datos_finalizar_sysaid[];
    ArrayList<String> id_impleado_com = new ArrayList<>();
    ArrayList<String> lista_nombre_emple = new ArrayList<>();
    ArrayList<String> lista_contra_emple_firma = new ArrayList<>();
    ArrayList<String> lista_ca = new ArrayList<>();
    MapView mapView;
    MapController miMapaControl;
    RequestQueue requestQueue;
    private ArrayList<String> lista_firma = new ArrayList<>();
    private ArrayList<String> lista_nombre_firma = new ArrayList<>();
    String[] array_nombre_listos_sysaid = new String[5];
    public static String TAG = "PANINI";

    ///////////////////////////// 2026 ////////////////////////////
   private final ArrayList<Bitmap> firmasGerentesBitmap = new ArrayList<>();
   private final ArrayList<String> firmasGerentesNombre = new ArrayList<>();
   private boolean firmasCargadas = false;
   private static final double UMBRAL_FIRMA = 0.60;


    static {
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Error de inicialización");
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            /// ///nuevo 2026
        Configuration.getInstance().load(getApplicationContext(), getSharedPreferences("osmdroid", MODE_PRIVATE));

        Configuration.getInstance().setUserAgentValue("ProyectoHoraExtra/1.0 " + "(com.example.proyecto_hora_extra)");

        Log.e("MAPA_INFO", "UserAgent: " + Configuration.getInstance().getUserAgentValue());


        setContentView((int) R.layout.activity_mostrar_info);
        this.d = getIntent().getStringArrayExtra("KEY");
        this.dialog_personalizado = new Dialog(this);
        this.dialog_firma = new Dialog(this);

        this.agencia = (TextView) findViewById(R.id.agencia_mos_info);
        this.descrip = (TextView) findViewById(R.id.descriccion_mos_info);
        this.problema = (TextView) findViewById(R.id.problema_mos_info);
        this.direccion = (TextView) findViewById(R.id.direccion_mos_info);
        this.fecha = (TextView) findViewById(R.id.fecha_mos_info);
        this.hora = (TextView) findViewById(R.id.hora_mos_info);


        /// //////Nuevo 2026
        MapView mapView2 = (MapView) findViewById(R.id.mapa_mos_info);
        this.mapView = mapView2;
        mapView2.setTileSource(new XYTileSource("OpenStreetMap", 0, 19, 256, ".png",
                new String[]{"https://tile.openstreetmap.org/"}));

        mapView2.setMultiTouchControls(true);
        mapView2.setTilesScaledToDpi(true);

        this.atras = (Button) findViewById(R.id.atras_mos_info);
        this.aceptar = (Button) findViewById(R.id.acectar_mos_info);
        this.finalizar = (Button) findViewById(R.id.btn_finalizar_mos);
        this.agencia.setText(this.d[0]);
        String[] strArr = this.d;
        this.corde1 = strArr[1];
        this.corde2 = strArr[2];
        this.descrip.setText(strArr[3]);
        this.direccion.setText(this.d[4]);
        this.fecha.setText("Fecha:" + this.d[5]);
        this.hora.setText("Hora " + this.d[6]);
        String[] strArr2 = this.d;
        this.id_peticion = strArr2[7];
        this.id_nom = strArr2[8].split(":");
        this.problema.setText(d[9]);
        Log.e("PANINI VALOR ID", this.id_nom[0] + ":" + this.id_nom[1]);
        datos_finalizar_sysaid = new String[24];
        double d1 = Double.parseDouble(this.corde1);
        double d2 = Double.parseDouble(this.corde2);
        MapController mapController = (MapController) this.mapView.getController();
        this.miMapaControl = mapController;
        mapController.setZoom(18);
        GeoPoint geoPoint = new GeoPoint(d1, d2);
        Log.v("PANINI GEOPOINT", d1 + ";" + d2);
        this.miMapaControl.setCenter(geoPoint);
        Marker startMarker = new Marker(this.mapView);
        startMarker.setPosition(geoPoint);
        startMarker.setAnchor(0.5f, 1.0f);
        startMarker.setIcon(getDrawable(R.drawable.marcador));
        this.mapView.getOverlays().add(startMarker);
        obtener_lista_nombre_firma();
        this.atras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Mostrar_info.this.onBackPressed();
            }
        });
        this.aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Mostrar_info.this.ventana_asignar_tarea();
            }
        });
        this.finalizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hoja_de_servicio();
            }
        });
        obtener_lista_nombre_emple();
        lista_categoria();

    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public AlertDialog ventana_asignar_tarea() {
        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(this);
        this.fecha_hora_inicio = new SimpleDateFormat("EEEE dd MMMM yyyy - HH:mm:ss a", Locale.getDefault()).format(new Date());
        alerBuilder.setTitle((CharSequence) "Confirmado");
        alerBuilder.setMessage((CharSequence) "Tarea asignada");
        alerBuilder.setPositiveButton((CharSequence) "Listo", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Mostrar_info.this.onBackPressed();
            }
        });
        alerBuilder.setIcon((int) R.drawable.images);
        alerBuilder.show();
        if (this.id_peticion.isEmpty() || this.id_peticion == null) {
            Log.v("PANINI MOSTREAR_INFO_VENTANA", "estado peticion NO MODIFICADO");
        } else {
            insertar_estado_peticion(ApiConfig.endpoint("insertar_estado_peticion.php") + "?id=" + this.id_peticion + "", "En proceso", this.id_nom[1]);
            Log.wtf("PANINI MOSTREAR_INFO_VENTANA", "estado peticion modificado");
        }
        return alerBuilder.create();
    }

    public AlertDialog ventana_de_mensaje(String titulo, String msj) {
        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(this);
        alerBuilder.setTitle(titulo);
        alerBuilder.setMessage(msj);
        alerBuilder.setPositiveButton("Ok", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alerBuilder.show();
        return alerBuilder.create();
    }

    public AlertDialog alerta_confirmacion_tarea_finalizada() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setIcon((int) R.drawable.images);
        alertDialog.setTitle((CharSequence) "Confimado");
        alertDialog.setPositiveButton((CharSequence) "Listo", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Mostrar_info.this.onBackPressed();
            }
        });
        alertDialog.show();
        return alertDialog.create();
    }

    public ArrayList<Bitmap> bitmaps_firmas() {
        ArrayList<Bitmap> bit = new ArrayList<>();

        if (!lista_firma.isEmpty()) {
            Log.e("PANINI", "bitmap :if  ");
            for (int x = 0; x < lista_firma.size(); x++) {
                Log.e("PANINI", "bitmap : Metodo dentro for");


                int finalX = x;
                Glide.with(getApplicationContext())
                        .asBitmap()
                        .load(ApiConfig.endpoint("firmas_img/") + lista_firma.get(x) + "")
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                bit.add(resource);
                                ///   Log.e("PANINI","bitmap : "+bit.get(finalX));
                            }
                        });
            }
        } else {
            obtener_lista_nombre_firma();
            Log.e("panini", "lista vacias");

        }

        return bit;

    }

    public void juntar_datos_sysaid(EditText[] datos, EditText[] datos2, String selectedtext, String text_sector) {
        String valores_datos2[] = new String[datos2.length];
        String valores_datos1[] = new String[datos.length];

        for (int x = 0; x < datos2.length; x++) {
            if (datos2[x].getText().toString().isEmpty()) {
                valores_datos2[x] = "null";
            } else {
                valores_datos2[x] = datos2[x].getText().toString();
            }
        }
        for (int i = 0; i < datos.length; i++) {
            if (datos[i].getText().toString().isEmpty()) {
                valores_datos1[i] = "null";
            } else {
                valores_datos1[i] = datos[i].getText().toString();
            }
        }
        datos_finalizar_sysaid[0] = valores_datos2[0];
        datos_finalizar_sysaid[4] = valores_datos1[1];
        datos_finalizar_sysaid[8] = valores_datos1[5];
        datos_finalizar_sysaid[12] = array_nombre_listos_sysaid[2];
        datos_finalizar_sysaid[1] = valores_datos2[1];
        datos_finalizar_sysaid[5] = valores_datos1[2];
        datos_finalizar_sysaid[9] = valores_datos2[3];
        datos_finalizar_sysaid[13] = array_nombre_listos_sysaid[3];
        datos_finalizar_sysaid[2] = valores_datos2[2];
        datos_finalizar_sysaid[6] = valores_datos1[3];
        datos_finalizar_sysaid[10] = array_nombre_listos_sysaid[0];
        datos_finalizar_sysaid[14] = array_nombre_listos_sysaid[4];
        datos_finalizar_sysaid[3] = valores_datos1[0];
        datos_finalizar_sysaid[7] = valores_datos1[4];
        datos_finalizar_sysaid[11] = array_nombre_listos_sysaid[1];
        datos_finalizar_sysaid[15] = valores_datos1[7];

        datos_finalizar_sysaid[16] = valores_datos1[8];
        datos_finalizar_sysaid[17] = text_sector;
        datos_finalizar_sysaid[18] = valores_datos2[9];
        datos_finalizar_sysaid[19] = valores_datos2[10];
        datos_finalizar_sysaid[20] = valores_datos2[11];
        datos_finalizar_sysaid[21] = valores_datos2[12];
        datos_finalizar_sysaid[22] = selectedtext;
        for (String str : datos_finalizar_sysaid) {
            Log.e("Juntar datos METODO", "" + str);
        }

    }

    public String get_fecha_actual() {
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        return thisDate;
    }

    public void ventana_firma() {
        this.dialog_firma.setContentView(R.layout.ventana_alerta_firma);
        CaptureBitmapView mSig = null;
        LinearLayout mContent = null;
        AutoCompleteTextView nom_user_firma;
        EditText pass_user_firma;
        TextView msj_firma_correcto, msj_firma_incorrecto, msj_intentos_fallidos;
        Button limpiar, salir, listo;
        limpiar = (Button) dialog_firma.findViewById(R.id.limpiar_firma);
        final int[] intentos = {3};
        msj_firma_correcto = (TextView) dialog_firma.findViewById(R.id.txt_msj_firma);
        msj_firma_incorrecto = (TextView) dialog_firma.findViewById(R.id.msj_intentos_firma);
        msj_intentos_fallidos = (TextView) dialog_firma.findViewById(R.id.msj_intentos_fallidas);
        nom_user_firma = (AutoCompleteTextView) dialog_firma.findViewById(R.id.nom_user_firma);
        pass_user_firma = (EditText) dialog_firma.findViewById(R.id.pass_user_firma);
        salir = (Button) dialog_firma.findViewById(R.id.salir_firma);
        listo = (Button) dialog_firma.findViewById(R.id.veri_firma);
        cargarFirmasGerentes(listo,msj_firma_correcto);
        msj_intentos_fallidos.setVisibility(View.GONE);
        nom_user_firma.setVisibility(View.GONE);
        pass_user_firma.setVisibility(View.GONE);
        mContent = (LinearLayout) dialog_firma.findViewById(R.id.signLayout);
        mSig = new CaptureBitmapView(getApplicationContext(), null);
        mContent.addView(mSig, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        ArrayList<Bitmap> bit_img_lista = new ArrayList<>();
        bit_img_lista = bitmaps_firmas();
        CaptureBitmapView finalMSig = mSig;
        final boolean[] modoCredenciales = {false};

        String nombre_firma[] = new String[lista_nombre_firma.size()];
        for (int x = 0; x < nombre_firma.length; x++) {
            nombre_firma[x] = lista_nombre_firma.get(x);
        }
        ArrayAdapter<String> adptador_nombre = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, nombre_firma);
        nom_user_firma.setAdapter(adptador_nombre);
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalMSig.ClearCanvas();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_firma.cancel();
            }
        });
        CaptureBitmapView finalMSig1 = mSig;
        ArrayList<Bitmap> finalBit_img_lista = bit_img_lista;

        listo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {

                /**
                  int resultado = 0;
                Bitmap img_firma = finalMSig1.getBitmap();
                String nom_firms_encargado = "";

                for (int x = 0; x < finalBit_img_lista.size(); x++) {
                    resultado = comparar_bitmap(img_firma, finalBit_img_lista.get(x));

                    if (resultado == 1) {
                        nom_firms_encargado = lista_nombre_firma.get(x);
                        break;
                    }
                }
                if (resultado == 1) {
                    Log.e("PANINI: ", "SON Iguales las firmas");
                    msj_firma_correcto.setText("¡Correcto! \ndatos guardados");
                    Log.e("PANINI datos a peticion: ", "" + datos_finalizar_sysaid[2] + " : " + datos_finalizar_sysaid[4] + " : " + datos_finalizar_sysaid[10]);
                    datos_finalizar_sysaid[23] = nom_firms_encargado;
                    finalizar_tarea();
                }

                if (resultado == 0) {
                    Log.e(TAG, "resultado en (no son =):" + resultado);
                    intentos[0]--;
                    msj_firma_incorrecto.setText("Intentos: " + intentos[0]);
                    msj_firma_incorrecto.setTextColor(Color.RED);
                    if (intentos[0] == 0) {
                        msj_intentos_fallidos.setAlpha(200);
                        nom_user_firma.setAlpha(200);
                        pass_user_firma.setAlpha(200);
                    }
                }


                if (intentos[0] == 0) {
                    intentos[0] = 3;
                    msj_firma_incorrecto.setText("");
                    msj_firma_incorrecto.setAlpha(0);
                }
                 **/
                if (!modoCredenciales[0]) {

                    if (!firmasCargadas) {
                        Toast.makeText(Mostrar_info.this, "Espere a que carguen las firmas", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Bitmap firmaNueva = finalMSig1.getBitmap();
                    String gerente = reconocerFirma(firmaNueva);

                    if (gerente != null) {
                        Log.i("FIRMA", "Firma reconocida: " + gerente);

                        msj_firma_correcto.setText("Firma reconocida");

                        datos_finalizar_sysaid[23] = gerente;
                        finalizar_tarea();
                        return;
                    }
                    /*
                     * Firma incorrecta.
                     */
                    intentos[0]--;
                    finalMSig1.ClearCanvas();
                    Log.i("FIRMA", "Firma no reconocida. Quedan " + intentos[0] + " intentos");

                    if (intentos[0] > 0) {
                        msj_firma_incorrecto.setText("Firma no reconocida. Intentos: " + intentos[0]);
                        msj_firma_incorrecto.setTextColor(Color.RED);

                    } else {

                        /*
                         * Agotó sus tres intentos.
                         */
                        modoCredenciales[0] = true;
                        msj_firma_incorrecto.setText("Intentos agotados");
                        msj_intentos_fallidos.setVisibility(View.VISIBLE);
                        nom_user_firma.setVisibility(View.VISIBLE);
                        pass_user_firma.setVisibility(View.VISIBLE);
                        listo.setText("Autorizar");
                    }
                    return;
                }

                if (modoCredenciales[0]) {

                    String nom = nom_user_firma.getText().toString().trim();

                    String pas = pass_user_firma.getText().toString().trim();

                    if (nom.isEmpty() || pas.isEmpty()) {
                        Toast.makeText(Mostrar_info.this, "Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    validar_gerente(nom, pas);
                }

            }
        });
        dialog_firma.show();
        // this.dialog_firma.getWindow().setLayout(700, 400);
        dialog_firma.setCancelable(false);
    }

    public String revisar_estado_trabajo() {
        String estado = "Disponible";
        if (datos_finalizar_sysaid.length > 0) {
            if (datos_finalizar_sysaid[22].equalsIgnoreCase("Resuelto por completo")) {
                estado = "Finalizada";
            }
        }
        return estado;
    }

    private void insertar_estado_peticion(String URL, String estado, String id_user) {
        final String str = id_user;
        final String str2 = estado;
        StringRequest stringRequest = new StringRequest(1, URL, new Response.Listener<String>() {
            public void onResponse(String response) {
                Log.v("PANINI  modificar estado:", "Se MODIFICO CON EXITO");
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.v("PANINI  modificar estado:", "Error al Modificar");
            }
        }) {
            public Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("id_emple", str);
                parametros.put("estado", str2);
                return parametros;
            }
        };
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        this.requestQueue = newRequestQueue;
        newRequestQueue.add(stringRequest);
    }

    private void actualizar_datos_peticion(String d[]) {
        int id_ = Integer.parseInt(this.id_peticion);
        String URL = ApiConfig.endpoint("actualizar_peticion.php") + "?id_peticion=" + id_ + "";
        String id_emple = "";
        for (int x = 0; x < lista_nombre_emple.size(); x++) {
            if (lista_nombre_emple.get(x).equalsIgnoreCase(d[2])) {
                id_emple = id_impleado_com.get(x);
            }
        }
        String finalId_emple = id_emple;
        StringRequest stringRequest = new StringRequest(1, URL, new Response.Listener<String>() {
            public void onResponse(String response) {
                Log.v("PANINI  modificar estado:", "Se MODIFICO CON EXITO  LA PAETICION");
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.v("PANINI  modificar estado:", "Error al Modificar");
            }
        }) {
            public Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("fecha", d[0]);
                parametros.put("hora", d[1]);
                parametros.put("id_emple", finalId_emple);
                parametros.put("estado", d[3]);
                return parametros;
            }
        };
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        this.requestQueue = newRequestQueue;
        newRequestQueue.add(stringRequest);
    }

    public void cargar_formulario() {
        if (!this.empleado_id.isEmpty()) {
            ///  insertar_hoja_de_servicio();
            insertar_estado_peticion(ApiConfig.endpoint("insertar_estado_peticion.php") + "?id=" + this.id_peticion + "", "Finalizada", this.id_nom[1]);
            alerta_confirmacion_tarea_finalizada();
            return;
        }
    }

    public void verificar_empleado(String URL) {
        Log.v("PANINI VER URL", URL + "");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Mostrar_info.this.empleado_id = jsonObject.getString("id_empleado");
                        Log.v("PANINI VERIFICAR empleado", "EMPLEADO ENCONTRADA:" + Mostrar_info.this.empleado_id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Mostrar_info.this.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                Mostrar_info.this.cargar_formulario();
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.v("PANINI", "error conexion: Verificar empleado" + error);
            }
        });
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        this.requestQueue = newRequestQueue;
        newRequestQueue.add(jsonArrayRequest);
    }

    public void insertar_hoja_de_servicio() {
        try {
            String URL = ApiConfig.endpoint("insertar_hoja_de_servicio.php");
            StringRequest stringRequest = new StringRequest(1, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("PANINI   Insertar datos_hoja_de_servicio :", "Se guardo CON EXITO Tarea Finalizada");
                    dialog_firma.cancel();
                    dialog_personalizado.cancel();
                    ventana_de_mensaje_final("¡Listo!", "Datos guardados corectamente.");

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("PANINI  Insertar datos_hoja_de_servicio:", "Error al insertar TAREA FINALIZADA" + error);
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("agencia", datos_finalizar_sysaid[0]);
                    parametros.put("peti", datos_finalizar_sysaid[1]);
                    parametros.put("fecha", datos_finalizar_sysaid[2]);
                    parametros.put("h_ini", datos_finalizar_sysaid[3]);
                    parametros.put("h_fin", datos_finalizar_sysaid[4]);
                    parametros.put("titulo", datos_finalizar_sysaid[5]);
                    parametros.put("cate", datos_finalizar_sysaid[6]);
                    parametros.put("comen", datos_finalizar_sysaid[7]);
                    parametros.put("trabajo", datos_finalizar_sysaid[8]);
                    parametros.put("material", datos_finalizar_sysaid[9]);
                    parametros.put("n_respon", datos_finalizar_sysaid[10]);
                    parametros.put("n_conduc", datos_finalizar_sysaid[11]);
                    parametros.put("n_apo1", datos_finalizar_sysaid[12]);
                    parametros.put("n_apo2", datos_finalizar_sysaid[13]);
                    parametros.put("n_apo3", datos_finalizar_sysaid[14]);
                    parametros.put("h_salida", datos_finalizar_sysaid[15]);
                    parametros.put("h_retorno", datos_finalizar_sysaid[16]);
                    parametros.put("sector", datos_finalizar_sysaid[17]);
                    parametros.put("transporte", datos_finalizar_sysaid[18]);
                    parametros.put("alimen", datos_finalizar_sysaid[19]);
                    parametros.put("hospe", datos_finalizar_sysaid[20]);
                    parametros.put("t_gastos", datos_finalizar_sysaid[21]);
                    parametros.put("solu_trabajo", datos_finalizar_sysaid[22]);
                    parametros.put("n_geren_super", datos_finalizar_sysaid[23]);

                    return parametros;
                }
            };

            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            this.requestQueue = newRequestQueue;
            newRequestQueue.add(stringRequest);
        } catch (Exception e) {
            Log.e("PANINI   Insertar Sysaid :", "Error " + e);
        }

    }

    public void obtener_lista_nombre_emple() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(ApiConfig.endpoint("traer_nombre_emple.php"), new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                for (int x = 0; x < response.length(); x++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(x);
                        lista_nombre_emple.add(jsonObject.getString("nombre_completo_empleado"));
                        id_impleado_com.add(jsonObject.getString("id_empleado"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("PANINI MostrarInfo error_try traer_nombre_emple", "" + e);
                    }
                    Log.e("PANINI LISTA ID EMPLE ", "" + Mostrar_info.this.id_impleado_com.get(x));
                }
                for (int f = 0; f < Mostrar_info.this.lista_nombre_emple.size(); f++) {
                    Log.e("PANINI Mostrar_info nombre_lista ", "" + Mostrar_info.this.lista_nombre_emple.get(f));
                    ////  Log.e("PANINI Mostrar_info Lista BLOB img ", "" + Mostrar_info.this.lista_firmas_tipo_blob.get(f));
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.e("PANINI MostrarInfo ErrorResponse traer_nombre_emple", "" + error);
            }
        });
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        this.requestQueue = newRequestQueue;
        newRequestQueue.add(jsonArrayRequest);
    }

    public void onDestroy() {
        super.onDestroy();
        this.dialog_personalizado.dismiss();
    }

    public void hoja_de_servicio() {
        this.dialog_personalizado.setContentView(R.layout.ventana_alerta_finalizar);
        STOP_HILO_SYSAID_DATOS = 0;
        STOP_HILO_SYSAID_NOMBRE = 0;
        STOP_HILO_SYSAID_GASTOS = 0;
        STOP_HILO_SYSAID_CATE = 0;
        AutoCompleteTextView n_responsable, n_conductor, n_apo1, n_apo2, n_apo3, cate_pro;
        RadioGroup rg = (RadioGroup) dialog_personalizado.findViewById(R.id.radios_grupo);
        RadioGroup rg2 = (RadioGroup) dialog_personalizado.findViewById(R.id.radios_grupo_sector);
        RadioButton r_resuelto_comple, r_resuelto_par, r_queda_traba, r_no_se_resol, r_fuera_c, r_dentro_c;
        ImageView img_error_1, img_error_2, img_error_3, img_error_4, img_error_5,
                img_r_1, img_r_2, img_r_3, img_r_4, img_r_5;
        TextView h_ini_txt_datos, h_fin_txt_datos, titulo_txt_pro, cate_txt_pro, comen_txt_pro, tabajo_txt_pro, h_salida_txt_contol_h, h_retorno_txt_control_h, n_responsable_txt_pro;
        EditText agencia_datos, peticion_datos, f_inicio_datos, h_inicio_datos, h_final_datos,
                titulo_pro, comen_pro, trabajo_pro, mate_meteriales, h_salida_control_h, h_retorno_control,
                tras_gasto, ali_gastos, hospe_gastos, total_gastos;
        Button listo = (Button) dialog_personalizado.findViewById(R.id.btn_sysaid_listo);
        Button atras = (Button) dialog_personalizado.findViewById(R.id.btn_sysaid_salir);
        agencia_datos = (EditText) dialog_personalizado.findViewById(R.id.direc_datos);
        peticion_datos = (EditText) dialog_personalizado.findViewById(R.id.peticion_datos);
        f_inicio_datos = (EditText) dialog_personalizado.findViewById(R.id.fecha_ini_datos);
        h_inicio_datos = (EditText) dialog_personalizado.findViewById(R.id.h_ini_datos);
        h_final_datos = (EditText) dialog_personalizado.findViewById(R.id.h_fin_datos);
        titulo_pro = (EditText) dialog_personalizado.findViewById(R.id.tiulo_info_problema);
        cate_pro = (AutoCompleteTextView) dialog_personalizado.findViewById(R.id.categorias_info_problema);
        comen_pro = (EditText) dialog_personalizado.findViewById(R.id.comen_info_problema);
        trabajo_pro = (EditText) dialog_personalizado.findViewById(R.id.trabajo_info_problema);
        mate_meteriales = (EditText) dialog_personalizado.findViewById(R.id.mate_materiales);
        h_salida_control_h = (EditText) dialog_personalizado.findViewById(R.id.h_salida_contol_h);
        h_retorno_control = (EditText) dialog_personalizado.findViewById(R.id.h_retorno_control_h);
        tras_gasto = (EditText) dialog_personalizado.findViewById(R.id.trans_gastos);
        ali_gastos = (EditText) dialog_personalizado.findViewById(R.id.alimentacion_gastos);
        hospe_gastos = (EditText) dialog_personalizado.findViewById(R.id.hospe_gastos);
        total_gastos = (EditText) dialog_personalizado.findViewById(R.id.total_gastos);
        r_resuelto_comple = (RadioButton) dialog_personalizado.findViewById(R.id.radio_resu_comple);
        r_resuelto_par = (RadioButton) dialog_personalizado.findViewById(R.id.radio_resu_parci);
        r_queda_traba = (RadioButton) dialog_personalizado.findViewById(R.id.radio_trabajo_pendi);
        r_no_se_resol = (RadioButton) dialog_personalizado.findViewById(R.id.radio_no_resul);
        r_dentro_c = (RadioButton) dialog_personalizado.findViewById(R.id.radio_sector_dentro_c);
        r_fuera_c = (RadioButton) dialog_personalizado.findViewById(R.id.radio_sector_fuera_c);
        h_ini_txt_datos = (TextView) dialog_personalizado.findViewById(R.id.h_ini_textview_dato);
        h_fin_txt_datos = (TextView) dialog_personalizado.findViewById(R.id.h_fin_textview_dato);
        titulo_txt_pro = (TextView) dialog_personalizado.findViewById(R.id.titulo_textview_pro);
        cate_txt_pro = (TextView) dialog_personalizado.findViewById(R.id.cate_textview_pro);
        comen_txt_pro = (TextView) dialog_personalizado.findViewById(R.id.comen_textview_pro);
        tabajo_txt_pro = (TextView) dialog_personalizado.findViewById(R.id.trabajo_textview_pro);
        n_responsable_txt_pro = (TextView) dialog_personalizado.findViewById(R.id.n_txt_emple_res);
        h_salida_txt_contol_h = (TextView) dialog_personalizado.findViewById(R.id.h_salida_textview_control_h);
        h_retorno_txt_control_h = (TextView) dialog_personalizado.findViewById(R.id.h_retorno_textview_control_h);
        agencia_datos.setEnabled(false);
        peticion_datos.setEnabled(false);
        f_inicio_datos.setEnabled(false);
        total_gastos.setEnabled(false);
        trabajo_pro.setTextColor(Color.WHITE);

        n_responsable = (AutoCompleteTextView) dialog_personalizado.findViewById(R.id.n_emple_res);
        n_conductor = (AutoCompleteTextView) dialog_personalizado.findViewById(R.id.n_emple_conductor);
        n_apo1 = (AutoCompleteTextView) dialog_personalizado.findViewById(R.id.n_emple_apo1);
        n_apo2 = (AutoCompleteTextView) dialog_personalizado.findViewById(R.id.n_emple_apo2);
        n_apo3 = (AutoCompleteTextView) dialog_personalizado.findViewById(R.id.n_emple_apo3);

        img_error_1 = (ImageView) dialog_personalizado.findViewById(R.id.img_error_1);
        img_error_2 = (ImageView) dialog_personalizado.findViewById(R.id.img_error_2);
        img_error_3 = (ImageView) dialog_personalizado.findViewById(R.id.img_error_3);
        img_error_4 = (ImageView) dialog_personalizado.findViewById(R.id.img_error_4);
        img_error_5 = (ImageView) dialog_personalizado.findViewById(R.id.img_error_5);

        img_r_1 = (ImageView) dialog_personalizado.findViewById(R.id.img_r_1);
        img_r_2 = (ImageView) dialog_personalizado.findViewById(R.id.img_r_2);
        img_r_3 = (ImageView) dialog_personalizado.findViewById(R.id.img_r_3);
        img_r_4 = (ImageView) dialog_personalizado.findViewById(R.id.img_r_4);
        img_r_5 = (ImageView) dialog_personalizado.findViewById(R.id.img_r_5);

        agencia_datos.setText(agencia.getText());
        peticion_datos.setText(id_peticion);
        f_inicio_datos.setText(get_fecha_actual());
        n_responsable.setText(id_nom[0]);

        String[] n_emple = new String[lista_nombre_emple.size()];
        if (!lista_nombre_emple.isEmpty()) {
            for (int x = 0; x < lista_nombre_emple.size(); x++) {
                n_emple[x] = lista_nombre_emple.get(x);
            }
        }
        ArrayAdapter<String> adptador_textview = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, n_emple);
        n_responsable.setAdapter(adptador_textview);
        n_conductor.setAdapter(adptador_textview);
        n_apo1.setAdapter(adptador_textview);
        n_apo2.setAdapter(adptador_textview);
        n_apo3.setAdapter(adptador_textview);

        AutoCompleteTextView[] array_autocomple_textview = {n_responsable, n_conductor, n_apo1, n_apo2, n_apo3};
        ImageView[] array_img_error = {img_error_1, img_error_2, img_error_3, img_error_4, img_error_5};
        ImageView[] array_img_r = {img_r_1, img_r_2, img_r_3, img_r_4, img_r_5};
        for (ImageView img_er : array_img_error) {
            img_er.setImageAlpha(0);
        }
        for (ImageView img_ : array_img_r) {
            img_.setImageAlpha(0);
        }
        hilo_de_verificacion_nombre(array_autocomple_textview, array_img_error, array_img_r);

        String categorias[] = new String[lista_ca.size()];
        if (!lista_ca.isEmpty()) {
            for (int x = 0; x < lista_ca.size(); x++) {
                categorias[x] = lista_ca.get(x);
            }
        } else {
            Log.e("PANINI", "Lista CAtegorias, VAcia");
            lista_categoria();
        }
        ArrayAdapter<String> adptador_cate = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, categorias);
        cate_pro.setAdapter(adptador_cate);
        ///hilo_de_verificacion_categoria(cate_pro,cate_txt_pro);

        TextView txt[] = {h_ini_txt_datos, h_fin_txt_datos, titulo_txt_pro, cate_txt_pro, comen_txt_pro, tabajo_txt_pro, n_responsable_txt_pro, h_salida_txt_contol_h, h_retorno_txt_control_h};
        EditText datos[] = {h_inicio_datos, h_final_datos, titulo_pro, cate_pro, comen_pro, trabajo_pro, n_responsable, h_salida_control_h, h_retorno_control};
        EditText datos2[] = {agencia_datos, peticion_datos, f_inicio_datos, mate_meteriales, n_responsable, n_conductor, n_apo1, n_apo2, n_apo3, tras_gasto, ali_gastos, hospe_gastos, total_gastos};
        hilo_de_verificacion_datos_sysaid(datos, txt);

        ali_gastos.setText("0.0");
        tras_gasto.setText("0.0");
        hospe_gastos.setText("0.0");
        total_gastos.setText("0.0");
        hilo_de_suma_gastos(ali_gastos, tras_gasto, hospe_gastos, total_gastos);

        listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int con_check = 0;
                int con_datos = 0;
                String selectedtext = "";
                String selec_check_sector = "";
                if (!r_resuelto_comple.isChecked() && !r_resuelto_par.isChecked() && !r_queda_traba.isChecked() && !r_no_se_resol.isChecked()) {
                    con_check = con_check + 1;
                } else {
                    int radioButtonID = rg.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) rg.findViewById(radioButtonID);
                    selectedtext = (String) radioButton.getText();
                    Log.e("PANINI RADIO SELECT", "" + selectedtext);
                }
                if (!r_dentro_c.isChecked() && !r_fuera_c.isChecked()) {

                } else {
                    int radioBID = rg2.getCheckedRadioButtonId();
                    RadioButton r_Button = (RadioButton) rg2.findViewById(radioBID);
                    selec_check_sector = (String) r_Button.getText();
                    Log.e("PANINI RADIO SELECT", "" + selec_check_sector);
                }
                for (int x = 0; x < txt.length; x++) {
                    if (txt[x].getCurrentTextColor() == Color.RED) {
                        con_datos = con_datos + 1;
                    }
                }
                if (con_check + con_datos != 0) {
                    ventana_de_mensaje("Alerta, hay campos vacíos", "Debe llenar minimo los campos en rojo o verifique que se a seleccionado una Solucion de Trabajo");
                } else {
                    juntar_datos_sysaid(datos, datos2, selectedtext, selec_check_sector);
                    ventana_firma();
                }
            }
        });
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                STOP_HILO_SYSAID_DATOS = 1;
                STOP_HILO_SYSAID_NOMBRE = 1;
                STOP_HILO_SYSAID_GASTOS = 1;
                STOP_HILO_SYSAID_CATE = 1;
                dialog_personalizado.cancel();
            }
        });

        this.dialog_personalizado.show();
        this.dialog_personalizado.getWindow().setLayout(700, 1600);
        dialog_personalizado.setCancelable(false);
    }

    private void hilo_de_verificacion_categoria(AutoCompleteTextView cate_pro, TextView cate_txt) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (STOP_HILO_SYSAID_CATE == 0) {
                    if (lista_ca.isEmpty()) {
                        lista_categoria();
                    } else {
                        String cate = cate_pro.getText().toString();
                        for (int x = 0; x < lista_ca.size(); x++) {
                            if (lista_ca.get(x).equalsIgnoreCase(cate)) {
                                cate_txt.setTextColor(Color.WHITE);
                            } else {
                                cate_txt.setTextColor(Color.RED);
                            }
                        }
                    }
                    handler.postDelayed(this, 1000);
                } else {
                    handler.removeCallbacks(this);
                }
            }

        }, 1000);
    }

    private void hilo_de_suma_gastos(EditText ali_gastos, EditText tras_gasto, EditText hospe, EditText total_gastos) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (STOP_HILO_SYSAID_GASTOS == 0) {

                    String ali = ali_gastos.getText().toString();
                    String tras = tras_gasto.getText().toString();
                    String hos = hospe.getText().toString();
                    if (!ali.isEmpty() && !tras.isEmpty() && !hos.isEmpty()) {
                        double ali_ = Double.parseDouble(ali);
                        double tras_ = Double.parseDouble(tras);
                        double hos_ = Double.parseDouble(hos);
                        double t = ali_ + tras_ + hos_;
                        total_gastos.setText(t + "0");
                    } else {
                        total_gastos.setText("0.0");
                    }


                    handler.postDelayed(this, 1000);
                } else {
                    handler.removeCallbacks(this);
                }
            }

        }, 1000);
    }

    private void validar_datos_sysaid(EditText[] datos, TextView txt[]) {
        for (int x = 0; x < datos.length; x++) {
            if (datos[x].getText().toString().isEmpty()) {
                txt[x].setTextColor(Color.RED);
            } else {
                txt[x].setTextColor(Color.WHITE);
            }
        }
    }

    private void hilo_de_verificacion_nombre(AutoCompleteTextView lista_campos[], ImageView img_error[], ImageView img_r2[]) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (STOP_HILO_SYSAID_NOMBRE == 0) {
                    String n[] = new String[lista_campos.length];
                    for (int x = 0; x < lista_campos.length; x++) {
                        n[x] = lista_campos[x].getText().toString();
                    }
                    verificar_nombres(n, img_error, img_r2);
                    handler.postDelayed(this, 1000);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 1000);
    }

    public void verificar_nombres(String n[], ImageView imgE[], ImageView img_r[]) {
        String[] array_nombre = new String[n.length];
        String[] copia_nombre = n;
        String[] ori_nombre = n;
        for (int x = 0; x < ori_nombre.length; x++) {
            for (int y = 0; y < copia_nombre.length; y++) {
                if (x != y) {
                    if (!ori_nombre[x].isEmpty()) {
                        if (ori_nombre[x].equalsIgnoreCase(copia_nombre[y])) {
                            array_nombre[y] = "null";
                            img_r[y].setImageAlpha(200);
                        } else {
                            array_nombre[x] = ori_nombre[x];
                            img_r[x].setImageAlpha(0);
                        }
                    } else {
                        array_nombre[x] = "null";
                    }
                }
            }
        }

        for (int x2 = 0; x2 < array_nombre.length; x2++) {
            if (!array_nombre[x2].isEmpty()) {

                if (this.lista_nombre_emple.isEmpty()) {
                    obtener_lista_nombre_emple();
                } else {
                    for (int y2 = 0; y2 < this.lista_nombre_emple.size(); y2++) {
                        if (array_nombre[x2].equalsIgnoreCase(this.lista_nombre_emple.get(y2))) {
                            this.array_nombre_listos_sysaid[x2] = this.lista_nombre_emple.get(y2);
                            imgE[x2].setImageAlpha(0);
                            break;
                        } else {
                            this.array_nombre_listos_sysaid[x2] = "null";
                            if (array_nombre[x2].equalsIgnoreCase("null")) {
                                ///Log.v("PANINI : es","null");
                                imgE[x2].setImageAlpha(0);

                            } else {
                                imgE[x2].setImageAlpha(200);
                            }

                        }
                    }
                }
            } else {
                this.array_nombre_listos_sysaid[x2] = "null";
            }
            for (String g : array_nombre_listos_sysaid) {
                ////  Log.e("PANINI ","lista_final nombres: "+g);
            }
        }
    }

    private void hilo_de_verificacion_datos_sysaid(EditText datos[], TextView txt[]) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (STOP_HILO_SYSAID_DATOS == 0) {
                    validar_datos_sysaid(datos, txt);
                    handler.postDelayed(this, 1000);
                } else {
                    handler.removeCallbacks(this);
                }
            }

        }, 1000);
    }

    public void obtener_lista_nombre_firma() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(ApiConfig.endpoint("traer_firma_emple.php"), new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                for (int x = 0; x < response.length(); x++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(x);
                        lista_firma.add(jsonObject.getString("firma_emple"));
                        lista_nombre_firma.add(jsonObject.getString("nombre_completo_empleado"));
                        lista_contra_emple_firma.add(jsonObject.getString("contra_empleado"));
                        Log.e("PANINI ", "" + jsonObject.getString("firma_emple"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("PANINI MostrarInfo error_try traer_nombre_emple", "" + e);
                    }

                }

            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.e("PANINI MostrarInfo ErrorResponse traer_nombre_emple", "" + error);
            }
        });
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        this.requestQueue = newRequestQueue;
        newRequestQueue.add(jsonArrayRequest);
    }

    private int comparar_bitmap(Bitmap Bp1, Bitmap Bp2) {
        // Parte de importación de definición de datos
        Mat src1 = new Mat();
        Mat dst1 = new Mat();
        Mat src2 = new Mat();
        Mat dst2 = new Mat();
        // Leer mapa de bits en MAT
        Utils.bitmapToMat(Bp1, src1);
        Utils.bitmapToMat(Bp2, src2);
        // Cambiar ARGB a escala de grises, cuatro canales a un canal
        cvtColor(src1, dst1, Imgproc.COLOR_BGR2GRAY);
        cvtColor(src2, dst2, Imgproc.COLOR_BGR2GRAY);
        // Reducir la imagen en escala de grises a 8 * 8
        resize(dst1, dst1, new Size(8, 8), 0, 0, INTER_CUBIC);
        resize(dst2, dst2, new Size(8, 8), 0, 0, INTER_CUBIC);

        // Parte del algoritmo central
        // Se convierte en una matriz bidimensional para obtenerla con Mat.get (fila, cul), bidimensional porque cada píxel puede tener muchos atributos (ARGB)
        // Después de volverse gris, solo hay una G, esta G es gris y la G anterior es verde.
        double[][] data1 = new double[64][1];
        double[][] data2 = new double[64][1];
        // iAvg registra el valor de gris de píxel promedio, arr registra el valor de gris de píxel, los datos son un trampolín.
        int iAvg1 = 0, iAvg2 = 0;
        double[] arr1 = new double[64];
        double[] arr2 = new double[64];
        // obtener datos en gris, usar datos para recargar arr, calcular el valor de gris promedio iAvg.
        for (int i = 0; i < 8; i++) {
            int tmp = i * 8;
            for (int j = 0; j < 8; j++) {
                int tmp1 = tmp + j;
                data1[tmp1] = dst1.get(i, j);
                data2[tmp1] = dst2.get(i, j);
                arr1[tmp1] = data1[tmp1][0];
                arr2[tmp1] = data2[tmp1][0];
                iAvg1 += arr1[tmp1];
                iAvg2 += arr2[tmp1];
            }
        }
        iAvg1 /= 64;
        iAvg2 /= 64;
        // Compare el valor de gris de cada píxel con el valor de gris promedio
        for (int i = 0; i < 64; i++) {
            arr1[i] = (arr1[i] >= iAvg1) ? 1 : 0;
            arr2[i] = (arr2[i] >= iAvg2) ? 1 : 0;
        }
        // Calcula el valor de la diferencia
        int iDiffNum = 0;
        for (int i = 0; i < 64; i++)
            if (arr1[i] != arr2[i])
                ++iDiffNum;
        // La salida depende de las preferencias personales
        if (iDiffNum <= 1) {
            Log.e("PANINI", "Si son iguales ::metodo,comparacion");
            return 1;
            /////si se parece
        } else {
            Log.e("PANINI", "no son iguales::metodo,comparacion");
            return 0;
            /////no se parece
        }
    }

    public void lista_categoria() {
        String URL = ApiConfig.endpoint("traer_categoria.php");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                for (int x = 0; x < response.length(); x++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(x);
                        lista_ca.add(jsonObject.getString("nombre_categoria"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("PANINI MostrarInfo error_try nombre_categoria", "" + e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.e("PANINI MostrarInfo ErrorResponse nombre_categoria", "" + error);
            }
        });
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        this.requestQueue = newRequestQueue;
        newRequestQueue.add(jsonArrayRequest);

    }

    public int verificar_valores_datos_finalizar_sysaid() {
        int conta_ = 0;
        for (String s : datos_finalizar_sysaid) {
            if (s != null && s.isEmpty()) {
                Log.e("VAlore a guardar", "\n" + s);
                conta_++;
            }
            for (String str : datos_finalizar_sysaid) {
                Log.e("Juntar datos Metodo : chequiar Empty", "" + str);
            }
        }
        return conta_;
    }

    public AlertDialog ventana_de_mensaje_final(String titulo, String msj) {
        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(this);
        alerBuilder.setTitle(titulo);
        alerBuilder.setMessage(msj);
        alerBuilder.setPositiveButton("Ok", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        alerBuilder.show();
        alerBuilder.setCancelable(false);
        return alerBuilder.create();
    }

    /// /////////////////////////////////CODIGO NUEVO 2026 POR QUE NO TENGO EL RESPALDO TERMINADO/////////////////////////////////////
    private void finalizar_tarea() {
        String URL = ApiConfig.endpoint("finalizar_tarea.php");
        String estadoFinal = revisar_estado_trabajo();
        if (id_nom == null || id_nom.length < 2 || id_nom[1] == null || id_nom[1].trim().isEmpty()) {
            ventana_de_mensaje("ERROR", "No se pudo identificar al empleado.");
            return;
        }

        final String finalIdEmpleado = id_nom[1].trim();

        Log.e("FINALIZAR_TAREA", "Petición: " + id_peticion + " | Empleado: " + finalIdEmpleado + " | Estado: " + estadoFinal);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                response -> {
                    try {
                        JSONObject json = new JSONObject(response);
                        boolean ok = json.getBoolean("ok");
                        String mensaje = json.getString("mensaje");
                        if (ok) {
                            Log.i("FINALIZAR TAREA", "Finalizacion Correcta");

                            dialog_firma.cancel();
                            dialog_personalizado.cancel();
                            ventana_de_mensaje_final("¡Listo!", "Datos guardados corectamente: " + mensaje);
                        } else {
                            ventana_de_mensaje("ERROR", mensaje);
                        }

                    } catch (JSONException e) {
                        Log.e("FINALIZAR_TAREA", "RESPUESTA RAW DEL SERVIDOR: [" + response + "]", e);
                        ventana_de_mensaje("RESPUESTA DEL SERVIDOR", response);
                    }
                }, error -> {
            Log.e("FINALIZAR_TAREA", "Error de conexion", error);
            ventana_de_mensaje("ERROR DE CONEXION", "No feu posible finalizar la tarea");
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("agencia", datos_finalizar_sysaid[0]);
                parametros.put("peti", datos_finalizar_sysaid[1]);
                parametros.put("fecha", datos_finalizar_sysaid[2]);
                parametros.put("h_ini", datos_finalizar_sysaid[3]);
                parametros.put("h_fin", datos_finalizar_sysaid[4]);
                parametros.put("titulo", datos_finalizar_sysaid[5]);
                parametros.put("cate", datos_finalizar_sysaid[6]);
                parametros.put("comen", datos_finalizar_sysaid[7]);
                parametros.put("trabajo", datos_finalizar_sysaid[8]);
                parametros.put("material", datos_finalizar_sysaid[9]);
                parametros.put("n_respon", datos_finalizar_sysaid[10]);
                parametros.put("n_conduc", datos_finalizar_sysaid[11]);
                parametros.put("n_apo1", datos_finalizar_sysaid[12]);
                parametros.put("n_apo2", datos_finalizar_sysaid[13]);
                parametros.put("n_apo3", datos_finalizar_sysaid[14]);
                parametros.put("h_salida", datos_finalizar_sysaid[15]);
                parametros.put("h_retorno", datos_finalizar_sysaid[16]);
                parametros.put("sector", datos_finalizar_sysaid[17]);
                parametros.put("transporte", datos_finalizar_sysaid[18]);
                parametros.put("alimen", datos_finalizar_sysaid[19]);
                parametros.put("hospe", datos_finalizar_sysaid[20]);
                parametros.put("t_gastos", datos_finalizar_sysaid[21]);
                parametros.put("solu_trabajo", datos_finalizar_sysaid[22]);
                parametros.put("n_geren_super", datos_finalizar_sysaid[23]);
                parametros.put("id_emple", finalIdEmpleado);
                parametros.put("estado", estadoFinal);
                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void validar_gerente(String nombre, String password) {
        String URL = ApiConfig.endpoint("validar_gerente.php");
        Log.e("AUTORIZACION", "validado gerente" + nombre);

        StringRequest request = new StringRequest(Request.Method.POST,URL, response ->  {
            Log.e("AUTORIZACION_RESPUESTA", ""+response);
            try {
                JSONObject json = new JSONObject(response);
                boolean ok = json.getBoolean("ok");
                String mensaje = json.getString("mensaje");

                if (ok){
                    String gerente = json.getString("nombre");
                    datos_finalizar_sysaid[23]=gerente;
                    Log.e("AUTORIZACION", "Gerente Autorizado" + gerente);
                    Log.i("FIRMA", "Autorización por credenciales: " + gerente);
                    finalizar_tarea();
                    return;

                }else {
                    ventana_de_mensaje("Autorizacion", mensaje);
                }

            }catch (JSONException e)
            {
                Log.e("AUTorizacion","JSON incorrecto: "+response,e);
                ventana_de_mensaje("ERROR","ERespuesta invalida del servidor");
            }
        }, error -> {
            Log.e("AUTORIZACION", "Error volley",error);
            ventana_de_mensaje("Error", "No se pudo validar al gerente.");
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("nombre", nombre);
                parametros.put("password", password);

                return parametros;

            }

        };
        Volley.newRequestQueue(this).add(request);
    }

    private void cargarFirmasGerentes(Button botonVerificar, TextView mensaje)
    {
        firmasGerentesBitmap.clear();
        firmasGerentesNombre.clear();

        firmasCargadas = false;

        botonVerificar.setEnabled(false);

        mensaje.setText("Cargando firmas...");

        String url = ApiConfig.endpoint("traer_firma_emple.php");

        JsonArrayRequest request = new JsonArrayRequest(url, response -> {

                            if (response.length() == 0) {
                                mensaje.setText("No existen firmas de gerentes registradas");
                                return;
                            }

                            final int total = response.length();
                            final int[] terminadas = {0};

                            for (int i = 0; i < total; i++) {
                                try {
                                    JSONObject obj = response.getJSONObject(i);

                                    String nombre = obj.getString("nombre_completo_empleado");

                                    String archivo = obj.getString("firma_emple");

                                    String urlFirma = ApiConfig.endpoint("firmas_img/") + archivo;

                                    Glide.with(Mostrar_info.this).asBitmap().load(urlFirma).into(
                                                    new SimpleTarget<Bitmap>() {

                                                        @Override
                                                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                                            firmasGerentesBitmap.add(resource);

                                                            firmasGerentesNombre.add(nombre);

                                                            terminadas[0]++;

                                                            comprobarCargaFirmas(terminadas[0], total, botonVerificar, mensaje);
                                                        }

                                                        @Override
                                                        public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                                            terminadas[0]++;

                                                            Log.e("FIRMA", "No se pudo cargar firma de " + nombre);

                                                            comprobarCargaFirmas(terminadas[0],total, botonVerificar, mensaje);
                                                        }
                                                    });

                                } catch (JSONException e) {
                                    Log.e("FIRMA", "Error JSON", e);
                                }
                            }
                        }, error -> {

                            Log.e("FIRMA", "Error cargando firmas", error);
                            mensaje.setText("No fue posible cargar las firmas");
                        });

        Volley.newRequestQueue(this).add(request);
    }
    private void comprobarCargaFirmas(int terminadas, int total, Button boton, TextView mensaje) {

        if (terminadas == total) {
            firmasCargadas = !firmasGerentesBitmap.isEmpty();

            boton.setEnabled(firmasCargadas);

            if (firmasCargadas) {

                mensaje.setText("Dibuje la firma del encargado"
                );

                Log.i("FIRMA", "Firmas cargadas: " + firmasGerentesBitmap.size()
                );

            } else {
                mensaje.setText("No se pudo cargar ninguna firma");
            }
        }
    }
    private Mat normalizarFirma(Bitmap bitmap) {

        if (bitmap == null)
        { return null;}

        Mat original = new Mat();
        Utils.bitmapToMat(bitmap, original);
        Mat gris = new Mat();

        if (original.channels() == 4)
        {
            Imgproc.cvtColor(original,gris,Imgproc.COLOR_RGBA2GRAY);

        } else {

            Imgproc.cvtColor(original,gris,Imgproc.COLOR_BGR2GRAY);
        }
        Mat binaria = new Mat();
        Imgproc.threshold(gris,binaria,0,255,Imgproc.THRESH_BINARY_INV+ Imgproc.THRESH_OTSU);
        List<MatOfPoint> contornos = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(binaria.clone(),contornos,hierarchy,Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);

        if (contornos.isEmpty()) {
            return null;
        }

        int minX = binaria.cols();
        int minY = binaria.rows();
        int maxX = 0;
        int maxY = 0;

        boolean encontrado = false;

        for (MatOfPoint contorno : contornos) {

            if (Imgproc.contourArea(contorno) < 5) {
                continue;
            }

            Rect r = Imgproc.boundingRect(contorno);
            minX = Math.min(minX, r.x);
            minY = Math.min(minY, r.y);
            maxX =Math.max(maxX,r.x + r.width);
            maxY = Math.max(maxY,r.y + r.height);
            encontrado = true;
        }

        if (!encontrado) {
            return null;
        }

        int margen = 10;
        minX = Math.max(0, minX - margen);
        minY = Math.max(0, minY - margen);
        maxX = Math.min(binaria.cols(), maxX + margen);
        maxY = Math.min(binaria.rows(), maxY + margen);
        Rect region = new Rect(minX, minY, maxX - minX, maxY - minY);
        Mat recorte = new Mat(binaria, region);

        /*
         * Tamaño estándar para todas las firmas.
         */
        int anchoCanvas = 400;
        int altoCanvas = 200;
        int margenCanvas = 20;
        double escala = Math.min((anchoCanvas - margenCanvas * 2.0) / recorte.cols(), (altoCanvas - margenCanvas * 2.0) / recorte.rows());
        int nuevoAncho = Math.max(1, (int) Math.round(recorte.cols() * escala));
        int nuevoAlto = Math.max(1, (int) Math.round(recorte.rows() * escala));
        Mat redimensionada = new Mat();
        Imgproc.resize(recorte, redimensionada, new Size(nuevoAncho, nuevoAlto));

        /*
         * La volvemos binaria después
         * del cambio de tamaño.
         */
        Imgproc.threshold(redimensionada, redimensionada, 127, 255, Imgproc.THRESH_BINARY);
        Mat canvas = Mat.zeros(altoCanvas, anchoCanvas, CvType.CV_8UC1);
        int x = (anchoCanvas - nuevoAncho) / 2;
        int y = (altoCanvas - nuevoAlto) / 2;
        Mat destino = canvas.submat(new Rect(x, y, nuevoAncho, nuevoAlto));
        redimensionada.copyTo(destino);

        return canvas;
    }
    private double similitudFirma(Bitmap firmaNueva, Bitmap firmaGuardada) {

        Mat nueva =normalizarFirma(firmaNueva);
        Mat guardada =normalizarFirma(firmaGuardada);
        if (nueva == null ||guardada == null) {
            return 0;
        }
        int pixelesNueva = Core.countNonZero(nueva);
        int pixelesGuardada = Core.countNonZero(guardada);

        /*
         * Evita aceptar una firma vacía.
         */
        if (pixelesNueva < 100 ||pixelesGuardada < 100) {

            return 0;
        }

        /*
         * Aumentamos ligeramente el grosor
         * para tolerar pequeñas diferencias
         * de posición del trazo.
         */
        Mat kernel =Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE,new Size(7, 7));
        Mat nuevaDilatada =new Mat();
        Mat guardadaDilatada =new Mat();
        Imgproc.dilate(nueva,nuevaDilatada,kernel);
        Imgproc.dilate(guardada,guardadaDilatada,kernel);
        Mat coincidencia1 =new Mat();
        Mat coincidencia2 = new Mat();

        /*
         * ¿Cuánto del trazo nuevo cae
         * cerca del trazo guardado?
         */
        Core.bitwise_and(nueva,guardadaDilatada,coincidencia1);
        /*
         * ¿Cuánto del trazo guardado cae
         * cerca del nuevo?
         */
        Core.bitwise_and(guardada,nuevaDilatada,coincidencia2);

        double coberturaNueva =(double) Core.countNonZero(coincidencia1) /pixelesNueva;
        double coberturaGuardada =(double) Core.countNonZero(coincidencia2)/pixelesGuardada;
        return (coberturaNueva + coberturaGuardada) / 2.0;
    }
    private String reconocerFirma(Bitmap firmaNueva) {

        double mejorResultado = 0;
        String mejorGerente = null;

        for (int i = 0;i < firmasGerentesBitmap.size();i++) {

            double similitud =similitudFirma(firmaNueva,firmasGerentesBitmap.get(i));

            String gerente =firmasGerentesNombre.get(i);

            Log.i("FIRMA_SCORE", gerente + " -> " + similitud);

            if (similitud > mejorResultado) {

                mejorResultado = similitud;

                mejorGerente = gerente;
            }
        }

        Log.i("FIRMA_SCORE", "Mejor coincidencia: " + mejorGerente + " = " + mejorResultado);

        if (mejorResultado >= UMBRAL_FIRMA) {

            return mejorGerente;
        }

        return null;
    }

}