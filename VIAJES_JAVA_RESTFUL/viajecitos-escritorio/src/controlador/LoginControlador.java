package controlador;

import Vista.LoginVista;
import servicio.UsuarioServicio;
import javax.swing.JOptionPane;
import java.io.IOException;
import modelo.LoginRespuesta;

/**
 *
 * @author Ordenador
 */
public class LoginControlador {
    private LoginVista vista;
    private UsuarioServicio servicio;

    public LoginControlador(LoginVista vista, UsuarioServicio servicio) {
        this.vista = vista;
        this.servicio = servicio;

        vista.addLoginListener(e -> {
            String correo = vista.getCorreo();
            String contraseña = vista.getPassword();
            try {
                LoginRespuesta respuesta = servicio.login(correo, contraseña);
                if (respuesta != null && "Login exitoso".equals(respuesta.getMensaje())) {
                    JOptionPane.showMessageDialog(vista, "Bienvenido/a, " + respuesta.getNombre());
                    // Aquí puedes abrir la vista según el rol
                    // if (respuesta.getRol().equals("ADMIN")) { ... }
                } else {
                    JOptionPane.showMessageDialog(vista, "Correo o contraseña incorrectos");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(vista, "Error al conectar con el servidor");
            }
        });
    }
}
