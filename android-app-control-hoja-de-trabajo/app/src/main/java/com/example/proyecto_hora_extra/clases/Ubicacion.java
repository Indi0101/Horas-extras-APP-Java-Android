package com.example.proyecto_hora_extra.clases;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.proyecto_hora_extra.MainActivity;
import com.example.proyecto_hora_extra.Marcar_hora_entrada;
import com.example.proyecto_hora_extra.fragmentos.Frag_perfil;

public class Ubicacion implements LocationListener {
    MainActivity mainActivity;
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
    //Log.v("PANINI Ubicacion 4:","Latitud"+location.getLatitude()+"Longitud"+location.getAltitude());
        this.mainActivity.setUbicacion(location);
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    @Override
    public void onProviderEnabled(@NonNull String provider) {
    }
    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }
}
