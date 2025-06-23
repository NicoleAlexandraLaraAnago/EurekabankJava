using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using VIAJECITOS_CLIWEB_SOAP.ViajecitosReference;

namespace VIAJECITOS_CLIWEB_SOAP.Cliente
{
    public partial class MisCompras : Page
    {
        ViajecitosServiceClient service = new ViajecitosServiceClient();
        private static List<CompraDetalleDTO> todas;

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
                todas = service.ObtenerComprasPorUsuario(usuario.Id).OrderByDescending(c => c.FechaCompra).ToList();
                BindGrid(todas);
            }
        }

        private void BindGrid(IEnumerable<CompraDetalleDTO> compras)
        {
            var vista = compras.Select(c => new
            {
                c.IdCompra,
                Ruta = $"{c.CiudadOrigen} → {c.CiudadDestino}",
                c.HoraSalida,
                c.Valor,
                c.Cantidad,
                Total = c.Valor * c.Cantidad,
                Fecha = c.FechaCompra.ToString("dd/MM/yyyy")
            });

            gvCompras.DataSource = vista;
            gvCompras.DataBind();
        }

        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(txtFiltroId.Text.Trim(), out int id))
            {
                BindGrid(todas);
                return;
            }

            var filtradas = todas.Where(c => c.IdCompra == id).ToList();
            BindGrid(filtradas);
        }

        protected void btnLimpiar_Click(object sender, EventArgs e)
        {
            txtFiltroId.Text = "";
            BindGrid(todas);
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("../Dashboard.aspx");
        }
    }
}