/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ckan1
 */
public class AccesoDB {
    private static final String URL = "jdbc:mysql://localhost:3306/eurekabank?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "Estrellas.99";
    
    public AccesoDB(){
    }
    
    public static Connection getConnection() throws SQLException{
        Connection cn = null;
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver).newInstance();
            cn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException e){
            throw new SQLException("ERROR, no se encuentra el dirver");
        } catch (Exception e){
            throw new SQLException("ERROR, no se tiene acceso el servidor");
        }
        return cn;
    }
}
