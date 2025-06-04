<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Dashboard.aspx.cs" Inherits="VIAJECITOS_CLIWEB_SOAP.Dashboard" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Dashboard</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('Content/imagenes/fondo.png') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', sans-serif;
        }

        .dashboard-container {
            background-color: rgba(255, 255, 255, 0.96);
            width: 450px;
            margin: 100px auto;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 0 25px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        h2 {
            margin-bottom: 15px;
            color: #333;
        }

        .badge-role {
            display: inline-block;
            padding: 8px 18px;
            border-radius: 50px;
            font-weight: bold;
            font-size: 15px;
            margin-bottom: 25px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .badge-role.cliente {
            background-color: #e0f7f4;
            color: #2d8e85;
            border: 2px solid #4bc0c8;
        }

        .badge-role.admin {
            background-color: #fff3cd;
            color: #856404;
            border: 2px solid #ffc107;
        }

        .btn-action {
            width: 100%;
            background-color: #4bc0c8;
            color: white;
            border: none;
            padding: 12px;
            font-weight: bold;
            border-radius: 6px;
            margin-bottom: 15px;
            cursor: pointer;
            font-size: 16px;
        }

        .btn-action:hover {
            background-color: #3ea9b0;
        }

        .btn-logout {
            margin-top: 15px;
            background-color: #ff4d4d;
        }

        .btn-logout:hover {
            background-color: #e63e3e;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="dashboard-container">
            <h2>Bienvenido, <asp:Label ID="lblNombre" runat="server" /> 👋</h2>

            <asp:Panel ID="panelRolCliente" runat="server" Visible="false">
                <div class="badge-role cliente">🟢 Cliente Viajecitos</div>
            </asp:Panel>

            <asp:Panel ID="panelRolAdmin" runat="server" Visible="false">
                <div class="badge-role admin">🔧 Administrador General</div>
            </asp:Panel>

            <!-- Panel para clientes -->
            <asp:Panel ID="panelCliente" runat="server" Visible="false">
                <asp:Button ID="btnBuscarVuelo" runat="server" Text="Buscar Vuelo" CssClass="btn-action" OnClick="btnBuscarVuelo_Click" />
                <asp:Button ID="btnMisCompras" runat="server" Text="Mis Compras" CssClass="btn-action" OnClick="btnMisCompras_Click" />
                <asp:Button ID="btnMisFacturas" runat="server" Text="Ver Facturas" CssClass="btn btn-action" OnClick="btnMisFacturas_Click" />

            </asp:Panel>

            <!-- Panel para admin -->
            <asp:Panel ID="panelAdmin" runat="server" Visible="false">
                <asp:Button ID="btnVerTodas" runat="server" Text="Ver Todas las Compras" CssClass="btn-action" OnClick="btnVerTodas_Click" />
            </asp:Panel>

            <asp:Button ID="btnCerrarSesion" runat="server" Text="Cerrar Sesión" CssClass="btn-action btn-logout" OnClick="btnCerrarSesion_Click" />
        </div>
    </form>
</body>
</html>
