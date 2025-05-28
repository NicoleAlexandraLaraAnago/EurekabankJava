using EUREKA_BANK_CLICON_RESTFUL.Models;
using EUREKA_BANK_CLICON_RESTFUL.Services;

class Program
{
    static async Task Main()
    {
        var api = new ApiService();

        Console.WriteLine("=== Login ===");
        Console.Write("Usuario: ");
        var user = Console.ReadLine();
        Console.Write("Clave: ");
        var pass = Console.ReadLine();

        if (!await api.LoginAsync(user, pass))
        {
            Console.WriteLine("Credenciales incorrectas.");
            return;
        }

        while (true)
        {
            Console.WriteLine("\n=== Menú Principal ===");
            Console.WriteLine("1. Ver movimientos");
            Console.WriteLine("2. Realizar movimiento");
            Console.WriteLine("3. Salir");
            Console.Write("Opción: ");
            var opcion = Console.ReadLine();

            if (opcion == "1")
            {
                Console.Write("Cuenta a consultar: ");
                var cuenta = Console.ReadLine();
                var movimientos = await api.ObtenerMovimientos(cuenta);
                foreach (var mov in movimientos)
                {
                    Console.WriteLine($"{mov.NumeroMovimiento} | {mov.Fecha:yyyy-MM-dd} | {mov.Tipo} | {mov.Accion} | {mov.Importe}");
                }
            }
            else if (opcion == "2")
            {
                Console.Write("Cuenta origen: ");
                var cuentaOrigen = Console.ReadLine();
                var cuentaAntes = await api.ObtenerCuenta(cuentaOrigen);

                Console.WriteLine("Tipo de movimiento: 1=Depósito, 2=Retiro, 3=Transferencia");
                var tipo = Console.ReadLine();
                string tipoTexto = tipo switch
                {
                    "1" => "DEPOSITO",
                    "2" => "RETIRO",
                    "3" => "TRANSFERENCIA",
                    _ => ""
                };

                string? cuentaDestino = null;
                if (tipoTexto == "TRANSFERENCIA")
                {
                    Console.Write("Cuenta destino: ");
                    cuentaDestino = Console.ReadLine();
                }

                Console.Write("Importe: ");
                var importe = decimal.Parse(Console.ReadLine());

                var req = new MovimientoRequest
                {
                    CuentaOrigen = cuentaOrigen,
                    TipoMovimiento = tipoTexto,
                    Importe = importe,
                    CuentaDestino = cuentaDestino
                };

                var ok = await api.RegistrarMovimiento(req);

                if (ok)
                {
                    var cuentaDespues = await api.ObtenerCuenta(cuentaOrigen);
                    Console.WriteLine($"\n✅ Movimiento registrado.");
                    Console.WriteLine($"💰 Saldo anterior: {cuentaAntes.Saldo}");
                    Console.WriteLine($"💰 Saldo actual: {cuentaDespues.Saldo}");

                    if (tipoTexto == "TRANSFERENCIA" && cuentaDestino != null)
                    {
                        var cuentaDestinoAntes = await api.ObtenerCuenta(cuentaDestino);
                        Console.WriteLine($"\n📤 Cuenta Origen: {cuentaOrigen} - Nuevo saldo: {cuentaDespues.Saldo}");
                        Console.WriteLine($"📥 Cuenta Destino: {cuentaDestino} - Nuevo saldo: {cuentaDestinoAntes.Saldo}");
                    }
                }
                else
                {
                    Console.WriteLine("❌ Error al registrar el movimiento.");
                }
            }
            else if (opcion == "3")
            {
                break;
            }
            else
            {
                Console.WriteLine("Opción no válida.");
            }
        }
    }
}
