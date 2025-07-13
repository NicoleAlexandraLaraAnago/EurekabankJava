<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - Agencia Viajecitos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="login-body">
<div class="container">
    <div class="row justify-content-center align-items-center min-vh-100">
        <div class="col-md-5">
            <div class="card p-4 login-card shadow">
                <h3 class="text-center text-primary">Agencia Viajecitos</h3>
                <form action="LoginServlet" method="post">
                    <div class="mb-3">
                        <label for="usuario" class="form-label">Usuario</label>
                        <input type="text" class="form-control" id="usuario" name="usuario" required>
                    </div>
                    <div class="mb-3">
                        <label for="clave" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="clave" name="clave" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Iniciar sesión</button>
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger mt-3">
                            <%= request.getAttribute("error") %>
                        </div>
                    <% } %>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %> <!-- ✅ FOOTER GLOBAL -->
</body>
</html>
