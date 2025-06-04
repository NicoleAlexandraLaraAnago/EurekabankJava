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
    public class ViajecitosService : IViajecitosService
    {
        private CondorServiceClient condor = new CondorServiceClient();

        public UsuarioDTO Login(string usuario, string password)
        {
            return condor.Login(usuario, password);
        }

        public VueloDTO BuscarVueloMayorValor(string origen, string destino, DateTime fecha)
        {
            return condor.BuscarVueloMayorValor(origen, destino, fecha);
        }

        public string RegistrarCompra(int idVuelo, int idUsuario)
        {
            return condor.RegistrarCompra(idVuelo, idUsuario);
        }

        public UsuarioDTO ObtenerUsuarioPorId(int idUsuario)
        {
            return condor.ObtenerUsuarioPorId(idUsuario);
        }
        public string RegistrarUsuario(string usuario, string password, string nombre, string apellido)
        {
            return condor.RegistrarUsuario(usuario, password, nombre, apellido);
        }
        public List<CompraDetalleDTO> ObtenerTodasLasCompras()
        {
            return condor.ObtenerTodasLasCompras().ToList();
        }

        public List<VueloDTO> ListarVuelos(string origen, string destino, DateTime fecha)
        {
            return condor.ListarVuelos(origen, destino, fecha).ToList();
        }

        public List<CiudadDTO> ListarCiudades()
        {
            return condor.ListarCiudades().ToList();
        }

        public List<CompraDetalleDTO> ObtenerComprasPorUsuario(int idUsuario)
        {
            return condor.ObtenerComprasPorUsuario(idUsuario).ToList();
        }

        public FacturaDTO RegistrarCompraConFactura(int idVuelo, int idUsuario, int cantidad)
        {
            return condor.RegistrarCompraConFactura(idVuelo, idUsuario, cantidad);
        }

        public List<FacturaDTO> ObtenerFacturasPorUsuario(int idUsuario)
        {
            return condor.ObtenerFacturasPorUsuario(idUsuario).ToList();
        }

    }
}
