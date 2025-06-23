using System;
using System.Collections.Generic;
using System.Linq;
using VIAJECITOS_CLIWEB_SOAP.ViajecitosReference;

namespace VIAJECITOS_CLIWEB_SOAP.Admin
{
    public partial class TodasLasCompras : System.Web.UI.Page
    {
        ViajecitosServiceClient service = new ViajecitosServiceClient();
        private static List<CompraDetalleDTO> todasLasCompras;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Usuario"] == null)
            {
                Response.Redirect("../Login.aspx");
                return;
            }

            var usuario = (UsuarioDTO)Session["Usuario"];
            if (usuario.Rol != "admin")
            {
                Response.Redirect("../Dashboard.aspx");
                return;
            }

            if (!IsPostBack)
            {
                todasLasCompras = service.ObtenerTodasLasCompras().ToList();
                BindGrid(todasLasCompras);
            }
        }

        private void BindGrid(List<CompraDetalleDTO> compras)
        {
            var lista = compras.Select(c => new
            {
                c.IdCompra,
                c.Usuario,
                NombreCompleto = $"{c.Nombre} {c.Apellido}",
                Ruta = $"{c.CiudadOrigen} → {c.CiudadDestino}",
                c.HoraSalida,
                c.Valor,
                Fecha = c.FechaCompra.ToString("yyyy-MM-dd")
            }).ToList();

            gvCompras.DataSource = lista;
            gvCompras.DataBind();
        }

        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            string filtroUsuario = txtUsuario.Text.Trim().ToLower();
            DateTime? fecha = string.IsNullOrWhiteSpace(txtFechaCompra.Text)
                ? (DateTime?)null
                : DateTime.Parse(txtFechaCompra.Text);

            var filtrado = todasLasCompras.Where(c =>
                (string.IsNullOrWhiteSpace(filtroUsuario) ||
                 c.Usuario.ToLower().Contains(filtroUsuario) ||
                 $"{c.Nombre} {c.Apellido}".ToLower().Contains(filtroUsuario)) &&
                (!fecha.HasValue || c.FechaCompra.Date == fecha.Value.Date)
            ).ToList();

            BindGrid(filtrado);
        }

        protected void btnLimpiar_Click(object sender, EventArgs e)
        {
            txtUsuario.Text = "";
            txtFechaCompra.Text = "";
            BindGrid(todasLasCompras);
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("../Dashboard.aspx");
        }
    }
}
