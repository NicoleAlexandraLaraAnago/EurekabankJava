/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package viajecitos.escritorio;

import Vista.LoginVista;
import controlador.LoginControlador;
import javax.swing.SwingUtilities;
import servicio.UsuarioServicio;

/**
 *
 * @author Ordenador
 */
public class ViajecitosEscritorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginVista vista = new LoginVista();
            UsuarioServicio servicio = new UsuarioServicio();
            new LoginControlador(vista, servicio);
            vista.setVisible(true);
        });
    
    }
}
