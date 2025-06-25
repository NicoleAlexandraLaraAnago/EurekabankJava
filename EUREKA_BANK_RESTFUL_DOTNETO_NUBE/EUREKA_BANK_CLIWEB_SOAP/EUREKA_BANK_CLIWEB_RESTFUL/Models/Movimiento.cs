using System.ComponentModel.DataAnnotations.Schema;

namespace EUREKA_BANK_CLIWEB_RESTFUL.Models
{
    public class Movimiento
    {
        public string CuentaId { get; set; }
        public int NumeroMovimiento { get; set; }
        public DateTime Fecha { get; set; }
        public string Tipo { get; set; }
        public decimal Importe { get; set; }
        public string CuentaDestino { get; set; }

        [NotMapped]
        public string Accion => Tipo == "001" || Tipo == "003" || Tipo == "005" || Tipo == "008" ? "INGRESO" : "SALIDA";
    }

}
