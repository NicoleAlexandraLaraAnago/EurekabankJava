package ec.edu.restfull.controlador;

import ec.edu.restfull.db.ConexionDB;
import ec.edu.restfull.modelo.HistorialBusqueda;
import ec.edu.restfull.modelo.Vuelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VueloControlador {

    public List<Vuelo> buscarVuelos(String origen, String destino, Date fecha) {
        List<Vuelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vuelos WHERE LOWER(ciudad_origen) = ? AND LOWER(ciudad_destino) = ? AND fecha_salida = ?";


        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, origen.toLowerCase());
            ps.setString(2, destino.toLowerCase());
            ps.setDate(3, new java.sql.Date(fecha.getTime())); // 👈 esta es la conversión correcta
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vuelo v = new Vuelo();
                v.setId(rs.getInt("id"));
                v.setCiudadOrigen(rs.getString("ciudad_origen"));
                v.setCiudadDestino(rs.getString("ciudad_destino"));
                v.setFechaSalida(rs.getDate("fecha_salida"));
                v.setHoraSalida(rs.getString("hora_salida"));
                v.setValor(rs.getDouble("valor"));
                v.setAsientosDisponibles(rs.getInt("asientos_disponibles"));
                lista.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<HistorialBusqueda> obtenerHistorial(int usuarioId) {
        List<HistorialBusqueda> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_busquedas WHERE usuario_id = ? ORDER BY fecha_realizada DESC";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HistorialBusqueda hb = new HistorialBusqueda();
                hb.setId(rs.getInt("id"));
                hb.setUsuarioId(rs.getInt("usuario_id"));
                hb.setCiudadOrigen(rs.getString("ciudad_origen"));
                hb.setCiudadDestino(rs.getString("ciudad_destino"));
                hb.setFechaBusqueda(rs.getDate("fecha_busqueda"));
                hb.setFechaRealizada(new Date(rs.getTimestamp("fecha_realizada").getTime())); // ✅ sin cast
                lista.add(hb);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    public void guardarHistorialBusqueda(int usuarioId, String ciudadOrigen, String ciudadDestino, Date fechaBusqueda) {
    String sql = "INSERT INTO historial_busquedas (usuario_id, ciudad_origen, ciudad_destino, fecha_busqueda, fecha_realizada) VALUES (?, ?, ?, ?, NOW())";

    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, usuarioId);
        ps.setString(2, ciudadOrigen);
        ps.setString(3, ciudadDestino);
        ps.setDate(4, new java.sql.Date(fechaBusqueda.getTime())); // ✅ conversión correcta
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public Vuelo buscarVueloPorId(int id) {
    Vuelo vuelo = null;
    String sql = "SELECT * FROM vuelos WHERE id = ?";
    try (Connection conn = ConexionDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            vuelo = new Vuelo();
            vuelo.setId(rs.getInt("id"));
            vuelo.setCiudadOrigen(rs.getString("ciudad_origen"));
            vuelo.setCiudadDestino(rs.getString("ciudad_destino"));
            vuelo.setFechaSalida(rs.getDate("fecha_salida"));
            vuelo.setHoraSalida(rs.getString("hora_salida"));
            vuelo.setValor(rs.getDouble("valor"));
            vuelo.setAsientosDisponibles(rs.getInt("asientos_disponibles"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return vuelo;
}


}
