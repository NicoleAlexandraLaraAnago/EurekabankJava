namespace EUREKA_BANK_CLIESC_RESTFUL
{
    partial class MenuForm
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
            btnMovimientos = new Button();
            btnRealizar = new Button();
            btnCerrarSesion = new Button();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Segoe UI", 36F, FontStyle.Bold, GraphicsUnit.Point, 0);
            label1.ForeColor = Color.MidnightBlue;
            label1.Location = new Point(43, 22);
            label1.Name = "label1";
            label1.Size = new Size(745, 81);
            label1.TabIndex = 0;
            label1.Text = "Bienvenido a EurekaBank";
            // 
            // btnMovimientos
            // 
            btnMovimientos.BackColor = Color.CornflowerBlue;
            btnMovimientos.Font = new Font("Segoe UI", 16.2F, FontStyle.Regular, GraphicsUnit.Point, 0);
            btnMovimientos.ForeColor = SystemColors.Window;
            btnMovimientos.Location = new Point(254, 137);
            btnMovimientos.Name = "btnMovimientos";
            btnMovimientos.Size = new Size(292, 54);
            btnMovimientos.TabIndex = 1;
            btnMovimientos.Text = "Ver Movimientos";
            btnMovimientos.UseVisualStyleBackColor = false;
            btnMovimientos.Click += btnMovimientos_Click;
            // 
            // btnRealizar
            // 
            btnRealizar.BackColor = Color.DarkCyan;
            btnRealizar.Font = new Font("Segoe UI", 16.2F, FontStyle.Regular, GraphicsUnit.Point, 0);
            btnRealizar.ForeColor = Color.White;
            btnRealizar.Location = new Point(254, 228);
            btnRealizar.Name = "btnRealizar";
            btnRealizar.Size = new Size(292, 51);
            btnRealizar.TabIndex = 2;
            btnRealizar.Text = "Realizar Movimientos";
            btnRealizar.UseVisualStyleBackColor = false;
            btnRealizar.Click += btnRealizar_Click;
            // 
            // btnCerrarSesion
            // 
            btnCerrarSesion.BackColor = Color.Firebrick;
            btnCerrarSesion.Font = new Font("Segoe UI", 16.2F, FontStyle.Bold, GraphicsUnit.Point, 0);
            btnCerrarSesion.ForeColor = Color.White;
            btnCerrarSesion.Location = new Point(254, 340);
            btnCerrarSesion.Name = "btnCerrarSesion";
            btnCerrarSesion.Size = new Size(292, 48);
            btnCerrarSesion.TabIndex = 3;
            btnCerrarSesion.Text = "Cerrar Sesión";
            btnCerrarSesion.UseVisualStyleBackColor = false;
            btnCerrarSesion.Click += btnCerrarSesion_Click;
            // 
            // MenuForm
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = SystemColors.Window;
            ClientSize = new Size(800, 450);
            Controls.Add(btnCerrarSesion);
            Controls.Add(btnRealizar);
            Controls.Add(btnMovimientos);
            Controls.Add(label1);
            Name = "MenuForm";
            Text = "MenuForm";
            Load += MenuForm_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label label1;
        private Button btnMovimientos;
        private Button btnRealizar;
        private Button btnCerrarSesion;
        private PictureBox pictureBox1;
    }
}
