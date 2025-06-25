namespace EUREKA_BANK_CLIESC_RESTFUL
{
    partial class MovimientosForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            label1 = new Label();
            label2 = new Label();
            txtCuenta = new TextBox();
            lblSaldo = new Label();
            dgvMovimientos = new DataGridView();
            btnBuscar = new Button();
            ((System.ComponentModel.ISupportInitialize)dgvMovimientos).BeginInit();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Segoe UI", 36F, FontStyle.Bold, GraphicsUnit.Point, 0);
            label1.ForeColor = Color.MidnightBlue;
            label1.Location = new Point(36, 6);
            label1.Name = "label1";
            label1.Size = new Size(752, 81);
            label1.TabIndex = 0;
            label1.Text = "Consulta de Movimientos";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            label2.Location = new Point(86, 115);
            label2.Name = "label2";
            label2.Size = new Size(181, 28);
            label2.TabIndex = 1;
            label2.Text = "Número de Cuenta:";
            // 
            // txtCuenta
            // 
            txtCuenta.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            txtCuenta.Location = new Point(285, 112);
            txtCuenta.Name = "txtCuenta";
            txtCuenta.Size = new Size(240, 34);
            txtCuenta.TabIndex = 2;
            // 
            // lblSaldo
            // 
            lblSaldo.AutoSize = true;
            lblSaldo.Font = new Font("Segoe UI Semibold", 12F, FontStyle.Bold | FontStyle.Italic, GraphicsUnit.Point, 0);
            lblSaldo.ForeColor = Color.Green;
            lblSaldo.Location = new Point(268, 166);
            lblSaldo.Name = "lblSaldo";
            lblSaldo.Size = new Size(82, 28);
            lblSaldo.TabIndex = 3;
            lblSaldo.Text = "Saldo: $";
            // 
            // dgvMovimientos
            // 
            dgvMovimientos.BackgroundColor = Color.WhiteSmoke;
            dgvMovimientos.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dgvMovimientos.Location = new Point(103, 207);
            dgvMovimientos.Name = "dgvMovimientos";
            dgvMovimientos.ReadOnly = true;
            dgvMovimientos.RowHeadersWidth = 51;
            dgvMovimientos.Size = new Size(577, 212);
            dgvMovimientos.TabIndex = 4;
            // 
            // btnBuscar
            // 
            btnBuscar.BackColor = Color.PaleTurquoise;
            btnBuscar.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            btnBuscar.Location = new Point(563, 106);
            btnBuscar.Name = "btnBuscar";
            btnBuscar.Size = new Size(140, 46);
            btnBuscar.TabIndex = 5;
            btnBuscar.Text = "Buscar";
            btnBuscar.UseVisualStyleBackColor = false;
            btnBuscar.Click += btnBuscar_Click;
            // 
            // MovimientosForm
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.White;
            ClientSize = new Size(800, 450);
            Controls.Add(btnBuscar);
            Controls.Add(dgvMovimientos);
            Controls.Add(lblSaldo);
            Controls.Add(txtCuenta);
            Controls.Add(label2);
            Controls.Add(label1);
            Name = "MovimientosForm";
            Text = "MovimientosForm";
            Load += MovimientosForm_Load;
            ((System.ComponentModel.ISupportInitialize)dgvMovimientos).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label label1;
        private Label label2;
        private TextBox txtCuenta;
        private Label lblSaldo;
        private DataGridView dgvMovimientos;
        private Button btnBuscar;
    }
}
