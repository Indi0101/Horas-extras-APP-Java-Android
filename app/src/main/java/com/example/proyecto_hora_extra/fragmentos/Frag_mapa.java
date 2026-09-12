package com.example.proyecto_hora_extra.fragmentos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_hora_extra.R;
import com.example.proyecto_hora_extra.config.ApiConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import android.os.Handler;
import android.os.Looper;

import com.example.proyecto_hora_extra.MainActivity;
import org.osmdroid.tileprovider.tilesource.XYTileSource;

public class Frag_mapa extends Fragment {
    public MapView miMapa;
    public MapController miMapaControl;
    public String n_emple[]=null;
    public double ubi[]=new double[3];
    public ArrayList<String> n_agencia=new ArrayList<>();
    public ArrayList<String> direc_agencia=new ArrayList<>();
    public ArrayList<double[]> corde_agencia=new ArrayList<>();
    public ArrayList<String> n_em=new ArrayList<>();
    public ArrayList<double[]> corde_emple=new ArrayList<>();
    private Marker marcadorMiUbicacion;
    private boolean mapaCentrado = false;
    private final Handler handlerMapa =
            new Handler(Looper.getMainLooper());

    RequestQueue requestQueue;
    @Nullable
    TextView t;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("PANINI FRAG_MAPA", "EJEMPLOOOOOOOOOOOOOOOO");

        View v = inflater.inflate(
                R.layout.fragmento_mapa,
                container,
                false
        );

        Log.wtf("PANINI Frag_mapa", "onCREATE");

        Bundle args = getArguments();

        if (args != null) {

            String key = args.getString("KEY");

            if (key != null) {
                n_emple = key.split(":");
            }

            double[] ubiArgs =
                    args.getDoubleArray("UBI");

            if (ubiArgs != null) {
                ubi = ubiArgs;
            }
        }

        miMapa = v.findViewById(R.id.mapVista);

        Configuration.getInstance()
                .setUserAgentValue(
                        "ProyectoHoraExtra/1.0 " +
                                "(com.example.proyecto_hora_extra)"
                );

        miMapa.setTileSource(
                new XYTileSource(
                        "OpenStreetMap",
                        0,
                        19,
                        256,
                        ".png",
                        new String[]{
                                "https://tile.openstreetmap.org/"
                        }
                )
        );

        miMapa.setMultiTouchControls(true);
        miMapa.setTilesScaledToDpi(true);

        miMapaControl =
                (MapController) miMapa.getController();

        // Vista inicial Honduras
        GeoPoint centroInicial =
                new GeoPoint(
                        14.8,
                        -86.5
                );

        miMapaControl.setZoom(8);
        miMapaControl.setCenter(centroInicial);

        Log.e(
                "MAPA_OSM",
                "UserAgent: "
                        + Configuration.getInstance()
                        .getUserAgentValue()
        );

        // Comenzar actualización de ubicación una sola vez
        handlerMapa.post(actualizarMapa);

        // Cargar datos de BD
        marcar_mapa();

