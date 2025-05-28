using Eurekabank_Servidor_SOAP.Data;
using Eurekabank_Servidor_SOAP.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Eurekabank_Servidor_SOAP.Services
{
    public class MovimientoService : IMovimientoService
    {
        private readonly AppDbContext _context;

        public MovimientoService(AppDbContext context)
        {
            _context = context;
        }

        private string ObtenerCodigoTipo(string tipo)
        {
            return tipo.ToUpper() switch
            {
                "APERTURA" => "001",
                "CANCELAR" => "002",
                "DEPOSITO" => "003",
                "RETIRO" => "004",
                "INTERES" => "005",
                "MANTENIMIENTO" => "006",
                "ITF" => "007",
                "TRANSFERENCIA" => "009",
                _ => throw new InvalidOperationException("Tipo de movimiento inválido.")
            };
        }

        private string ObtenerCodigoTipoDestino(string tipo)
        {
            return tipo.ToUpper() switch
            {
                "TRANSFERENCIA" => "008",
                _ => ObtenerCodigoTipo(tipo)
            };
        }

        public List<Movimiento> GetMovimientos(string cuenta)
        {
            return _context.Movimientos
                .Where(m => m.CuentaId == cuenta)
                .OrderBy(m => m.NumeroMovimiento)
                .ToList();
        }

        public async Task<bool> RegistrarMovimiento(MovimientoRequest req)
        {
            var cuentaOrigen = await _context.Cuentas.FindAsync(req.CuentaOrigen);
            if (cuentaOrigen == null || cuentaOrigen.Estado != "ACTIVO")
                return false;

            var movimientosOrigen = _context.Movimientos
                .Where(m => m.CuentaId == req.CuentaOrigen)
                .ToList();

            int nuevoNumero = movimientosOrigen.Any()
                ? movimientosOrigen.Max(m => m.NumeroMovimiento) + 1
                : 1;

            var tipo = req.TipoMovimiento.ToUpper();
            var tipoCodigo = ObtenerCodigoTipo(tipo);
            decimal importe = req.Importe;

            if ((tipo == "RETIRO" || tipo == "TRANSFERENCIA") && cuentaOrigen.Saldo < importe)
                throw new InvalidOperationException("Saldo insuficiente en la cuenta origen.");

            if (tipo == "RETIRO" || tipo == "TRANSFERENCIA")
                cuentaOrigen.Saldo -= importe;
            else
                cuentaOrigen.Saldo += importe;

            var movimiento = new Movimiento
            {
                CuentaId = req.CuentaOrigen,
                NumeroMovimiento = nuevoNumero,
                Fecha = DateTime.Now,
                Tipo = tipoCodigo,
                Importe = importe,
                CuentaDestino = req.CuentaDestino,
                EmpleadoId = "0001"
            };

            _context.Movimientos.Add(movimiento);

            if (tipo == "TRANSFERENCIA" && req.CuentaDestino != null)
            {
                var cuentaDestino = await _context.Cuentas.FindAsync(req.CuentaDestino);
                if (cuentaDestino == null || cuentaDestino.Estado != "ACTIVO")
                    return false;

                cuentaDestino.Saldo += importe;

                int numMovDestino = _context.Movimientos
                    .Where(m => m.CuentaId == req.CuentaDestino)
                    .Any()
                    ? _context.Movimientos
                        .Where(m => m.CuentaId == req.CuentaDestino)
                        .Max(m => m.NumeroMovimiento) + 1
                    : 1;

                var tipoDestinoCodigo = ObtenerCodigoTipoDestino(tipo);

                var movDestino = new Movimiento
                {
                    CuentaId = req.CuentaDestino,
                    NumeroMovimiento = numMovDestino,
                    Fecha = DateTime.Now,
                    Tipo = tipoDestinoCodigo,
                    Importe = importe,
                    CuentaDestino = req.CuentaOrigen,
                    EmpleadoId = "0001"
                };

                _context.Movimientos.Add(movDestino);
            }

            await _context.SaveChangesAsync();
            return true;
        }
    }
}
