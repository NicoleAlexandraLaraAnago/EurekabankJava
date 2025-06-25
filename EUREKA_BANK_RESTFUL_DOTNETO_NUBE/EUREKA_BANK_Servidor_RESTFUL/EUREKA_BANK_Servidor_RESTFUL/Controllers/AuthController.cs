using EUREKA_BANK_Servidor_RESTFUL.Services;
using Microsoft.AspNetCore.Mvc;

namespace EUREKA_BANK_Servidor_RESTFUL.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class AuthController : ControllerBase
    {
        private readonly AuthService _authService = new AuthService();

        [HttpPost("login")]
        public IActionResult Login([FromBody] LoginRequest login)
        {
            if (_authService.Login(login.Username, login.Password))
                return Ok("Acceso concedido");
            else
                return Unauthorized("Credenciales incorrectas");
        }
    }

    public class LoginRequest
    {
        public string Username { get; set; }
        public string Password { get; set; }
    }

}
