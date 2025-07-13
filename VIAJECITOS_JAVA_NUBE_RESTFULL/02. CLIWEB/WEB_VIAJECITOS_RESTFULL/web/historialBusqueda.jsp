<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, ec.edu.restfull.servicio.HistorialBusqueda" %>
<%@ page import="ec.edu.restfull.servicio.Usuario" %>
<%
    List<HistorialBusqueda> historial = (List<HistorialBusqueda>) session.getAttribute("historialBusqueda");
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
    <title>Historial de Búsqueda</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="factura-container">
    <h2 class="factura-titulo">Historial de Búsquedas de Vuelos</h2>

    <table class="tabla-factura">
        <thead>
            <tr>
                <th>Origen</th>
                <th>Destino</th>
                <th>Fecha buscada</th>
                <th>Fecha realizada</th>
            </tr>
        </thead>
        <tbody>
        <% if (historial != null && !historial.isEmpty()) {
            for (HistorialBusqueda h : historial) { %>
            <tr>
                <td><%= h.getCiudadOrigen() %></td>
                <td><%= h.getCiudadDestino() %></td>
                <td><%= h.getFechaBusqueda() %></td>
                <td><%= h.getFechaRealizada() %></td>
            </tr>
        <% } 
           } else { %>
            <tr><td colspan="4">No se encontraron búsquedas recientes.</td></tr>
        <% } %>
        </tbody>
    </table>

    <div class="factura-acciones">
        <a href="menu.jsp">Volver al menú</a>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
