using System.Runtime.Serialization;

namespace Eurekabank_Servidor_SOAP.Models
{
    [DataContract]
    public class MovimientoRequest
    {
        [DataMember]
        public string CuentaOrigen { get; set; }

        [DataMember]
        public decimal Importe { get; set; }

        [DataMember]
        public string TipoMovimiento { get; set; }

        [DataMember]
        public string? CuentaDestino { get; set; }
    }
}
