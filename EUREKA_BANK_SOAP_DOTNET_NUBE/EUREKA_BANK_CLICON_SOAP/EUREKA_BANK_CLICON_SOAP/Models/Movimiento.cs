using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EUREKA_BANK_CLICON_RESTFUL.Models
{
    public class Movimiento
    {
        public string CuentaId { get; set; }
        public int NumeroMovimiento { get; set; }
        public DateTime Fecha { get; set; }
        public string Tipo { get; set; }
        public string Accion { get; set; }
        public decimal Importe { get; set; }
        public string CuentaDestino { get; set; }
    }

}
