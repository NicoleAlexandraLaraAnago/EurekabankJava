using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace CONDOR_Servidor.DTO
{
    [DataContract]
    public class CompraDetalleDTO
    {
        [DataMember] public int IdCompra;
        [DataMember] public string Usuario;
        [DataMember] public string Nombre;
        [DataMember] public string Apellido;
        [DataMember] public string CiudadOrigen;
        [DataMember] public string CiudadDestino;
        [DataMember] public decimal Valor;
        [DataMember] public DateTime HoraSalida;
        [DataMember] public DateTime FechaCompra;
        [DataMember] public int Cantidad;

    }
}