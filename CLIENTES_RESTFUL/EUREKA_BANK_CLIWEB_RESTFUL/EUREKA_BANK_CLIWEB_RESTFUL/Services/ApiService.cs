using EUREKA_BANK_CLIWEB_RESTFUL.Models;
using System.Text;
using Newtonsoft.Json;
using System.Net.Http;

namespace EUREKA_BANK_CLIWEB_RESTFUL.Services
{
    public class ApiService
    {
        private readonly HttpClient _client;

        public ApiService()
        {
            _client = new HttpClient();
            _client.BaseAddress = new Uri("https://localhost:7286/api/");
        }

        public async Task<bool> LoginAsync(string usuario, string clave)
        {
            return usuario == "MONSTER" && clave == "MONSTER9";
        }

        public async Task<Cuenta> ObtenerCuenta(string cuenta)
        {
            var movimientos = await ObtenerMovimientos(cuenta);
            decimal saldo = movimientos.Sum(m => m.Accion == "INGRESO" ? m.Importe : -m.Importe);

            return new Cuenta { CuentaId = cuenta, Saldo = saldo };
        }

        public async Task<List<Movimiento>> ObtenerMovimientos(string cuenta)
        {
            var response = await _client.GetAsync($"Movimientos/{cuenta}");
            var json = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<List<Movimiento>>(json);
        }

        public async Task<string> RealizarMovimiento(MovimientoRequest req)
        {
            var json = JsonConvert.SerializeObject(req);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            var response = await _client.PostAsync("Movimientos", content);
            return response.IsSuccessStatusCode ? "Movimiento registrado" : "Error: " + await response.Content.ReadAsStringAsync();
        }
    }
}
