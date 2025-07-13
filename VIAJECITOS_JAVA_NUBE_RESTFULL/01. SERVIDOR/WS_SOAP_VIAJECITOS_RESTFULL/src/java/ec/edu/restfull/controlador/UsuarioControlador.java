/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.restfull.controlador;

import ec.edu.restfull.db.ConexionDB;
import ec.edu.restfull.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Sebastian
 */
public class UsuarioControlador {
     public Usuario login(String nombreUsuario, String contrasena) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCedula(rs.getString("cedula"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
    public Usuario obtenerUsuarioPorId(int id) {
    Usuario usuario = null;
    String sql = "SELECT * FROM usuarios WHERE id = ?";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setCedula(rs.getString("cedula"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setNombreUsuario(rs.getString("nombre_usuario"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return usuario;
}

}
