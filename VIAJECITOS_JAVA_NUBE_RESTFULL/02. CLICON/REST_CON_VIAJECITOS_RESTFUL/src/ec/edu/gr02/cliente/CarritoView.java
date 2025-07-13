package ec.edu.gr02.cliente;

import ec.edu.gr02.servicio.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class CarritoView {

    private final Usuario usuario;
    private final List<DetalleCompra> carrito;
    private final ViajecitosService servicio;
    private final Scanner scanner = new Scanner(System.in);

    public CarritoView(Usuario usuario, List<DetalleCompra> carrito) {
        this.usuario = usuario;
        this.carrito = carrito;
        ViajecitosService_Service service = new ViajecitosService_Service();
        this.servicio = service.getViajecitosServicePort();
    }

    public void mostrarResumenCarrito() {
        if (carrito.isEmpty()) {
            System.out.println("❌ No hay vuelos en el carrito.");
            return;
        }

        System.out.println("\n--- Carrito de Compras ---");
        double subtotal = 0;
        for (int i = 0; i < carrito.size(); i++) {
            DetalleCompra d = carrito.get(i);
            Vuelo vuelo = servicio.buscarVueloPorId(d.getVueloId());
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(vuelo.getFechaSalida().toGregorianCalendar().getTime());
            System.out.printf("%d) %s → %s | Fecha: %s | Hora: %s | Asientos: %d | Subtotal: $%.2f%n",
                    i + 1,
                    vuelo.getCiudadOrigen(),
                    vuelo.getCiudadDestino(),
                    fecha,
                    vuelo.getHoraSalida(),
                    d.getCantidadAsientos(),
                    d.getSubtotalVuelo());
            subtotal += d.getSubtotalVuelo();
        }

        double total = subtotal * 1.15;
        System.out.printf("\nSubtotal: $%.2f%n", redondear(subtotal));
        System.out.printf("Total con IVA (15%%): $%.2f%n", redondear(total));

        eliminarItemsOpcional();

        procesarCompra();
    }

    private void eliminarItemsOpcional() {
        System.out.print("\n¿Deseas eliminar algún vuelo del carrito? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        while (respuesta.equals("s")) {
            System.out.print("Ingrese el número del vuelo a eliminar (0 para terminar): ");
            int index = Integer.parseInt(scanner.nextLine());
            if (index == 0) break;
            if (index < 1 || index > carrito.size()) {
                System.out.println("❌ Índice fuera de rango.");
            } else {
                carrito.remove(index - 1);
                System.out.println("✅ Vuelo eliminado.");
            }
        }
    }

    private void procesarCompra() {
        if (carrito.isEmpty()) {
            System.out.println("❌ No hay vuelos en el carrito.");
            return;
        }

        System.out.println("\n--- Método de Pago ---");
        System.out.println("1) Efectivo");
        System.out.println("2) Transferencia");
        System.out.println("3) Tarjeta (Crédito)");
        System.out.print("Seleccione una opción [1-3]: ");
        int metodoPagoId = Integer.parseInt(scanner.nextLine());

        String tipoAmortizacion = "Francesa";
        int cuotas = 1;
        double tasa = 0;

        if (metodoPagoId == 3) {
            System.out.println("\n--- Pago a Crédito ---");
            System.out.print("Tipo de amortización (Francesa/Alemana): ");
            tipoAmortizacion = scanner.nextLine();
            System.out.print("Número de cuotas: ");
            cuotas = Integer.parseInt(scanner.nextLine());
            System.out.print("Tasa anual (%): ");
            tasa = Double.parseDouble(scanner.nextLine());

            if (cuotas <= 0 || tasa <= 0) {
                System.out.println("❌ Cuotas o tasa inválidas.");
                return;
            }
        }

        // Validar duplicidad y fechas
        try {
            Date ahora = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Set<String> fechasHoras = new HashSet<>();

            for (DetalleCompra dc : carrito) {
                Vuelo vuelo = servicio.buscarVueloPorId(dc.getVueloId());
                Date fecha = vuelo.getFechaSalida().toGregorianCalendar().getTime();
                String fechaHoraStr = new SimpleDateFormat("yyyy-MM-dd").format(fecha) + " " + vuelo.getHoraSalida();
                Date fechaHora = sdf.parse(fechaHoraStr);

                if (fechaHora.before(ahora)) {
                    System.out.printf("❌ Vuelo ID %d tiene fecha/hora pasada: %s%n", vuelo.getId(), sdf.format(fechaHora));
                    return;
                }

                if (fechasHoras.contains(fechaHoraStr)) {
                    System.out.printf("❌ Conflicto: otro vuelo ya está en el carrito con la misma fecha y hora: %s%n", fechaHoraStr);
                    return;
                }
                fechasHoras.add(fechaHoraStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error al validar fechas.");
            return;
        }

        // Crear compra
        Compra compra = new Compra();
        compra.setUsuarioId(usuario.getId());
        compra.setMetodoPagoId(metodoPagoId);
        compra.setCodigoEmpleado("EMP001");
        compra.getDetalles().addAll(carrito);

        double subtotal = carrito.stream().mapToDouble(DetalleCompra::getSubtotalVuelo).sum();
        compra.setSubtotal(subtotal);

        Factura factura = servicio.comprarYFacturar(compra, tipoAmortizacion, cuotas, tasa);

        if (factura != null) {
            System.out.println("\n✅ Compra realizada con éxito.");
            System.out.println("🧾 Factura generada. ID: " + factura.getId());
            CarritoGlobal.carrito.clear();
            new FacturaView(factura, usuario).mostrarFacturaConsola();
        } else {
            System.out.println("❌ Error al procesar la compra.");
        }
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
