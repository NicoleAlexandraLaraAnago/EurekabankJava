using System.Diagnostics;
using EUREKA_BANK_CLIWEB_RESTFUL.Models;
using EUREKA_BANK_CLIWEB_RESTFUL.Services;
using Microsoft.AspNetCore.Mvc;

namespace EUREKA_BANK_CLIWEB_RESTFUL.Controllers
{
    public class HomeController : Controller
    {
        private readonly ApiService _api;

        public HomeController()
        {
            _api = new ApiService();
        }

        public IActionResult Index() => View();

        [HttpPost]
        public async Task<IActionResult> Index(string usuario, string clave)
        {
            if (await _api.LoginAsync(usuario, clave))
                return RedirectToAction("Menu");
            ViewBag.Error = "Credenciales incorrectas";
            return View();
        }

        public IActionResult Menu() => View();

        public IActionResult VerMovimientos() => View();

        [HttpPost]
        public async Task<IActionResult> VerMovimientos(string cuenta)
        {
            var movimientos = await _api.ObtenerMovimientos(cuenta);
            var cuentaInfo = await _api.ObtenerCuenta(cuenta);

            ViewBag.Cuenta = cuenta;
            ViewBag.Saldo = cuentaInfo?.Saldo.ToString("N2") ?? "No disponible";

            return View("VerMovimientos", movimientos);
        }



        public IActionResult Realizar() => View();

        [HttpPost]
        public async Task<IActionResult> Realizar(MovimientoRequest req)
        {
            var resultado = await _api.RealizarMovimiento(req);
            ViewBag.Mensaje = resultado;
            return View();
        }
    }
}
