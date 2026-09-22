package main.database;

import main.database.ConexionBD;

import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        try (Connection conexion = ConexionBD.getConnection()) {

            if (conexion != null) {
                System.out.println("✅ Conexión exitosa a MySQL");
                System.out.println(
                        "Base de datos: "
                                + conexion.getCatalog()
                );
            }

        } catch (Exception e) {

            System.err.println("❌ Error de conexión:");
            e.printStackTrace();

        }
    }
}