<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ec.edu.restfull.servicio.*, java.util.*, javax.xml.datatype.XMLGregorianCalendar" %>
<jsp:useBean id="facturaSeleccionada" class="ec.edu.restfull.servicio.Factura" scope="session" />
<jsp:useBean id="amortizacionFactura" class="java.util.ArrayList" scope="session" />

<%
    ViajecitosService_Service service = new ViajecitosService_Service();
    ViajecitosService servicio = service.getViajecitosServicePort();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Factura</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="card shadow-lg">
        <div class="card-header bg-busqueda-vuelo text-center">
            <h3 class="mb-0">Factura Electrónica - Viajecitos S.A.</h3>
        </div>
        <div class="card-body">
            <p><strong>Factura N°:</strong> <%= facturaSeleccionada.getId() %></p>
            <p><strong>Fecha:</strong> <%= facturaSeleccionada.getFechaEmision() %></p>

            <h4 class="mt-4">Cliente</h4>
            <ul class="list-group mb-3">
                <li class="list-group-item">Nombre: <strong><%= facturaSeleccionada.getUsuario().getNombre() %> <%= facturaSeleccionada.getUsuario().getApellido() %></strong></li>
                <li class="list-group-item">CI: <%= facturaSeleccionada.getUsuario().getCedula() %></li>
                <li class="list-group-item">Email: <%= facturaSeleccionada.getUsuario().getCorreo() %></li>
            </ul>

            <h4>Detalle de Compra</h4>
            <div class="table-responsive">
                <table class="table table-striped text-center align-middle">
                    <thead class="table-light">
                        <tr>
                            <th>Origen</th>
                            <th>Destino</th>
                            <th>Fecha Vuelo</th>
                            <th>Hora Salida</th>
                            <th>Asientos</th>
                            <th>Subtotal</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                        for (DetalleCompra detalle : facturaSeleccionada.getCompra().getDetalles()) {
                            Vuelo vuelo = servicio.buscarVueloPorId(detalle.getVueloId());

                            String fechaVuelo = "N/D";
                            try {
                                fechaVuelo = sdf.format(vuelo.getFechaSalida().toGregorianCalendar().getTime());
                            } catch (Exception e) {
                                fechaVuelo = "Error";
                            }
                    %>
                        <tr>
                            <td><%= vuelo.getCiudadOrigen() %></td>
                            <td><%= vuelo.getCiudadDestino() %></td>
                            <td><%= fechaVuelo %></td>
                            <td><%= vuelo.getHoraSalida() %></td>
                            <td><%= detalle.getCantidadAsientos() %></td>
                            <td>$<%= String.format("%.2f", detalle.getSubtotalVuelo()) %></td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <h4>Totales</h4>
            <ul class="list-group mb-3">
                <li class="list-group-item">Subtotal: <strong>$<%= facturaSeleccionada.getCompra().getSubtotal() %></strong></li>
                <li class="list-group-item">IVA (15%): <strong>$<%= String.format("%.2f", facturaSeleccionada.getCompra().getTotal() - facturaSeleccionada.getCompra().getSubtotal()) %></strong></li>
                <li class="list-group-item">Total: <strong>$<%= facturaSeleccionada.getCompra().getTotal() %></strong></li>
            </ul>

            <h4>Método de Pago</h4>
            <p><%= facturaSeleccionada.getMetodoPago().getNombreMetodo() %> (<%= facturaSeleccionada.getMetodoPago().getTipoPago() %>)</p>

            <% if ("Crédito".equalsIgnoreCase(facturaSeleccionada.getMetodoPago().getTipoPago())) { %>
                <h4>Tabla de Amortización</h4>
                <div class="table-responsive">
                    <table class="table table-bordered text-center align-middle">
                        <thead class="table-light">
                            <tr>
                                <th># Cuota</th>
                                <th>Fecha Pago</th>
                                <th>Monto</th>
                                <th>Saldo</th>
                                <th>Estado</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            for (Object obj : amortizacionFactura) {
                                Amortizacion a = (Amortizacion) obj;
                        %>
                            <tr>
                                <td><%= a.getNumeroCuota() %></td>
                                <td><%= a.getFechaPago() %></td>
                                <td>$<%= a.getMontoCuota() %></td>
                                <td>$<%= a.getSaldoRestante() %></td>
                                <td><%= a.getEstadoPago() %></td>
                            </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            <% } %>
        </div>
        <div class="card-footer text-center">
            <a href="HistorialFacturasServlet" class="btn btn-secondary">Volver al Historial</a>
            <a href="menu.jsp" class="btn btn-light">Volver al Menú Principal</a>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
