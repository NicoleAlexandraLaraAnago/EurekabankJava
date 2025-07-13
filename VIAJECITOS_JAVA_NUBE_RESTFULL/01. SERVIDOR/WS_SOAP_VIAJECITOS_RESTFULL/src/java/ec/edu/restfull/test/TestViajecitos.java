package ec.edu.restfull.test;

import ec.edu.restfull.modelo.Usuario;
import ec.edu.restfull.modelo.Vuelo;
import ec.edu.restfull.servicio.ViajecitosService;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class TestViajecitos {

    public static void main(String[] args) {
        ViajecitosService servicio = new ViajecitosService();

        // 1. Login
        Usuario usuario = servicio.login("Monster", "Monster9");
        if (usuario == null) {
            System.out.println("❌ Login fallido");
            return;
        }
        System.out.println("✅ Login exitoso: " + usuario.getNombre() + " " + usuario.getApellido());

        // 2. Buscar vuelos (✅ corregido para incluir el ID del usuario)
        Date fecha = Date.valueOf("2025-06-16");
        List<Vuelo> vuelos = servicio.buscarVuelos(usuario.getId(), "uio", "Gye", fecha);
        if (vuelos.isEmpty()) {
            System.out.println("❌ No se encontraron vuelos");
            return;
        }

        System.out.println("✈ Vuelos disponibles:");
        for (Vuelo v : vuelos) {
            System.out.println(" - ID: " + v.getId() + " " + v.getCiudadOrigen() + " → " + v.getCiudadDestino() + " $" + v.getValor());
        }

        // 3. Crear compra para el primer vuelo
        /*
        Vuelo vueloSeleccionado = vuelos.get(0);
        int cantidad = 1;
        double subtotal = vueloSeleccionado.getValor() * cantidad;

        DetalleCompra detalle = new DetalleCompra();
        detalle.setVueloId(vueloSeleccionado.getId());
        detalle.setCantidadAsientos(cantidad);
        detalle.setSubtotalVuelo(subtotal);

        Compra compra = new Compra();
        compra.setUsuarioId(usuario.getId());
        compra.setMetodoPagoId(3); // Crédito
        compra.setCodigoEmpleado("EMP001");
        compra.setSubtotal(subtotal);
        compra.setTotal(subtotal);
        compra.setDetalles(Collections.singletonList(detalle));

        // 4. Comprar y generar factura con tipo de amortización FRANCESA o ALEMANA
        String tipoAmortizacion = "Alemana";
        int cuotas = 3;
        double tasaAnual = 0.18;

        Factura factura = servicio.comprarYFacturar(compra, tipoAmortizacion, cuotas, tasaAnual);

        if (factura == null || factura.getId() <= 0) {
            System.out.println("❌ Error al generar factura");
            return;
        }

        // 5. Mostrar factura
        System.out.println("🧾 Factura generada:");
        System.out.println(" - ID: " + factura.getId());
        System.out.println(" - Usuario: " + factura.getUsuario().getNombre() + " " + factura.getUsuario().getApellido());
        System.out.println(" - Método de pago: " + factura.getMetodoPago().getNombreMetodo() + " (" + factura.getMetodoPago().getTipoPago() + ")");
        System.out.println(" - Total: $" + factura.getCompra().getTotal());
        System.out.println(" - Fecha: " + factura.getFechaEmision());

        // 6. Mostrar tabla de amortización si aplica
        if ("Crédito".equalsIgnoreCase(factura.getMetodoPago().getTipoPago())) {
            List<Amortizacion> amortizaciones = servicio.obtenerAmortizacionPorFactura(factura.getId());
            System.out.println("💳 Tabla de amortización (" + tipoAmortizacion + "):");
            for (Amortizacion a : amortizaciones) {
                System.out.printf(" - Cuota %d: $%.2f | Fecha: %s | Estado: %s%n",
                        a.getNumeroCuota(), a.getMontoCuota(), a.getFechaPago(), a.getEstadoPago());
            }
        } else {
            System.out.println("💵 No requiere amortización.");
        }

        // 7. Historial de búsquedas
        List<HistorialBusqueda> historial = servicio.verHistorialBusquedas(usuario.getId());
        System.out.println("📚 Historial de búsquedas:");
        for (HistorialBusqueda h : historial) {
            System.out.println(" - " + h.getCiudadOrigen() + " → " + h.getCiudadDestino() + " en " + h.getFechaBusqueda());
        }*/
    }

}
