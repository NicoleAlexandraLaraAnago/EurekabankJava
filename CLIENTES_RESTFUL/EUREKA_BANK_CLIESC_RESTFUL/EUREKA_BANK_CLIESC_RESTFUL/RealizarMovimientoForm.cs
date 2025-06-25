using EUREKA_BANK_CLIESC_RESTFUL.Models;
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
    public partial class RealizarMovimientoForm : Form
    {
        private readonly ApiService _api;
        public RealizarMovimientoForm()
        {
            InitializeComponent();
            _api = new ApiService();

            cmbTipo.Items.AddRange(new[] { "DEPÓSITO", "RETIRO", "TRANSFERENCIA" });
            cmbTipo.SelectedIndexChanged += CmbTipo_SelectedIndexChanged;
            txtCuentaDestino.Visible = false;
        }

        private void CmbTipo_SelectedIndexChanged(object sender, EventArgs e)
        {
            string tipo = cmbTipo.SelectedItem?.ToString();
            txtCuentaDestino.Visible = tipo == "TRANSFERENCIA";
        }

        private void cmbTipo_SelectedIndexChanged(object sender, EventArgs e)
        {
            txtCuentaDestino.Visible = cmbTipo.SelectedItem?.ToString() == "TRANSFERENCIA";
        }


        private async void btnRealizar_Click(object sender, EventArgs e)
        {
            string cuentaOrigen = txtCuentaOrigen.Text.Trim();
            string tipo = cmbTipo.SelectedItem?.ToString();
            string cuentaDestino = txtCuentaDestino.Text.Trim();
            decimal importe;

            if (string.IsNullOrEmpty(cuentaOrigen) || string.IsNullOrEmpty(tipo) || !decimal.TryParse(txtImporte.Text, out importe))
            {
                MessageBox.Show("Complete todos los campos correctamente.");
                return;
            }

            if (tipo == "TRANSFERENCIA" && string.IsNullOrEmpty(cuentaDestino))
            {
                MessageBox.Show("Ingrese la cuenta destino.");
                return;
            }

            // === ✅ VALIDACIÓN DE SALDO ===
            if (tipo == "TRANSFERENCIA" || tipo == "RETIRO")
            {
                var cuenta = await _api.ObtenerCuenta(cuentaOrigen);
                if (cuenta == null)
                {
                    MessageBox.Show("Cuenta de origen no encontrada.");
                    return;
                }

                if (importe > cuenta.Saldo)
                {
                    MessageBox.Show("El importe excede el saldo disponible.", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }
            }

            var req = new MovimientoRequest
            {
                CuentaOrigen = cuentaOrigen,
                CuentaDestino = tipo == "TRANSFERENCIA" ? cuentaDestino : null,
                TipoMovimiento = tipo,
                Importe = importe
            };

            string resultado = await _api.RealizarMovimiento(req);
            lblResultado.Text = resultado;
            lblResultado.ForeColor = resultado.Contains("Error") ? Color.Red : Color.Green;

            if (!resultado.Contains("Error"))
            {
                MessageBox.Show("Movimiento exitoso. Volviendo al menú.");
                this.Close(); // O redirige a MenuForm
            }
        }


        private void txtImporte_KeyPress(object sender, KeyPressEventArgs e)
        {
            // Solo permite números y un punto decimal
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar) && e.KeyChar != '.')
                e.Handled = true;

            if (e.KeyChar == '.' && txtImporte.Text.Contains("."))
                e.Handled = true;
        }


        private void RealizarMovimientoForm_Load(object sender, EventArgs e)
        {

        }
    }
}
