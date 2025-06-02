using System;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Windows.Forms;
using Viajecitos_Desktop.ViajecitosReference;

namespace Viajecitos_Desktop
{
    public partial class MisComprasForm : Form
    {
        private readonly UsuarioDTO _usr;
        private readonly ViajecitosServiceClient svc = new ViajecitosServiceClient();

        private const string RutaFondo =
@"C:\Users\Notebook-ASUS\Desktop\VIAJECITOS_SOAP_DOTNET_GR04\02.CLIESC\Viajecitos_Desktop\Viajecitos_Desktop\Resources\2.jpg";

        public MisComprasForm(UsuarioDTO usr)
        {
            InitializeComponent();
            _usr = usr;

            /* ---------- Fondo ---------- */
            if (File.Exists(RutaFondo))
            {
                BackgroundImage = Image.FromFile(RutaFondo);
                BackgroundImageLayout = ImageLayout.Stretch;
                BackColor = Color.LightPink;      // opaco
            }

            /* ---------- Botón Volver ---------- */
            Ui.PintarBotonOutline(btnVolver);
            btnVolver.Click += (_, __) => Close();            // <- enganchado

            /* ---------- DataGrid ---------- */
            Ui.EstiloGrid(dgMisCompras);

            /* ---------- Datos ---------- */
            CargarCompras();
        }

        /* =========================================================
                           Cargar lista de compras
           =========================================================*/
        private void CargarCompras()
        {
            var compras = svc.ObtenerComprasPorUsuario(_usr.Id)
                             .Select(c => new
                             {
                                 c.IdCompra,
                                 Ruta = $"{c.CiudadOrigen} → {c.CiudadDestino}",
                                 c.HoraSalida,
                                 c.Valor,
                                 Fecha = c.FechaCompra.ToString("yyyy-MM-dd")
                             })
                             .ToList();

            dgMisCompras.DataSource = compras;
        }
    }
}
