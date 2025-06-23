<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Registro.aspx.cs" Inherits="VIAJECITOS_CLIWEB_SOAP.Registro" %>

<!DOCTYPE html>
<html lang="es">
<head runat="server">
    <meta charset="utf-8" />
    <title>Registro - Viajecitos</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('Content/imagenes/fondo.png') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', sans-serif;
        }

        .registro-container {
            background-color: rgba(255, 255, 255, 0.96);
            width: 420px;
            margin: 80px auto;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        .registro-container img {
            width: 150px;
            margin-bottom: 15px;
        }

        .registro-container h2 {
            margin-bottom: 25px;
            color: #3b3f51;
        }

        .form-label {
            text-align: left;
            display: block;
            font-weight: 600;
            color: #333;
            margin-bottom: 4px;
        }

        .form-control {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 6px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        .btn-primary {
            background-color: #4bc0c8;
            color: white;
            border: none;
            padding: 10px;
            font-weight: bold;
            border-radius: 6px;
            width: 100%;
            cursor: pointer;
        }

        .btn-primary:hover {
            background-color: #3ea9b0;
        }

        .text-link {
            margin-top: 20px;
            display: block;
            color: #4bc0c8;
            text-decoration: none;
        }

        .text-link:hover {
            text-decoration: underline;
        }

        .text-danger {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <form runat="server">
        <div class="registro-container">
            <img src="Content/imagenes/logo.png" alt="Logo" />
            <h2>Crear Cuenta</h2>

            <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger" />

            <label class="form-label">Usuario</label>
            <asp:TextBox ID="txtUsuario" runat="server" CssClass="form-control" placeholder="Nombre de usuario" />

            <label class="form-label">Contraseña</label>
            <asp:TextBox ID="txtPassword" runat="server" TextMode="Password" CssClass="form-control" placeholder="Contraseña" />

            <label class="form-label">Confirmar Contraseña</label>
            <asp:TextBox ID="txtConfirm" runat="server" TextMode="Password" CssClass="form-control" placeholder="Repite tu contraseña" />

            <label class="form-label">Nombre</label>
            <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" placeholder="Tu nombre" />

            <label class="form-label">Apellido</label>
            <asp:TextBox ID="txtApellido" runat="server" CssClass="form-control" placeholder="Tu apellido" />

            <asp:Button ID="btnRegistrar" runat="server" Text="Registrarse" CssClass="btn-primary" OnClick="btnRegistrar_Click" />

            <a href="Login.aspx" class="text-link">¿Ya tienes una cuenta? Inicia sesión</a>
        </div>
    </form>
</body>
</html>
