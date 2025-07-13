package ec.edu.restfull.cliente;

import ec.edu.restfull.servicio.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class CarritoView extends JFrame {

    private final Usuario usuario;
    private final List<DetalleCompra> carrito;
    private JTable tabla;
    private JLabel lblSubtotal, lblTotal;
    private JComboBox<String> cmbMetodoPago, cmbAmortizacion;
    private JTextField txtCuotas, txtTasa;
    private final ViajecitosService servicio;

    public CarritoView(Usuario usuario, List<DetalleCompra> carrito) {
        this.usuario = usuario;
        this.carrito = carrito;
        this.servicio = new ViajecitosService_Service().getViajecitosServicePort();

        initComponents();
    }

    private void initComponents() {
        setTitle("🛒 Carrito de Compras - Viajecitos");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        JPanel panelCentral = new JPanel(new BorderLayout(15, 15));
        panelCentral.setBackground(Color.decode("#b2e7c3"));

        String[] columnas = {"Origen", "Destino", "Fecha", "Hora", "Asientos", "Subtotal"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        tabla = new JTable(model);

        double subtotal = 0;
        for (DetalleCompra d : carrito) {
            Vuelo vuelo = servicio.buscarVueloPorId(d.getVueloId());
            model.addRow(new Object[]{
                    vuelo.getCiudadOrigen(),
                    vuelo.getCiudadDestino(),
                    new SimpleDateFormat("yyyy-MM-dd").format(vuelo.getFechaSalida().toGregorianCalendar().getTime()),
                    vuelo.getHoraSalida(),
                    d.getCantidadAsientos(),
                    d.getSubtotalVuelo()
            });
            subtotal += d.getSubtotalVuelo();
        }

        lblSubtotal = new JLabel("Subtotal: $" + redondear(subtotal));
        lblTotal = new JLabel("Total (con IVA 15%): $" + redondear(subtotal * 1.15));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("✈️ Vuelos en tu Carrito"));

        cmbMetodoPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta"});
        cmbAmortizacion = new JComboBox<>(new String[]{"Francesa", "Alemana"});
        txtCuotas = new JTextField();
        txtTasa = new JTextField();

        cmbMetodoPago.addActionListener(e -> activarCamposCredito(cmbMetodoPago.getSelectedItem().toString().equals("Tarjeta")));

        JButton btnComprar = new JButton("Comprar y Generar Factura");
        btnComprar.addActionListener(e -> procesarCompra());

        JPanel panelPago = new JPanel(new GridLayout(4, 2, 10, 10));
        panelPago.add(new JLabel("Método de pago:"));
        panelPago.add(cmbMetodoPago);
        panelPago.add(new JLabel("Tipo de amortización:"));
        panelPago.add(cmbAmortizacion);
        panelPago.add(new JLabel("Cuotas:"));
        panelPago.add(txtCuotas);
        panelPago.add(new JLabel("Tasa Anual (%):"));
        panelPago.add(txtTasa);

        activarCamposCredito(false);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.add(lblSubtotal);
        infoPanel.add(lblTotal);

        panelCentral.add(infoPanel, BorderLayout.NORTH);
        panelCentral.add(scroll, BorderLayout.CENTER);
        panelCentral.add(panelPago, BorderLayout.SOUTH);
        add(panelCentral, BorderLayout.CENTER);
        add(btnComprar, BorderLayout.SOUTH);
    }

    private void activarCamposCredito(boolean visible) {
        cmbAmortizacion.setEnabled(visible);
        txtCuotas.setEnabled(visible);
        txtTasa.setEnabled(visible);
    }

    private void procesarCompra() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay vuelos en el carrito.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String metodoSeleccionado = cmbMetodoPago.getSelectedItem().toString();
        int metodoPagoId = metodoSeleccionado.equalsIgnoreCase("Tarjeta") ? 3 : 1;
        String tipoPago = metodoPagoId == 3 ? "Crédito" : "Efectivo";

        String tipoAmortizacion = (String) cmbAmortizacion.getSelectedItem();
        int cuotas = txtCuotas.getText().isEmpty() ? 1 : Integer.parseInt(txtCuotas.getText());
        double tasa = txtTasa.getText().isEmpty() ? 0 : Double.parseDouble(txtTasa.getText());

        Compra compra = new Compra();
        compra.setUsuarioId(usuario.getId());
        compra.setMetodoPagoId(metodoPagoId);
        compra.setCodigoEmpleado("EMP001");
        compra.getDetalles().addAll(carrito);
        double subtotal = carrito.stream().mapToDouble(DetalleCompra::getSubtotalVuelo).sum();
        compra.setSubtotal(subtotal);

        Factura factura = servicio.comprarYFacturar(compra, tipoAmortizacion, cuotas, tasa);

        if (factura != null) {
            JOptionPane.showMessageDialog(this, "¡Compra realizada con éxito! Factura N° " + factura.getId());
            new FacturaView(factura, usuario).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al generar la factura.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