        return v;

    }

    private void marcar_mapa()
    {
        Log.e("PANINI f_mapa","marcar_mapa");
        marcar_agencias();
        marcar_empleados();
    }

    private void marcar_empleados() {

        String URL =
                ApiConfig.endpoint(
                        "traer_ubi_emple.php"
                )
                        + "?estado=salida";

        JsonArrayRequest request =
                new JsonArrayRequest(
                        URL,

                        response -> {

                            Log.e(
                                    "MAPA_DB",
                                    "Empleados recibidos: "
                                            + response.length()
                            );

                            n_em.clear();
                            corde_emple.clear();

                            for (
                                    int i = 0;
                                    i < response.length();
                                    i++
                            ) {

                                try {

                                    JSONObject obj =
                                            response.getJSONObject(i);

                                    String nombre =
                                            obj.optString(
                                                    "nombre_completo_empleado",
                                                    ""
                                            );

                                    String coordenadas =
                                            obj.optString(
                                                    "codenadas_marca_de_entrada_salida",
                                                    ""
                                            );

                                    Log.e(
                                            "MAPA_DB",
                                            "Empleado: "
                                                    + nombre
                                                    + " | Coordenada: ["
                                                    + coordenadas
                                                    + "]"
                                    );

                                    if (
                                            nombre.trim().isEmpty()
                                                    ||
                                                    coordenadas.trim().isEmpty()
                                    ) {

                                        Log.w(
                                                "MAPA_DB",
                                                "Registro empleado omitido"
                                        );

                                        continue;
                                    }

                                    String[] partes =
                                            coordenadas.split(":");

                                    if (partes.length < 2) {

                                        Log.w(
                                                "MAPA_DB",
                                                "Coordenada con formato inválido: "
                                                        + coordenadas
                                        );

                                        continue;
                                    }

                                    double lat =
                                            Double.parseDouble(
                                                    partes[0].trim()
                                            );

                                    double lon =
                                            Double.parseDouble(
                                                    partes[1].trim()
                                            );

                                    if (
                                            !coordenadaValida(
                                                    lat,
                                                    lon
                                            )
                                    ) {

                                        Log.w(
                                                "MAPA_DB",
                                                "Coordenada fuera de rango: "
                                                        + lat
                                                        + ", "
                                                        + lon
                                        );

                                        continue;
                                    }

                                    /*
                                     * IMPORTANTE:
                                     * nombre y coordenada se agregan juntos.
                                     */
                                    n_em.add(nombre);

                                    corde_emple.add(
                                            new double[]{
                                                    lat,
                                                    lon
                                            }
                                    );

                                } catch (
                                        JSONException |
                                        NumberFormatException e
                                ) {

                                    Log.e(
                                            "MAPA_DB",
                                            "Registro empleado inválido "
                                                    + i,
                                            e
                                    );
                                }
                            }

                            /*
                             * Dibujar empleados válidos.
                             */
                            for (
                                    int i = 0;
                                    i < corde_emple.size();
                                    i++
                            ) {

                                if (
                                        !isAdded()
                                                ||
                                                miMapa == null
                                ) {
                                    return;
                                }

                                double[] coordenada =
                                        corde_emple.get(i);

                                GeoPoint punto =
                                        new GeoPoint(
                                                coordenada[0],
                                                coordenada[1]
                                        );

                                Marker marker =
                                        new Marker(miMapa);

                                marker.setPosition(
                                        punto
                                );

                                marker.setAnchor(
                                        Marker.ANCHOR_CENTER,
                                        Marker.ANCHOR_BOTTOM
                                );

                                marker.setIcon(getResources().getDrawable(R.drawable.persona_emple, null));

                                marker.setTitle(
                                        n_em.get(i)
                                );

                                miMapa.getOverlays()
                                        .add(marker);
                            }

                            if (miMapa != null) {
                                miMapa.invalidate();
                            }
                        },

                        error -> {

                            Log.e(
                                    "MAPA_DB",
                                    "Error traer_ubi_emple.php",
                                    error
                            );
                        }
                );

        requestQueue =
                Volley.newRequestQueue(
                        requireContext()
                );

        requestQueue.add(request);
    }
    private void marcar_agencias() {
        String URL =
                ApiConfig.endpoint(
                        "traer_agencias_ubi.php"
                );

        JsonArrayRequest request =
                new JsonArrayRequest(
                        URL,

                        response -> {

                            Log.e(
                                    "MAPA_DB",
                                    "Agencias recibidas: "
                                            + response.length()
                            );

                            n_agencia.clear();
                            direc_agencia.clear();
                            corde_agencia.clear();

                            for (
                                    int i = 0;
                                    i < response.length();
                                    i++
                            ) {

                                try {

                                    JSONObject obj =
                                            response.getJSONObject(i);

                                    String nombre =
                                            obj.optString(
                                                    "nombre_agencias",
                                                    ""
                                            );

                                    String direccion =
                                            obj.optString(
                                                    "direccion_agencias",
                                                    ""
                                            );

                                    String coordenadas =
                                            obj.optString(
                                                    "cordenada_agencias",
                                                    ""
                                            );

                                    Log.e(
                                            "MAPA_DB",
                                            "Agencia: "
                                                    + nombre
                                                    + " | Coordenada: ["
                                                    + coordenadas
                                                    + "]"
                                    );

                                    if (
                                            coordenadas.trim()
                                                    .isEmpty()
                                    ) {

                                        Log.w(
                                                "MAPA_DB",
                                                "Agencia sin coordenadas"
                                        );

                                        continue;
                                    }

                                    String[] partes =
                                            coordenadas.split(",");

                                    if (partes.length < 2) {

                                        Log.w(
                                                "MAPA_DB",
                                                "Formato inválido: "
                                                        + coordenadas
                                        );

                                        continue;
                                    }

                                    double lat =
                                            Double.parseDouble(
                                                    partes[0].trim()
                                            );

                                    double lon =
                                            Double.parseDouble(
                                                    partes[1].trim()
                                            );

                                    if (
                                            !coordenadaValida(
                                                    lat,
                                                    lon
                                            )
                                    ) {

                                        Log.w(
                                                "MAPA_DB",
                                                "Coordenada agencia inválida: "
                                                        + lat
                                                        + ", "
                                                        + lon
                                        );

                                        continue;
                                    }

                                    n_agencia.add(nombre);
                                    direc_agencia.add(direccion);

                                    corde_agencia.add(
                                            new double[]{
                                                    lat,
                                                    lon
                                            }
                                    );

                                } catch (
                                        JSONException |
                                        NumberFormatException e
                                ) {

                                    Log.e(
                                            "MAPA_DB",
                                            "Registro agencia inválido "
                                                    + i,
                                            e
                                    );
                                }
                            }

                            for (
                                    int i = 0;
                                    i < corde_agencia.size();
                                    i++
                            ) {

                                if (
                                        !isAdded()
                                                ||
                                                miMapa == null
                                ) {
                                    return;
                                }

                                double[] coordenada =
                                        corde_agencia.get(i);

                                GeoPoint punto =
                                        new GeoPoint(
                                                coordenada[0],
                                                coordenada[1]
                                        );

                                Marker marker =
                                        new Marker(miMapa);

                                marker.setPosition(
                                        punto
                                );

                                marker.setAnchor(
                                        Marker.ANCHOR_CENTER,
                                        Marker.ANCHOR_BOTTOM
                                );

                                marker.setIcon(
                                        getResources()
                                                .getDrawable(
                                                        R.drawable.ic_baseline_add_business_24,
                                                        null
                                                )
                                );

                                marker.setTitle(
                                        n_agencia.get(i)
                                                + "\n"
                                                + direc_agencia.get(i)
                                );

                                miMapa.getOverlays()
                                        .add(marker);
                            }

                            if (miMapa != null) {
                                miMapa.invalidate();
                            }
                        },

                        error -> {

                            Log.e(
                                    "MAPA_DB",
                                    "Error traer_agencias_ubi.php",
                                    error
                            );
                        }
                );

        requestQueue =
                Volley.newRequestQueue(
                        requireContext()
                );

        requestQueue.add(request);
    }
    public static Frag_mapa f_mapa(String txt,double[] d)
    {
        Log.e("PANINI f_mapa","static Frag_mapa");
        Frag_mapa frag_mapa=new Frag_mapa();
        Bundle b=new Bundle();
        b.putString("KEY",txt);
        b.putDoubleArray("UBI",d);
        frag_mapa.setArguments(b);
        return frag_mapa;
    }
            /** @Override
             public void setUserVisibleHint(boolean isVisibleToUser) {
                super.setUserVisibleHint(isVisibleToUser);
                if (isVisibleToUser) {
                    getFragmentManager().beginTransaction().detach(this).attach(this).commit();
                }
            }**/
    /// ///////Nuevo 2026///////////////
    private void actualizarMiUbicacion() {

        if (getActivity() == null || miMapa == null) {
            return;
        }

        MainActivity activity = (MainActivity) getActivity();

        double[] ubicacionActual = activity.ubi_r();

        if (ubicacionActual == null || ubicacionActual.length < 2) {
            return;
        }

        double latitud = ubicacionActual[0];

        double longitud = ubicacionActual[1];

        /*
         * MainActivity comienza con 0,0.
         * No colocamos el marcador hasta
         * recibir una ubicación GPS real.
         */
        if (latitud == 0.0 && longitud == 0.0) {

            Log.i("MAPA_UBI", "Esperando ubicación GPS...");
            return;
        }

        GeoPoint punto = new GeoPoint(latitud, longitud);

        /*
         * Crear el marcador solamente
         * la primera vez.
         */
        if (marcadorMiUbicacion == null) {

            marcadorMiUbicacion = new Marker(miMapa);

            marcadorMiUbicacion.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            marcadorMiUbicacion.setIcon(getResources().getDrawable(R.drawable.marcador, null));

            marcadorMiUbicacion.setTitle("Mi ubicación");

            miMapa.getOverlays().add(marcadorMiUbicacion);
        }

        /*
         * Actualizar posición.
         */
        marcadorMiUbicacion.setPosition(punto);

        /*
         * Centrar solamente la primera vez.
         * Después el usuario puede mover
         * libremente el mapa.
         */
        if (!mapaCentrado)
        {
            miMapaControl.setZoom(15);
            miMapaControl.animateTo(punto);
            mapaCentrado = true;
        }

        miMapa.invalidate();

        Log.i("MAPA_UBI", "Ubicación actual: " + latitud + ", " + longitud);
    }
    private final Runnable actualizarMapa = new Runnable() {
                @Override
                public void run() {
                    actualizarMiUbicacion();
                    handlerMapa.postDelayed(this, 2000
                    );
                }
            };

    @Override
    public void onDestroyView() {

        handlerMapa.removeCallbacks(
                actualizarMapa
        );

        super.onDestroyView();
    }
    private boolean coordenadaValida(double latitud, double longitud) {
        return latitud >= -90 &&
                latitud <= 90 &&
                longitud >= -180 &&
                longitud <= 180 &&
                !(latitud == 0.0 && longitud == 0.0);
    }
}
