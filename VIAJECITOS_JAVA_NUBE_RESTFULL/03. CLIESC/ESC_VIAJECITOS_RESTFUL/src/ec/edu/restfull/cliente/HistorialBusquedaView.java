package ec.edu.restfull.cliente;



import ec.edu.restfull.servicio.HistorialBusqueda;
import ec.edu.restfull.servicio.Usuario;
import ec.edu.restfull.servicio.ViajecitosService;
import ec.edu.restfull.servicio.ViajecitosService_Service;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistorialBusquedaView extends JFrame {

    private final Usuario usuario;
    private final ViajecitosService servicio;
    private JTable tablaHistorial;

    public HistorialBusquedaView(Usuario usuario) {
        this.usuario = usuario;
        ViajecitosService_Service ws = new ViajecitosService_Service();
        this.servicio = ws.getViajecitosServicePort();

        initComponents();
        cargarHistorial();
    }

    private void initComponents() {
        setTitle("Historial de Búsquedas - " + usuario.getNombre() + " " + usuario.getApellido());
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel contenedorPrincipal = new JPanel();
        contenedorPrincipal.setLayout(new BoxLayout(contenedorPrincipal, BoxLayout.Y_AXIS));
        contenedorPrincipal.setBorder(new EmptyBorder(20, 30, 20, 30));
        contenedorPrincipal.setBackground(Color.WHITE);

        // Encabezado
        JLabel lblTitulo = new JLabel("Historial de Búsquedas");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedorPrincipal.add(lblTitulo);

        JLabel lblUsuario = new JLabel("Usuario: " + usuario.getNombre() + " " + usuario.getApellido());
        lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedorPrincipal.add(lblUsuario);

        contenedorPrincipal.add(Box.createVerticalStrut(15));

        // Tabla
        String[] columnas = {"Ciudad Origen", "Ciudad Destino", "Fecha Buscada", "Fecha Realizada"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        tablaHistorial = new JTable(modelo);
        tablaHistorial.setRowHeight(26);
        tablaHistorial.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaHistorial.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tablaHistorial);
        scroll.setPreferredSize(new Dimension(780, 300));
        scroll.setBorder(BorderFactory.createTitledBorder("Búsquedas realizadas"));
        contenedorPrincipal.add(scroll);

        contenedorPrincipal.add(Box.createVerticalStrut(20));

        // Botón volver
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.setBackground(new Color(0, 102, 102));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> {
            new MenuView(usuario).setVisible(true);
            this.dispose();
        });

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(Color.WHITE);
        panelBoton.add(btnVolver);
        contenedorPrincipal.add(panelBoton);

        add(contenedorPrincipal, BorderLayout.CENTER);
    }

    private void cargarHistorial() {
        try {
            List<HistorialBusqueda> historial = servicio.verHistorialBusquedas(usuario.getId());
            DefaultTableModel modelo = (DefaultTableModel) tablaHistorial.getModel();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (HistorialBusqueda h : historial) {
                String fechaBusqueda = sdf.format(h.getFechaBusqueda().toGregorianCalendar().getTime());
                String fechaRealizada = sdf.format(h.getFechaRealizada().toGregorianCalendar().getTime());
                modelo.addRow(new Object[]{
                        h.getCiudadOrigen(),
                        h.getCiudadDestino(),
                        fechaBusqueda,
                        fechaRealizada
                });
            }

            if (historial.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay historial de búsquedas", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar historial", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
