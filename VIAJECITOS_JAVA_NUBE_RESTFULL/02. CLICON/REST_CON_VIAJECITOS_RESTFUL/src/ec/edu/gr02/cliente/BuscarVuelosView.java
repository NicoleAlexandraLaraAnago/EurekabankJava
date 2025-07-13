package ec.edu.gr02.cliente;

import ec.edu.gr02.servicio.*;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.*;

public class BuscarVuelosView {

    private final Scanner scanner = new Scanner(System.in);
    private final ViajecitosService servicio;
    private final Usuario usuario;

    private List<Vuelo> vuelosEncontrados = new ArrayList<>();

    public BuscarVuelosView(Usuario usuario) {
        this.usuario = usuario;
        ViajecitosService_Service service = new ViajecitosService_Service();
        this.servicio = service.getViajecitosServicePort();
    }

    public void mostrarMenu() {
        System.out.println("=== Búsqueda de Vuelos ===");

        System.out.print("Ingrese ciudad de origen: ");
        String origen = scanner.nextLine();

        System.out.print("Ingrese ciudad de destino: ");
        String destino = scanner.nextLine();

        System.out.print("Ingrese fecha (yyyy-MM-dd): ");
        String fechaStr = scanner.nextLine();
        Date fecha;
        try {
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
        } catch (Exception e) {
            System.out.println("❌ Fecha inválida.");
            return;
        }

        XMLGregorianCalendar fechaXML = convertirFecha(fecha);
        vuelosEncontrados = servicio.buscarVuelos(usuario.getId(), origen, destino, fechaXML);

        if (vuelosEncontrados.isEmpty()) {
            System.out.println("❌ No se encontraron vuelos.");
            return;
        }

        System.out.println("\n--- Vuelos encontrados ---");
        for (int i = 0; i < vuelosEncontrados.size(); i++) {
            Vuelo v = vuelosEncontrados.get(i);
            System.out.printf("%d) ID: %d | Origen: %s | Destino: %s | Fecha: %s | Hora: %s | Valor: %.2f | Disponibles: %d%n",
                    i + 1,
                    v.getId(),
                    v.getCiudadOrigen(),
                    v.getCiudadDestino(),
                    new SimpleDateFormat("yyyy-MM-dd").format(v.getFechaSalida().toGregorianCalendar().getTime()),
                    v.getHoraSalida(),
                    v.getValor(),
                    v.getAsientosDisponibles()
            );
        }

        agregarVuelosAlCarrito();
    }

    private void agregarVuelosAlCarrito() {
        System.out.println("\n¿Deseas agregar algún vuelo al carrito? (s/n)");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        if (!respuesta.equals("s")) return;

        while (true) {
            System.out.print("Ingrese número de vuelo (0 para terminar): ");
            int num;
            try {
                num = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("⚠️ Valor inválido.");
                continue;
            }

            if (num == 0) break;

            if (num < 1 || num > vuelosEncontrados.size()) {
                System.out.println("❌ Número fuera de rango.");
                continue;
            }

            Vuelo vuelo = vuelosEncontrados.get(num - 1);

            System.out.print("Ingrese cantidad de asientos a reservar: ");
            int asientos;
            try {
                asientos = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("⚠️ Valor inválido.");
                continue;
            }

            if (asientos <= 0 || asientos > vuelo.getAsientosDisponibles()) {
                System.out.println("❌ Cantidad no válida.");
                continue;
            }

            boolean yaExiste = CarritoGlobal.carrito.stream()
                    .anyMatch(c -> c.getVueloId() == vuelo.getId());

            if (yaExiste) {
                System.out.println("⚠️ Este vuelo ya está en el carrito.");
                continue;
            }

            DetalleCompra dc = new DetalleCompra();
            dc.setVueloId(vuelo.getId());
            dc.setCantidadAsientos(asientos);
            dc.setSubtotalVuelo(vuelo.getValor() * asientos);

            CarritoGlobal.carrito.add(dc);
            System.out.println("✅ Vuelo agregado al carrito.");
        }

        if (CarritoGlobal.carrito.isEmpty()) {
            System.out.println("❌ No se agregaron vuelos.");
        } else {
            System.out.println("\n✈️ Vuelos agregados correctamente. Puedes continuar con la compra desde el menú.");
        }
    }

    private XMLGregorianCalendar convertirFecha(Date fecha) {
        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
