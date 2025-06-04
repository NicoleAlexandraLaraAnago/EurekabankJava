using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using VIAJECITOS_CLIWEB_SOAP.ViajecitosReference;

namespace VIAJECITOS_CLIWEB_SOAP.Cliente
{
    public partial class VerFacturas : System.Web.UI.Page
    {
        private readonly ViajecitosServiceClient svc = new ViajecitosServiceClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Usuario"] == null)
            {
                Response.Redirect("../Login.aspx");
                return;
            }

            if (!IsPostBack)
            {
                var usuario = (UsuarioDTO)Session["Usuario"];
                var facturas = svc.ObtenerFacturasPorUsuario(usuario.Id).ToList();

                ViewState["Facturas"] = facturas;

                var datos = facturas.Select(f => new
                {
                    f.IdCompra,
                    f.CiudadDestino,
                    FechaFactura = f.FechaFactura.ToString("yyyy-MM-dd HH:mm"),
                    Boletos = (int)(f.Subtotal / f.ValorUnitario),
                    f.TotalAPagar
                }).ToList();

                gvFacturas.DataSource = datos;
                gvFacturas.DataBind();
            }
        }

        protected void gvFacturas_RowCommand(object sender, System.Web.UI.WebControls.GridViewCommandEventArgs e)
        {
            if (e.CommandName == "VerDetalle")
            {
                int index = Convert.ToInt32(e.CommandArgument);
                var facturas = ViewState["Facturas"] as List<FacturaDTO>;

                if (facturas != null && index >= 0 && index < facturas.Count)
                {
                    var factura = facturas[index];
                    int cantidadBoletos = (int)(factura.Subtotal / factura.ValorUnitario);

                    string html = $@"
                        🧍 <strong>Comprador:</strong> {factura.NombreComprador}<br/>
                        🛫 <strong>Ruta:</strong> {factura.CiudadOrigen} → {factura.CiudadDestino}<br/>
                        📅 <strong>Fecha:</strong> {factura.FechaFactura:yyyy-MM-dd HH:mm}<br/>
                        🎫 <strong>Boletos:</strong> {cantidadBoletos}<br/>
                        💵 <strong>Valor Unitario:</strong> ${factura.ValorUnitario:N2}<br/>
                        💰 <strong>Subtotal:</strong> ${factura.Subtotal:N2}<br/>
                        🧾 <strong>IVA:</strong> ${factura.IVA:N2}<br/>
                        🔢 <strong>Total a Pagar:</strong> ${factura.TotalAPagar:N2}";

                                        ScriptManager.RegisterStartupScript(this, GetType(), "mostrarFactura", $@"
                        document.getElementById('contenidoFactura').innerHTML = `{html}`;
                        document.getElementById('modalFactura').style.display = 'block';
                    ", true);

                }
            }
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("../Dashboard.aspx");
        }
    }
}
