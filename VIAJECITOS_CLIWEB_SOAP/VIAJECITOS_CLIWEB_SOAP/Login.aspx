<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="VIAJECITOS_CLIWEB_SOAP.Login" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Iniciar Sesión</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('Content/imagenes/fondo.png') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', sans-serif;
        }

        .login-container {
            background-color: rgba(255, 255, 255, 0.95);
            width: 380px;
            margin: 100px auto;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        .login-container img {
            width: 150px; /* AUMENTADO */
            margin-bottom: 25px;
        }

        .login-container h2 {
            color: #3b3f51;
            margin-bottom: 20px;
        }

        .login-container label {
            font-weight: bold;
            color: #555;
        }

        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-top: 4px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .login-container input[type="submit"],
        .login-container .asp-button {
            width: 100%;
            background-color: #4bc0c8;
            color: white;
            border: none;
            padding: 10px;
            font-weight: bold;
            border-radius: 6px;
            cursor: pointer;
        }

        .login-container .asp-button:hover {
            background-color: #3ea9b0;
        }

        .login-container a {
            color: #4bc0c8;
            text-decoration: none;
        }

        .login-container a:hover {
            text-decoration: underline;
        }

        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="login-container">
            <img src="Content/imagenes/logo.png" alt="Logo Monsters Inc" />
            <h2>Iniciar Sesión</h2>

            <asp:Label Text="Usuario:" runat="server" /><br />
            <asp:TextBox ID="txtUsuario" runat="server" /><br />

            <asp:Label Text="Contraseña:" runat="server" /><br />
            <asp:TextBox ID="txtPassword" runat="server" TextMode="Password" /><br />

            <asp:Button ID="btnLogin" runat="server" CssClass="asp-button" Text="Ingresar" OnClick="btnLogin_Click" /><br /><br />

            <asp:Label ID="lblMensaje" runat="server" CssClass="error" /><br />

            ¿No tienes una cuenta?
            <a href="Registro.aspx">Regístrate aquí</a>
        </div>
    </form>
</body>
</html>
