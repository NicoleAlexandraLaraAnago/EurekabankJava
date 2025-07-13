package ec.edu.gr02.cliente;

import ec.edu.gr02.servicio.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class HistorialFacturasView {

    private final Usuario usuario;
    private final ViajecitosService servicio;
    private List<Factura> listaFacturas;

    public HistorialFacturasView(Usuario usuario) {
        this.usuario = usuario;
        this.servicio = new ViajecitosService_Service().getViajecitosServicePort();
    }

    public void mostrarHistorialFacturasConsola() {
        System.out.println("\n===== HISTORIAL DE FACTURAS =====");
        System.out.println("Usuario: " + usuario.getNombre() + " " + usuario.getApellido());

        try {
            listaFacturas = servicio.historialFacturasPorUsuario(usuario.getId());

            if (listaFacturas == null || listaFacturas.isEmpty()) {
                System.out.println("\nNo tienes facturas registradas.");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            System.out.printf("\n%-10s %-15s %-20s %-12s %-10s%n", "ID", "Fecha Emisión", "Método de Pago", "Subtotal", "Total");
            System.out.println("-----------------------------------------------------------------------");

            for (Factura f : listaFacturas) {
                String fecha = sdf.format(f.getFechaEmision().toGregorianCalendar().getTime());
                System.out.printf("%-10d %-15s %-20s $%-10.2f $%-10.2f%n",
                        f.getId(),
                        fecha,
                        f.getMetodoPago().getNombreMetodo(),
                        redondear(f.getCompra().getSubtotal()),
                        redondear(f.getCompra().getTotal()));
            }

            seleccionarFactura();

        } catch (Exception e) {
            System.out.println("❌ Error al cargar facturas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void seleccionarFactura() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n¿Deseas ver alguna factura? Ingresa el ID (0 para salir): ");
        int id = -1;

        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        if (id == 0) {
            System.out.println("Regresando al menú...");
            return;
        }

        abrirFactura(id);
    }

    private void abrirFactura(int facturaId) {
        try {
            Factura f = servicio.obtenerFactura(facturaId);
            if (f != null && f.getUsuario() != null) {
                FacturaView vista = new FacturaView(f, usuario);
                vista.mostrarFacturaConsola();
            } else {
                System.out.println("❌ La factura no contiene datos completos.");
            }
        } catch (Exception e) {
            System.out.println("❌ No se pudo abrir la factura seleccionada: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private double redondear(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
}
