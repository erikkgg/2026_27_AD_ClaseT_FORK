package conexion;

import dao.DAOException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    private final String url;
    private final String usuario;
    private final String password;

    public ConexionDB(Path ficheroPropiedades) throws DAOException {
        Properties p = new Properties();
        try(Reader read = Files.newBufferedReader(ficheroPropiedades)) {
            //Leemos el fichero de propiedades de conexion con el servidor MySQL

            p.load(read);

        }catch(IOException ex){
            throw new DAOException("No se puede leer el fichero properties, " + ficheroPropiedades);
        }

        this.url = p.getProperty("url");
        this.usuario = p.getProperty("usuario");
        this.password = p.getProperty("password");

        if (url == null || usuario == null || password == null){
            throw new DAOException("Faltan datos de conexion en, " + ficheroPropiedades);
        }
    }// Fin Constructor

    public Connection obtenerConexion() throws DAOException{
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, password);
            return conexion;
        } catch(SQLException ex){
            throw new DAOException("Error en obtener conexion." + ex);
        }
    }
}
