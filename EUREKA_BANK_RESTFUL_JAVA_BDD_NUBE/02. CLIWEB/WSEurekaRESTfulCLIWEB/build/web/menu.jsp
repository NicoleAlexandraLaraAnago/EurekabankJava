<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menú Principal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #b3e5e5;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 500px;
            margin: 80px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        img {
            width: 180px;
            margin-bottom: 30px;
        }

        .btn {
            display: block;
            width: 100%;
            max-width: 280px;
            margin: 15px auto;
            padding: 14px 0;
            background-color: #222;
            color: white;
            text-decoration: none;
            font-size: 16px;
            border-radius: 8px;
            transition: 0.3s ease;
        }

        .btn:hover {
            background-color: #444;
        }

        .btn-exit {
            background-color: #c0392b;
        }

        .btn-exit:hover {
            background-color: #e74c3c;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Bienvenido, MONSTER</h2>
    <img src="img/sulli.jpg" alt="Monster">
    
    <a href="movimientos.jsp" class="btn">Consultar Movimientos</a>
    <a href="deposito.jsp" class="btn">Realizar Movimiento</a>
    <a href="index.jsp" class="btn btn-exit">Cerrar Sesión</a>
</div>
</body>
</html>
