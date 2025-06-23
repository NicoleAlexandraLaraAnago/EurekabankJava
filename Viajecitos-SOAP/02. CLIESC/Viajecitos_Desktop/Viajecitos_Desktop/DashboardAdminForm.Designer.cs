namespace Viajecitos_Desktop
{
    partial class DashboardAdminForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DashboardAdminForm));
            this.btnTodasCompras = new System.Windows.Forms.Button();
            this.btnCerrar = new System.Windows.Forms.Button();
            this.lblSaludo = new System.Windows.Forms.Label();
            this.lblRol = new System.Windows.Forms.Label();
            this.lblMarca = new System.Windows.Forms.Label();
            this.panelFormulario = new System.Windows.Forms.Panel();
            this.panelFormulario.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnTodasCompras
            // 
            this.btnTodasCompras.BackColor = System.Drawing.Color.MidnightBlue;
            this.btnTodasCompras.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnTodasCompras.ForeColor = System.Drawing.SystemColors.Window;
            this.btnTodasCompras.Location = new System.Drawing.Point(246, 271);
            this.btnTodasCompras.Margin = new System.Windows.Forms.Padding(4);
            this.btnTodasCompras.Name = "btnTodasCompras";
            this.btnTodasCompras.Size = new System.Drawing.Size(235, 48);
            this.btnTodasCompras.TabIndex = 0;
            this.btnTodasCompras.Text = "Todas Las Compras";
            this.btnTodasCompras.UseVisualStyleBackColor = false;
            // 
            // btnCerrar
            // 
            this.btnCerrar.BackColor = System.Drawing.Color.DarkRed;
            this.btnCerrar.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCerrar.ForeColor = System.Drawing.Color.White;
            this.btnCerrar.Location = new System.Drawing.Point(275, 350);
            this.btnCerrar.Margin = new System.Windows.Forms.Padding(4);
            this.btnCerrar.Name = "btnCerrar";
            this.btnCerrar.Size = new System.Drawing.Size(177, 40);
            this.btnCerrar.TabIndex = 1;
            this.btnCerrar.Text = "Cerrar Sesión\n\n";
            this.btnCerrar.UseVisualStyleBackColor = false;
            // 
            // lblSaludo
            // 
            this.lblSaludo.AutoSize = true;
            this.lblSaludo.BackColor = System.Drawing.Color.GhostWhite;
            this.lblSaludo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblSaludo.Location = new System.Drawing.Point(73, 103);
            this.lblSaludo.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblSaludo.Name = "lblSaludo";
            this.lblSaludo.Size = new System.Drawing.Size(108, 25);
            this.lblSaludo.TabIndex = 2;
            this.lblSaludo.Text = "Dashboard";
            this.lblSaludo.Click += new System.EventHandler(this.lblSaludo_Click);
            // 
            // lblRol
            // 
            this.lblRol.AutoSize = true;
            this.lblRol.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblRol.Location = new System.Drawing.Point(325, 205);
            this.lblRol.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblRol.Name = "lblRol";
            this.lblRol.Size = new System.Drawing.Size(68, 25);
            this.lblRol.TabIndex = 3;
            this.lblRol.Text = "Admin";
            // 
            // lblMarca
            // 
            this.lblMarca.AutoSize = true;
            this.lblMarca.BackColor = System.Drawing.Color.Transparent;
            this.lblMarca.Font = new System.Drawing.Font("Segoe UI", 25.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblMarca.ForeColor = System.Drawing.Color.MidnightBlue;
            this.lblMarca.Location = new System.Drawing.Point(30, 25);
            this.lblMarca.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblMarca.Name = "lblMarca";
            this.lblMarca.Size = new System.Drawing.Size(285, 59);
            this.lblMarca.TabIndex = 5;
            this.lblMarca.Text = "Viajecitos SA";
            // 
            // panelFormulario
            // 
            this.panelFormulario.Controls.Add(this.lblMarca);
            this.panelFormulario.Controls.Add(this.lblSaludo);
            this.panelFormulario.Location = new System.Drawing.Point(197, 60);
            this.panelFormulario.Name = "panelFormulario";
            this.panelFormulario.Size = new System.Drawing.Size(345, 384);
            this.panelFormulario.TabIndex = 6;
            // 
            // DashboardAdminForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(778, 554);
            this.Controls.Add(this.lblRol);
            this.Controls.Add(this.btnCerrar);
            this.Controls.Add(this.btnTodasCompras);
            this.Controls.Add(this.panelFormulario);
            this.Margin = new System.Windows.Forms.Padding(4);
            this.Name = "DashboardAdminForm";
            this.Text = "DashboardAdminForm";
            this.panelFormulario.ResumeLayout(false);
            this.panelFormulario.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnTodasCompras;
        private System.Windows.Forms.Button btnCerrar;
        private System.Windows.Forms.Label lblSaludo;
        private System.Windows.Forms.Label lblRol;
        private System.Windows.Forms.Label lblMarca;
        private System.Windows.Forms.Panel panelFormulario;
    }
}