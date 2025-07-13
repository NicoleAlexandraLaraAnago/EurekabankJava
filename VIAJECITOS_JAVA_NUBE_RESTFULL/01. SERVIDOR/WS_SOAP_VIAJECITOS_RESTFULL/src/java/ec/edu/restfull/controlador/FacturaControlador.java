package ec.edu.restfull.controlador;

import ec.edu.restfull.db.ConexionDB;
import ec.edu.restfull.modelo.Amortizacion;
import ec.edu.restfull.modelo.Compra;
import ec.edu.restfull.modelo.DetalleCompra;
import ec.edu.restfull.modelo.Factura;
import ec.edu.restfull.modelo.MetodoPago;
import ec.edu.restfull.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

public class FacturaControlador {

    // Generar factura
    public int generarFactura(int compraId) {
        int facturaId = -1;
        String sql = "INSERT INTO facturas (compra_id, fecha_emision) VALUES (?, NOW())";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, compraId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                facturaId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturaId;
    }

    // Obtener factura con todos los datos relacionados
    public Factura obtenerFacturaCompleta(int facturaId) {
        Factura factura = null;
        String sql = "SELECT f.id AS factura_id, f.compra_id, f.fecha_emision, " +
                     "c.usuario_id, c.metodo_pago_id, c.codigo_empleado, c.subtotal, c.total, " +
                     "u.nombre, u.apellido, u.correo, u.cedula, m.nombre_metodo, m.tipo_pago " +
                     "FROM facturas f " +
                     "JOIN compras c ON f.compra_id = c.id " +
                     "JOIN usuarios u ON c.usuario_id = u.id " +
                     "JOIN metodos_pago m ON c.metodo_pago_id = m.id " +
                     "WHERE f.id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, facturaId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                factura = new Factura();
                factura.setId(rs.getInt("factura_id"));
                factura.setCompraId(rs.getInt("compra_id"));
                factura.setFechaEmision(new java.util.Date(rs.getTimestamp("fecha_emision").getTime()));

                // Set usuario
                Usuario u = new Usuario();
                u.setId(rs.getInt("usuario_id"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setCorreo(rs.getString("correo"));
                u.setCedula(rs.getString("cedula"));
                factura.setUsuario(u);

                // Set método de pago
                MetodoPago m = new MetodoPago();
                m.setId(rs.getInt("metodo_pago_id"));
                m.setNombreMetodo(rs.getString("nombre_metodo"));
                m.setTipoPago(rs.getString("tipo_pago"));
                factura.setMetodoPago(m);

                // Set compra básica
                Compra c = new Compra();
                int compraId = rs.getInt("compra_id");
                c.setId(compraId);
                c.setUsuarioId(rs.getInt("usuario_id"));
                c.setMetodoPagoId(rs.getInt("metodo_pago_id"));
                c.setCodigoEmpleado(rs.getString("codigo_empleado"));
                c.setSubtotal(rs.getDouble("subtotal"));
                c.setTotal(rs.getDouble("total"));

                // Cargar detalles de compra
                List<DetalleCompra> detalles = new ArrayList<>();
                String sqlDetalles = "SELECT * FROM detalle_compras WHERE compra_id = ?";
                try (PreparedStatement psDet = conn.prepareStatement(sqlDetalles)) {
                    psDet.setInt(1, compraId);
                    ResultSet rsDet = psDet.executeQuery();
                    while (rsDet.next()) {
                        DetalleCompra dc = new DetalleCompra();
                        dc.setVueloId(rsDet.getInt("vuelo_id"));
                        dc.setCantidadAsientos(rsDet.getInt("cantidad_asientos"));
                        dc.setSubtotalVuelo(rsDet.getDouble("subtotal_vuelo"));
                        detalles.add(dc);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                c.setDetalles(detalles);
                factura.setCompra(c);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return factura;
    }

    // Obtener amortización de una factura
    public List<Amortizacion> obtenerAmortizacion(int facturaId) {
        List<Amortizacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM amortizacion WHERE factura_id = ? ORDER BY numero_cuota ASC";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, facturaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Amortizacion a = new Amortizacion();
                a.setId(rs.getInt("id"));
                a.setFacturaId(rs.getInt("factura_id"));
                a.setNumeroCuota(rs.getInt("numero_cuota"));
                a.setFechaPago(new java.util.Date(rs.getDate("fecha_pago").getTime()));
                a.setMontoCuota(rs.getDouble("monto_cuota"));
                a.setSaldoRestante(rs.getDouble("saldo_restante"));
                a.setEstadoPago(rs.getString("estado_pago"));
                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Listar facturas por usuario
    public List<Factura> listarFacturasPorUsuario(int usuarioId) {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT f.id AS factura_id, f.fecha_emision, c.id AS compra_id, c.subtotal, c.total, m.nombre_metodo " +
                     "FROM facturas f " +
                     "JOIN compras c ON f.compra_id = c.id " +
                     "JOIN metodos_pago m ON c.metodo_pago_id = m.id " +
                     "WHERE c.usuario_id = ? " +
                     "ORDER BY f.fecha_emision DESC";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Factura factura = new Factura();
                factura.setId(rs.getInt("factura_id"));
                factura.setCompraId(rs.getInt("compra_id"));
                factura.setFechaEmision(new java.util.Date(rs.getTimestamp("fecha_emision").getTime()));

                Compra compra = new Compra();
                compra.setId(rs.getInt("compra_id"));
                compra.setSubtotal(rs.getDouble("subtotal"));
                compra.setTotal(rs.getDouble("total"));

                MetodoPago metodo = new MetodoPago();
                metodo.setNombreMetodo(rs.getString("nombre_metodo"));

                factura.setCompra(compra);
                factura.setMetodoPago(metodo);

                lista.add(factura);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public void generarAmortizacionFrancesa(int facturaId, int cuotas, double tasaAnual) {
        Factura factura = obtenerFacturaCompleta(facturaId);
        double monto = factura.getCompra().getTotal();
        double tasaMensual = tasaAnual / 12 / 100.0;
        double cuota = (monto * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -cuotas));
        double saldo = monto;
        Calendar cal = Calendar.getInstance();

        for (int i = 1; i <= cuotas; i++) {
            double interes = saldo * tasaMensual;
            double capital = cuota - interes;
            saldo -= capital;

            insertarAmortizacion(facturaId, i, cal, cuota, saldo);
        }
    }

    public void generarAmortizacionAlemana(int facturaId, int cuotas, double tasaAnual) {
        Factura factura = obtenerFacturaCompleta(facturaId);
        double monto = factura.getCompra().getTotal();
        double tasaMensual = tasaAnual / 12 / 100.0;
        double amortizacionFija = monto / cuotas;
        double saldo = monto;
        Calendar cal = Calendar.getInstance();

        for (int i = 1; i <= cuotas; i++) {
            double interes = saldo * tasaMensual;
            double cuota = amortizacionFija + interes;
            saldo -= amortizacionFija;

            insertarAmortizacion(facturaId, i, cal, cuota, saldo);
        }
    }

    private void insertarAmortizacion(int facturaId, int numeroCuota, Calendar cal, double cuota, double saldo) {
        try {
            cal.add(Calendar.MONTH, 1);
            java.util.Date fecha = cal.getTime();

            try (Connection conn = ConexionDB.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO amortizacion (factura_id, numero_cuota, fecha_pago, monto_cuota, saldo_restante, estado_pago) VALUES (?, ?, ?, ?, ?, ?)")) {

                ps.setInt(1, facturaId);
                ps.setInt(2, numeroCuota);
                ps.setDate(3, new java.sql.Date(fecha.getTime()));
                ps.setDouble(4, redondear(cuota));
                ps.setDouble(5, redondear(Math.max(0, saldo)));
                ps.setString(6, "Pendiente");

                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
