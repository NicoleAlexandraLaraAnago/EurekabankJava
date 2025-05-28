using EUREKA_BANK_CLIESC_RESTFUL.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace EUREKA_BANK_CLIESC_RESTFUL.Services
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

        public async Task<Cuenta> ObtenerCuenta(string cuentaId)
        {
            var response = await _client.GetAsync($"Cuentas/{cuentaId}");
            if (!response.IsSuccessStatusCode) return null;

            var json = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Cuenta>(json);
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
