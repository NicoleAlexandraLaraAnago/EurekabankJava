using System.ComponentModel.DataAnnotations.Schema;

namespace EUREKA_BANK_Servidor_RESTFUL.Models
{
    [Table("Movimiento")]
    public class Movimiento
    {
        [Column("chr_cuencodigo")]
        public string CuentaId { get; set; }

        [Column("int_movinumero")]
        public int NumeroMovimiento { get; set; }

        [Column("dtt_movifecha")]
        public DateTime Fecha { get; set; }

        [Column("chr_tipocodigo")]
        public string Tipo { get; set; } // Aquí aún es el código tipo, no descripción

        [NotMapped]
        public string Accion => new[] { "001", "003", "005", "008" }.Contains(Tipo?.PadLeft(3, '0')) ? "INGRESO" : "SALIDA";

        [Column("dec_moviimporte")]
        public decimal Importe { get; set; }

        [Column("chr_cuenreferencia")]
        public string? CuentaDestino { get; set; }

        [Column("chr_emplcodigo")]
        public string EmpleadoId { get; set; }
    }
}
