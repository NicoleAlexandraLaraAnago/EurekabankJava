using System;
using System.Drawing;
using System.IO;
using System.Windows.Forms;
using Viajecitos_Desktop.ViajecitosReference;

namespace Viajecitos_Desktop
{
    public partial class DashboardUserForm : Form
    {
        private readonly UsuarioDTO _usr;

        private const string RutaFondo =
@"C:\Users\Notebook-ASUS\Desktop\VIAJECITOS_SOAP_DOTNET_GR04\02.CLIESC\Viajecitos_Desktop\Viajecitos_Desktop\Resources\2.jpg";
        private const string RutaAvatar =
@"C:\Users\Notebook-ASUS\Desktop\VIAJECITOS_SOAP_DOTNET_GR04\02.CLIESC\Viajecitos_Desktop\Viajecitos_Desktop\Resources\1.jpg";

        public DashboardUserForm(UsuarioDTO usr)
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

            Ui.PintarBoton(btnBuscarVuelo);
            Ui.PintarBoton(btnMisCompras);
            Ui.PintarBotonOutline(btnCerrar);

            btnBuscarVuelo.Click += BtnBuscarVuelo_Click;
            btnMisCompras.Click += BtnMisCompras_Click;
            btnCerrar.Click += BtnCerrar_Click;
        }

        private void BtnBuscarVuelo_Click(object sender, EventArgs e)
        {
            using (var f = new BuscarVueloForm(_usr))
            {
                f.ShowDialog();
            }
        }

        private void BtnMisCompras_Click(object sender, EventArgs e)
        {
            using (var f = new MisComprasForm(_usr))
            {
                f.ShowDialog();
            }
        }

        private void BtnCerrar_Click(object sender, EventArgs e)
        {
            Owner?.Show();
            Close();
        }

        /* Si el diseñador aún referencia Load, déjalo vacío */
        private void DashboardUserForm_Load(object sender, EventArgs e) { }
    }
}
