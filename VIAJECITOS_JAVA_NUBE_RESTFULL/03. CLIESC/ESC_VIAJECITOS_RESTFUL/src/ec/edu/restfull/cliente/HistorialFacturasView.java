package ec.edu.restfull.cliente;

import ec.edu.restfull.servicio.Factura;
import ec.edu.restfull.servicio.Usuario;
import ec.edu.restfull.servicio.ViajecitosService;
import ec.edu.restfull.servicio.ViajecitosService_Service;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistorialFacturasView extends JFrame {

    private final Usuario usuario;
    private final ViajecitosService servicio;
    private JTable tablaFacturas;
    private List<Factura> listaFacturas;

    public HistorialFacturasView(Usuario usuario) {
        this.usuario = usuario;
        this.servicio = new ViajecitosService_Service().getViajecitosServicePort();

        initComponents();
        cargarFacturas();
    }

    private void initComponents() {
        setTitle("🧾 Historial de Facturas - " + usuario.getNombre() + " " + usuario.getApellido());
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBorder(new EmptyBorder(20, 30, 20, 30));
        contenedor.setBackground(Color.decode("#f5f7fa"));

        JLabel lblTitulo = new JLabel("📜 Historial de Facturas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(lblTitulo);

        JLabel lblUsuario = new JLabel("👤 Usuario: " + usuario.getNombre() + " " + usuario.getApellido());
        lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(lblUsuario);

        contenedor.add(Box.createVerticalStrut(15));

        DefaultTableModel modelo = new DefaultTableModel(new String[]{
                "Factura ID", "Fecha Emisión", "Método Pago", "Subtotal", "Total"
        }, 0);

        tablaFacturas = new JTable(modelo) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaFacturas.setRowHeight(26);
        tablaFacturas.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaFacturas.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tablaFacturas);
        scroll.setPreferredSize(new Dimension(800, 300));
        scroll.setBorder(BorderFactory.createTitledBorder("🗂️ Facturas Generadas"));
        contenedor.add(scroll);

        contenedor.add(Box.createVerticalStrut(15));

        JButton btnVolver = new JButton("🔙 Volver al Menú");
        btnVolver.setBackground(Color.decode("#a7e7f7"));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> {
            new MenuView(usuario).setVisible(true);
            this.dispose();
        });

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(Color.decode("#f5f7fa"));
        panelBoton.add(btnVolver);
        contenedor.add(panelBoton);

        add(contenedor);

        tablaFacturas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1 && tablaFacturas.getSelectedRow() != -1) {
                    int fila = tablaFacturas.getSelectedRow();
                    int facturaId = Integer.parseInt(tablaFacturas.getValueAt(fila, 0).toString());
                    abrirFactura(facturaId);
                }
            }
        });
    }

    private void cargarFacturas() {
        try {
            listaFacturas = servicio.historialFacturasPorUsuario(usuario.getId());
            DefaultTableModel modelo = (DefaultTableModel) tablaFacturas.getModel();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (Factura f : listaFacturas) {
                String fecha = sdf.format(f.getFechaEmision().toGregorianCalendar().getTime());
                modelo.addRow(new Object[]{
                        f.getId(),
                        fecha,
                        f.getMetodoPago().getNombreMetodo(),
                        redondear(f.getCompra().getSubtotal()),
                        redondear(f.getCompra().getTotal())
                });
            }

            if (listaFacturas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No tienes facturas registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar facturas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirFactura(int facturaId) {
        try {
            Factura f = servicio.obtenerFactura(facturaId);
            if (f != null && f.getUsuario() != null) {
                new FacturaView(f, usuario).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Factura no contiene datos completos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "No se pudo abrir la factura seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double redondear(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
}
