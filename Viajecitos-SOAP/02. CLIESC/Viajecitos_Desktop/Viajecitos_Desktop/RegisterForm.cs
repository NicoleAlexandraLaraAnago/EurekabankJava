using System;
using System.Drawing;
using System.IO;
using System.Windows.Forms;
using Viajecitos_Desktop.ViajecitosReference;

namespace Viajecitos_Desktop
{
    public partial class DashboardForm : Form
    {
        private readonly ViajecitosServiceClient svc = new ViajecitosServiceClient();

        /// <summary>
        /// Propiedades que devolvemos al LoginForm si el registro fue exitoso.
        /// </summary>
        public string NuevoUsuario { get; private set; } = "";
        public string NuevaClave { get; private set; } = "";

        /// <summary>
        /// Ruta absoluta de la imagen de fondo.
        /// </summary>
        private const string RutaFondo =
@"C:\Users\Notebook-ASUS\Desktop\VIAJECITOS_SOAP_DOTNET_GR04\02.CLIESC\Viajecitos_Desktop\Viajecitos_Desktop\Resources\2.jpg";

        public DashboardForm()
        {
            InitializeComponent();

            panelFormulario.BackColor = Color.FromArgb(180, Color.White); // Blanco con opacidad
   


            // Si existe la imagen, la cargamos como fondo y ponemos backcolor opaco
            if (File.Exists(RutaFondo))
            {
                BackgroundImage = Image.FromFile(RutaFondo);
                BackgroundImageLayout = ImageLayout.Stretch;
            }


            // Asociamos los eventos de clic
            btnRegistrar.Click += BtnRegistrar_Click;

            // El botón "Cancelar" simplemente cierra el diálogo devolviendo Cancel
            btnCancelar.Click += (s, e) => DialogResult = DialogResult.Cancel;
        }

        /* ===========================================================
         *                   Evento: BtnRegistrar_Click
         * Validamos que los TextBox realmente existan y estén llenos.
         * =========================================================*/
        private void BtnRegistrar_Click(object sender, EventArgs e)
        {
            // --- Leemos cada campo usando el nombre real de control ---
            string usuario = txtUsuarioReg.Text.Trim();
            string pass = txtPasswordReg.Text.Trim();
            string confirmar = txtConfirmReg.Text.Trim();
            string nombre = txtNombreReg.Text.Trim();
            string apellido = txtApellidoReg.Text.Trim();

            // --- Validaciones ---
            // 1) Ninguno debe quedar en blanco
            // 2) La contraseña y confirmación deben coincidir
            if (string.IsNullOrEmpty(usuario)
                || string.IsNullOrEmpty(pass)
                || string.IsNullOrEmpty(confirmar)
                || string.IsNullOrEmpty(nombre)
                || string.IsNullOrEmpty(apellido)
                || pass != confirmar)
            {
                MessageBox.Show(
                    "Complete todos los campos y verifique la contraseña.",
                    "Atención",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Warning
                );
                return;
            }

            try
            {
                // Llamamos al servicio para registrar el usuario
                string mensaje =
                    svc.RegistrarUsuario(usuario, pass, nombre, apellido);

                // Mostramos el texto que devuelva el servicio
                MessageBox.Show(
                    mensaje,
                    "Registro",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information
                );

                // Si el mensaje contiene "exitoso", devolvemos OK al Login con los datos
                if (mensaje.IndexOf("exitoso", StringComparison.OrdinalIgnoreCase) >= 0)
                {
                    NuevoUsuario = usuario;
                    NuevaClave = pass;
                    DialogResult = DialogResult.OK;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(
                    "Error al registrar usuario:\n" + ex.Message,
                    "Error",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error
                );
            }
        }
    }
}
