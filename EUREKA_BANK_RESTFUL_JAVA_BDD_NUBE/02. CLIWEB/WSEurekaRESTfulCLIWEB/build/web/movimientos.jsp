<%@ page import="java.util.*, com.google.gson.*, com.google.gson.reflect.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movimientos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #b2dfdb;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        .card {
            background-color: white;
            padding: 30px;
            border-radius: 20px;
            box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.2);
            width: 80%;
            max-width: 800px;
            text-align: center;
        }
        input[type="text"] {
            padding: 8px;
            width: 200px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #00796b;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }
        table {
            margin-top: 20px;
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
        }
        th {
            background-color: #004d40;
            color: white;
        }
        .volver {
            margin-top: 20px;
            text-decoration: none;
            background-color: #00796b;
            color: white;
            padding: 10px 20px;
            border-radius: 6px;
        }
    </style>
</head>
<body>
    <div class="card">
        <h2>Movimientos de la Cuenta</h2>

        <form method="post" action="MovimientosServlet">
            <label>Número de cuenta:</label>
            <input type="text" name="cuenta" required />
            <input type="submit" value="Consultar" />
        </form>

        <%
            String json = (String) request.getAttribute("resultado");
            if (json != null && json.trim().startsWith("[")) {
                Gson gson = new Gson();
                java.lang.reflect.Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
                List<Map<String, Object>> movimientos = gson.fromJson(json, listType);
        %>

        <table>
            <tr>
                <th>N° Movimiento</th>
                <th>Fecha</th>
                <th>Tipo</th>
                <th>Acción</th>
                <th>Importe</th>
            </tr>
            <% for (Map<String, Object> mov : movimientos) { %>
            <tr>
                <td><%= mov.get("nromov") %></td>
                <td><%= mov.get("fecha") %></td>
                <td><%= mov.get("tipo") %></td>
                <td><%= mov.get("accion") %></td>
                <td><%= mov.get("importe") %></td>
            </tr>
            <% } %>
        </table>

        <% } else if (json != null) { %>
            <p>No se encontraron movimientos o hubo un error.</p>
        <% } %>

        <br>
        <a href="menu.jsp" class="volver">Volver al Menú</a>
    </div>
</body>
</html>
