namespace ClienteEscritorioSoap
{
    partial class Form3
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
            dataGridView1 = new DataGridView();
            btnVolver = new Button();
            label2 = new Label();
            txtCuenta = new TextBox();
            btnBuscar = new Button();
            NumeroMovimiento = new DataGridViewTextBoxColumn();
            Fecha = new DataGridViewTextBoxColumn();
            Tipo = new DataGridViewTextBoxColumn();
            Importe = new DataGridViewTextBoxColumn();
            Accion = new DataGridViewTextBoxColumn();
            Destino = new DataGridViewTextBoxColumn();
            ((System.ComponentModel.ISupportInitialize)dataGridView1).BeginInit();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.BackColor = SystemColors.ActiveCaption;
            label1.Location = new Point(326, 31);
            label1.Name = "label1";
            label1.Size = new Size(161, 20);
            label1.TabIndex = 0;
            label1.Text = "Consultar Movimientos";
            // 
            // dataGridView1
            // 
            dataGridView1.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridView1.Columns.AddRange(new DataGridViewColumn[] { NumeroMovimiento, Fecha, Tipo, Importe, Accion, Destino });
            dataGridView1.Location = new Point(29, 97);
            dataGridView1.Name = "dataGridView1";
            dataGridView1.RowHeadersWidth = 51;
            dataGridView1.Size = new Size(759, 341);
            dataGridView1.TabIndex = 1;
            dataGridView1.CellContentClick += dataGridView1_CellContentClick_1;
            // 
            // btnVolver
            // 
            btnVolver.Location = new Point(694, 12);
            btnVolver.Name = "btnVolver";
            btnVolver.Size = new Size(94, 29);
            btnVolver.TabIndex = 2;
            btnVolver.Text = "Volver";
            btnVolver.UseVisualStyleBackColor = true;
            btnVolver.Click += btnVolver_Click;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(39, 35);
            label2.Name = "label2";
            label2.Size = new Size(55, 20);
            label2.TabIndex = 3;
            label2.Text = "Cuenta";
            label2.Click += label2_Click;
            // 
            // txtCuenta
            // 
            txtCuenta.Location = new Point(104, 32);
            txtCuenta.Name = "txtCuenta";
            txtCuenta.Size = new Size(125, 27);
            txtCuenta.TabIndex = 4;
            txtCuenta.TextChanged += txtCuenta_TextChanged;
            // 
            // btnBuscar
            // 
            btnBuscar.Location = new Point(537, 30);
            btnBuscar.Name = "btnBuscar";
            btnBuscar.Size = new Size(94, 29);
            btnBuscar.TabIndex = 5;
            btnBuscar.Text = "Buscar";
            btnBuscar.UseVisualStyleBackColor = true;
            btnBuscar.Click += btnBuscar_Click;
            // 
            // NumeroMovimiento
            // 
            NumeroMovimiento.HeaderText = "NumeroMovimiento";
            NumeroMovimiento.MinimumWidth = 6;
            NumeroMovimiento.Name = "NumeroMovimiento";
            NumeroMovimiento.Width = 125;
            // 
            // Fecha
            // 
            Fecha.HeaderText = "Fecha";
            Fecha.MinimumWidth = 6;
            Fecha.Name = "Fecha";
            Fecha.Width = 125;
            // 
            // Tipo
            // 
            Tipo.HeaderText = "Tipo";
            Tipo.MinimumWidth = 6;
            Tipo.Name = "Tipo";
            Tipo.Width = 125;
            // 
            // Importe
            // 
            Importe.HeaderText = "Importe";
            Importe.MinimumWidth = 6;
            Importe.Name = "Importe";
            Importe.Width = 125;
            // 
            // Accion
            // 
            Accion.HeaderText = "Accion";
            Accion.MinimumWidth = 6;
            Accion.Name = "Accion";
            Accion.Width = 125;
            // 
            // Destino
            // 
            Destino.HeaderText = "Destino";
            Destino.MinimumWidth = 6;
            Destino.Name = "Destino";
            Destino.Width = 125;
            // 
            // Form3
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(btnBuscar);
            Controls.Add(txtCuenta);
            Controls.Add(label2);
            Controls.Add(btnVolver);
            Controls.Add(dataGridView1);
            Controls.Add(label1);
            Name = "Form3";
            Text = "Form3";
            ((System.ComponentModel.ISupportInitialize)dataGridView1).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label label1;
        private DataGridView dataGridView1;
        private Button btnVolver;
        private Label label2;
        private TextBox txtCuenta;
        private Button btnBuscar;
        private DataGridViewTextBoxColumn NumeroMovimiento;
        private DataGridViewTextBoxColumn Fecha;
        private DataGridViewTextBoxColumn Tipo;
        private DataGridViewTextBoxColumn Importe;
        private DataGridViewTextBoxColumn Accion;
        private DataGridViewTextBoxColumn Destino;
    }
}