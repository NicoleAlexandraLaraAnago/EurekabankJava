using ServiceReferenceMovimiento;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace ClienteEscritorioSoap
{
    public partial class Form2 : Form
    {
        private string usuarioActual;


        public Form2(string usuario)
        {
            InitializeComponent();
            usuarioActual = usuario;
        }

        private void Form2_Load(object sender, EventArgs e)
        {

        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {

            // Abre la pantalla de movimientos
            Form3 movimientos = new Form3(usuarioActual);
            movimientos.Show();
            this.Hide(); // Oculta el menú

        }

        private void rbtnDeposito_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void txtCuenta_TextChanged(object sender, EventArgs e)
        {

        }

        private void txtImporte_TextChanged(object sender, EventArgs e)
        {

        }

        private async void btnOk_Click(object sender, EventArgs e)
        {
            try
            {
                var cliente = new MovimientoServiceClient(MovimientoServiceClient.EndpointConfiguration.BasicHttpBinding_IMovimientoService);

                string cuenta = txtCuenta.Text;
                decimal importe = decimal.Parse(txtImporte.Text);
                string tipo = rbtnDeposito.Checked ? "DEPOSITO" :
                              rbtnRetiro.Checked ? "RETIRO" :
                              rbtnTransferencia.Checked ? "TRANSFERENCIA" : null;
                string cuentaDestino = tipo == "TRANSFERENCIA" ? txtDestino.Text : null;

                if (tipo == null)
                {
                    MessageBox.Show("Seleccione un tipo de movimiento", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }

                var req = new MovimientoRequest
                {
                    CuentaOrigen = cuenta,
                    Importe = importe,
                    TipoMovimiento = tipo,
                    CuentaDestino = cuentaDestino
                };

                bool exito = await cliente.RegistrarMovimientoAsync(req);
                if (exito)
                {
                    MessageBox.Show($"{tipo} realizado con éxito", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                else
                {
                    MessageBox.Show("No se pudo realizar el movimiento", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al realizar la operación: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }


        private void rbtnTransferencia_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void txtDestino_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
