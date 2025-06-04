using System;
using System.Drawing;
using System.Windows.Forms;

namespace Viajecitos_Desktop
{
    internal static class Ui
    {
        /* ===== Colores pastel morados ===== */
        private static readonly Color Morado = ColorTranslator.FromHtml("#B59BFF");
        private static readonly Color MoradoOscuro = ColorTranslator.FromHtml("#9A7BFF");

        /* ----------------- Botón relleno ----------------- */
        public static void PintarBoton(Button b)
        {
            b.FlatStyle = FlatStyle.Flat;
            b.FlatAppearance.BorderSize = 0;
            b.BackColor = Morado;
            b.ForeColor = Color.White;
            b.Font = new Font("Segoe UI", 10, FontStyle.Bold);

            b.MouseEnter += (s, e) => b.BackColor = MoradoOscuro;
            b.MouseLeave += (s, e) => b.BackColor = Morado;
        }

        /* ---------------- Botón contorno ----------------- */
        public static void PintarBotonOutline(Button b)
        {
            b.FlatStyle = FlatStyle.Flat;
            b.FlatAppearance.BorderColor = Morado;
            b.FlatAppearance.BorderSize = 2;
            b.BackColor = Color.White;
            b.ForeColor = Morado;
            b.Font = new Font("Segoe UI", 10, FontStyle.Bold);

            b.MouseEnter += (s, e) =>
            {
                b.BackColor = Morado;
                b.ForeColor = Color.White;
            };
            b.MouseLeave += (s, e) =>
            {
                b.BackColor = Color.White;
                b.ForeColor = Morado;
            };
        }

        /* ---------------- Estilo DataGridView ---------------- */
        public static void EstiloGrid(DataGridView gv)
        {
            if (gv == null) return;

            gv.ReadOnly = true;
            gv.AllowUserToAddRows = gv.AllowUserToDeleteRows = false;
            gv.AllowUserToResizeRows = false;
            gv.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            gv.SelectionMode = DataGridViewSelectionMode.FullRowSelect;

            // Encabezado
            gv.EnableHeadersVisualStyles = false;
            gv.ColumnHeadersDefaultCellStyle.BackColor = Morado;
            gv.ColumnHeadersDefaultCellStyle.ForeColor = Color.White;
            gv.ColumnHeadersDefaultCellStyle.Font =
                new Font("Segoe UI", 10, FontStyle.Bold);
            gv.ColumnHeadersDefaultCellStyle.Alignment =
                DataGridViewContentAlignment.MiddleCenter;

            // Filas alternas
            gv.RowsDefaultCellStyle.BackColor =
                ColorTranslator.FromHtml("#F4E8FF");
            gv.AlternatingRowsDefaultCellStyle.BackColor = Color.White;
            gv.DefaultCellStyle.SelectionBackColor = Morado;
            gv.DefaultCellStyle.SelectionForeColor = Color.White;
        }
    }
}
