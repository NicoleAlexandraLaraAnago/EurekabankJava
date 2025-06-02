/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import modelo.Vuelo;
import modelo.Compra;
import servicio.CompraServicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DetalleVueloDialog extends JDialog {
    private int idUsuario;
    private String nombreUsuario;


public DetalleVueloDialog(JFrame parent, Vuelo vuelo, int idUsuario, String nombreUsuario) {
    super(parent, "Detalle del Vuelo", true);
    this.idUsuario = idUsuario;
    this.nombreUsuario = nombreUsuario;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Mostrar los detalles del vuelo
        add(new JLabel("ID: " + vuelo.getId()));
        add(new JLabel("Origen: " + vuelo.getCiudadOrigen()));
        add(new JLabel("Destino: " + vuelo.getCiudadDestino()));
        add(new JLabel("Hora de salida: " + vuelo.getHoraSalida()));
        add(new JLabel("Valor: $" + vuelo.getValor()));

        // Botón para confirmar compra
        JButton confirmar = new JButton("Confirmar Compra");
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarCompra(vuelo);
            }
        });

        // Botón para cancelar
        JButton cerrar = new JButton("Cancelar");
        cerrar.addActionListener(e -> dispose());

        // Panel de botones
        JPanel botones = new JPanel();
        botones.add(confirmar);
        botones.add(cerrar);

        add(botones);

        pack();
        setLocationRelativeTo(parent);
    }

    // Método para confirmar la compra
private void confirmarCompra(Vuelo vuelo) {
    Compra compra = new Compra();
    compra.setVuelo(vuelo);
    compra.setComprador("Nicole Alexandra Lara Anago"); // puedes pasar esto como parámetro al dialog
    compra.setFechaCompra(java.time.LocalDateTime.now().toString());

    CompraServicio servicio = new CompraServicio();
    boolean exito = servicio.realizarCompra(compra);

    if (exito) {
        JOptionPane.showMessageDialog(this, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Error al realizar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


}
