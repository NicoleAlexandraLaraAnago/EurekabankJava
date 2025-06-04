using System;
using VIAJECITOS_CLIWEB_SOAP.ViajecitosReference;

namespace VIAJECITOS_CLIWEB_SOAP
{
    public partial class Login : System.Web.UI.Page
    {
        ViajecitosServiceClient service = new ViajecitosServiceClient();

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            string usuario = txtUsuario.Text.Trim();
            string password = txtPassword.Text.Trim();

            var result = service.Login(usuario, password);
            if (result != null)
            {
                Session["Usuario"] = result;
                Response.Redirect("Dashboard.aspx");
            }
            else
            {
                lblMensaje.Text = "Credenciales incorrectas.";
            }
        }
    }
}
