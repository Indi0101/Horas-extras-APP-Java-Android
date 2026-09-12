package com.example.proyecto_hora_extra.config;

public final class ApiConfig{
    private ApiConfig(){

    }

    public static final String BASE_URL="http://192.168.101.14/conexion_hora_extra/";
    public static String endpoint(String archivoPHP){
        return BASE_URL+archivoPHP;
    }
}