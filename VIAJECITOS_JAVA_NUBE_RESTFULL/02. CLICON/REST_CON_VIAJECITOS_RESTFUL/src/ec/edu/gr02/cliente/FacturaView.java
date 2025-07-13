package ec.edu.gr02.cliente;

import ec.edu.gr02.servicio.*;

import java.text.SimpleDateFormat;
import java.util.List;

public class FacturaView {

    private final Factura factura;
    private final Usuario usuario;
    private final ViajecitosService servicio;

    public FacturaView(Factura factura, Usuario usuario) {
        this.factura = factura;
        this.usuario = usuario;
        ViajecitosService_Service service = new ViajecitosService_Service();
        this.servicio = service.getViajecitosServicePort();
    }

    public void mostrarFacturaConsola() {
        System.out.println("\n========== FACTURA ELECTRÓNICA ==========\n");

        // Emisor
        System.out.println("RUC: 1799999999001");
        System.out.println("Factura N°: 001-001-000000123");
        System.out.println("Clave de Acceso SRI: 1234567890123456789012345678901234");
        System.out.println("Código Empleado: EMP001");

        // Cliente
        Usuario cliente = factura.getUsuario();
        System.out.println("\n--- Datos del Cliente ---");
        System.out.println("Nombre: " + cliente.getNombre() + " " + cliente.getApellido());
        System.out.println("Cédula: " + cliente.getCedula());
        System.out.println("Correo: " + cliente.getCorreo());

        try {
            String fechaFormateada = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                    .format(factura.getFechaEmision().toGregorianCalendar().getTime());
            System.out.println("Fecha de Emisión: " + fechaFormateada);
        } catch (Exception e) {
            System.out.println("Fecha de Emisión: Fecha inválida");
        }

        System.out.println("Método de Pago: " + factura.getMetodoPago().getNombreMetodo());
        System.out.println("Tipo de Pago: " + factura.getMetodoPago().getTipoPago());

        // Detalle de vuelos
        System.out.println("\n--- Detalle de Vuelos Comprados ---");
        System.out.printf("%-15s %-15s %-12s %-10s %-10s%n", "Origen", "Destino", "Hora", "Asientos", "Subtotal");

        for (DetalleCompra d : factura.getCompra().getDetalles()) {
            try {
                Vuelo vuelo = servicio.buscarVueloPorId(d.getVueloId());
                System.out.printf("%-15s %-15s %-12s %-10d $%.2f%n",
                        vuelo.getCiudadOrigen(),
                        vuelo.getCiudadDestino(),
                        vuelo.getHoraSalida(),
                        d.getCantidadAsientos(),
                        redondear(d.getSubtotalVuelo()));
            } catch (Exception e) {
                System.out.printf("%-15s %-15s %-12s %-10d $%.2f%n",
                        "N/D", "N/D", "N/D",
                        d.getCantidadAsientos(),
                        redondear(d.getSubtotalVuelo()));
            }
        }

        // Totales
        double subtotal = factura.getCompra().getSubtotal();
        double total = factura.getCompra().getTotal();
        double iva = total - subtotal;

        System.out.println("\n--- Totales ---");
        System.out.printf("Subtotal: $%.2f%n", redondear(subtotal));
        System.out.printf("IVA (15%%): $%.2f%n", redondear(iva));
        System.out.printf("Total a Pagar: $%.2f%n", redondear(total));

        // Amortización (si aplica)
        if (factura.getMetodoPago().getTipoPago().equalsIgnoreCase("Crédito")) {
            List<Amortizacion> cuotas = servicio.obtenerAmortizacionPorFactura(factura.getId());

            System.out.println("\n--- Tabla de Amortización ---");
            System.out.printf("%-10s %-15s %-10s %-10s %-10s%n", "Cuota", "Fecha Pago", "Monto", "Saldo", "Estado");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (Amortizacion a : cuotas) {
                String fechaCuota = "N/D";
                try {
                    fechaCuota = sdf.format(a.getFechaPago().toGregorianCalendar().getTime());
                } catch (Exception e) {
                    // fecha inválida
                }

                System.out.printf("%-10d %-15s $%-9.2f $%-9.2f %-10s%n",
                        a.getNumeroCuota(),
                        fechaCuota,
                        redondear(a.getMontoCuota()),
                        redondear(a.getSaldoRestante()),
                        a.getEstadoPago());
            }
        }

        System.out.println("\nGracias por su compra. Puede volver al menú principal.\n");
    }

    private double redondear(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
}
