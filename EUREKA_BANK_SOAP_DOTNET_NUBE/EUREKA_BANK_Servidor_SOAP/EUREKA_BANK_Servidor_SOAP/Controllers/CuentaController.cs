using EUREKA_BANK_Servidor_RESTFUL.Data;
using EUREKA_BANK_Servidor_RESTFUL.Models;
using Microsoft.AspNetCore.Mvc;

namespace EUREKA_BANK_Servidor_RESTFUL.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CuentasController : ControllerBase
    {
        private readonly AppDbContext _context;

        public CuentasController(AppDbContext context)
        {
            _context = context;
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Cuenta>> GetCuenta(string id)
        {
            var cuenta = await _context.Cuentas.FindAsync(id);
            if (cuenta == null)
                return NotFound();

            return cuenta;
        }
    }
}