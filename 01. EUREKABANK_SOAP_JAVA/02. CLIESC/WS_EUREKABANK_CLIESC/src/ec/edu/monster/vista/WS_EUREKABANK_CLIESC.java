package ec.edu.monster.vista;

import javax.swing.SwingUtilities;

public class WS_EUREKABANK_CLIESC {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VistaLogin().setVisible(true);  // Reemplaza con tu clase real
        });
    }
}
