package app;

import conexion.ConexionDB;
import dao.DAOException;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

public class DemoConexionMySQL {

    public static void main(String[] args) {
        try (Connection conn = new ConexionDB(Path.of("src/db.properties")).obtenerConexion()){

                System.out.println("Conexion establecida OK.");

        } catch (DAOException e) {
            System.err.println("ERROR AL CONECTAR " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("ERROR AL CONECTAR " + e.getMessage());
            System.out.println("Motivo: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Codigo: " + e.getErrorCode());

        }
    }

}
