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
    public partial class MenuForm : Form
    {
        public MenuForm()
        {
            InitializeComponent();
        }

        private void btnMovimientos_Click(object sender, EventArgs e)
        {
            var form = new MovimientosForm();
            form.ShowDialog(); // Muestra como diálogo
        }

        private void btnRealizar_Click(object sender, EventArgs e)
        {
            var form = new RealizarMovimientoForm();
            form.ShowDialog();
        }

        private void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            // Volver al login
            var login = new LoginForm();
            login.Show();
            this.Close();
        }

        private void MenuForm_Load(object sender, EventArgs e)
        {

        }
    }
}
