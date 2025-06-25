using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EUREKA_BANK_CLICON_RESTFUL.Models
{
    public class MovimientoRequest
    {
        public string CuentaOrigen { get; set; }
        public decimal Importe { get; set; }
        public string TipoMovimiento { get; set; }
        public string CuentaDestino { get; set; }
    }

}
