namespace ClienteEscritorioSoap
{
    partial class Form2
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
            grpOperaciones = new GroupBox();
            button1 = new Button();
            rbtnTransferencia = new RadioButton();
            rbtnRetiro = new RadioButton();
            rbtnDeposito = new RadioButton();
            label1 = new Label();
            label2 = new Label();
            txtCuenta = new TextBox();
            txtImporte = new TextBox();
            label3 = new Label();
            btnOk = new Button();
            txtDestino = new TextBox();
            grpOperaciones.SuspendLayout();
            SuspendLayout();
            // 
            // grpOperaciones
            // 
            grpOperaciones.Controls.Add(button1);
            grpOperaciones.Controls.Add(rbtnTransferencia);
            grpOperaciones.Controls.Add(rbtnRetiro);
            grpOperaciones.Controls.Add(rbtnDeposito);
            grpOperaciones.Location = new Point(33, 55);
            grpOperaciones.Name = "grpOperaciones";
            grpOperaciones.Size = new Size(181, 309);
            grpOperaciones.TabIndex = 0;
            grpOperaciones.TabStop = false;
            grpOperaciones.Text = "Operaciones";
            // 
            // button1
            // 
            button1.Location = new Point(36, 226);
            button1.Name = "button1";
            button1.Size = new Size(117, 29);
            button1.TabIndex = 3;
            button1.Text = "Movimientos";
            button1.UseVisualStyleBackColor = true;
            button1.Click += button1_Click;
            // 
            // rbtnTransferencia
            // 
            rbtnTransferencia.AutoSize = true;
            rbtnTransferencia.Location = new Point(36, 165);
            rbtnTransferencia.Name = "rbtnTransferencia";
            rbtnTransferencia.Size = new Size(117, 24);
            rbtnTransferencia.TabIndex = 2;
            rbtnTransferencia.TabStop = true;
            rbtnTransferencia.Text = "Transferencia";
            rbtnTransferencia.UseVisualStyleBackColor = true;
            rbtnTransferencia.CheckedChanged += rbtnTransferencia_CheckedChanged;
            // 
            // rbtnRetiro
            // 
            rbtnRetiro.AutoSize = true;
            rbtnRetiro.Location = new Point(36, 116);
            rbtnRetiro.Name = "rbtnRetiro";
            rbtnRetiro.Size = new Size(70, 24);
            rbtnRetiro.TabIndex = 1;
            rbtnRetiro.TabStop = true;
            rbtnRetiro.Text = "Retiro";
            rbtnRetiro.UseVisualStyleBackColor = true;
            rbtnRetiro.CheckedChanged += radioButton1_CheckedChanged;
            // 
            // rbtnDeposito
            // 
            rbtnDeposito.AutoSize = true;
            rbtnDeposito.Location = new Point(36, 66);
            rbtnDeposito.Name = "rbtnDeposito";
            rbtnDeposito.Size = new Size(91, 24);
            rbtnDeposito.TabIndex = 0;
            rbtnDeposito.TabStop = true;
            rbtnDeposito.Text = "Depósito";
            rbtnDeposito.UseVisualStyleBackColor = true;
            rbtnDeposito.CheckedChanged += rbtnDeposito_CheckedChanged;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(336, 106);
            label1.Name = "label1";
            label1.Size = new Size(55, 20);
            label1.TabIndex = 1;
            label1.Text = "Cuenta";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(336, 171);
            label2.Name = "label2";
            label2.Size = new Size(62, 20);
            label2.TabIndex = 2;
            label2.Text = "Importe";
            // 
            // txtCuenta
            // 
            txtCuenta.Location = new Point(439, 103);
            txtCuenta.Name = "txtCuenta";
            txtCuenta.Size = new Size(125, 27);
            txtCuenta.TabIndex = 3;
            txtCuenta.TextChanged += txtCuenta_TextChanged;
            // 
            // txtImporte
            // 
            txtImporte.Location = new Point(439, 164);
            txtImporte.Name = "txtImporte";
            txtImporte.Size = new Size(125, 27);
            txtImporte.TabIndex = 4;
            txtImporte.TextChanged += txtImporte_TextChanged;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(292, 241);
            label3.Name = "label3";
            label3.Size = new Size(106, 20);
            label3.TabIndex = 6;
            label3.Text = "CuentaDestino";
            // 
            // btnOk
            // 
            btnOk.Location = new Point(629, 281);
            btnOk.Name = "btnOk";
            btnOk.Size = new Size(94, 29);
            btnOk.TabIndex = 7;
            btnOk.Text = "OK";
            btnOk.UseVisualStyleBackColor = true;
            btnOk.Click += btnOk_Click;
            // 
            // txtDestino
            // 
            txtDestino.Location = new Point(439, 238);
            txtDestino.Name = "txtDestino";
            txtDestino.Size = new Size(125, 27);
            txtDestino.TabIndex = 8;
            txtDestino.TextChanged += txtDestino_TextChanged;
            // 
            // Form2
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(txtDestino);
            Controls.Add(btnOk);
            Controls.Add(label3);
            Controls.Add(txtImporte);
            Controls.Add(txtCuenta);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(grpOperaciones);
            Name = "Form2";
            Text = "Form2";
            Load += Form2_Load;
            grpOperaciones.ResumeLayout(false);
            grpOperaciones.PerformLayout();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private GroupBox grpOperaciones;
        private RadioButton rbtnDeposito;
        private RadioButton rbtnRetiro;
        private Button button1;
        private RadioButton rbtnTransferencia;
        private Label label1;
        private Label label2;
        private TextBox txtCuenta;
        private TextBox txtImporte;
        private Label label3;
        private Button btnOk;
        private TextBox txtDestino;
    }
}