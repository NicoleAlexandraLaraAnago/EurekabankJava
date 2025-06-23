using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Viajecitos_Desktop.ViajecitosReference;

namespace Viajecitos_Desktop
{
    public partial class MisFacturasForm : Form
    {
        private readonly int _idUsuario;
        private readonly ViajecitosServiceClient svc = new ViajecitosServiceClient();

        public MisFacturasForm(int idUsuario)
        {
            _idUsuario = idUsuario;
            InitializeComponent();
            CargarFacturas();
            dataGridViewFacturas.CellDoubleClick += dataGridViewFacturas_CellDoubleClick;

        }
        private void CargarFacturas()
        {
            try
            {
                var facturas = svc.ObtenerFacturasPorUsuario(_idUsuario);

                var vista = facturas.Select(f => new
                {
                    f.IdCompra,
                    f.CiudadDestino,
                    FechaFactura = f.FechaFactura.ToString("g"),
                    CantidadBoletos = (int)(f.Subtotal / f.ValorUnitario),
                    f.TotalAPagar,
                    FacturaOriginal = f // guardamos la factura completa por si acaso
                }).ToList();

                dataGridViewFacturas.DataSource = vista;

                // Opcional: ocultar columna FacturaOriginal si aparece
                if (dataGridViewFacturas.Columns["FacturaOriginal"] != null)
                {
                    dataGridViewFacturas.Columns["FacturaOriginal"].Visible = false;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al cargar facturas: " + ex.Message);
            }
        }


        private void dataGridViewFacturas_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex < 0) return;

            var fila = dataGridViewFacturas.Rows[e.RowIndex].DataBoundItem;
            var factura = fila.GetType().GetProperty("FacturaOriginal")?.GetValue(fila) as FacturaDTO;

            if (factura == null)
            {
                MessageBox.Show("No se pudo obtener la factura.");
                return;
            }

            int cantidad = (int)(factura.Subtotal / factura.ValorUnitario);

            string msg = $"🧾 Factura Detallada\n\n" +
                         $"Comprador: {factura.NombreComprador}\n" +
                         $"Ruta: {factura.CiudadOrigen} → {factura.CiudadDestino}\n" +
                         $"Fecha: {factura.FechaFactura:g}\n" +
                         $"Boletos: {cantidad}\n" +
                         $"Número: {factura.NumeroBoleto}\n" +
                         $"Valor Unitario: ${factura.ValorUnitario}\n" +
                         $"Subtotal: ${factura.Subtotal}\n" +
                         $"IVA: ${factura.IVA}\n" +
                         $"Total: ${factura.TotalAPagar}";

            MessageBox.Show(msg, "Detalle de Factura");
        }

        private void btnRegresar_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
