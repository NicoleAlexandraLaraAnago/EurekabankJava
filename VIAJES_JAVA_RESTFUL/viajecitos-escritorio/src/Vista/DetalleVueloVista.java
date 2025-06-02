// Vista/DetalleVueloVista.java
package Vista;

import javax.swing.*;
import modelo.Vuelo;

public class DetalleVueloVista extends JFrame {
    public DetalleVueloVista(Vuelo vuelo) {
        setTitle("Detalle del Vuelo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("ID: " + vuelo.getId()));
        add(new JLabel("Origen: " + vuelo.getCiudadOrigen()));
        add(new JLabel("Destino: " + vuelo.getCiudadDestino()));
        add(new JLabel("Hora de salida: " + vuelo.getHoraSalida()));
        add(new JLabel("Valor: $" + vuelo.getValor()));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
