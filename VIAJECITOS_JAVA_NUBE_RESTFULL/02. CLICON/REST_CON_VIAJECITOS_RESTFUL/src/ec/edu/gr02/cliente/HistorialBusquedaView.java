package ec.edu.gr02.cliente;

import ec.edu.gr02.servicio.*;

import java.text.SimpleDateFormat;
import java.util.List;

public class HistorialBusquedaView {

    private final Usuario usuario;
    private final ViajecitosService servicio;

    public HistorialBusquedaView(Usuario usuario) {
        this.usuario = usuario;
        ViajecitosService_Service ws = new ViajecitosService_Service();
        this.servicio = ws.getViajecitosServicePort();
    }

    public void mostrarHistorialConsola() {
        System.out.println("\n===== HISTORIAL DE BÚSQUEDAS =====");
        System.out.println("Usuario: " + usuario.getNombre() + " " + usuario.getApellido());

        try {
            List<HistorialBusqueda> historial = servicio.verHistorialBusquedas(usuario.getId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (historial.isEmpty()) {
                System.out.println("\nNo hay historial de búsquedas registrado.");
                return;
            }

            System.out.printf("\n%-20s %-20s %-15s %-15s%n", "Ciudad Origen", "Ciudad Destino", "Fecha Buscada", "Fecha Realizada");
            System.out.println("----------------------------------------------------------------------------------------");

            for (HistorialBusqueda h : historial) {
                String fechaBusqueda = sdf.format(h.getFechaBusqueda().toGregorianCalendar().getTime());
                String fechaRealizada = sdf.format(h.getFechaRealizada().toGregorianCalendar().getTime());

                System.out.printf("%-20s %-20s %-15s %-15s%n",
                        h.getCiudadOrigen(),
                        h.getCiudadDestino(),
                        fechaBusqueda,
                        fechaRealizada);
            }

        } catch (Exception e) {
            System.out.println("❌ Error al cargar historial: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
