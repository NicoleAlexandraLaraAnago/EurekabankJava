<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar sesión</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #b3e5e5;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 400px;
            margin: 80px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        img {
            width: 150px;
            margin-bottom: 20px;
        }

        label {
            display: block;
            text-align: left;
            margin: 10px 0 5px;
            color: #555;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            background-color: #222;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #444;
        }

        .mensaje {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Iniciar sesión</h2>
    <img src="img/sulli.jpg" alt="Logo Monster">
    <form action="LoginServlet" method="post">
        <label for="usuario">Usuario:</label>
        <input type="text" name="usuario" value="">

        <label for="contrasena">Contraseña:</label>
        <input type="password" name="contrasena">

        <input type="submit" value="Iniciar sesión">
    </form>
    <div class="mensaje">${mensaje}</div>
</div>
</body>
</html>
