<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg bg-gris-pastel">

    <div class="container-fluid">
        <a class="navbar-brand" href="#">Viajecitos</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="menuNavbar">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="buscarVuelos.jsp">Buscar Vuelos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="HistorialFacturasServlet">Historial de Facturas</a>
                </li>
            </ul>

            <!-- Carrito antes de Bienvenido -->
            <a class="nav-link me-3" href="carrito.jsp">
                <i class="bi bi-cart"></i> Carrito
                <span class="badge bg-secondary" id="contadorCarrito">${sessionScope.totalVuelos}</span>
            </a>

            <span class="navbar-text me-3">
                Bienvenido, <strong>${usuarioNombre}</strong>
            </span>
            <form action="CerrarSesionServlet" method="post">
                <button type="submit" class="btn btn-light btn-sm">Cerrar sesión</button>
            </form>
        </div>
    </div>
</nav>
