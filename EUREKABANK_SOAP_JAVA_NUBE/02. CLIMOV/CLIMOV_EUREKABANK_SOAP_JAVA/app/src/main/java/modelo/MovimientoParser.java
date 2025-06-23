package modelo;

import modelo.Movimiento;
import org.ksoap2.serialization.SoapObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimientoParser {

    public List<Movimiento> parsearMovimientosSOAP(SoapObject response) {
        List<Movimiento> listaMovimientos = new ArrayList<>();

        if (response == null) {
            return listaMovimientos;
        }

        // Suponemos que el primer hijo contiene todos los movimientos
        SoapObject movimientosContainer = (SoapObject) response.getProperty(0);

        int totalProps = movimientosContainer.getPropertyCount();
        int camposPorMovimiento = 6; // accion, cuenta, fecha, importe, nromov, tipo

        int totalMovimientos = totalProps / camposPorMovimiento;

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < totalMovimientos; i++) {
            try {
                String accion = movimientosContainer.getProperty(i * camposPorMovimiento).toString();
                String cuenta = movimientosContainer.getProperty(i * camposPorMovimiento + 1).toString();
                String fechaStr = movimientosContainer.getProperty(i * camposPorMovimiento + 2).toString();
                String importeStr = movimientosContainer.getProperty(i * camposPorMovimiento + 3).toString();
                String nromovStr = movimientosContainer.getProperty(i * camposPorMovimiento + 4).toString();
                String tipo = movimientosContainer.getProperty(i * camposPorMovimiento + 5).toString();

                Date fecha = formatoFecha.parse(fechaStr.substring(0, 10));
                double importe = Double.parseDouble(importeStr);
                int nromov = Integer.parseInt(nromovStr);

                Movimiento movimiento = new Movimiento(cuenta, nromov, fecha, tipo, accion, importe);

                listaMovimientos.add(movimiento);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listaMovimientos;
    }
}
