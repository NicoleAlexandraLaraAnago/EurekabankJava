<%@ page import="java.util.*" %>
<%@ page import="ec.edu.restfull.servicio.Vuelo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ec.edu.restfull.servicio.Usuario" %>

<%
    session.removeAttribute("resultadosBusqueda");

    List<Vuelo> carrito = (List<Vuelo>) session.getAttribute("carrito");
    Map<Integer, Integer> asientosSeleccionados = (Map<Integer, Integer>) session.getAttribute("asientosSeleccionados");

    double total = 0.0;
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
    <title>Carrito de Vuelos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">
</head>
<body>

<%@ include file="header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="card shadow-lg">
        <div class="card-header bg-busqueda-vuelo text-center">
            <h3 class="mb-0">🧳 Carrito de Vuelos</h3>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-striped align-middle text-center">
                    <thead class="table-light">
                        <tr>
                            <th>Origen</th>
                            <th>Destino</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Valor</th>
                            <th>Cantidad</th>
                            <th>Subtotal</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% if (carrito != null && !carrito.isEmpty()) {
                        for (Vuelo v : carrito) {
                            int cantidad = asientosSeleccionados.getOrDefault(v.getId(), 1);
                            double subtotal = cantidad * v.getValor();
                            total += subtotal;
                    %>
                        <tr>
                            <td><%= v.getCiudadOrigen() %></td>
                            <td><%= v.getCiudadDestino() %></td>
                            <td><%= v.getFechaSalida() %></td>
                            <td><%= v.getHoraSalida() %></td>
                            <td>$<%= String.format("%.2f", v.getValor()) %></td>
                            <td><%= cantidad %></td>
                            <td>$<%= String.format("%.2f", subtotal) %></td>
                            <td>
                                <form action="EliminarDelCarritoServlet" method="post" class="d-inline">
                                    <input type="hidden" name="vueloId" value="<%= v.getId() %>" />
                                    <button class="btn btn-danger btn-sm">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                    <%   }
                       } else { %>
                        <tr>
                            <td colspan="8" class="text-center text-muted">No hay vuelos en el carrito.</td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <% if (carrito != null && !carrito.isEmpty()) { %>
                <div class="text-end mt-4">
                    <h4>Total a pagar: <strong>$<%= String.format("%.2f", total) %></strong></h4>
                    <a href="pago.jsp" class="btn btn-success mt-3">Proceder al pago</a>
                </div>
            <% } %>
        </div>

        <div class="card-footer text-center">
            <a href="buscarVuelos.jsp" class="btn btn-light">← Seguir buscando vuelos</a>
        </div>
    </div>
</div>


</body>
</html>
