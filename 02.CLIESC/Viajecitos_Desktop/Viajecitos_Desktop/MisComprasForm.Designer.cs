namespace Viajecitos_Desktop
{
    partial class MisComprasForm
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
            this.dgMisCompras = new System.Windows.Forms.DataGridView();
            this.btnVolver = new System.Windows.Forms.Button();
            this.txtBuscar = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.dgMisCompras)).BeginInit();
            this.SuspendLayout();
            // 
            // dgMisCompras
            // 
            this.dgMisCompras.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgMisCompras.Location = new System.Drawing.Point(83, 161);
            this.dgMisCompras.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.dgMisCompras.Name = "dgMisCompras";
            this.dgMisCompras.RowHeadersWidth = 51;
            this.dgMisCompras.Size = new System.Drawing.Size(913, 297);
            this.dgMisCompras.TabIndex = 6;
            // 
            // btnVolver
            // 
            this.btnVolver.BackColor = System.Drawing.Color.MidnightBlue;
            this.btnVolver.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnVolver.ForeColor = System.Drawing.SystemColors.Window;
            this.btnVolver.Location = new System.Drawing.Point(491, 485);
            this.btnVolver.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.btnVolver.Name = "btnVolver";
            this.btnVolver.Size = new System.Drawing.Size(123, 39);
            this.btnVolver.TabIndex = 7;
            this.btnVolver.Text = "Volver";
            this.btnVolver.UseVisualStyleBackColor = false;
            // 
            // txtBuscar
            // 
            this.txtBuscar.AutoSize = true;
            this.txtBuscar.BackColor = System.Drawing.SystemColors.Window;
            this.txtBuscar.Font = new System.Drawing.Font("Segoe UI", 28.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtBuscar.ForeColor = System.Drawing.Color.MidnightBlue;
            this.txtBuscar.Location = new System.Drawing.Point(400, 71);
            this.txtBuscar.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.txtBuscar.Name = "txtBuscar";
            this.txtBuscar.Size = new System.Drawing.Size(265, 62);
            this.txtBuscar.TabIndex = 8;
            this.txtBuscar.Text = "Mis Vuelos";
            // 
            // MisComprasForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(1067, 554);
            this.Controls.Add(this.txtBuscar);
            this.Controls.Add(this.btnVolver);
            this.Controls.Add(this.dgMisCompras);
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "MisComprasForm";
            this.Text = "MisComprasForm";
            ((System.ComponentModel.ISupportInitialize)(this.dgMisCompras)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dgMisCompras;
        private System.Windows.Forms.Button btnVolver;
        private System.Windows.Forms.Label txtBuscar;
    }
}