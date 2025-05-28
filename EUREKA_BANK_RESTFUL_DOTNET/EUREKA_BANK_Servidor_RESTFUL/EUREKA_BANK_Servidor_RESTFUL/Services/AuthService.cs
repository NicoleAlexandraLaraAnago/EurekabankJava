namespace EUREKA_BANK_Servidor_RESTFUL.Services
{
    public class AuthService
    {
        private const string USERNAME = "MONSTER";
        private const string PASSWORD = "MONSTER9";

        public bool Login(string username, string password)
        {
            return username == USERNAME && password == PASSWORD;
        }
    }

}
