package ec.edu.monster.servicio;

import ec.edu.monster.db.AccesoDB;
import ec.edu.monster.modelo.Movimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.List;

public class EurekaService {

    public List<Movimiento> leerMovimientos(String cuenta) {
        Connection cn = null;
        List<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT m.chr_cuencodigo cuenta, m.int_movinumero nromov, "
                   + "m.dtt_movifecha fecha, t.vch_tipodescripcion tipo, "
                   + "t.vch_tipoaccion accion, m.dec_moviimporte importe "
                   + "FROM TipoMovimiento t "
                   + "INNER JOIN Movimiento m ON t.chr_tipocodigo = m.chr_tipocodigo "
                   + "WHERE m.chr_cuencodigo = ? "
                   + "ORDER BY m.dtt_movifecha DESC, m.int_movinumero DESC";
        try {
            cn = AccesoDB.getConnection();
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuenta);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Movimiento rec = new Movimiento();
                rec.setCuenta(rs.getString("cuenta"));
                rec.setNromov(rs.getInt("nromov"));
                rec.setFecha(rs.getDate("fecha"));
                rec.setTipo(rs.getString("tipo"));
                rec.setAccion(rs.getString("accion"));
                rec.setImporte(rs.getDouble("importe"));
                lista.add(rec);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (Exception e) {}
        }
        return lista;
    }

    public String convertirMovimientosAJSON(List<Movimiento> lista) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Movimiento m : lista) {
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            objBuilder.add("cuenta", m.getCuenta());
            objBuilder.add("nromov", m.getNromov());
            objBuilder.add("fecha", m.getFecha().toString());
            objBuilder.add("tipo", m.getTipo());
            objBuilder.add("accion", m.getAccion());
            objBuilder.add("importe", m.getImporte());
            arrayBuilder.add(objBuilder);
        }
        return arrayBuilder.build().toString();
    }

    public void registrarDeposito(String cuenta, double importe, String codEmp) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "SELECT dec_cuensaldo, int_cuencontmov FROM Cuenta "
                       + "WHERE chr_cuencodigo = ? AND vch_cuenestado = 'ACTIVO' FOR UPDATE";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuenta);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) throw new SQLException("Cuenta no existe o está inactiva.");
            double saldo = rs.getDouble("dec_cuensaldo");
            int cont = rs.getInt("int_cuencontmov");
            rs.close();
            pstm.close();

            saldo += importe;
            cont++;

            sql = "UPDATE Cuenta SET dec_cuensaldo = ?, int_cuencontmov = ? WHERE chr_cuencodigo = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setDouble(1, saldo);
            pstm.setInt(2, cont);
            pstm.setString(3, cuenta);
            pstm.executeUpdate();
            pstm.close();

            sql = "INSERT INTO Movimiento(chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte) "
                + "VALUES (?, ?, SYSDATE(), ?, '003', ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuenta);
            pstm.setInt(2, cont);
            pstm.setString(3, codEmp);
            pstm.setDouble(4, importe);
            pstm.executeUpdate();
            cn.commit();
        } catch (Exception e) {
            try { if (cn != null) cn.rollback(); } catch (Exception ex) {}
            throw new RuntimeException(e.getMessage());
        } finally {
            try { if (cn != null) cn.close(); } catch (Exception e) {}
        }
    }

    public void registrarRetiro(String cuenta, double importe, String codEmp) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "SELECT dec_cuensaldo, int_cuencontmov FROM Cuenta "
                       + "WHERE chr_cuencodigo = ? AND vch_cuenestado = 'ACTIVO' FOR UPDATE";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuenta);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) throw new SQLException("Cuenta no existe o está inactiva.");
            double saldo = rs.getDouble("dec_cuensaldo");
            int cont = rs.getInt("int_cuencontmov");
            rs.close(); pstm.close();

            if (saldo < importe) throw new SQLException("Saldo insuficiente.");

            saldo -= importe;
            cont++;

            sql = "UPDATE Cuenta SET dec_cuensaldo = ?, int_cuencontmov = ? WHERE chr_cuencodigo = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setDouble(1, saldo);
            pstm.setInt(2, cont);
            pstm.setString(3, cuenta);
            pstm.executeUpdate();
            pstm.close();

            sql = "INSERT INTO Movimiento(chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte) "
                + "VALUES (?, ?, SYSDATE(), ?, '004', ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuenta);
            pstm.setInt(2, cont);
            pstm.setString(3, codEmp);
            pstm.setDouble(4, importe);
            pstm.executeUpdate();
            cn.commit();
        } catch (Exception e) {
            try { if (cn != null) cn.rollback(); } catch (Exception ex) {}
            throw new RuntimeException(e.getMessage());
        } finally {
            try { if (cn != null) cn.close(); } catch (Exception e) {}
        }
    }

    public void registrarTransferencia(String cuentaOrigen, String cuentaDestino, double importe, String codEmp) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);

            String sql = "SELECT dec_cuensaldo, int_cuencontmov FROM Cuenta WHERE chr_cuencodigo = ? AND vch_cuenestado = 'ACTIVO' FOR UPDATE";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuentaOrigen);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) throw new SQLException("Cuenta origen no válida.");
            double saldoOrigen = rs.getDouble("dec_cuensaldo");
            int contOrigen = rs.getInt("int_cuencontmov");
            rs.close(); pstm.close();

            if (saldoOrigen < importe) throw new SQLException("Saldo insuficiente.");

            pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuentaDestino);
            rs = pstm.executeQuery();
            if (!rs.next()) throw new SQLException("Cuenta destino no válida.");
            double saldoDestino = rs.getDouble("dec_cuensaldo");
            int contDestino = rs.getInt("int_cuencontmov");
            rs.close(); pstm.close();

            saldoOrigen -= importe;
            saldoDestino += importe;
            contOrigen++;
            contDestino++;

            sql = "UPDATE Cuenta SET dec_cuensaldo = ?, int_cuencontmov = ? WHERE chr_cuencodigo = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setDouble(1, saldoOrigen);
            pstm.setInt(2, contOrigen);
            pstm.setString(3, cuentaOrigen);
            pstm.executeUpdate();
            pstm.setDouble(1, saldoDestino);
            pstm.setInt(2, contDestino);
            pstm.setString(3, cuentaDestino);
            pstm.executeUpdate();
            pstm.close();

            sql = "INSERT INTO Movimiento(chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) "
                + "VALUES (?, ?, SYSDATE(), ?, '009', ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuentaOrigen);
            pstm.setInt(2, contOrigen);
            pstm.setString(3, codEmp);
            pstm.setDouble(4, importe);
            pstm.setString(5, cuentaDestino);
            pstm.executeUpdate();

            sql = "INSERT INTO Movimiento(chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) "
                + "VALUES (?, ?, SYSDATE(), ?, '008', ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuentaDestino);
            pstm.setInt(2, contDestino);
            pstm.setString(3, codEmp);
            pstm.setDouble(4, importe);
            pstm.setString(5, cuentaOrigen);
            pstm.executeUpdate();

            cn.commit();
        } catch (Exception e) {
            try { if (cn != null) cn.rollback(); } catch (Exception ex) {}
            throw new RuntimeException(e.getMessage());
        } finally {
            try { if (cn != null) cn.close(); } catch (Exception ex) {}
        }
    }
}
