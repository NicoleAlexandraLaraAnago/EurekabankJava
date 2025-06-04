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
    public partial class ConfirmarCompraForm : Form
    {
        private int idVuelo;
        private int idUsuario;
        private string ruta;
        private ViajecitosServiceClient svc = new ViajecitosServiceClient();
        public ConfirmarCompraForm(int idVuelo, int idUsuario, string ruta)
        {
            InitializeComponent();
            this.idVuelo = idVuelo;
            this.idUsuario = idUsuario;
            this.ruta = ruta;
            lblRuta.Text = $"Vuelo: {ruta}";
        }

        private void btnConfirmar_Click(object sender, EventArgs e)
        {
            int cantidad = (int)nudCantidad.Value;

            var factura = svc.RegistrarCompraConFactura(idVuelo, idUsuario, cantidad);
            if (factura == null)
            {
                MessageBox.Show("No se pudo completar la compra.");
                return;
            }

            string msg = $"Factura generada\n\n" +
                         $"Comprador: {factura.NombreComprador}\n" +
                         $"Ruta: {factura.CiudadOrigen} → {factura.CiudadDestino}\n" +
                         $"Fecha: {factura.FechaFactura}\n" +
                         $"Boletos: {factura.NumeroBoleto}\n" +
                         $"Subtotal: ${factura.Subtotal}\n" +
                         $"IVA: ${factura.IVA}\n" +
                         $"Total: ${factura.TotalAPagar}";

            MessageBox.Show(msg, "Factura");
            Close();
        }
    }
}
