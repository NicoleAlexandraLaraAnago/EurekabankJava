using System;
using VIAJECITOS_CLIWEB_SOAP.ViajecitosReference;

namespace VIAJECITOS_CLIWEB_SOAP
{
    public partial class Dashboard : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Usuario"] == null)
            {
                Response.Redirect("Login.aspx");
                return;
            }

            var usuario = (UsuarioDTO)Session["Usuario"];
            lblNombre.Text = usuario.Nombre;

            if (!IsPostBack)
            {
                if (usuario.Rol == "admin")
                {
                    panelAdmin.Visible = true;
                    panelRolAdmin.Visible = true;
                }
                else
                {
                    panelCliente.Visible = true;
                    panelRolCliente.Visible = true;
                }
            }
        }

        protected void btnBuscarVuelo_Click(object sender, EventArgs e)
        {
            Response.Redirect("Cliente/BuscarVuelo.aspx");
        }

        protected void btnMisCompras_Click(object sender, EventArgs e)
        {
            Response.Redirect("Cliente/MisCompras.aspx");
        }

        protected void btnVerTodas_Click(object sender, EventArgs e)
        {
            Response.Redirect("Admin/TodasLasCompras.aspx");
        }
        protected void btnMisFacturas_Click(object sender, EventArgs e)
        {
            Response.Redirect("Cliente/VerFacturas.aspx");
        }

        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            Session.Clear();
            Response.Redirect("Login.aspx");
        }
    }
}
