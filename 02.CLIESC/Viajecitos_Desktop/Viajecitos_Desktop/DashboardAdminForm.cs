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

        private const string RutaFondo =
@"C:\Users\Notebook-ASUS\Desktop\VIAJECITOS_SOAP_DOTNET_GR04\02.CLIESC\Viajecitos_Desktop\Viajecitos_Desktop\Resources\2.jpg";
        private const string RutaAvatar =
@"C:\Users\Notebook-ASUS\Desktop\VIAJECITOS_SOAP_DOTNET_GR04\02.CLIESC\Viajecitos_Desktop\Viajecitos_Desktop\Resources\1.jpg";

        public DashboardAdminForm(UsuarioDTO usr)
        {
            InitializeComponent();
            _usr = usr;

            /* Fondo */
            if (File.Exists(RutaFondo))
            {
                BackgroundImage = Image.FromFile(RutaFondo);
                BackgroundImageLayout = ImageLayout.Stretch;
                
            }

            /* Avatar */
            if (File.Exists(RutaAvatar))
                picAvatar.Image = Image.FromFile(RutaAvatar);

            lblSaludo.Text = $"Bienvenido, {_usr.Nombre}";
            lblRol.Text = _usr.Rol;

            Ui.PintarBoton(btnTodasCompras);
            Ui.PintarBotonOutline(btnCerrar);

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
