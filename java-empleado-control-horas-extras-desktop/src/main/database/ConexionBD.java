package main.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static final String CONFIG_FILE = "db.properties";

    private static String url;
    private static String user;
    private static String password;

    static {
        cargarConfiguracion();
    }

    private static void cargarConfiguracion() {

        Properties properties = new Properties();

        try (InputStream input = ConexionBD.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (input == null) {
                throw new RuntimeException(
                        "No se encontró el archivo " + CONFIG_FILE
                );
            }

            properties.load(input);

            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");

        } catch (IOException e) {
            throw new RuntimeException(
                    "Error al cargar la configuración de la base de datos",
                    e
            );
        }
    }

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                url,
                user,
                password
        );
    }
}
