using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using VIAJECITOS_CLIWEB_SOAP.ViajecitosReference;

namespace VIAJECITOS_CLIWEB_SOAP
{
    public partial class Registro : System.Web.UI.Page
    {
        private readonly ViajecitosServiceClient _service = new ViajecitosServiceClient();

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            lblMensaje.CssClass = "fw-semibold mb-3 d-block";   // reset

            // Validaciones básicas
            if (string.IsNullOrWhiteSpace(txtUsuario.Text) ||
                string.IsNullOrWhiteSpace(txtPassword.Text) ||
                string.IsNullOrWhiteSpace(txtConfirm.Text) ||
                string.IsNullOrWhiteSpace(txtNombre.Text) ||
                string.IsNullOrWhiteSpace(txtApellido.Text))
            {
                lblMensaje.Text = "Todos los campos son obligatorios.";
                lblMensaje.CssClass += " text-danger";
                return;
            }

            if (txtPassword.Text != txtConfirm.Text)
            {
                lblMensaje.Text = "Las contraseñas no coinciden.";
                lblMensaje.CssClass += " text-danger";
                return;
            }

            // Llamar al servicio
            string resp = _service.RegistrarUsuario(
                txtUsuario.Text.Trim(),
                txtPassword.Text.Trim(),
                txtNombre.Text.Trim(),
                txtApellido.Text.Trim());

            if (resp.StartsWith("Error"))
            {
                lblMensaje.Text = resp;
                lblMensaje.CssClass += " text-danger";
            }
            else
            {
                lblMensaje.Text = "💡 " + resp + " Redirigiendo al login…";
                lblMensaje.CssClass += " text-success";

                // Redirige al login luego de 2 s
                Response.AddHeader("REFRESH", "2;URL=Login.aspx");
            }
        }
    }
}