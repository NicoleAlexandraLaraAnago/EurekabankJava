<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="BuscarVuelo.aspx.cs" Inherits="VIAJECITOS_CLIWEB_SOAP.Cliente.BuscarVuelo" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Buscar Vuelos</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('../../Content/imagenes/fondo.png') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', sans-serif;
        }

        .form-container {
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

        h3 {
            margin-top: 30px;
            color: #2d8e85;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 20px;
            color: #333;
        }

        .form-control {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }

        .btn-action {
            background-color: #4bc0c8;
            color: white;
            padding: 10px 20px;
            border: none;
            font-weight: bold;
            border-radius: 6px;
            cursor: pointer;
            margin-top: 20px;
            font-size: 15px;
        }

        .btn-action:hover {
            background-color: #3ea9b0;
        }

        .btn-volver {
            background-color: #ff4d4d;
        }

        .btn-volver:hover {
            background-color: #e63e3e;
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

        .success-msg {
            color: green;
            font-weight: bold;
            margin-top: 15px;
            display: block;
        }

        .destacado-panel {
            margin-top: 30px;
            background: linear-gradient(to right, #fefcea, #f1da36);
            border-left: 8px solid #f9b200;
            padding: 20px 30px;
            border-radius: 12px;
            box-shadow: 0 3px 15px rgba(0, 0, 0, 0.1);
        }

        .vuelo-mas-caro {
            font-size: 16px;
            color: #333;
            font-weight: 500;
            line-height: 1.6;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="form-container">
            <h2>Buscar Vuelos</h2>

            <label>Ciudad Origen:</label>
            <asp:DropDownList ID="ddlOrigen" runat="server" CssClass="form-control" />

            <label>Ciudad Destino:</label>
            <asp:DropDownList ID="ddlDestino" runat="server" CssClass="form-control" />

            <label>Fecha del Vuelo:</label>
            <asp:TextBox ID="txtFecha" runat="server" TextMode="Date" CssClass="form-control" />

            <asp:Button ID="btnBuscar" runat="server" Text="Buscar" CssClass="btn-action" OnClick="btnBuscar_Click" />

            <!-- VUELO MÁS CARO DESTACADO -->
            <asp:Panel ID="panelMasCaro" runat="server" Visible="false" CssClass="destacado-panel">
                <h3>✈️ Vuelo Más Caro Encontrado</h3>
                <div class="vuelo-mas-caro">
                    <asp:Label ID="lblVueloMasCaro" runat="server" />
                    <br /><br />
                    <asp:Button ID="btnComprar" runat="server" Text="Comprar Este Vuelo" CssClass="btn-action" OnClick="btnComprar_Click" />
                </div>
            </asp:Panel>

            <!-- OTROS VUELOS -->
            <asp:Panel ID="panelOtrosVuelos" runat="server" Visible="false">
                <h3>🛫 Otros Vuelos Disponibles</h3>
                <asp:GridView ID="gvOtrosVuelos" runat="server" AutoGenerateColumns="False" CssClass="grid" OnRowCommand="gvOtrosVuelos_RowCommand">
                    <Columns>
                        <asp:BoundField HeaderText="ID" DataField="Id" />
                        <asp:BoundField HeaderText="Ruta" DataField="Ruta" />
                        <asp:BoundField HeaderText="Hora de Salida" DataField="HoraSalida" />
                        <asp:BoundField HeaderText="Valor" DataField="Valor" DataFormatString="${0}" />
                        <asp:TemplateField>
                            <ItemTemplate>
                                <asp:Button ID="btnComprarOtro" runat="server" Text="Comprar"
                                    CssClass="btn-action"
                                    CommandName="ComprarVuelo"
                                    CommandArgument='<%# Eval("Id") %>' />
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>
            </asp:Panel>

            <asp:Label ID="lblMensaje" runat="server" CssClass="success-msg" />

            <asp:Button ID="btnVolver" runat="server" Text="Volver al Menú Principal" CssClass="btn-action btn-volver" OnClick="btnVolver_Click" />
        </div>
    </form>
</body>
</html>
