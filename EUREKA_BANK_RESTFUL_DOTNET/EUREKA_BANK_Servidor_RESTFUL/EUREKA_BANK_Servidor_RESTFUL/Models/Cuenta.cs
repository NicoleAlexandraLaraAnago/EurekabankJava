using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace EUREKA_BANK_Servidor_RESTFUL.Models
{
    [Table("Cuenta")]
    public class Cuenta
    {
        [Key]
        [Column("chr_cuencodigo")]
        public string CuentaId { get; set; }

        [Column("chr_monecodigo")]
        public string CodigoMoneda { get; set; }

        [Column("chr_sucucodigo")]
        public string CodigoSucursal { get; set; }

        [Column("chr_emplcreacuenta")]
        public string CodigoEmpleadoCreador { get; set; }

        [Column("chr_cliecodigo")]
        public string CodigoCliente { get; set; }

        [Column("dec_cuensaldo")]
        public decimal Saldo { get; set; }

        [Column("dtt_cuenfechacreacion")]
        public DateTime FechaCreacion { get; set; }

        [Column("vch_cuenestado")]
        public string Estado { get; set; } = "ACTIVO";

        [Column("int_cuencontmov")]
        public int ContadorMovimientos { get; set; }

        [Column("chr_cuenclave")]
        public string Clave { get; set; }
    }
}
