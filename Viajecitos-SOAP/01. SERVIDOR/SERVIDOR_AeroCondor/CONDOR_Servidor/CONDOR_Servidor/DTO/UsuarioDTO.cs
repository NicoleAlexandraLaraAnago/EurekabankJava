using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace CONDOR_Servidor.DTO
{
    [DataContract]
    public class UsuarioDTO
    {
        [DataMember] public int Id;
        [DataMember] public string Usuario;
        [DataMember] public string Nombre;
        [DataMember] public string Apellido;
        [DataMember] public string Rol;
    }
}