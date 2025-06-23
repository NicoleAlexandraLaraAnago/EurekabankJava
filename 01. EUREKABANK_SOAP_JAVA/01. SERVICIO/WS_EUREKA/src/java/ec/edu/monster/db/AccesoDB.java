package ec.edu.monster.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDB {
    private static final String URL = "jdbc:mysql://database-1.cdycgs6oe6nh.us-east-2.rds.amazonaws.com:3306/eurekabank?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "admin";
    private static final String PASS = "diego250899";

    public AccesoDB() {
    }

    public static Connection getConnection() throws SQLException {
        Connection cn = null;
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver).newInstance();
            cn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw new SQLException("ERROR, no se encuentra el driver");
        } catch (Exception e) {
            throw new SQLException("ERROR, no se tiene acceso al servidor");
        }
        return cn;
    }
}
