<%@page import="java.util.*"%>
<%@page import="ec.edu.restfull.servicio.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <title>Pago de Carrito</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link href="css/estilos.css" rel="stylesheet">
    <script>
        function toggleCredito() {
            const tipo = document.getElementById("metodoPagoId").value;
            const seccionCredito = document.getElementById("seccionCredito");
            if (tipo === "3") {
                seccionCredito.style.display = "block";
            } else {
                seccionCredito.style.display = "none";
            }
        }
    </script>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="container d-flex justify-content-center align-items-center mt-5 mb-5">
    <div class="card shadow-lg" style="width: 40rem;">
        <div class="card-header bg-busqueda-vuelo text-center">
            <h3 class="mb-0">💳 Selección de Método de Pago</h3>
        </div>

        <div class="card-body">
            <form action="ProcesarPagoServlet" method="post" class="row g-3">
                <div class="col-12">
                    <label for="metodoPagoId" class="form-label">Método de Pago</label>
                    <select name="metodoPagoId" id="metodoPagoId" class="form-select" required onchange="toggleCredito()">
                        <option value="1" data-tipo="Efectivo">Efectivo</option>
                        <option value="3" data-tipo="Crédito">Tarjeta (Crédito)</option>
                    </select>
                    <input type="hidden" name="tipoPago" id="tipoPago" value="Efectivo">
                </div>

                <div id="seccionCredito" style="display:none;">
                    <div class="col-12">
                        <label for="tipoAmortizacion" class="form-label">Tipo de Amortización</label>
                        <select name="tipoAmortizacion" class="form-select">
                            <option value="Francesa">Francesa</option>
                            <option value="Alemana">Alemana</option>
                        </select>
                    </div>
                    <div class="col-6">
                        <label for="cuotas" class="form-label">N° de Cuotas</label>
                        <input type="number" name="cuotas" class="form-control" value="6" min="1" required>
                    </div>
                    <div class="col-6">
                        <label for="tasa" class="form-label">Tasa Anual (%)</label>
                        <input type="number" name="tasa" class="form-control" step="0.01" value="15.0" required>
                    </div>
                </div>

                <div class="col-12 text-center mt-3">
                    <button type="submit" class="btn btn-success">Realizar Pago</button>
                </div>
            </form>
        </div>

        <div class="card-footer text-center">
            <a href="carrito.jsp" class="btn btn-light">← Volver al carrito</a>
        </div>
    </div>
</div>

<script>
    document.getElementById("metodoPagoId").addEventListener("change", function() {
        const tipo = this.options[this.selectedIndex].getAttribute("data-tipo");
        document.getElementById("tipoPago").value = tipo;
        toggleCredito();
    });
</script>


</body>
</html>
