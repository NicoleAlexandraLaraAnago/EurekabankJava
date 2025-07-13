package ec.edu.gr02.cliente;

import ec.edu.gr02.servicio.*;

import java.util.Scanner;

public class LoginView {

    private final Scanner scanner = new Scanner(System.in);

    public LoginView() {
        mostrarLoginConsola();
    }

    private void mostrarLoginConsola() {
        System.out.println("========= VIAJECITOS - INICIO DE SESIÓN =========");

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Contraseña: ");
        String clave = scanner.nextLine();

        realizarLogin(usuario, clave);
    }

    private void realizarLogin(String usuario, String clave) {
        try {
            ViajecitosService_Service service = new ViajecitosService_Service();
            ViajecitosService port = service.getViajecitosServicePort();

            Usuario u = port.login(usuario, clave);

            if (u != null) {
                System.out.println("\n✅ Bienvenido, " + u.getNombre() + " " + u.getApellido());
                new MenuView(u).mostrarMenuConsola();
            } else {
                System.out.println("\n❌ Usuario o contraseña incorrecta.");
            }

        } catch (Exception ex) {
            System.out.println("\n❌ Error al iniciar sesión: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
