<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="MisCompras.aspx.cs" Inherits="VIAJECITOS_CLIWEB_SOAP.Cliente.MisCompras" %>

<!DOCTYPE html>
<html lang="es">
<head runat="server">
    <meta charset="utf-8" />
    <title>Mis Compras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />

    <style>
        :root {
            --mor1: #f4e8ff;
            --mor2: #d5c6ff;
            --mor3: #b59bff;
        }

        body {
            min-height: 100vh;
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: url('<%= ResolveUrl("~/Content/imagenes/2.jpg") %>') no-repeat center/cover fixed;
        }

        body::before {
            content: "";
            position: fixed;
            inset: 0;
            background: linear-gradient(135deg, var(--mor1), var(--mor2));
            opacity: .55;
            backdrop-filter: blur(6px);
            z-index: -1;
        }

        .contenedor {
            max-width: 1100px;
            margin: 60px auto;
            background: #fff;
            border-radius: 1.5rem;
            box-shadow: 0 8px 24px rgba(112, 51, 170, 0.2);
            padding: 40px;
            box-sizing: border-box;
            position: relative;
            z-index: 1;
        }

        h3 {
            color: #5b3b8f;
            font-weight: bold;
        }

        .btn-main {
            background: var(--mor3);
            border: none;
            color: white;
        }

        .btn-main:hover {
            background: #a287ff;
        }

        .form-label {
            font-weight: 600;
        }

        table thead {
            background-color: var(--mor3);
            color: white;
        }

        table tr:nth-child(even) {
            background-color: #f8f6ff;
        }

        .text-end button, .text-end input {
            min-width: 140px;
        }
    </style>
</head>
<body>
    <form runat="server">
        <div class="contenedor">
            <h3 class="mb-4">🧾 Mis Compras Realizadas</h3>

            <div class="row g-2 mb-3">
                <div class="col-md-4">
                    <label class="form-label">Filtrar por ID:</label>
                    <asp:TextBox ID="txtFiltroId" runat="server" CssClass="form-control" />
                </div>
                <div class="col-md-2 d-grid">
                    <asp:Button ID="btnFiltrar" runat="server" Text="Filtrar" CssClass="btn btn-main" OnClick="btnFiltrar_Click" />
                </div>
                <div class="col-md-2 d-grid">
                    <asp:Button ID="btnLimpiar" runat="server" Text="Limpiar" CssClass="btn btn-secondary" OnClick="btnLimpiar_Click" />
                </div>
            </div>

            <asp:GridView ID="gvCompras" runat="server" AutoGenerateColumns="false" CssClass="table table-bordered text-center">
                <Columns>
                    <asp:BoundField DataField="IdCompra" HeaderText="ID Compra" />
                    <asp:BoundField DataField="Ruta" HeaderText="Ruta" />
                    <asp:BoundField DataField="HoraSalida" HeaderText="Hora" />
                    <asp:BoundField DataField="Valor" HeaderText="Valor" DataFormatString="{0:C2}" />
                    <asp:BoundField DataField="Cantidad" HeaderText="Cantidad" />
                    <asp:BoundField DataField="Total" HeaderText="Total a Pagar" DataFormatString="{0:C2}" />
                    <asp:BoundField DataField="Fecha" HeaderText="Fecha" />
                </Columns>
            </asp:GridView>

            <div class="text-end mt-4">
                <asp:Button ID="btnVolver" runat="server" Text="⬅ Volver al menú" CssClass="btn btn-secondary" OnClick="btnVolver_Click" />
            </div>

    

        </div>
    </form>
</body>
</html>
