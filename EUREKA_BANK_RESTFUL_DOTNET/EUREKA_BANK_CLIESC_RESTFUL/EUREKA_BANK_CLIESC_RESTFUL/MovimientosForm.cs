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
    public partial class MovimientosForm : Form
    {
        private readonly ApiService _api;
        public MovimientosForm()
        {
            InitializeComponent();
            _api = new ApiService();
            dgvMovimientos.AutoGenerateColumns = false;

            // Configurar columnas del DataGridView
            dgvMovimientos.Columns.Add(new DataGridViewTextBoxColumn { HeaderText = "#", Width = 40 });
            dgvMovimientos.Columns.Add(new DataGridViewTextBoxColumn { HeaderText = "Fecha", DataPropertyName = "Fecha" });
            dgvMovimientos.Columns.Add(new DataGridViewTextBoxColumn { HeaderText = "Tipo", DataPropertyName = "Tipo" });
            dgvMovimientos.Columns.Add(new DataGridViewTextBoxColumn { HeaderText = "Acción", DataPropertyName = "Accion" });
            dgvMovimientos.Columns.Add(new DataGridViewTextBoxColumn { HeaderText = "Importe", DataPropertyName = "Importe" });
        }

        private async void btnBuscar_Click(object sender, EventArgs e)
        {
            string cuentaId = txtCuenta.Text.Trim();
            if (string.IsNullOrEmpty(cuentaId))
            {
                MessageBox.Show("Ingrese el número de cuenta.");
                return;
            }

            var cuenta = await _api.ObtenerCuenta(cuentaId);
            if (cuenta == null)
            {
                MessageBox.Show("Cuenta no encontrada.");
                return;
            }

            var movimientos = await _api.ObtenerMovimientos(cuentaId);
            lblSaldo.Text = $"Saldo: ${cuenta.Saldo:N2}";

            dgvMovimientos.Rows.Clear();
            for (int i = 0; i < movimientos.Count; i++)
            {
                dgvMovimientos.Rows.Add(i + 1, movimientos[i].Fecha.ToShortDateString(), movimientos[i].Tipo, movimientos[i].Accion, movimientos[i].Importe.ToString("N2"));
            }

            if (movimientos.Count == 0)
            {
                MessageBox.Show("No hay movimientos para esta cuenta.");
            }
        }

        private void MovimientosForm_Load(object sender, EventArgs e)
        {

        }
    }
}
