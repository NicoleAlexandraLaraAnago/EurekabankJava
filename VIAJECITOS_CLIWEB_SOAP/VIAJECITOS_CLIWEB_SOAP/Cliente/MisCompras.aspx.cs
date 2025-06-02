using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using VIAJECITOS_CLIWEB_SOAP.ViajecitosReference;

namespace VIAJECITOS_CLIWEB_SOAP.Cliente
{
    public partial class MisCompras : System.Web.UI.Page
    {
        ViajecitosServiceClient service = new ViajecitosServiceClient();

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
                var compras = service.ObtenerComprasPorUsuario(usuario.Id);

                var lista = compras.Select(c => new
                {
                    c.IdCompra,
                    Ruta = $"{c.CiudadOrigen} → {c.CiudadDestino}",
                    c.HoraSalida,
                    c.Valor,
                    c.FechaCompra
                }).ToList();

                gvMisCompras.DataSource = lista;
                gvMisCompras.DataBind();
            }
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("../Dashboard.aspx");
        }
    }
}