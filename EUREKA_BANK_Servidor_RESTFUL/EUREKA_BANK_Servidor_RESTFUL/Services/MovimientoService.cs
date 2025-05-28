using EUREKA_BANK_Servidor_RESTFUL.Data;
using EUREKA_BANK_Servidor_RESTFUL.Models;
using Microsoft.EntityFrameworkCore;

namespace EUREKA_BANK_Servidor_RESTFUL.Services
{
    public class MovimientoService
    {
        private readonly AppDbContext _context;

        public MovimientoService(AppDbContext context)
        {
            _context = context;
        }

        // Mapea el tipo en texto al código correspondiente para cuenta origen
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
                "TRANSFERENCIA" => "009", // SALIDA en cuenta origen
                _ => throw new InvalidOperationException("Tipo de movimiento inválido.")
            };
        }

        // Código de INGRESO para cuenta destino en caso de TRANSFERENCIA
        private string ObtenerCodigoTipoDestino(string tipo)
        {
            return tipo.ToUpper() switch
            {
                "TRANSFERENCIA" => "008", // INGRESO en cuenta destino
                _ => ObtenerCodigoTipo(tipo)
            };
        }

        public IEnumerable<Movimiento> GetMovimientos(string cuenta)
        {
            return _context.Movimientos
                .Where(m => m.CuentaId == cuenta)
                .OrderBy(m => m.NumeroMovimiento);
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

            // Validar saldo suficiente si es retiro o transferencia
            if ((tipo == "RETIRO" || tipo == "TRANSFERENCIA") && cuentaOrigen.Saldo < importe)
                throw new InvalidOperationException("Saldo insuficiente en la cuenta origen.");

            // Actualizar saldo de cuenta origen
            if (tipo == "RETIRO" || tipo == "TRANSFERENCIA")
                cuentaOrigen.Saldo -= importe;
            else
                cuentaOrigen.Saldo += importe;

            // Movimiento en cuenta origen
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

            // Si es transferencia, registrar también en cuenta destino
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
