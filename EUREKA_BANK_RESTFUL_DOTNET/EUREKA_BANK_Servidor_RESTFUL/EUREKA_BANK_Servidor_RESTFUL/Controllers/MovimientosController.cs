using EUREKA_BANK_Servidor_RESTFUL.Models;
using EUREKA_BANK_Servidor_RESTFUL.Services;
using Microsoft.AspNetCore.Mvc;

namespace EUREKA_BANK_Servidor_RESTFUL.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class MovimientosController : ControllerBase
    {
        private readonly MovimientoService _service;

        public MovimientosController(MovimientoService service)
        {
            _service = service;
        }

        [HttpGet("{cuenta}")]
        public IActionResult GetMovimientos(string cuenta)
        {
            var movimientos = _service.GetMovimientos(cuenta);
            return Ok(movimientos);
        }

        [HttpPost]
        public async Task<IActionResult> RegistrarMovimiento([FromBody] MovimientoRequest req)
        {
            var result = await _service.RegistrarMovimiento(req);
            if (result)
                return Ok("Movimiento registrado");
            return BadRequest("No se pudo registrar");
        }
    }

}
