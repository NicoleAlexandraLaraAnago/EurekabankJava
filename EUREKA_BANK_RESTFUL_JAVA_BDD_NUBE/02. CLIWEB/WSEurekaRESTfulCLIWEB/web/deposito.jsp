<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Realizar Movimiento</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #b3e5e5;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 500px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 40px;
            border-radius: 25px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        h2 {
            color: #333333;
            font-size: 28px;
            margin-bottom: 25px;
        }
        input[type="text"], select {
            width: 90%;
            padding: 12px;
            margin: 12px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 16px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 14px;
            background-color: #00796B;
            color: white;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            margin-top: 15px;
        }
        input[type="submit"]:hover {
            background-color: #004d40;
        }
        .mensaje {
            margin-top: 20px;
            font-weight: bold;
        }
        .mensaje.ok {
            color: green;
        }
        .mensaje.error {
            color: red;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            color: #4a148c;
            text-decoration: underline;
        }
        #cuentaDestinoGroup {
            display: none;
        }
    </style>

    <script>
        function toggleCuentaDestino() {
            const tipo = document.querySelector("select[name='tipoMovimiento']").value;
            document.getElementById("cuentaDestinoGroup").style.display = (tipo === "008") ? "block" : "none";
        }
        window.onload = toggleCuentaDestino;
    </script>
</head>
<body>
    <div class="container">
        <h2>Realizar Movimiento</h2>
        <form action="DepositoServlet" method="post">
            <input type="text" name="cuenta" placeholder="Número de cuenta" required>
            <div id="cuentaDestinoGroup">
                <input type="text" name="cuentaDestino" placeholder="Cuenta destino (solo transferencia)">
            </div>
            <input type="text" name="importe" placeholder="Importe" required>
            <select name="tipoMovimiento" onchange="toggleCuentaDestino()" required>
                <option value="003">Depósito</option>
                <option value="004">Retiro</option>
                <option value="008">Transferencia</option>
                <option value="005">Interés</option>
            </select>
            <input type="submit" value="Registrar Movimiento">
        </form>

        <%
            String estadoJson = (String) request.getAttribute("estado");
            if (estadoJson != null) {
                if (estadoJson.contains("\"estado\":1")) {
                    String saldoAntes = "N/A";
                    String saldoDespues = "N/A";
                    try {
                        if (estadoJson.contains("saldoAntes")) {
                            saldoAntes = estadoJson.split("saldoAntes\":")[1].split(",")[0].replaceAll("[^0-9.]", "");
                        }
                        if (estadoJson.contains("saldoDespues")) {
                            saldoDespues = estadoJson.split("saldoDespues\":")[1].split("[,}]")[0].replaceAll("[^0-9.]", "");
                        }
                    } catch (Exception e) {
                        saldoAntes = "Error";
                        saldoDespues = "Error";
                    }
        %>
                    <div class="mensaje ok">
                        ✅ Movimiento registrado correctamente.<br>
                        Saldo anterior: $<%= saldoAntes %><br>
                        Saldo actual: $<%= saldoDespues %>
                    </div>
        <%
                } else {
        %>
                    <div class="mensaje error">❌ Error al registrar el movimiento.<br>Respuesta: <%= estadoJson %></div>
        <%
                }
            }
        %>

        <a href="menu.jsp">Volver al menú</a>
    </div>
</body>
</html>
