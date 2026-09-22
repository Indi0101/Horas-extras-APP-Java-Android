/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pconexionsql;
/**
 *
 * @author CelesteZaldivar
 */

import main.database.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;

public class PconexionSQL {

    public PconexionSQL() {
    }

    public Connection conexion() {

        try {
            return ConexionBD.getConnection();

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al conectar con la base de datos",
                    e
            );
        }
    }
}
