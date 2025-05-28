namespace Eurekabank_Servidor_SOAP.Services
{
    public class AuthService : IAuthService
    {
        private const string USERNAME = "MONSTER";
        private const string PASSWORD = "MONSTER9";

        public bool Login(string username, string password)
        {
            return username == USERNAME && password == PASSWORD;
        }
    }
}
