using CoreWCF;
using System.ServiceModel;

[ServiceContract]
public interface IAuthService
{
    [OperationContract]
    bool Login(string username, string password);
}
