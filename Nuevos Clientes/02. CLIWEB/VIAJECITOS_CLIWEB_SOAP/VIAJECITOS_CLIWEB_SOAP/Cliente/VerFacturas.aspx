<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="VerFacturas.aspx.cs" Inherits="VIAJECITOS_CLIWEB_SOAP.Cliente.VerFacturas" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Mis Facturas</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('../Content/imagenes/fondo.png') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', sans-serif;
        }

        .facturas-container {
            max-width: 850px;
            margin: 100px auto;
            background-color: rgba(255, 255, 255, 0.97);
            padding: 40px;
            border-radius: 24px;
            box-shadow: 0 0 25px rgba(0, 0, 0, 0.3);
        }

        .facturas-container h2 {
            text-align: center;
            font-size: 26px;
            margin-bottom: 30px;
            color: #333;
        }

        .facturas-container h2::before {
            content: '📑 ';
        }

        .tabla-facturas {
            width: 100%;
            border-collapse: collapse;
        }

        .tabla-facturas th {
            background-color: #4bc0c8;
            color: white;
            padding: 12px;
            text-align: left;
            font-weight: bold;
        }

        .tabla-facturas td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        .tabla-facturas tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .tabla-facturas tr:hover {
            background-color: #e0f7f4;
        }

        .btn-volver {
            display: block;
            margin: 30px auto 0;
            background-color: #ff4d4d;
            border: none;
            padding: 14px 24px;
            color: white;
            font-weight: bold;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn-volver:hover {
            background-color: #e63e3e;
        }

        .link-detalle {
            color: #007bff;
            font-weight: bold;
            text-decoration: underline;
            cursor: pointer;
        }

        .modal-factura {
            display: none;
            position: fixed;
            z-index: 999;
            left: 0; top: 0;
            width: 100%; height: 100%;
            background-color: rgba(0, 0, 0, 0.6);
            overflow: auto;
        }

        .modal-contenido {
            background-color: #fff;
            margin: 10% auto;
            padding: 30px;
            border-radius: 12px;
            width: 400px;
            text-align: left;
            font-size: 16px;
            position: relative;
            box-shadow: 0 0 15px rgba(0,0,0,0.3);
            animation: fadeIn 0.3s ease-in-out;
        }

        .modal-contenido h3 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 22px;
            color: #333;
        }

        .modal-contenido div {
            line-height: 1.8;
        }

        .cerrar {
            position: absolute;
            right: 16px;
            top: 12px;
            font-size: 24px;
            color: #888;
            cursor: pointer;
        }

        .cerrar:hover {
            color: #f44336;
        }

        @keyframes fadeIn {
            from {opacity: 0;}
            to {opacity: 1;}
        }

    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="facturas-container">
            <h2>Mis Facturas</h2>

            <asp:GridView ID="gvFacturas" runat="server" AutoGenerateColumns="False" CssClass="tabla-facturas"
                OnRowCommand="gvFacturas_RowCommand">
                <Columns>
                    <asp:BoundField DataField="IdCompra" HeaderText="ID Compra" />
                    <asp:BoundField DataField="CiudadDestino" HeaderText="Destino" />
                    <asp:BoundField DataField="FechaFactura" HeaderText="Fecha" />
                    <asp:BoundField DataField="Boletos" HeaderText="Boletos" />
                    <asp:BoundField DataField="TotalAPagar" HeaderText="Total" DataFormatString="${0:N2}" />

                    <asp:TemplateField HeaderText="Acción">
                        <ItemTemplate>
                            <asp:LinkButton ID="lnkVerDetalle" runat="server"
                                            CommandName="VerDetalle"
                                            CommandArgument="<%# Container.DataItemIndex %>"
                                            CssClass="link-detalle"
                                            Text="Ver Detalle" />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>

            <asp:Button ID="btnVolver" runat="server" CssClass="btn-volver" Text="🔙 Volver al Dashboard"
                        OnClick="btnVolver_Click" />
            <!-- Modal de factura -->
            <div id="modalFactura" class="modal-factura" style="display:none;">
                <div class="modal-contenido">
                    <span class="cerrar" onclick="cerrarModal()">&times;</span>
                    <h3>🧾 Factura Detallada</h3>
                    <div id="contenidoFactura"></div>
                </div>
            </div>

        </div>
    </form>
    <script type="text/javascript">
    function cerrarModal() {
        document.getElementById('modalFactura').style.display = 'none';
    }

    // Cerrar si el usuario da clic fuera del modal
    window.onclick = function(event) {
        var modal = document.getElementById('modalFactura');
        if (event.target === modal) {
            cerrarModal();
        }
    }
    </script>

</body>
</html>
