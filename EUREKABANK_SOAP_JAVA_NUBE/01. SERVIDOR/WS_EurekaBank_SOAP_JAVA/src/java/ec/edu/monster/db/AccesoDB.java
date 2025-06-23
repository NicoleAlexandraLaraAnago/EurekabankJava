package ec.edu.monster.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para manejar el acceso a la base de datos en Google Cloud SQL.
 *
 * @author leito
 */
public class AccesoDB {
    // La URL debe incluir el nombre de la instancia y la base de datos
    private static final String URL = "jdbc:mysql://34.136.97.35:3306/eurekabank?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=UTC";
    
    private static final String USER = "root";
    private static final String PASS = "root";
    
    public AccesoDB() {
    }
    
    public static Connection getConnection() throws SQLException {
        Connection cn = null;
        try {
            // Cargar el driver MySQL a memoria
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            // Obtener el objeto Connection
            cn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new SQLException("Error al conectar a la base de datos: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error: No se encuentra el driver JDBC de MySQL.", e);
        }
        return cn;
    }
}