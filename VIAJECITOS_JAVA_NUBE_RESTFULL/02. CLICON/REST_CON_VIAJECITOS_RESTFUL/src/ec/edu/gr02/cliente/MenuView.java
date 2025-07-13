package ec.edu.gr02.cliente;

import ec.edu.gr02.servicio.Usuario;

import java.util.Scanner;

public class MenuView {

    private final Usuario usuario;
    private final Scanner scanner = new Scanner(System.in);

    public MenuView(Usuario usuario) {
        this.usuario = usuario;
    }

    public void mostrarMenuConsola() {
        int opcion = -1;
        do {
            System.out.println("\n========= MENÚ PRINCIPAL - VIAJECITOS =========");
            System.out.println("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellido());
            System.out.println("-----------------------------------------------");
            System.out.println("1) ✈ Buscar Vuelos");
            System.out.println("2) 📄 Ver Historial de Facturas");
            System.out.println("3) 🔍 Ver Historial de Búsquedas");
            System.out.println("0) 🔙 Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Opción inválida.");
                continue;
            }

            switch (opcion) {
                case 1:
                    new BuscarVuelosView(usuario).mostrarMenu();
                    break;
                case 2:
                    new HistorialFacturasView(usuario).mostrarHistorialFacturasConsola();
                    break;
                case 3:
                    new HistorialBusquedaView(usuario).mostrarHistorialConsola();
                    break;
                case 0:
                    System.out.println("👋 Hasta pronto.");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }

        } while (opcion != 0);
    }
}
