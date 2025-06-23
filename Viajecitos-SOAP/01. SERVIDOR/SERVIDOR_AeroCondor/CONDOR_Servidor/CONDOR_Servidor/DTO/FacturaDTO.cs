using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace CONDOR_Servidor.DTO
{
    [DataContract]
    public class FacturaDTO
    {
        [DataMember] public int IdFactura { get; set; }
        [DataMember] public int IdCompra { get; set; }
        [DataMember] public DateTime FechaFactura { get; set; }
        [DataMember] public string NombreComprador { get; set; }
        [DataMember] public string CiudadOrigen { get; set; }
        [DataMember] public string CiudadDestino { get; set; }
        [DataMember] public string NumeroBoleto { get; set; }
        [DataMember] public decimal ValorUnitario { get; set; }
        [DataMember] public decimal Subtotal { get; set; }
        [DataMember] public decimal IVA { get; set; }
        [DataMember] public decimal TotalAPagar { get; set; }
    }
}