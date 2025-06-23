<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="TodasLasCompras.aspx.cs" Inherits="VIAJECITOS_CLIWEB_SOAP.Admin.TodasLasCompras" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Todas las Compras</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('../../Content/imagenes/fondo.png') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', sans-serif;
        }

        .container {
            max-width: 1100px;
            margin: 60px auto;
            background-color: rgba(255, 255, 255, 0.95);
            padding: 30px 40px;
            border-radius: 16px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
        }

        .titulo {
            font-size: 28px;
            font-weight: bold;
            color: #333;
            margin-bottom: 30px;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .filtros {
            margin-bottom: 30px;
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            align-items: center;
        }

        .filtros label {
            font-weight: bold;
            color: #333;
        }

        .filtros input[type="text"],
        .filtros input[type="date"] {
            padding: 8px;
            border-radius: 6px;
            border: 1px solid #ccc;
            width: 200px;
        }

        .filtros input[type="submit"],
        .filtros input[type="button"],
        .asp-button {
            padding: 8px 16px;
            border: none;
            background-color: #4bc0c8;
            color: white;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
        }

        .filtros input[type="submit"]:hover,
        .filtros input[type="button"]:hover,
        .asp-button:hover {
            background-color: #3ea9b0;
        }

        .grid {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        .grid th, .grid td {
            padding: 12px;
            border: 1px solid #ccc;
            text-align: center;
        }

        .grid th {
            background-color: #e0f7f4;
            color: #2d8e85;
        }

        .botones {
            text-align: center;
            margin-top: 30px;
        }

        .btn-volver {
            background-color: #ff4d4d;
        }

        .btn-volver:hover {
            background-color: #e63e3e;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="container">
            <div class="titulo">📋 Todas las Compras Registradas</div>

            <div class="filtros">
                <asp:Label Text="Usuario:" runat="server" />
                <asp:TextBox ID="txtUsuario" runat="server" />

                <asp:Label Text="Fecha de Compra:" runat="server" />
                <asp:TextBox ID="txtFechaCompra" runat="server" TextMode="Date" />

                <asp:Button ID="btnFiltrar" runat="server" Text="Filtrar" CssClass="asp-button" OnClick="btnFiltrar_Click" />
                <asp:Button ID="btnLimpiar" runat="server" Text="Limpiar" CssClass="asp-button" OnClick="btnLimpiar_Click" />
            </div>

            <asp:GridView ID="gvCompras" runat="server" AutoGenerateColumns="False" CssClass="grid" EmptyDataText="No se han registrado compras.">
                <Columns>
                    <asp:BoundField DataField="IdCompra" HeaderText="ID Compra" />
                    <asp:BoundField DataField="Usuario" HeaderText="Usuario" />
                    <asp:BoundField DataField="NombreCompleto" HeaderText="Nombre Completo" />
                    <asp:BoundField DataField="Ruta" HeaderText="Ruta" />
                    <asp:BoundField DataField="HoraSalida" HeaderText="Hora de Salida" />
                    <asp:BoundField DataField="Valor" HeaderText="Valor" DataFormatString="${0}" />
                    <asp:BoundField DataField="Fecha" HeaderText="Fecha de Compra" />
                </Columns>
            </asp:GridView>

            <div class="botones">
                <asp:Button ID="btnVolver" runat="server" Text="Volver al Menú Principal" CssClass="asp-button btn-volver" OnClick="btnVolver_Click" />
            </div>
        </div>
    </form>
</body>
</html>
