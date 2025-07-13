<%@page import="ec.edu.restfull.servicio.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    request.setAttribute("usuarioNombre", usuario.getNombre() + " " + usuario.getApellido());
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Buscar Vuelos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/estilos.css" rel="stylesheet">

    
</head>
<body>

<%@ include file="header.jsp" %>

<!-- Banner -->
<!-- Banner -->
<div class="hero-banner">
    <img src="${pageContext.request.contextPath}/img/logo.jpg" alt="Logo" class="img-fluid" />
</div>

<!-- Formulario de búsqueda -->
<div class="container bg-busqueda-vuelo p-4 rounded-3 shadow-lg mt-custom">



    <ul class="nav nav-tabs mb-3" id="tipoViajeTabs" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" type="button">Ida y vuelta</button>
        </li>
    </ul>

    <form action="BuscarVuelosServlet" method="post" class="row g-3 align-items-end">
            <div class="col-md-3">
                <label for="origen" class="form-label">De</label>
                <input type="text" name="origen" id="origen" class="form-control" required>
            </div>
            <div class="col-md-3">
                <label for="destino" class="form-label">A</label>
                <input type="text" name="destino" id="destino" class="form-control" required>
            </div>
            <div class="col-md-2">
                <label for="fecha" class="form-label">Salida</label>
                <input type="date" name="fecha" id="fecha" class="form-control" required>
            </div>
            <div class="col-md-2 d-grid">
      <input type="hidden" name="usuarioId" value="<%= usuario.getId() %>">
      <button type="submit" class="btn btn-fucsia">Buscar <i class="bi bi-arrow-right-circle-fill ms-1"></i></button>
    </div>
        </form>
</div>

<!-- Mensaje de error -->
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<div class="container mt-4">
    <div class="alert alert-danger d-flex align-items-center" role="alert">
        <i class="bi bi-exclamation-triangle-fill me-2"></i>
        <div><strong>Error:</strong> <%= error %></div>
    </div>
</div>
<%
    }
%>

<!-- Resultados -->
<div class="container mt-4">
    <c:choose>
        <c:when test="${not empty sessionScope.resultadosBusqueda}">
            <h4>Resultados:</h4>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Origen</th>
                        <th>Destino</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Valor</th>
                        <th>Asientos</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="v" items="${sessionScope.resultadosBusqueda}">
                        <tr>
                            <td>${v.ciudadOrigen}</td>
                            <td>${v.ciudadDestino}</td>
                            <td>${v.fechaSalida}</td>
                            <td>${v.horaSalida}</td>
                            <td>${v.valor}</td>
                            <td>${v.asientosDisponibles}</td>
                            <td>
                                <form action="AgregarCarritoServlet" method="post" class="d-flex align-items-center gap-1">
                                    <input type="hidden" name="vueloId" value="${v.id}" />
                                    <input type="hidden" name="origen" value="${v.ciudadOrigen}" />
                                    <input type="hidden" name="destino" value="${v.ciudadDestino}" />
                                    <input type="hidden" name="fecha" value="${v.fechaSalida}" />
                                    <input type="hidden" name="hora" value="${v.horaSalida}" />
                                    <input type="hidden" name="valor" value="${v.valor}" />
                                    <input type="hidden" name="asientos" value="${v.asientosDisponibles}" />
                                    <input type="number" name="cantidad" min="1" max="${v.asientosDisponibles}" value="1" class="form-control form-control-sm" style="width: 70px;" required>
                                    <button type="submit" class="btn btn-success btn-sm">Agregar</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
    </c:choose>
</div>

</body>
</html>
