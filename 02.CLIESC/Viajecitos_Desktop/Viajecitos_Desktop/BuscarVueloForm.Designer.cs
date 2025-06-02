namespace Viajecitos_Desktop
{
    partial class BuscarVueloForm
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
            this.cmbOrigen = new System.Windows.Forms.ComboBox();
            this.cmbDestino = new System.Windows.Forms.ComboBox();
            this.dtpFecha = new System.Windows.Forms.DateTimePicker();
            this.btnBuscar = new System.Windows.Forms.Button();
            this.btnRegresar = new System.Windows.Forms.Button();
            this.dgMasCaro = new System.Windows.Forms.DataGridView();
            this.dgTodos = new System.Windows.Forms.DataGridView();
            this.txtBuscar = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.dgMasCaro)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgTodos)).BeginInit();
            this.SuspendLayout();
            // 
            // cmbOrigen
            // 
            this.cmbOrigen.FormattingEnabled = true;
            this.cmbOrigen.Location = new System.Drawing.Point(104, 85);
            this.cmbOrigen.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.cmbOrigen.Name = "cmbOrigen";
            this.cmbOrigen.Size = new System.Drawing.Size(212, 24);
            this.cmbOrigen.TabIndex = 0;
            // 
            // cmbDestino
            // 
            this.cmbDestino.FormattingEnabled = true;
            this.cmbDestino.Location = new System.Drawing.Point(377, 86);
            this.cmbDestino.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.cmbDestino.Name = "cmbDestino";
            this.cmbDestino.Size = new System.Drawing.Size(212, 24);
            this.cmbDestino.TabIndex = 1;
            // 
            // dtpFecha
            // 
            this.dtpFecha.Location = new System.Drawing.Point(681, 87);
            this.dtpFecha.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.dtpFecha.Name = "dtpFecha";
            this.dtpFecha.Size = new System.Drawing.Size(260, 22);
            this.dtpFecha.TabIndex = 2;
            // 
            // btnBuscar
            // 
            this.btnBuscar.BackColor = System.Drawing.Color.MidnightBlue;
            this.btnBuscar.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnBuscar.ForeColor = System.Drawing.SystemColors.Window;
            this.btnBuscar.Location = new System.Drawing.Point(951, 75);
            this.btnBuscar.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.btnBuscar.Name = "btnBuscar";
            this.btnBuscar.Size = new System.Drawing.Size(100, 43);
            this.btnBuscar.TabIndex = 3;
            this.btnBuscar.Text = "Buscar";
            this.btnBuscar.UseVisualStyleBackColor = false;
            // 
            // btnRegresar
            // 
            this.btnRegresar.BackColor = System.Drawing.Color.MidnightBlue;
            this.btnRegresar.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnRegresar.ForeColor = System.Drawing.SystemColors.Window;
            this.btnRegresar.Location = new System.Drawing.Point(492, 437);
            this.btnRegresar.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.btnRegresar.Name = "btnRegresar";
            this.btnRegresar.Size = new System.Drawing.Size(100, 39);
            this.btnRegresar.TabIndex = 4;
            this.btnRegresar.Text = "Regresar";
            this.btnRegresar.UseVisualStyleBackColor = false;
            // 
            // dgMasCaro
            // 
            this.dgMasCaro.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgMasCaro.Location = new System.Drawing.Point(83, 175);
            this.dgMasCaro.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.dgMasCaro.Name = "dgMasCaro";
            this.dgMasCaro.RowHeadersWidth = 51;
            this.dgMasCaro.Size = new System.Drawing.Size(913, 84);
            this.dgMasCaro.TabIndex = 5;
            // 
            // dgTodos
            // 
            this.dgTodos.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgTodos.Location = new System.Drawing.Point(83, 313);
            this.dgTodos.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.dgTodos.Name = "dgTodos";
            this.dgTodos.RowHeadersWidth = 51;
            this.dgTodos.Size = new System.Drawing.Size(913, 117);
            this.dgTodos.TabIndex = 6;
            // 
            // txtBuscar
            // 
            this.txtBuscar.AutoSize = true;
            this.txtBuscar.BackColor = System.Drawing.SystemColors.Window;
            this.txtBuscar.Font = new System.Drawing.Font("Segoe UI", 28.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtBuscar.ForeColor = System.Drawing.Color.MidnightBlue;
            this.txtBuscar.Location = new System.Drawing.Point(368, 10);
            this.txtBuscar.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.txtBuscar.Name = "txtBuscar";
            this.txtBuscar.Size = new System.Drawing.Size(332, 62);
            this.txtBuscar.TabIndex = 7;
            this.txtBuscar.Text = "Buscar Vuelos";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(39, 86);
            this.label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(51, 18);
            this.label1.TabIndex = 8;
            this.label1.Text = "Desde";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(325, 89);
            this.label2.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(47, 18);
            this.label2.TabIndex = 9;
            this.label2.Text = "Hasta";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(619, 91);
            this.label3.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(49, 18);
            this.label3.TabIndex = 10;
            this.label3.Text = "Fecha";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.BackColor = System.Drawing.SystemColors.Window;
            this.label4.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(428, 130);
            this.label4.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(227, 41);
            this.label4.TabIndex = 11;
            this.label4.Text = "Vuelo Mas Caro";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.BackColor = System.Drawing.SystemColors.Window;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(485, 273);
            this.label5.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(108, 36);
            this.label5.TabIndex = 12;
            this.label5.Text = "Vuelos";
            // 
            // BuscarVueloForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.Window;
            this.ClientSize = new System.Drawing.Size(1067, 554);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.txtBuscar);
            this.Controls.Add(this.dgTodos);
            this.Controls.Add(this.dgMasCaro);
            this.Controls.Add(this.btnRegresar);
            this.Controls.Add(this.btnBuscar);
            this.Controls.Add(this.dtpFecha);
            this.Controls.Add(this.cmbDestino);
            this.Controls.Add(this.cmbOrigen);
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "BuscarVueloForm";
            this.Text = "BuscarVueloForm";
            ((System.ComponentModel.ISupportInitialize)(this.dgMasCaro)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgTodos)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox cmbOrigen;
        private System.Windows.Forms.ComboBox cmbDestino;
        private System.Windows.Forms.DateTimePicker dtpFecha;
        private System.Windows.Forms.Button btnBuscar;
        private System.Windows.Forms.Button btnRegresar;
        private System.Windows.Forms.DataGridView dgMasCaro;
        private System.Windows.Forms.DataGridView dgTodos;
        private System.Windows.Forms.Label txtBuscar;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
    }
}