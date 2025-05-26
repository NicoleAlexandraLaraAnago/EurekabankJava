<%-- 
    Document   : realizarDeposito
    Created on : 05-dic-2024, 22:50:41
    Author     : ckan1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="ec.edu.monster.controlador.EurekaController" %>
<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String usuario = (String) sesion.getAttribute("usuario");

    String cuenta = request.getParameter("cuenta");
    String importeStr = request.getParameter("importe");
    if (cuenta != null && importeStr != null) {
        try {
            double importe = Double.parseDouble(importeStr);
            EurekaController controlador = new EurekaController();
            int resultado = controlador.regDeposito(cuenta, importe);
            if (resultado == 1) {
                request.setAttribute("mensaje", "Depósito realizado con éxito.");
            } else {
                request.setAttribute("mensaje", "No se pudo realizar el depósito.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "El importe debe ser un número válido.");
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Depósito</title>
        <link rel="stylesheet" href="styles.css">
        <style>
            body {
                background-image: url('resources/dolar.jpg');
                background-size: cover;
                background-position: center;
                font-family: Arial, sans-serif;
            }
            .deposit-container {
                width: 500px;
                margin: 100px auto;
                padding: 20px;
                background-color: rgba(255, 255, 255, 0.9);
                box-shadow: 0px 0px 15px 0px rgba(0,0,0,0.2);
                display: flex;
                align-items: center;
                justify-content: space-between;
            }
            .form-container {
                width: 60%;
            }
            .form-group {
                margin-bottom: 15px;
                text-align: left;
            }
            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            input[type="text"] {
                width: calc(100% - 20px);
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            button[type="submit"] {
                width: calc(100% - 20px);
                background-color: #3364ff;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            button[type="submit"]:hover {
                background-color: #3364ff;
            }
            .logo-container {
                width: 35%;
                text-align: center;
            }
            .logo-container img {
                width: 150px;
            }
            .back-button {
                position: absolute;
                top: 20px;
                right: 20px;
                background-color: #3364ff;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .back-button:hover {
                background-color: #3364ff;
            }
            .mensaje {
                margin-top: 20px;
                color: green;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <button class="back-button" onclick="window.location.href='Menu.jsp'">Volver</button>
        <div class="deposit-container">
            <div class="form-container">
                <h2>Nuevo Depósito</h2>
                <form action="realizarDeposito.jsp" method="post">
                    <div class="form-group">
                        <label for="cuenta">Cuenta:</label>
                        <input type="text" name="cuenta" id="cuenta" required>
                    </div>
                    <div class="form-group">
                        <label for="importe">Importe:</label>
                        <input type="text" name="importe" id="importe" required>
                    </div>
                    <button type="submit">Procesar</button>
                    <c:if test="${not empty requestScope.mensaje}">
                        <p class="mensaje">${requestScope.mensaje}</p>
                    </c:if>
                </form>
            </div>
            <div class="logo-container">
                <img src="resources/banco.jpg" alt="ESPE Logo">
            </div>
        </div>
    </body>
</html>

