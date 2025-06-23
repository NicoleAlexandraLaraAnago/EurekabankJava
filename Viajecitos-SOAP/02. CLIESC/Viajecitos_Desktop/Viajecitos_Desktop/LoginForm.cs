using System;
using System.Drawing;
using System.IO;
using System.Windows.Forms;
using Viajecitos_Desktop.ViajecitosReference;

/* ↙ Descomenta SOLO si RegisterForm
      está en un sub-namespace:
 */
// using Viajecitos_Desktop.Forms;

namespace Viajecitos_Desktop         // <– namespace de LoginForm
{
    public partial class LoginForm : Form
    {
        private readonly ViajecitosServiceClient svc = new ViajecitosServiceClient();

        private const string RutaFondo =
@"C:\Users\Notebook-ASUS\Desktop\VIAJECITOS_SOAP_DOTNET_GR04\02.CLIESC\Viajecitos_Desktop\Viajecitos_Desktop\Resources\2.jpg";

        private const string RutaAvatar =
@"C:\Users\Notebook-ASUS\Desktop\VIAJECITOS_SOAP_DOTNET_GR04\02.CLIESC\Viajecitos_Desktop\Viajecitos_Desktop\Resources\1.jpg";

        public LoginForm()
        {
            InitializeComponent();


            btnIngresar.Click += BtnIngresar_Click;
            btnRegistrar.Click += BtnRegistrar_Click;

            Load += LoginForm_Load;
        }

        /* -------------  LOAD --------------- */
        private void LoginForm_Load(object sender, EventArgs e)
        {
            if (File.Exists(RutaFondo))
            {
                BackgroundImage = Image.FromFile(RutaFondo);
                BackgroundImageLayout = ImageLayout.Stretch;
                BackColor = Color.LightPink;
            }

            if (File.Exists(RutaAvatar))
                picAvatar.Image = Image.FromFile(RutaAvatar);
        }

        /* -------- REGISTRARSE -------------- */
        private void BtnRegistrar_Click(object sender, EventArgs e)
        {
            /* Si RegisterForm está en el mismo namespace NO
               hace falta calificar; si está en otro, usa el
               namespace completo o agrega el using.           */

            using (var rf = new DashboardForm())
            {
                if (rf.ShowDialog() == DialogResult.OK)
                {
                    txtUsuario.Text = rf.NuevoUsuario;
                    txtPassword.Text = rf.NuevaClave;
                    txtPassword.Focus();
                }
            }
        }

        /* ------------- INGRESAR ------------ */
        private void BtnIngresar_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtUsuario.Text) ||
                txtPassword.TextLength == 0)
            {
                MessageBox.Show("Ingrese usuario y contraseña.");
                return;
            }

            try
            {
                UsuarioDTO u = svc.Login(txtUsuario.Text.Trim(),
                                         txtPassword.Text);

                if (u == null)
                {
                    MessageBox.Show("Credenciales incorrectas.");
                    return;
                }

                Form dash = u.Rol.Equals("admin", StringComparison.OrdinalIgnoreCase)
                            ? (Form)new DashboardAdminForm(u)
                            : (Form)new DashboardUserForm(u);

                dash.Owner = this;
                dash.Show();
                Hide();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error servicio:\n" + ex.Message,
                                "Error", MessageBoxButtons.OK,
                                MessageBoxIcon.Error);
            }
        }
    }
}
