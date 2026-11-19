package dao.implementaciones;

import conexion.ConexionDB;
import dao.DAOException;
import dao.ProductoDAO;
import modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class ProductoDAOImplMySQL implements ProductoDAO {

    private static final String SQL_INSERTAR = "INSERT INTO productos VALUES(?, ?)";

    private static final String SQL_LISTAR = "SELECT id_producto, precio FROM productos";

    private static final String SQL_ACTUALIZAR = "UPDATE productos SET precio = 28.00 WHERE id_producto = 1001";

    private final ConexionDB conexionDB;

    public ProductoDAOImplMySQL(ConexionDB conexionDB) {
        this.conexionDB = conexionDB;
    }

    @Override
    public void insertar(Producto producto) throws DAOException {
        try(Connection conn = conexionDB.obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(SQL_INSERTAR)
        ) {
            ps.setInt(1, producto.getReferencia());
            ps.setDouble(2, producto.getPrecio());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DAOException("Ya existe un producto con esa referencia: " + producto.getReferencia(), e);
        } catch (SQLException e) {
            throw new DAOException("Error en la insercion de producto " + producto.getReferencia(), e);
        }
    }

    @Override
    public List<Producto> listar() throws DAOException {
        return List.of();
    }

    @Override
    public boolean actualizar(Producto producto) throws DAOException {
        return false;
    }
}
