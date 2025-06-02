/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Vista.RegistroVista;
import Vista.LoginVista;
import servicio.UsuarioServicio;
import javax.swing.JOptionPane;
import modelo.Usuario;
import java.io.IOException;

public class RegistroControlador {
    private RegistroVista vista;
    private UsuarioServicio servicio;

    public RegistroControlador(RegistroVista vista, UsuarioServicio servicio) {
        this.vista = vista;
        this.servicio = servicio;

        vista.addRegistroListener(e -> {
            String nombre = vista.getNombre();
            String correo = vista.getCorreo();
            String password = vista.getPassword();

            try {
                Usuario usuario = new Usuario();
                usuario.setNombre(nombre);
                usuario.setCorreo(correo);
                usuario.setPassword(password);
                usuario.setRol("USER");

                boolean registrado = servicio.registrar(usuario);
                if (registrado) {
                    JOptionPane.showMessageDialog(vista, "Usuario registrado correctamente");
                    vista.dispose();
                    new LoginVista().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(vista, "Error: El correo ya está registrado");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(vista, "Error al conectar con el servidor");
            }
        });
    }
}
