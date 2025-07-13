package ec.edu.restfull.cliente;

import ec.edu.restfull.servicio.Usuario;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuView extends JFrame {

    private final Usuario usuario;

    public MenuView(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Menú Principal - Viajecitos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        try {
            // Fondo
            Image bgImage = ImageIO.read(getClass().getResource("/ec/edu/restfull/recursos/logo.jpg"));
            JLabel background = new JLabel(new ImageIcon(bgImage));
            background.setLayout(new BorderLayout());

            // Panel principal centrado
            JPanel panelCentral = new JPanel(new BorderLayout(20, 20));
            panelCentral.setPreferredSize(new Dimension(400, 300));
            panelCentral.setBackground(Color.decode("#b2e7c3")); // Verde pastel
            panelCentral.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 2),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
            ));

            // Panel superior con perfil y bienvenida
            JPanel panelSuperior = new JPanel(new BorderLayout());
            panelSuperior.setOpaque(false);

            JLabel lblBienvenida = new JLabel("Bienvenido, " + usuario.getNombre() + " " + usuario.getApellido(), SwingConstants.LEFT);
            lblBienvenida.setFont(new Font("SansSerif", Font.BOLD, 16));
            lblBienvenida.setForeground(Color.BLACK);

            // Emoji perfil
            JLabel lblPerfil = new JLabel("👤", SwingConstants.RIGHT);
            lblPerfil.setFont(new Font("SansSerif", Font.PLAIN, 30));

            panelSuperior.add(lblBienvenida, BorderLayout.CENTER);
            panelSuperior.add(lblPerfil, BorderLayout.EAST);

            // Botones
            JPanel panelBotones = new JPanel(new GridLayout(3, 1, 15, 15));
            panelBotones.setOpaque(false);
            JButton btnBuscarVuelos = crearBoton("✈ Buscar Vuelos");
            JButton btnVerFacturas = crearBoton("📄 Ver Facturas");
            JButton btnHistorialBusqueda = crearBoton("🔍 Historial de Búsquedas");

            panelBotones.add(btnBuscarVuelos);
            panelBotones.add(btnVerFacturas);
            panelBotones.add(btnHistorialBusqueda);

            // Armar panel central
            panelCentral.add(panelSuperior, BorderLayout.NORTH);
            panelCentral.add(panelBotones, BorderLayout.CENTER);

            JPanel contenedorCentral = new JPanel(new GridBagLayout());
            contenedorCentral.setOpaque(false);
            contenedorCentral.add(panelCentral);

            background.add(contenedorCentral, BorderLayout.CENTER);


            add(background);

            // Acciones botones
            btnBuscarVuelos.addActionListener(e -> {
                new BuscarVuelosView(usuario).setVisible(true);
                dispose();
            });

            btnVerFacturas.addActionListener(e -> {
                new HistorialFacturasView(usuario).setVisible(true);
                dispose();
            });

            btnHistorialBusqueda.addActionListener(e -> {
                new HistorialBusquedaView(usuario).setVisible(true);
                dispose();
            });

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen de fondo.");
        }
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(Color.decode("#a7e7f7")); // Celeste pastel
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return btn;
    }
}
