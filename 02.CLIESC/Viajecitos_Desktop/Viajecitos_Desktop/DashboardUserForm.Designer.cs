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
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.picAvatar)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // picAvatar
            // 
            this.picAvatar.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("picAvatar.BackgroundImage")));
            this.picAvatar.ErrorImage = ((System.Drawing.Image)(resources.GetObject("picAvatar.ErrorImage")));
            this.picAvatar.Location = new System.Drawing.Point(38, 58);
            this.picAvatar.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.picAvatar.Name = "picAvatar";
            this.picAvatar.Size = new System.Drawing.Size(13, 17);
            this.picAvatar.TabIndex = 0;
            this.picAvatar.TabStop = false;
            // 
            // lblMarca
            // 
            this.lblMarca.AutoSize = true;
            this.lblMarca.Font = new System.Drawing.Font("Segoe UI", 28.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblMarca.ForeColor = System.Drawing.Color.MidnightBlue;
            this.lblMarca.Location = new System.Drawing.Point(42, 47);
            this.lblMarca.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblMarca.Name = "lblMarca";
            this.lblMarca.Size = new System.Drawing.Size(310, 62);
            this.lblMarca.TabIndex = 1;
            this.lblMarca.Text = "Viajecitos SA";
            // 
            // lblSaludo
            // 
            this.lblSaludo.AutoSize = true;
            this.lblSaludo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblSaludo.Location = new System.Drawing.Point(135, 120);
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
            this.lblRol.Location = new System.Drawing.Point(152, 159);
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
            this.btnBuscarVuelo.Location = new System.Drawing.Point(96, 213);
            this.btnBuscarVuelo.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
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
            this.btnMisCompras.Location = new System.Drawing.Point(96, 281);
            this.btnMisCompras.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.btnMisCompras.Name = "btnMisCompras";
            this.btnMisCompras.Size = new System.Drawing.Size(212, 39);
            this.btnMisCompras.TabIndex = 5;
            this.btnMisCompras.Text = "Mis Compras";
            this.btnMisCompras.UseVisualStyleBackColor = false;
            // 
            // btnCerrar
            // 
            this.btnCerrar.BackColor = System.Drawing.Color.DarkRed;
            this.btnCerrar.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCerrar.ForeColor = System.Drawing.Color.White;
            this.btnCerrar.Location = new System.Drawing.Point(96, 347);
            this.btnCerrar.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.btnCerrar.Name = "btnCerrar";
            this.btnCerrar.Size = new System.Drawing.Size(212, 46);
            this.btnCerrar.TabIndex = 6;
            this.btnCerrar.Text = "Cerrar Sesion";
            this.btnCerrar.UseVisualStyleBackColor = false;
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(410, 37);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(273, 420);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox1.TabIndex = 7;
            this.pictureBox1.TabStop = false;
            // 
            // DashboardUserForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(746, 554);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.btnCerrar);
            this.Controls.Add(this.btnMisCompras);
            this.Controls.Add(this.btnBuscarVuelo);
            this.Controls.Add(this.lblRol);
            this.Controls.Add(this.lblSaludo);
            this.Controls.Add(this.lblMarca);
            this.Controls.Add(this.picAvatar);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "DashboardUserForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "DashboardUserForm";
            ((System.ComponentModel.ISupportInitialize)(this.picAvatar)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox picAvatar;
        private System.Windows.Forms.Label lblMarca;
        private System.Windows.Forms.Label lblSaludo;
        private System.Windows.Forms.Label lblRol;
        private System.Windows.Forms.Button btnBuscarVuelo;
        private System.Windows.Forms.Button btnMisCompras;
        private System.Windows.Forms.Button btnCerrar;
        private System.Windows.Forms.PictureBox pictureBox1;
    }
}