using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace CONDOR_Servidor.DTO
{
    [DataContract]
    public class VueloDTO
    {
        [DataMember] public int Id { get; set; }
        [DataMember] public string CiudadOrigen { get; set; }
        [DataMember] public string CiudadDestino { get; set; }
        [DataMember] public decimal Valor { get; set; }
        [DataMember] public DateTime HoraSalida { get; set; }
        [DataMember] public int CuposDisponibles { get; set; }
    }
}