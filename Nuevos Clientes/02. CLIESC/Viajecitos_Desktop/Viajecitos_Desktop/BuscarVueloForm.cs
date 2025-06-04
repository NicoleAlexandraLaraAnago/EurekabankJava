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
        private readonly UsuarioDTO _usr;
        private readonly ViajecitosServiceClient svc = new ViajecitosServiceClient();

        public BuscarVueloForm(UsuarioDTO usr)
        {
            InitializeComponent();
            //panelFormulario.BackColor = Color.FromArgb(180, Color.White);
            _usr = usr;

            var ciudades = svc.ListarCiudades().ToList();
            cmbOrigen.DataSource = ciudades;
            cmbDestino.DataSource = ciudades.ToList();
            cmbOrigen.DisplayMember = cmbDestino.DisplayMember = "NombreCiudad";
            cmbOrigen.ValueMember = cmbDestino.ValueMember = "Codigo";

            btnBuscar.Click += BtnBuscar_Click;
            btnRegresar.Click += (_, __) => Close();
            dgMasCaro.CellClick += dgMasCaro_CellClick;
            dgTodos.CellClick += dgTodos_CellClick;

            AplicarEstilo(dgMasCaro);
            AplicarEstilo(dgTodos);
        }

        private void BtnBuscar_Click(object sender, EventArgs e)
        {
            var o = ((CiudadDTO)cmbOrigen.SelectedItem).Codigo;
            var d = ((CiudadDTO)cmbDestino.SelectedItem).Codigo;
            DateTime f = dtpFecha.Value.Date;

            var caro = svc.BuscarVueloMayorValor(o, d, f);
            dgMasCaro.Columns.Clear();
            if (caro != null)
            {
                dgMasCaro.DataSource = new[] {
                    new {
                        IdVuelo = caro.Id,
                        Ruta = $"{caro.CiudadOrigen} → {caro.CiudadDestino}",
                        caro.HoraSalida,
                        caro.Valor
                    }
                };
                AgregarBotonCompra(dgMasCaro);
            }

            var lista = svc.ListarVuelos(o, d, f)
                           .Select(v => new
                           {
                               IdVuelo = v.Id,
                               Ruta = $"{v.CiudadOrigen} → {v.CiudadDestino}",
                               v.HoraSalida,
                               v.Valor
                           })
                           .ToList();
            dgTodos.Columns.Clear();
            dgTodos.DataSource = lista;
            AgregarBotonCompra(dgTodos);
        }

        private void dgMasCaro_CellClick(object sender, DataGridViewCellEventArgs e)
            => IntentarCompra(dgMasCaro, e);

        private void dgTodos_CellClick(object sender, DataGridViewCellEventArgs e)
            => IntentarCompra(dgTodos, e);

        private void IntentarCompra(DataGridView dg, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0) return;
            if (dg.Columns[e.ColumnIndex].Name != "Comprar") return;

            int idVuelo = Convert.ToInt32(dg.Rows[e.RowIndex].Cells["IdVuelo"].Value);
            string ruta = dg.Rows[e.RowIndex].Cells["Ruta"].Value.ToString();

            using (var f = new ConfirmarCompraForm(idVuelo, _usr.Id, ruta))
            {
                f.ShowDialog();
            }
        }

        private static void AgregarBotonCompra(DataGridView dg)
        {
            if (dg.Columns.OfType<DataGridViewButtonColumn>().Any(c => c.Name == "Comprar"))
                return;

            var col = new DataGridViewButtonColumn
            {
                Name = "Comprar",
                Text = "Comprar",
                UseColumnTextForButtonValue = true
            };
            dg.Columns.Add(col);
        }

        private void AplicarEstilo(DataGridView dg)
        {
            dg.BackgroundColor = SystemColors.Window;
            dg.DefaultCellStyle.BackColor = SystemColors.Window;
            dg.DefaultCellStyle.ForeColor = SystemColors.ControlText;
            dg.DefaultCellStyle.SelectionBackColor = SystemColors.Highlight;
            dg.DefaultCellStyle.SelectionForeColor = SystemColors.HighlightText;
            dg.EnableHeadersVisualStyles = true;
            dg.ColumnHeadersDefaultCellStyle.BackColor = SystemColors.Control;
            dg.ColumnHeadersDefaultCellStyle.ForeColor = SystemColors.ControlText;
            dg.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
        }
    }
}
