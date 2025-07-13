package ec.edu.restfull.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sebastian
 */
public class ConexionDB {
    // Declarar las variables como estáticas
    private static final String URL = "jdbc:mysql://localhost:3306/viajecitos_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Estrellas.99";


    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver actualizado
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el Driver JDBC", e);
        }
    }
}