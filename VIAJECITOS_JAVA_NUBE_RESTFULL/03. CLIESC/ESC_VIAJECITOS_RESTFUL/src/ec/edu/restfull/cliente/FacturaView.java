package ec.edu.restfull.cliente;



import ec.edu.restfull.servicio.Amortizacion;
import ec.edu.restfull.servicio.DetalleCompra;
import ec.edu.restfull.servicio.Factura;
import ec.edu.restfull.servicio.Usuario;
import ec.edu.restfull.servicio.ViajecitosService;
import ec.edu.restfull.servicio.ViajecitosService_Service;
import ec.edu.restfull.servicio.Vuelo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class FacturaView extends JFrame {

    private final Factura factura;
    private final Usuario usuario;
    private final ViajecitosService servicio;

    public FacturaView(Factura factura, Usuario usuario) {
        this.factura = factura;
        this.usuario = usuario;
        ViajecitosService_Service service = new ViajecitosService_Service();
        this.servicio = service.getViajecitosServicePort();
        initComponents();
    }

    private void initComponents() {
        setTitle("Factura Electrónica");
        setSize(950, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal scrollable
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(Color.WHITE);
        panelContenido.setBorder(new EmptyBorder(15, 30, 15, 30));

        // ---------- ENCABEZADO ----------
        JPanel header = new JPanel(new GridLayout(3, 1));
        header.setBackground(Color.WHITE);
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.setBorder(BorderFactory.createTitledBorder("Datos del Emisor"));

        header.add(new JLabel("RUC: 1799999999001"));
        header.add(new JLabel("Factura N°: 001-001-000000123"));
        header.add(new JLabel("Clave de Acceso SRI: 1234567890123456789012345678901234"));
        header.add(new JLabel("Codigo Empleado: EMP001"));
        panelContenido.add(header);

        panelContenido.add(Box.createVerticalStrut(10));

        // ---------- DATOS CLIENTE ----------
        Usuario cliente = factura.getUsuario();
        JPanel panelCliente = new JPanel(new GridLayout(3, 2, 10, 5));
        panelCliente.setBackground(Color.WHITE);
        panelCliente.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
        panelCliente.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelCliente.add(new JLabel("Nombre: " + cliente.getNombre() + " " + cliente.getApellido()));
        panelCliente.add(new JLabel("Cédula: " + cliente.getCedula()));
        panelCliente.add(new JLabel("Correo: " + cliente.getCorreo()));

        String fechaFormateada = "Fecha inválida";
        try {
            fechaFormateada = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                    .format(factura.getFechaEmision().toGregorianCalendar().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        panelCliente.add(new JLabel("Fecha de Emisión: " + fechaFormateada));
        panelCliente.add(new JLabel("Método de Pago: " + factura.getMetodoPago().getNombreMetodo()));
        panelCliente.add(new JLabel("Tipo de Pago: " + factura.getMetodoPago().getTipoPago()));

        panelContenido.add(panelCliente);
        panelContenido.add(Box.createVerticalStrut(10));

        // ---------- TABLA DETALLE DE VUELOS ----------
        DefaultTableModel modeloDetalle = new DefaultTableModel(
                new String[]{"Origen", "Destino", "Hora Salida", "Asientos", "Subtotal"}, 0);
        JTable tablaDetalle = new JTable(modeloDetalle);
        tablaDetalle.setRowHeight(25);
        tablaDetalle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaDetalle.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        for (DetalleCompra d : factura.getCompra().getDetalles()) {
            try {
                Vuelo vuelo = servicio.buscarVueloPorId(d.getVueloId());
                modeloDetalle.addRow(new Object[]{
                        vuelo.getCiudadOrigen(),
                        vuelo.getCiudadDestino(),
                        vuelo.getHoraSalida(),
                        d.getCantidadAsientos(),
                        redondear(d.getSubtotalVuelo())
                });
            } catch (Exception e) {
                modeloDetalle.addRow(new Object[]{
                        "N/D", "N/D", "N/D", d.getCantidadAsientos(), redondear(d.getSubtotalVuelo())
                });
            }
        }

        JScrollPane scrollDetalle = new JScrollPane(tablaDetalle);
        scrollDetalle.setPreferredSize(new Dimension(850, 160));
        scrollDetalle.setBorder(BorderFactory.createTitledBorder("Detalle de Vuelos Comprados"));
        scrollDetalle.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelContenido.add(scrollDetalle);

        panelContenido.add(Box.createVerticalStrut(10));

        // ---------- TOTALES ----------
        double subtotal = factura.getCompra().getSubtotal();
        double total = factura.getCompra().getTotal();
        double iva = total - subtotal;

        JPanel panelTotales = new JPanel();
        panelTotales.setLayout(new BoxLayout(panelTotales, BoxLayout.Y_AXIS));
        panelTotales.setBackground(Color.WHITE);
        panelTotales.setBorder(BorderFactory.createTitledBorder("Totales"));
        panelTotales.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelTotales.add(crearLabelDerecha("Subtotal: $" + redondear(subtotal)));
        panelTotales.add(crearLabelDerecha("IVA (15%): $" + redondear(iva)));
        panelTotales.add(crearLabelDerecha("Total a Pagar: $" + redondear(total)));

        panelContenido.add(panelTotales);
        panelContenido.add(Box.createVerticalStrut(10));

        // ---------- TABLA AMORTIZACIÓN (si aplica) ----------
        if (factura.getMetodoPago().getTipoPago().equalsIgnoreCase("Crédito")) {
            List<Amortizacion> cuotas = servicio.obtenerAmortizacionPorFactura(factura.getId());

            DefaultTableModel modeloAmort = new DefaultTableModel(
                    new String[]{"# Cuota", "Fecha Pago", "Monto", "Saldo", "Estado"}, 0);
            JTable tablaAmort = new JTable(modeloAmort);
            tablaAmort.setRowHeight(24);
            tablaAmort.setFont(new Font("SansSerif", Font.PLAIN, 13));
            tablaAmort.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Amortizacion a : cuotas) {
                String fechaCuota = "N/D";
                try {
                    fechaCuota = sdf.format(a.getFechaPago().toGregorianCalendar().getTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                modeloAmort.addRow(new Object[]{
                        a.getNumeroCuota(),
                        fechaCuota,
                        redondear(a.getMontoCuota()),
                        redondear(a.getSaldoRestante()),
                        a.getEstadoPago()
                });
            }

            JScrollPane scrollAmort = new JScrollPane(tablaAmort);
            scrollAmort.setPreferredSize(new Dimension(850, 120));
            scrollAmort.setBorder(BorderFactory.createTitledBorder("Tabla de Amortización"));
            scrollAmort.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelContenido.add(scrollAmort);
        }

        panelContenido.add(Box.createVerticalStrut(10));

        // ---------- BOTÓN VOLVER ----------
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.setBackground(new Color(0, 102, 102));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btnVolver.addActionListener(e -> {
            new MenuView(usuario).setVisible(true);
            this.dispose();
        });

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(Color.WHITE);
        panelBoton.add(btnVolver);
        panelBoton.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelContenido.add(panelBoton);

        // Scroll principal
        JScrollPane scroll = new JScrollPane(panelContenido);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);
    }

    private JLabel crearLabelDerecha(String texto) {
        JLabel lbl = new JLabel(texto, SwingConstants.RIGHT);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        lbl.setAlignmentX(Component.RIGHT_ALIGNMENT);
        return lbl;
    }

    private double redondear(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
}
