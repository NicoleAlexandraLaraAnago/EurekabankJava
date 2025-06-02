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
            this.picAvatar = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.picAvatar)).BeginInit();
            this.SuspendLayout();
            // 
            // btnTodasCompras
            // 
            this.btnTodasCompras.BackColor = System.Drawing.Color.MidnightBlue;
            this.btnTodasCompras.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnTodasCompras.ForeColor = System.Drawing.SystemColors.Window;
            this.btnTodasCompras.Location = new System.Drawing.Point(120, 247);
            this.btnTodasCompras.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
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
            this.btnCerrar.Location = new System.Drawing.Point(149, 326);
            this.btnCerrar.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
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
            this.lblSaludo.Location = new System.Drawing.Point(181, 137);
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
            this.lblRol.Location = new System.Drawing.Point(199, 181);
            this.lblRol.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblRol.Name = "lblRol";
            this.lblRol.Size = new System.Drawing.Size(68, 25);
            this.lblRol.TabIndex = 3;
            this.lblRol.Text = "Admin";
            // 
            // lblMarca
            // 
            this.lblMarca.AutoSize = true;
            this.lblMarca.BackColor = System.Drawing.SystemColors.Window;
            this.lblMarca.Font = new System.Drawing.Font("Segoe UI", 25.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblMarca.ForeColor = System.Drawing.Color.Indigo;
            this.lblMarca.Location = new System.Drawing.Point(110, 63);
            this.lblMarca.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblMarca.Name = "lblMarca";
            this.lblMarca.Size = new System.Drawing.Size(293, 60);
            this.lblMarca.TabIndex = 5;
            this.lblMarca.Text = "Viajecitos SA";
            // 
            // picAvatar
            // 
            this.picAvatar.Image = ((System.Drawing.Image)(resources.GetObject("picAvatar.Image")));
            this.picAvatar.Location = new System.Drawing.Point(463, 45);
            this.picAvatar.Name = "picAvatar";
            this.picAvatar.Size = new System.Drawing.Size(244, 390);
            this.picAvatar.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.picAvatar.TabIndex = 6;
            this.picAvatar.TabStop = false;
            // 
            // DashboardAdminForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(778, 554);
            this.Controls.Add(this.picAvatar);
            this.Controls.Add(this.lblMarca);
            this.Controls.Add(this.lblRol);
            this.Controls.Add(this.lblSaludo);
            this.Controls.Add(this.btnCerrar);
            this.Controls.Add(this.btnTodasCompras);
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "DashboardAdminForm";
            this.Text = "DashboardAdminForm";
            ((System.ComponentModel.ISupportInitialize)(this.picAvatar)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnTodasCompras;
        private System.Windows.Forms.Button btnCerrar;
        private System.Windows.Forms.Label lblSaludo;
        private System.Windows.Forms.Label lblRol;
        private System.Windows.Forms.Label lblMarca;
        private System.Windows.Forms.PictureBox picAvatar;
    }
}