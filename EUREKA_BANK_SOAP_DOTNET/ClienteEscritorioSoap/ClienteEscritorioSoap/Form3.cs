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

namespace ClienteEscritorioSoap
{
    public partial class Form3 : Form
    {
        private string usuario;

        public Form3(string usuario)
        {
            InitializeComponent();
            this.usuario = usuario;
            ConfigurarGrid();
        }

        private void ConfigurarGrid()
        {
            dataGridView1.AutoGenerateColumns = false;
            dataGridView1.Columns.Clear();

            dataGridView1.Columns.Add(new DataGridViewTextBoxColumn
            {
                HeaderText = "Número Movimiento",
                DataPropertyName = "NumeroMovimiento"
            });

            dataGridView1.Columns.Add(new DataGridViewTextBoxColumn
            {
                HeaderText = "Fecha",
                DataPropertyName = "Fecha"
            });

            dataGridView1.Columns.Add(new DataGridViewTextBoxColumn
            {
                HeaderText = "Tipo",
                DataPropertyName = "Tipo"
            });

            dataGridView1.Columns.Add(new DataGridViewTextBoxColumn
            {
                HeaderText = "Importe",
                DataPropertyName = "Importe"
            });

            dataGridView1.Columns.Add(new DataGridViewTextBoxColumn
            {
                HeaderText = "Cuenta Destino",
                DataPropertyName = "CuentaDestino"
            });

            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
        }

        private void dataGridView1_CellContentClick_1(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void btnVolver_Click(object sender, EventArgs e)
        {
            // Crear y mostrar el menú nuevamente, pasando el mismo usuario
            Form2 menu = new Form2(usuario);
            menu.Show();

            // Ocultar la pantalla de movimientos
            this.Hide();
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void txtCuenta_TextChanged(object sender, EventArgs e)
        {

        }

        private async void btnBuscar_Click(object sender, EventArgs e)

        {
            try
            {
                string cuenta = txtCuenta.Text.Trim();

                if (string.IsNullOrEmpty(cuenta))
                {
                    MessageBox.Show("Ingrese un número de cuenta.");
                    return;
                }

                // Instancia del cliente SOAP
                MovimientoServiceClient client = new MovimientoServiceClient();

                // Obtener movimientos
                var movimientos = await client.GetMovimientosAsync(cuenta);

                // Mostrar en DataGridView
                dataGridView1.DataSource = movimientos;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al obtener movimientos: " + ex.Message);
            }
        }
    }
}
