using EUREKA_BANK_Servidor_RESTFUL.Models;
using System.Collections.Generic;
using System.Reflection.Emit;
using Microsoft.EntityFrameworkCore;

namespace EUREKA_BANK_Servidor_RESTFUL.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

        public DbSet<Movimiento> Movimientos { get; set; }
        public DbSet<Cuenta> Cuentas { get; set; }
        public DbSet<TipoMovimiento> TipoMovimientos { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Movimiento>().HasKey(m => new { m.CuentaId, m.NumeroMovimiento });
        }
    }

}
