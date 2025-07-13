<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, ec.edu.restfull.servicio.Factura, ec.edu.restfull.servicio.Compra" %>
<%@page import="ec.edu.restfull.servicio.Usuario"%>
<%
    List<Factura> facturas = (List<Factura>) session.getAttribute("historialFacturas");
%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    request.setAttribute("usuarioNombre", usuario.getNombre() + " " + usuario.getApellido());
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Historial de Facturas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">
</head>
<body>
    <%@ include file="header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="card shadow-lg">
        <div class="card-header bg-busqueda-vuelo text-center">
            <h2 class="mb-0">Historial de Facturas</h2>
        </div>

        <div class="card-body">
            <% if (facturas != null && !facturas.isEmpty()) { %>
            <div class="table-responsive">
                <table class="table table-striped align-middle text-center">
                    <thead class="table-light">
                        <tr>
                            <th>ID Factura</th>
                            <th>Fecha de emisión</th>
                            <th>Subtotal</th>
                            <th>Total</th>
                            <th>Método de pago</th>
                            <th>Ver</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% for (Factura f : facturas) {
                        Compra c = f.getCompra();
                    %>
                        <tr>
                            <td><%= f.getId() %></td>
                            <td><%= f.getFechaEmision() %></td>
                            <td>$<%= String.format("%.2f", c.getSubtotal()) %></td>
                            <td>$<%= String.format("%.2f", c.getTotal()) %></td>
                            <td><%= f.getMetodoPago().getNombreMetodo() %></td>
                            <td>
                                <form action="VerFacturaServlet" method="post" class="d-inline">
                                    <input type="hidden" name="facturaId" value="<%= f.getId() %>"/>
                                    <button type="submit" class="btn btn-fucsia btn-sm">Ver</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
            <% } else { %>
                <div class="alert alert-info text-center">No tienes facturas emitidas.</div>
            <% } %>
        </div>

        <div class="card-footer text-center">
            <a href="menu.jsp" class="btn btn-secondary">Volver al menú</a>
        </div>
    </div>
</div>

</body>
</html>
