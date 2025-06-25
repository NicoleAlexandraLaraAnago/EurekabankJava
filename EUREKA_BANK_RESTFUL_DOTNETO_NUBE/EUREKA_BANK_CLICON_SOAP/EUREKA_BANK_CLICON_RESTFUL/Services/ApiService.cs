using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EUREKA_BANK_CLICON_RESTFUL.Services
{
    using System.Net.Http.Json;
    using EUREKA_BANK_CLICON_RESTFUL.Models;
    using Newtonsoft.Json;

    public class ApiService
    {
        private readonly HttpClient _client;
        private const string BaseUrl = "https://localhost:7286/api";

        public ApiService()
        {
            _client = new HttpClient();
        }

        public async Task<bool> LoginAsync(string username, string password)
        {
            var login = new LoginRequest { Username = username, Password = password };
            var response = await _client.PostAsJsonAsync($"{BaseUrl}/Auth/login", login);
            return response.IsSuccessStatusCode;
        }

        public async Task<List<Movimiento>> ObtenerMovimientos(string cuenta)
        {
            var response = await _client.GetAsync($"{BaseUrl}/Movimientos/{cuenta}");
            var json = await response.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<List<Movimiento>>(json);
        }

        public async Task<Cuenta> ObtenerCuenta(string cuenta)
        {
            var movimientos = await ObtenerMovimientos(cuenta);
            decimal saldo = movimientos.Sum(m => m.Accion == "INGRESO" ? m.Importe : -m.Importe);

            return new Cuenta { CuentaId = cuenta, Saldo = saldo };
        }

        public async Task<bool> RegistrarMovimiento(MovimientoRequest req)
        {
            var response = await _client.PostAsJsonAsync($"{BaseUrl}/Movimientos", req);
            return response.IsSuccessStatusCode;
        }
    }

}
