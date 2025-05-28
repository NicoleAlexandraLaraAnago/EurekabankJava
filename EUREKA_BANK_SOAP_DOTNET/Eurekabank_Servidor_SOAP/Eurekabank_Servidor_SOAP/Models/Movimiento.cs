using System;
using System.ComponentModel.DataAnnotations.Schema;
using System.Runtime.Serialization;

namespace Eurekabank_Servidor_SOAP.Models
{
    [Table("Movimiento")]
    [DataContract]
    public class Movimiento
    {
        [Column("chr_cuencodigo")]
        [DataMember]
        public string CuentaId { get; set; }

        [Column("int_movinumero")]
        [DataMember]
        public int NumeroMovimiento { get; set; }

        [Column("dtt_movifecha")]
        [DataMember]
        public DateTime Fecha { get; set; }

        [Column("chr_tipocodigo")]
        [DataMember]
        public string Tipo { get; set; }

        [Column("dec_moviimporte")]
        [DataMember]
        public decimal Importe { get; set; }

        [Column("chr_cuenreferencia")]
        [DataMember]
        public string? CuentaDestino { get; set; }

        [Column("chr_emplcodigo")]
        [DataMember]
        public string EmpleadoId { get; set; }

        [NotMapped]
        public string Accion => new[] { "001", "003", "005", "008" }.Contains(Tipo?.PadLeft(3, '0')) ? "INGRESO" : "SALIDA";
    }
}
