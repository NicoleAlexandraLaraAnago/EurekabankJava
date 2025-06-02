using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using VIAJECITOS_CLIWEB_SOAP.ViajecitosReference;

namespace VIAJECITOS_CLIWEB_SOAP.Cliente
{
    public partial class BuscarVuelo : System.Web.UI.Page
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
                var ciudades = service.ListarCiudades();
                ddlOrigen.DataSource = ciudades;
                ddlOrigen.DataTextField = "NombreCiudad";
                ddlOrigen.DataValueField = "Codigo";
                ddlOrigen.DataBind();

                ddlDestino.DataSource = ciudades;
                ddlDestino.DataTextField = "NombreCiudad";
                ddlDestino.DataValueField = "Codigo";
                ddlDestino.DataBind();
            }
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            string origen = ddlOrigen.SelectedValue;
            string destino = ddlDestino.SelectedValue;
            DateTime fecha = DateTime.Parse(txtFecha.Text);

            var vuelos = service.ListarVuelos(origen, destino, fecha);
            if (vuelos.Length == 0)
            {
                lblMensaje.Text = "No se encontraron vuelos disponibles.";
                panelMasCaro.Visible = false;
                panelOtrosVuelos.Visible = false;
                return;
            }

            var vueloMasCaro = vuelos.OrderByDescending(v => v.Valor).First();
            Session["VueloActual"] = vueloMasCaro;

            lblVueloMasCaro.Text = $"ID: {vueloMasCaro.Id}<br />Ruta: {vueloMasCaro.CiudadOrigen} → {vueloMasCaro.CiudadDestino}<br />" +
                                   $"Salida: {vueloMasCaro.HoraSalida}<br />Valor: ${vueloMasCaro.Valor}";
            panelMasCaro.Visible = true;

            var otros = vuelos
                        .Where(v => v.Id != vueloMasCaro.Id)
                        .Select(v => new
                        {
                            v.Id,
                            Ruta = $"{v.CiudadOrigen} → {v.CiudadDestino}",
                            HoraSalida = v.HoraSalida,
                            Valor = v.Valor
                        }).ToList();

            gvOtrosVuelos.DataSource = otros;
            gvOtrosVuelos.DataBind();
            panelOtrosVuelos.Visible = otros.Count > 0;
        }

        protected void btnComprar_Click(object sender, EventArgs e)
        {
            var vuelo = (VueloDTO)Session["VueloActual"];
            var usuario = (UsuarioDTO)Session["Usuario"];

            string resultado = service.RegistrarCompra(vuelo.Id, usuario.Id);
            lblMensaje.Text = "✅ " + resultado;
            panelMasCaro.Visible = false;
            panelOtrosVuelos.Visible = false;
        }

        protected void gvOtrosVuelos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "ComprarVuelo")
            {
                int idVuelo = Convert.ToInt32(e.CommandArgument);
                var usuario = (UsuarioDTO)Session["Usuario"];

                string resultado = service.RegistrarCompra(idVuelo, usuario.Id);
                lblMensaje.Text = $"✅ {resultado}";

                panelMasCaro.Visible = false;
                panelOtrosVuelos.Visible = false;
            }
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("../Dashboard.aspx");
        }
    }
}