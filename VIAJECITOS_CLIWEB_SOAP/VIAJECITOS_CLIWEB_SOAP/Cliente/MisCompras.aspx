<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="MisCompras.aspx.cs" Inherits="VIAJECITOS_CLIWEB_SOAP.Cliente.MisCompras" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Mis Compras</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('../../Content/imagenes/fondo.png') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', sans-serif;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.96);
            width: 700px;
            margin: 60px auto;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 0 25px rgba(0, 0, 0, 0.2);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        .grid {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
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

        .btn-volver {
            margin-top: 25px;
            background-color: #ff4d4d;
            color: white;
            padding: 10px 20px;
            border: none;
            font-weight: bold;
            border-radius: 6px;
            cursor: pointer;
            font-size: 15px;
        }

        .btn-volver:hover {
            background-color: #e63e3e;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="container">
            <h2>🧾 Mis Compras Realizadas</h2>

            <asp:GridView ID="gvMisCompras" runat="server" AutoGenerateColumns="false" CssClass="grid" EmptyDataText="No tienes compras registradas.">
                <Columns>
                    <asp:BoundField DataField="IdCompra" HeaderText="ID Compra" />
                    <asp:BoundField DataField="Ruta" HeaderText="Ruta" />
                    <asp:BoundField DataField="HoraSalida" HeaderText="Hora de Salida" />
                    <asp:BoundField DataField="Valor" HeaderText="Valor" DataFormatString="${0}" />
                    <asp:BoundField DataField="FechaCompra" HeaderText="Fecha de Compra" DataFormatString="{0:dd/MM/yyyy}" />
                </Columns>
            </asp:GridView>

            <asp:Button ID="btnVolver" runat="server" Text="Volver al Menú Principal" CssClass="btn-volver" OnClick="btnVolver_Click" />
        </div>
    </form>
</body>
</html>
