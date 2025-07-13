package ec.edu.restfull.controlador;

import ec.edu.restfull.db.ConexionDB;
import ec.edu.restfull.modelo.Compra;
import ec.edu.restfull.modelo.DetalleCompra;
import java.sql.*;
import java.util.List;

public class CompraControlador {

    public int registrarCompra(Compra compra) {
        int compraId = -1;

        try (Connection conn = ConexionDB.obtenerConexion()) {
            conn.setAutoCommit(false);
            
            // Calcular IVA del 15%
            double subtotal = compra.getSubtotal();
            double iva = subtotal * 0.15;
            double total = subtotal + iva;
            compra.setTotal(total); // actualizamos el total con IVA incluido


            // Insertar compra
            String sqlCompra = "INSERT INTO compras (usuario_id, metodo_pago_id, fecha_compra, codigo_empleado, subtotal, total) VALUES (?, ?, NOW(), ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, compra.getUsuarioId());
                ps.setInt(2, compra.getMetodoPagoId());
                ps.setString(3, compra.getCodigoEmpleado());
                ps.setDouble(4, compra.getSubtotal());
                ps.setDouble(5, compra.getTotal());
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    compraId = rs.getInt(1);
                }
            }

            // Insertar detalles
            String sqlDetalle = "INSERT INTO detalle_compras (compra_id, vuelo_id, cantidad_asientos, subtotal_vuelo) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sqlDetalle)) {
                for (DetalleCompra dc : compra.getDetalles()) {
                    ps.setInt(1, compraId);
                    ps.setInt(2, dc.getVueloId());
                    ps.setInt(3, dc.getCantidadAsientos());
                    ps.setDouble(4, dc.getSubtotalVuelo());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            // Actualizar asientos disponibles en vuelos
            String sqlActualizarAsientos = "UPDATE vuelos SET asientos_disponibles = asientos_disponibles - ? WHERE id = ?";
            try (PreparedStatement psActualizar = conn.prepareStatement(sqlActualizarAsientos)) {
                for (DetalleCompra dc : compra.getDetalles()) {
                    psActualizar.setInt(1, dc.getCantidadAsientos());
                    psActualizar.setInt(2, dc.getVueloId());
                    psActualizar.addBatch();
                }
                psActualizar.executeBatch();
            }

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return compraId;
    }
}
