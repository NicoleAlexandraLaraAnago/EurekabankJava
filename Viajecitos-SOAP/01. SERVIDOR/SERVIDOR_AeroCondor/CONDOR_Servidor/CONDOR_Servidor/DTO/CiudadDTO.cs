using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace CONDOR_Servidor.DTO
{
    [DataContract]
    public class CiudadDTO
    {
        [DataMember] public string Codigo;
        [DataMember] public string NombreCiudad;
    }
}