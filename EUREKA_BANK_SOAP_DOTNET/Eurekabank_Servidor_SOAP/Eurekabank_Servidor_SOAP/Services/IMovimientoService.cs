using CoreWCF;
using Eurekabank_Servidor_SOAP.Models;
using System.Collections.Generic;
using System.ServiceModel;
using System.Threading.Tasks;

[ServiceContract]
public interface IMovimientoService
{
    [OperationContract]
    List<Movimiento> GetMovimientos(string cuenta);

    [OperationContract]
    Task<bool> RegistrarMovimiento(MovimientoRequest req);
}
