using EUREKA_BANK_CLIESC_RESTFUL.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace EUREKA_BANK_CLIESC_RESTFUL
{
    public partial class LoginForm : Form
    {
        private readonly ApiService _api;
        public LoginForm()
        {
            InitializeComponent();
            _api = new ApiService();
        }
        private async void btnLogin_Click(object sender, EventArgs e)
        {
            string usuario = txtUsuario.Text.Trim();
            string clave = txtClave.Text.Trim();

            if (await _api.LoginAsync(usuario, clave))
            {
                // Abrir menú principal y ocultar este
                MenuForm menu = new MenuForm();
                menu.Show();
                this.Hide();
            }
            else
            {
                lblMensaje.Text = "Credenciales incorrectas.";
                lblMensaje.Visible = true;
            }
        }

        private void LoginForm_Load(object sender, EventArgs e)
        {
            lblMensaje.Visible = false;
        }
    }
}
