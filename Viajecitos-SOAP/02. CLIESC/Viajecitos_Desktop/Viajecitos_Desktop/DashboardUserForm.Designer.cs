namespace Viajecitos_Desktop
{
    partial class DashboardUserForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DashboardUserForm));
            this.picAvatar = new System.Windows.Forms.PictureBox();
            this.lblMarca = new System.Windows.Forms.Label();
            this.lblSaludo = new System.Windows.Forms.Label();
            this.lblRol = new System.Windows.Forms.Label();
            this.btnBuscarVuelo = new System.Windows.Forms.Button();
            this.btnMisCompras = new System.Windows.Forms.Button();
            this.btnCerrar = new System.Windows.Forms.Button();
            this.btnMisFacturas = new System.Windows.Forms.Button();
            this.panelFormulario = new System.Windows.Forms.Panel();
            ((System.ComponentModel.ISupportInitialize)(this.picAvatar)).BeginInit();
            this.panelFormulario.SuspendLayout();
            this.SuspendLayout();
            // 
            // picAvatar
            // 
            this.picAvatar.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("picAvatar.BackgroundImage")));
            this.picAvatar.ErrorImage = ((System.Drawing.Image)(resources.GetObject("picAvatar.ErrorImage")));
            this.picAvatar.Location = new System.Drawing.Point(73, 29);
            this.picAvatar.Margin = new System.Windows.Forms.Padding(4);
            this.picAvatar.Name = "picAvatar";
            this.picAvatar.Size = new System.Drawing.Size(13, 17);
            this.picAvatar.TabIndex = 0;
            this.picAvatar.TabStop = false;
            // 
            // lblMarca
            // 
            this.lblMarca.AutoSize = true;
            this.lblMarca.BackColor = System.Drawing.Color.Transparent;
            this.lblMarca.Font = new System.Drawing.Font("Segoe UI", 28.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblMarca.ForeColor = System.Drawing.Color.MidnightBlue;
            this.lblMarca.Location = new System.Drawing.Point(36, 11);
            this.lblMarca.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblMarca.Name = "lblMarca";
            this.lblMarca.Size = new System.Drawing.Size(310, 62);
            this.lblMarca.TabIndex = 1;
            this.lblMarca.Text = "Viajecitos SA";
            // 
            // lblSaludo
            // 
            this.lblSaludo.AutoSize = true;
            this.lblSaludo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblSaludo.Location = new System.Drawing.Point(83, 89);
            this.lblSaludo.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblSaludo.Name = "lblSaludo";
            this.lblSaludo.Size = new System.Drawing.Size(108, 25);
            this.lblSaludo.TabIndex = 2;
            this.lblSaludo.Text = "Dashboard";
            // 
            // lblRol
            // 
            this.lblRol.AutoSize = true;
            this.lblRol.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblRol.Location = new System.Drawing.Point(129, 127);
            this.lblRol.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblRol.Name = "lblRol";
            this.lblRol.Size = new System.Drawing.Size(73, 25);
            this.lblRol.TabIndex = 3;
            this.lblRol.Text = "Cliente";
            // 
            // btnBuscarVuelo
            // 
            this.btnBuscarVuelo.BackColor = System.Drawing.Color.MidnightBlue;
            this.btnBuscarVuelo.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnBuscarVuelo.ForeColor = System.Drawing.Color.White;
            this.btnBuscarVuelo.Location = new System.Drawing.Point(254, 224);
            this.btnBuscarVuelo.Margin = new System.Windows.Forms.Padding(4);
            this.btnBuscarVuelo.Name = "btnBuscarVuelo";
            this.btnBuscarVuelo.Size = new System.Drawing.Size(212, 46);
            this.btnBuscarVuelo.TabIndex = 4;
            this.btnBuscarVuelo.Text = "Buscar Vuelo";
            this.btnBuscarVuelo.UseVisualStyleBackColor = false;
            // 
            // btnMisCompras
            // 
            this.btnMisCompras.BackColor = System.Drawing.Color.MidnightBlue;
            this.btnMisCompras.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnMisCompras.ForeColor = System.Drawing.Color.White;
            this.btnMisCompras.Location = new System.Drawing.Point(254, 292);
            this.btnMisCompras.Margin = new System.Windows.Forms.Padding(4);
            this.btnMisCompras.Name = "btnMisCompras";
            this.btnMisCompras.Size = new System.Drawing.Size(212, 44);
            this.btnMisCompras.TabIndex = 5;
            this.btnMisCompras.Text = "Mis Compras";
            this.btnMisCompras.UseVisualStyleBackColor = false;
            // 
            // btnCerrar
            // 
            this.btnCerrar.BackColor = System.Drawing.Color.DarkRed;
            this.btnCerrar.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCerrar.ForeColor = System.Drawing.Color.White;
            this.btnCerrar.Location = new System.Drawing.Point(254, 422);
            this.btnCerrar.Margin = new System.Windows.Forms.Padding(4);
            this.btnCerrar.Name = "btnCerrar";
            this.btnCerrar.Size = new System.Drawing.Size(212, 46);
            this.btnCerrar.TabIndex = 6;
            this.btnCerrar.Text = "Cerrar Sesion";
            this.btnCerrar.UseVisualStyleBackColor = false;
            // 
            // btnMisFacturas
            // 
            this.btnMisFacturas.BackColor = System.Drawing.Color.MidnightBlue;
            this.btnMisFacturas.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnMisFacturas.ForeColor = System.Drawing.SystemColors.Window;
            this.btnMisFacturas.Location = new System.Drawing.Point(254, 359);
            this.btnMisFacturas.Name = "btnMisFacturas";
            this.btnMisFacturas.Size = new System.Drawing.Size(212, 40);
            this.btnMisFacturas.TabIndex = 8;
            this.btnMisFacturas.Text = "Mis Facturas";
            this.btnMisFacturas.UseVisualStyleBackColor = false;
            this.btnMisFacturas.Click += new System.EventHandler(this.btnMisFacturas_Click);
            // 
            // panelFormulario
            // 
            this.panelFormulario.Controls.Add(this.lblMarca);
            this.panelFormulario.Controls.Add(this.lblSaludo);
            this.panelFormulario.Controls.Add(this.lblRol);
            this.panelFormulario.Controls.Add(this.picAvatar);
            this.panelFormulario.Location = new System.Drawing.Point(181, 58);
            this.panelFormulario.Name = "panelFormulario";
            this.panelFormulario.Size = new System.Drawing.Size(367, 434);
            this.panelFormulario.TabIndex = 9;
            // 
            // DashboardUserForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(746, 554);
            this.Controls.Add(this.btnMisFacturas);
            this.Controls.Add(this.btnCerrar);
            this.Controls.Add(this.btnMisCompras);
            this.Controls.Add(this.btnBuscarVuelo);
            this.Controls.Add(this.panelFormulario);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.Margin = new System.Windows.Forms.Padding(4);
            this.Name = "DashboardUserForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "DashboardUserForm";
            ((System.ComponentModel.ISupportInitialize)(this.picAvatar)).EndInit();
            this.panelFormulario.ResumeLayout(false);
            this.panelFormulario.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.PictureBox picAvatar;
        private System.Windows.Forms.Label lblMarca;
        private System.Windows.Forms.Label lblSaludo;
        private System.Windows.Forms.Label lblRol;
        private System.Windows.Forms.Button btnBuscarVuelo;
        private System.Windows.Forms.Button btnMisCompras;
        private System.Windows.Forms.Button btnCerrar;
        private System.Windows.Forms.Button btnMisFacturas;
        private System.Windows.Forms.Panel panelFormulario;
    }
}