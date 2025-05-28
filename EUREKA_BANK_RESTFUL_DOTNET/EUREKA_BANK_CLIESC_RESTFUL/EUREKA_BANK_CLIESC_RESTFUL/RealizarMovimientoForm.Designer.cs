namespace EUREKA_BANK_CLIESC_RESTFUL
{
    partial class RealizarMovimientoForm
    {
        private System.ComponentModel.IContainer components = null;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        private void InitializeComponent()
        {
            label1 = new Label();
            label2 = new Label();
            txtCuentaOrigen = new TextBox();
            label3 = new Label();
            txtImporte = new TextBox();
            label4 = new Label();
            cmbTipo = new ComboBox();
            label5 = new Label();
            txtCuentaDestino = new TextBox();
            btnRealizar = new Button();
            lblResultado = new Label();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Segoe UI", 36F, FontStyle.Bold, GraphicsUnit.Point, 0);
            label1.ForeColor = Color.MidnightBlue;
            label1.Location = new Point(102, 9);
            label1.Name = "label1";
            label1.RightToLeft = RightToLeft.No;
            label1.Size = new Size(617, 81);
            label1.TabIndex = 0;
            label1.Text = "Realizar Movimiento";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            label2.Location = new Point(208, 131);
            label2.Name = "label2";
            label2.Size = new Size(142, 28);
            label2.TabIndex = 1;
            label2.Text = "Cuenta Origen:";
            // 
            // txtCuentaOrigen
            // 
            txtCuentaOrigen.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            txtCuentaOrigen.Location = new Point(356, 131);
            txtCuentaOrigen.Name = "txtCuentaOrigen";
            txtCuentaOrigen.Size = new Size(227, 34);
            txtCuentaOrigen.TabIndex = 2;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            label3.Location = new Point(208, 180);
            label3.Name = "label3";
            label3.Size = new Size(86, 28);
            label3.TabIndex = 3;
            label3.Text = "Importe:";
            // 
            // txtImporte
            // 
            txtImporte.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            txtImporte.Location = new Point(300, 177);
            txtImporte.Name = "txtImporte";
            txtImporte.Size = new Size(125, 34);
            txtImporte.TabIndex = 4;
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            label4.Location = new Point(208, 223);
            label4.Name = "label4";
            label4.Size = new Size(194, 28);
            label4.TabIndex = 5;
            label4.Text = "Tipo de Movimiento:";
            // 
            // cmbTipo
            // 
            cmbTipo.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            cmbTipo.FormattingEnabled = true;
            cmbTipo.Location = new Point(408, 220);
            cmbTipo.Name = "cmbTipo";
            cmbTipo.Size = new Size(183, 36);
            cmbTipo.TabIndex = 6;
            cmbTipo.SelectedIndexChanged += cmbTipo_SelectedIndexChanged;
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            label5.Location = new Point(208, 279);
            label5.Name = "label5";
            label5.Size = new Size(149, 28);
            label5.TabIndex = 7;
            label5.Text = "Cuenta Destino:";
            // 
            // txtCuentaDestino
            // 
            txtCuentaDestino.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            txtCuentaDestino.Location = new Point(365, 276);
            txtCuentaDestino.Name = "txtCuentaDestino";
            txtCuentaDestino.Size = new Size(212, 34);
            txtCuentaDestino.TabIndex = 8;
            txtCuentaDestino.Visible = false;
            // 
            // btnRealizar
            // 
            btnRealizar.BackColor = Color.RoyalBlue;
            btnRealizar.Font = new Font("Segoe UI", 12F, FontStyle.Bold, GraphicsUnit.Point, 0);
            btnRealizar.ForeColor = Color.White;
            btnRealizar.Location = new Point(352, 332);
            btnRealizar.Name = "btnRealizar";
            btnRealizar.Size = new Size(117, 42);
            btnRealizar.TabIndex = 9;
            btnRealizar.Text = "Realizar";
            btnRealizar.UseVisualStyleBackColor = false;
            btnRealizar.Click += btnRealizar_Click;
            // 
            // lblResultado
            // 
            lblResultado.AutoSize = true;
            lblResultado.Location = new Point(390, 394);
            lblResultado.Name = "lblResultado";
            lblResultado.Size = new Size(50, 20);
            lblResultado.TabIndex = 10;
            lblResultado.Text = "label6";
            lblResultado.Visible = false;
            // 
            // RealizarMovimientoForm
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.White;
            ClientSize = new Size(800, 450);
            Controls.Add(lblResultado);
            Controls.Add(btnRealizar);
            Controls.Add(txtCuentaDestino);
            Controls.Add(label5);
            Controls.Add(cmbTipo);
            Controls.Add(label4);
            Controls.Add(txtImporte);
            Controls.Add(label3);
            Controls.Add(txtCuentaOrigen);
            Controls.Add(label2);
            Controls.Add(label1);
            Name = "RealizarMovimientoForm";
            Text = "RealizarMovimientoForm";
            Load += RealizarMovimientoForm_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label label1;
        private Label label2;
        private TextBox txtCuentaOrigen;
        private Label label3;
        private TextBox txtImporte;
        private Label label4;
        private ComboBox cmbTipo;
        private Label label5;
        private TextBox txtCuentaDestino;
        private Button btnRealizar;
        private Label lblResultado;
    }
}
