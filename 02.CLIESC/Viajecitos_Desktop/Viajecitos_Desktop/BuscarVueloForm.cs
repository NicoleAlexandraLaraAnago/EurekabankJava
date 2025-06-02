using System;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Windows.Forms;
using Viajecitos_Desktop.ViajecitosReference;

namespace Viajecitos_Desktop
{
    public partial class BuscarVueloForm : Form
    {
        private readonly UsuarioDTO _usr;                    // => ahora se asigna
        private readonly ViajecitosServiceClient svc = new ViajecitosServiceClient();

        private const string RutaFondo =
@"C:\Users\Notebook-ASUS\Desktop\VIAJECITOS_SOAP_DOTNET_GR04\02.CLIESC\Viajecitos_Desktop\Viajecitos_Desktop\Resources\2.jpg";

        public BuscarVueloForm(UsuarioDTO usr)
        {
            InitializeComponent();
            _usr = usr;                                      // <- CORREGIDO

            /* ---------------- Estética ---------------- */
            if (File.Exists(RutaFondo))
            {
                BackgroundImage = Image.FromFile(RutaFondo);
                BackgroundImageLayout = ImageLayout.Stretch;
                BackColor = Color.LightPink;     // opaco
            }

            Ui.PintarBoton(btnBuscar);
            Ui.PintarBotonOutline(btnRegresar);

            Ui.EstiloGrid(dgMasCaro);
            Ui.EstiloGrid(dgTodos);

            AgregarBotonCompra(dgMasCaro);
            AgregarBotonCompra(dgTodos);

            /* ---------------- Datos iniciales ---------------- */
            var ciudades = svc.ListarCiudades().ToList();
            cmbOrigen.DataSource = ciudades;
            cmbDestino.DataSource = ciudades.ToList();       // nueva lista
            cmbOrigen.DisplayMember = cmbDestino.DisplayMember = "NombreCiudad";
            cmbOrigen.ValueMember = cmbDestino.ValueMember = "Codigo";

            /* ------------- Enganche de eventos ------------- */
            btnBuscar.Click += BtnBuscar_Click;
            btnRegresar.Click += (_, __) => Close();
            dgMasCaro.CellClick += dgMasCaro_CellClick;
            dgTodos.CellClick += dgTodos_CellClick;
        }

        /* =========================================================
                           BOTÓN   B U S C A R
           =========================================================*/
        private void BtnBuscar_Click(object sender, EventArgs e)
        {
            var o = ((CiudadDTO)cmbOrigen.SelectedItem).Codigo;
            var d = ((CiudadDTO)cmbDestino.SelectedItem).Codigo;
            DateTime f = dtpFecha.Value.Date;

            /* ---- tabla 'Más caro' ---- */
            var caro = svc.BuscarVueloMayorValor(o, d, f);
            dgMasCaro.Columns.Clear();
            dgMasCaro.DataSource = caro == null ? null : new[] {
                new { caro.Id,
                      Ruta       = $"{caro.CiudadOrigen} → {caro.CiudadDestino}",
                      caro.HoraSalida,
                      caro.Valor }
            };
            AgregarBotonCompra(dgMasCaro);

            /* ---- tabla 'Todos' ---- */
            var lista = svc.ListarVuelos(o, d, f)
                           .Select(v => new
                           {
                               v.Id,
                               Ruta = $"{v.CiudadOrigen} → {v.CiudadDestino}",
                               v.HoraSalida,
                               v.Valor
                           })
                           .ToList();

            dgTodos.Columns.Clear();
            dgTodos.DataSource = lista;
            AgregarBotonCompra(dgTodos);
        }

        /* =========================================================
                       COMPRA desde cualquiera de las tablas
           =========================================================*/
        private void dgMasCaro_CellClick(object sender, DataGridViewCellEventArgs e)
            => IntentarCompra(dgMasCaro, e);

        private void dgTodos_CellClick(object sender, DataGridViewCellEventArgs e)
            => IntentarCompra(dgTodos, e);

        private void IntentarCompra(DataGridView dg, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0) return;
            if (dg.Columns[e.ColumnIndex].Name != "Comprar") return;

            int idVuelo = Convert.ToInt32(dg.Rows[e.RowIndex].Cells["Id"].Value);
            string r = svc.RegistrarCompra(idVuelo, _usr.Id);
            MessageBox.Show(r, "Compra");
        }

        /* =========================================================
                         UTILIDADES
           =========================================================*/
        private static void AgregarBotonCompra(DataGridView dg)
        {
            /* Evita duplicar la columna */
            if (dg.Columns.OfType<DataGridViewButtonColumn>()
                          .Any(c => c.Name == "Comprar")) return;

            var col = new DataGridViewButtonColumn
            {
                Name = "Comprar",
                Text = "Comprar",
                UseColumnTextForButtonValue = true
            };
            dg.Columns.Add(col);
        }
    }
}
