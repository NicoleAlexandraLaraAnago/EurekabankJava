using System.Collections.Generic;
using System.Reflection.Emit;
using Microsoft.EntityFrameworkCore;
using Eurekabank_Servidor_SOAP.Models;

namespace Eurekabank_Servidor_SOAP.Data

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
