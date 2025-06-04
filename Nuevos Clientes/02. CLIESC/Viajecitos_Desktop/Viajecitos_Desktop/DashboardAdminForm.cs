using System;
using System.Drawing;
using System.IO;
using System.Windows.Forms;
using Viajecitos_Desktop.ViajecitosReference;

namespace Viajecitos_Desktop
{
    public partial class DashboardAdminForm : Form
    {
        private readonly UsuarioDTO _usr;


        public DashboardAdminForm(UsuarioDTO usr)
        {
            InitializeComponent();
            _usr = usr;

            panelFormulario.BackColor = Color.FromArgb(180, Color.White);

            lblSaludo.Text = $"Bienvenido, {_usr.Nombre}";
            lblRol.Text = _usr.Rol;

            btnTodasCompras.Click += BtnTodasCompras_Click;
            btnCerrar.Click += BtnCerrar_Click;
        }

        private void BtnTodasCompras_Click(object sender, EventArgs e)
        {
            using (var f = new TodasComprasForm())
            {
                f.ShowDialog();
            }
        }

        private void BtnCerrar_Click(object sender, EventArgs e)
        {
            Owner?.Show();
            Close();
        }

        private void DashboardAdminForm_Load(object sender, EventArgs e) { }

        private void lblSaludo_Click(object sender, EventArgs e)
        {

        }
    }
}
