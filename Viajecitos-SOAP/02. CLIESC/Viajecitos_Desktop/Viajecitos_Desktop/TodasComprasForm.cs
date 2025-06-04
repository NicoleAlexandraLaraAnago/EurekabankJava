using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using Viajecitos_Desktop.ViajecitosReference;

namespace Viajecitos_Desktop
{
    public partial class TodasComprasForm : Form
    {
        private readonly ViajecitosServiceClient svc = new ViajecitosServiceClient();
        private List<CompraDetalleDTO> _all = new List<CompraDetalleDTO>();

        public TodasComprasForm()
        {
            InitializeComponent();

           

            /* ——— Enlazar eventos de clic ——— */
            btnFiltrar.Click += btnFiltrar_Click;
            btnLimpiar.Click += btnLimpiar_Click;
            btnVolver.Click += btnVolver_Click;

            /* ——— Carga inicial de datos ——— */
            Cargar();
        }

        /// <summary>
        /// Trae todas las compras del servicio y pasa al método Bind.
        /// </summary>
        private void Cargar()
        {
            _all = svc.ObtenerTodasLasCompras().ToList();
            Bind(_all);
        }

        /// <summary>
        /// Llena el DataGridView con la lista transformada.
        /// </summary>
        private void Bind(IEnumerable<CompraDetalleDTO> src)
        {
            dgCompras.DataSource = src
                .Select(c => new
                {
                    c.IdCompra,
                    c.Usuario,
                    NombreCompleto = c.Nombre + " " + c.Apellido,
                    Ruta = c.CiudadOrigen + " → " + c.CiudadDestino,
                    c.HoraSalida,
                    c.Valor,
                    Fecha = c.FechaCompra.ToString("yyyy-MM-dd")
                })
                .ToList();
        }

        /// <summary>
        /// Filtra por usuario (o nombre completo) y, si el DateTimePicker está marcado,
        /// filtra por fecha de salida exacta.
        /// </summary>
        private void btnFiltrar_Click(object sender, EventArgs e)
        {
            string fUser = txtUsuario.Text.Trim().ToLower();
            bool usarFecha = dtpFechaSalida.Checked;
            DateTime fSel = dtpFechaSalida.Value.Date;

            var resultado = _all.Where(c =>
                (string.IsNullOrEmpty(fUser) ||
                 c.Usuario.ToLower().Contains(fUser) ||
                 (c.Nombre + " " + c.Apellido).ToLower().Contains(fUser))
                &&
                (!usarFecha || c.FechaCompra.Date == fSel)
            );

            Bind(resultado);
        }

        /// <summary>
        /// Limpia filtros: borra el textbox, desmarca el DateTimePicker y recarga todo.
        /// </summary>
        private void btnLimpiar_Click(object sender, EventArgs e)
        {
            txtUsuario.Clear();
            dtpFechaSalida.Checked = false;
            Bind(_all);
        }

        /// <summary>
        /// Cierra este formulario y retorna al Dashboard (porque fue mostrado con ShowDialog()).
        /// </summary>
        private void btnVolver_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
