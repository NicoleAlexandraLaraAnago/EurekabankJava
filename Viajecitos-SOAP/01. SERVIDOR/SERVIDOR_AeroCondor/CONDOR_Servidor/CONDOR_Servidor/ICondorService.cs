using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using CONDOR_Servidor.DTO;

namespace CONDOR_Servidor
{
    [ServiceContract]
    public interface ICondorService
    {
        // Registro de nuevo usuario (rol cliente por defecto)
        [OperationContract]
        string RegistrarUsuario(string usuario, string password, string nombre, string apellido);

        // Login de usuario (retorna datos si las credenciales son correctas)
        [OperationContract]
        UsuarioDTO Login(string usuario, string password);

        // Devuelve el vuelo más costoso según origen, destino y fecha
        [OperationContract]
        VueloDTO BuscarVueloMayorValor(string origen, string destino, DateTime fecha);

        // Registra la compra del boleto
        [OperationContract]
        string RegistrarCompra(int idVuelo, int idUsuario);

        // Retorna los datos completos de un usuario por su ID
        [OperationContract]
        UsuarioDTO ObtenerUsuarioPorId(int idUsuario);

        [OperationContract]
        List<CompraDetalleDTO> ObtenerTodasLasCompras();

        [OperationContract]
        List<VueloDTO> ListarVuelos(string origen, string destino, DateTime fecha);

        [OperationContract]
        List<CiudadDTO> ListarCiudades();

        [OperationContract]
        List<CompraDetalleDTO> ObtenerComprasPorUsuario(int idUsuario);

        [OperationContract]
        FacturaDTO RegistrarCompraConFactura(int idVuelo, int idUsuario, int cantidad);

        [OperationContract]
        List<FacturaDTO> ObtenerFacturasPorUsuario(int idUsuario);
    }
}
