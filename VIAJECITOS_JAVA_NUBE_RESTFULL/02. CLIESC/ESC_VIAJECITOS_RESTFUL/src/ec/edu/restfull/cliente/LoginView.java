package ec.edu.restfull.cliente;

import ec.edu.restfull.servicio.Usuario;
import ec.edu.restfull.servicio.ViajecitosService;
import ec.edu.restfull.servicio.ViajecitosService_Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnLogin;
    private JLabel lblMensaje;

    public LoginView() {
        setTitle("Viajecitos - Inicio de Sesión");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(false);

        try {
            Image originalImage = ImageIO.read(getClass().getResource("/ec/edu/restfull/recursos/fondo.jpg"));
            // Escalamos la imagen a un porcentaje menor, ej. 60% de la ventana
            int width = getWidth() * 90 / 100;
            int height = getHeight() * 90 / 100;
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            JLabel background = new JLabel(new ImageIcon(scaledImage));
            background.setLayout(new GridBagLayout());
            setContentPane(background);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen de fondo.");
        }

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 350));
        panel.setBackground(Color.decode("#b2e7c3"));  // Verde pastel
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setLayout(new GridLayout(7, 1, 10, 10));

        JLabel lblTitulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setForeground(Color.BLACK);

        txtUsuario = new JTextField();
        txtClave = new JPasswordField();

        btnLogin = new JButton("Ingresar");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLogin.setBackground(Color.decode("#a7e7f7"));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblMensaje.setForeground(Color.BLACK);

        panel.add(lblTitulo);
        panel.add(new JLabel("Usuario:", SwingConstants.CENTER));
        panel.add(txtUsuario);
        panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
        panel.add(txtClave);
        panel.add(btnLogin);
        panel.add(lblMensaje);

        getContentPane().add(panel);

        btnLogin.addActionListener(e -> realizarLogin());
    }

    private void realizarLogin() {
        try {
            String usuario = txtUsuario.getText();
            String clave = new String(txtClave.getPassword());

            ViajecitosService_Service service = new ViajecitosService_Service();
            ViajecitosService port = service.getViajecitosServicePort();

            Usuario u = port.login(usuario, clave);

            if (u != null) {
                lblMensaje.setForeground(new Color(0, 153, 0));
                lblMensaje.setText("Bienvenido, " + u.getNombre() + " " + u.getApellido());
                new MenuView(u).setVisible(true);
                this.dispose();
            } else {
                lblMensaje.setForeground(Color.RED);
                lblMensaje.setText("Usuario o contraseña incorrecta");
            }

        } catch (Exception ex) {
            lblMensaje.setForeground(Color.RED);
            lblMensaje.setText("Error: " + ex.getMessage());
        }
    }
}
