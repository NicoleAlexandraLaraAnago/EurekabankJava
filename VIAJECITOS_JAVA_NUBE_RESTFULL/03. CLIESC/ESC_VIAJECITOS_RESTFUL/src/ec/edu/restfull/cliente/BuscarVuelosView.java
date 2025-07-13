package ec.edu.restfull.cliente;


import com.toedter.calendar.JDateChooser;
import ec.edu.restfull.cliente.CarritoGlobal;
import ec.edu.restfull.servicio.DetalleCompra;
import ec.edu.restfull.servicio.Usuario;
import ec.edu.restfull.servicio.ViajecitosService;
import ec.edu.restfull.servicio.ViajecitosService_Service;
import ec.edu.restfull.servicio.Vuelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

public class BuscarVuelosView extends JFrame {

    private JTextField txtOrigen;
    private JTextField txtDestino;
    private JDateChooser dateChooser;
    private JButton btnBuscar, btnAgregarCarrito, btnVolver;
    private JTable tablaVuelos;
    private DefaultTableModel modeloTabla;

    private final ViajecitosService servicio;
    private final Usuario usuario;

    private List<Vuelo> vuelosEncontrados = new ArrayList<>();

    public BuscarVuelosView(Usuario usuario) {
        this.usuario = usuario;
        ViajecitosService_Service service = new ViajecitosService_Service();
        this.servicio = service.getViajecitosServicePort();
        initComponents();
    }

private void initComponents() {
    setTitle("Buscar Vuelos - Viajecitos");
    setSize(1000, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JPanel contenedor = new JPanel(new BorderLayout(20, 20));
    contenedor.setBackground(new Color(245, 250, 250));
    contenedor.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

    // Panel de búsqueda con verde pastel
    JPanel panelBusqueda = new JPanel(new GridLayout(2, 4, 10, 10));
    panelBusqueda.setBorder(BorderFactory.createTitledBorder("Datos de Búsqueda"));
    panelBusqueda.setBackground(Color.decode("#b2e7c3"));

    Font labelFont = new Font("SansSerif", Font.BOLD, 14);

    txtOrigen = new JTextField();
    txtDestino = new JTextField();
    dateChooser = new JDateChooser();
    btnBuscar = crearBoton("🔍 Buscar");

    panelBusqueda.add(crearLabel("Origen:", labelFont));
    panelBusqueda.add(txtOrigen);
    panelBusqueda.add(crearLabel("Destino:", labelFont));
    panelBusqueda.add(txtDestino);
    panelBusqueda.add(crearLabel("Fecha:", labelFont));
    panelBusqueda.add(dateChooser);
    panelBusqueda.add(new JLabel(""));
    panelBusqueda.add(btnBuscar);

    // Tabla
    modeloTabla = new DefaultTableModel(new Object[]{"ID", "Origen", "Destino", "Fecha", "Hora", "Valor", "Disponibles", "Asientos"}, 0) {
        public boolean isCellEditable(int row, int column) {
            return column == 7;
        }
    };
    tablaVuelos = new JTable(modeloTabla);
    tablaVuelos.setRowHeight(25);
    tablaVuelos.setFont(new Font("SansSerif", Font.PLAIN, 13));
    tablaVuelos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

    JScrollPane scroll = new JScrollPane(tablaVuelos);
    scroll.setBorder(BorderFactory.createTitledBorder("Vuelos Disponibles"));

    // Panel de botones
    JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
    panelInferior.setBackground(new Color(245, 250, 250));
    btnAgregarCarrito = crearBoton("🛒 Agregar al Carrito");
    btnVolver = crearBoton("↩ Volver");
    panelInferior.add(btnAgregarCarrito);
    panelInferior.add(btnVolver);

    contenedor.add(panelBusqueda, BorderLayout.NORTH);
    contenedor.add(scroll, BorderLayout.CENTER);
    contenedor.add(panelInferior, BorderLayout.SOUTH);

    add(contenedor, BorderLayout.CENTER);

    // Eventos
    btnBuscar.addActionListener(e -> buscarVuelos());
    btnAgregarCarrito.addActionListener(e -> agregarVuelosAlCarrito());
    btnVolver.addActionListener(e -> {
        new MenuView(usuario).setVisible(true);
        dispose();
    });
}

private JLabel crearLabel(String texto, Font font) {
    JLabel label = new JLabel(texto);
    label.setFont(font);
    label.setForeground(Color.BLACK);
    return label;
}

private JButton crearBoton(String texto) {
    JButton btn = new JButton(texto);
    btn.setFont(new Font("SansSerif", Font.BOLD, 14));
    btn.setBackground(Color.decode("#a7e7f7")); // Celeste pastel
    btn.setForeground(Color.BLACK);
    btn.setFocusPainted(false);
    btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    return btn;
}

    private void buscarVuelos() {
        modeloTabla.setRowCount(0);
        String origen = txtOrigen.getText().trim();
        String destino = txtDestino.getText().trim();
        Date fecha = dateChooser.getDate();

        if (origen.isEmpty() || destino.isEmpty() || fecha == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        XMLGregorianCalendar fechaXML = convertirFecha(fecha);
        vuelosEncontrados = servicio.buscarVuelos(usuario.getId(), origen, destino, fechaXML);

        for (Vuelo v : vuelosEncontrados) {
            modeloTabla.addRow(new Object[]{
                    v.getId(),
                    v.getCiudadOrigen(),
                    v.getCiudadDestino(),
                    new SimpleDateFormat("yyyy-MM-dd").format(v.getFechaSalida().toGregorianCalendar().getTime()),
                    v.getHoraSalida(),
                    v.getValor(),
                    v.getAsientosDisponibles(),
                    0
            });
        }

        if (vuelosEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron vuelos", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void agregarVuelosAlCarrito() {
    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
        int asientos;
        try {
            asientos = Integer.parseInt(modeloTabla.getValueAt(i, 7).toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido de asientos en la fila " + (i + 1), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (asientos > 0) {
            Vuelo vuelo = vuelosEncontrados.get(i);
            if (asientos <= vuelo.getAsientosDisponibles()) {
                DetalleCompra dc = new DetalleCompra();
                dc.setVueloId(vuelo.getId());
                dc.setCantidadAsientos(asientos);
                dc.setSubtotalVuelo(vuelo.getValor() * asientos);

                // Validar si ya existe el mismo vuelo en el carrito
                boolean yaExiste = CarritoGlobal.carrito.stream()
                        .anyMatch(c -> c.getVueloId() == vuelo.getId());

                if (!yaExiste) {
                    CarritoGlobal.carrito.add(dc);
                } else {
                    JOptionPane.showMessageDialog(this, "El vuelo con ID " + vuelo.getId() + " ya está en el carrito", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Asientos seleccionados exceden los disponibles en la fila " + (i + 1), "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
    }

    if (CarritoGlobal.carrito.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No se ha agregado ningún vuelo válido al carrito", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int opc = JOptionPane.showConfirmDialog(this, "¿Deseas revisar el carrito ahora?", "Confirmar", JOptionPane.YES_NO_OPTION);
    if (opc == JOptionPane.YES_OPTION) {
        new CarritoView(usuario, CarritoGlobal.carrito).setVisible(true);
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Vuelos agregados al carrito. Puedes seguir buscando otros destinos.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}


    private XMLGregorianCalendar convertirFecha(Date fecha) {
        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
