using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using VIAJECITOS_Servidor.CondorReference;

namespace VIAJECITOS_Servidor
{
    [ServiceContract]
    public interface IViajecitosService
    {
        [OperationContract]
        UsuarioDTO Login(string usuario, string password);

        [OperationContract]
        VueloDTO BuscarVueloMayorValor(string origen, string destino, DateTime fecha);

        [OperationContract]
        string RegistrarCompra(int idVuelo, int idUsuario);

        [OperationContract]
        UsuarioDTO ObtenerUsuarioPorId(int idUsuario);

        [OperationContract]
        string RegistrarUsuario(string usuario, string password, string nombre, string apellido);

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
