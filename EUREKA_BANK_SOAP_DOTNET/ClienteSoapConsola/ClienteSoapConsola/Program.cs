using System;
using System.Linq;
using System.Threading.Tasks;
using ServiceReferenceLogin;
using ServiceReferenceMovimiento;

namespace ConsolaCliente
{
    class Program
    {
        static async Task Main(string[] args)
        {
            Console.WriteLine("=== CLIENTE DE AUTENTICACIÓN ===");
            Console.WriteLine("░░░░░░░░░░░░░░░░░░░░░░░░░░");
            Console.WriteLine("░░░░░░░░░░░░░░░░░░░░░░░░░░");
            Console.WriteLine("░░░░░░░░██░░░░░░░██░░░░░░░");
            Console.WriteLine("░░░░░░░░█░████████░█░░░░░░");
            Console.WriteLine("░░░░░░░░██░░░░░░░██░░░░░░░");
            Console.WriteLine("░░░░░░░░░█░░█░█░░░█░░░░░░░");
            Console.WriteLine("░░░░░░░░░░█░░░░░░█░░░░░░░░");
            Console.WriteLine("░░░░░░░░░░████████░░░░░░░░");
            Console.WriteLine("░░░░░░░░░███░░░░░████░░░░░");
            Console.WriteLine("░░░░░░░░█░░█░░░░░█░░█░░░░░");
            Console.WriteLine("░░░░░░░░████░░░░████░░░░░░");
            Console.WriteLine("░░░░░░░░░░████████░░░░░░░░");
            Console.WriteLine("░░░░░░░░░░░█░█░██░░░░░░░░░");
            Console.WriteLine("░░░░░░░░░░░█░█░░█░░░░░░░░░");
            Console.WriteLine("░░░░░░░░░░░██████░░░░░░░░░");

            Console.Write("Usuario: ");
            string username = Console.ReadLine();

            Console.Write("Contraseña: ");
            string password = Console.ReadLine();

            var authClient = new AuthServiceClient(AuthServiceClient.EndpointConfiguration.BasicHttpBinding_IAuthService);

            try
            {
                bool loginOk = await authClient.LoginAsync(username, password);
                if (!loginOk)
                {
                    Console.WriteLine("❌ Usuario o contraseña incorrectos.");
                    Console.WriteLine("Presiona cualquier tecla para salir...");
                    Console.ReadKey();
                    return;
                }

                Console.WriteLine("Login exitoso. ¡Bienvenido!");

                var movClient = new MovimientoServiceClient(MovimientoServiceClient.EndpointConfiguration.BasicHttpBinding_IMovimientoService);

                while (true)
                {
                    Console.WriteLine("\n=== MENÚ DE MOVIMIENTOS ===");
                    Console.WriteLine("1. Ver movimientos de una cuenta");
                    Console.WriteLine("2. Registrar movimiento (Transferencia, Depósito, Retiro)");
                    Console.WriteLine("3. Consultar saldo de cuenta");
                    Console.WriteLine("4. Salir");
                    Console.Write("Elige una opción: ");
                    var opcion = Console.ReadLine();

                    if (opcion == "4")
                        break;

                    switch (opcion)
                    {
                        case "1":
                            await VerMovimientos(movClient);
                            break;

                        case "2":
                            await RegistrarMovimiento(movClient);
                            break;

                        case "3":
                            await ConsultarSaldo(movClient);
                            break;

                        default:
                            Console.WriteLine("Opción no válida.");
                            break;
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("⚠️ Error al conectar con el servicio: " + ex.Message);
            }

            Console.WriteLine("\nPresiona cualquier tecla para salir...");
            Console.ReadKey();
        }

        static async Task VerMovimientos(MovimientoServiceClient movClient)
        {
            Console.Write("Ingresa código de cuenta: ");
            string cuenta = Console.ReadLine();

            try
            {
                var movimientos = await movClient.GetMovimientosAsync(cuenta);
                if (movimientos == null || movimientos.Length == 0)
                {
                    Console.WriteLine("No se encontraron movimientos para esta cuenta.");
                    return;
                }

                Console.WriteLine("\nMovimientos:");
                foreach (var mov in movimientos)
                {
                    Console.WriteLine($"#{mov.NumeroMovimiento} | {mov.Fecha} | {mov.Tipo} | {mov.Importe:C} | {ObtenerAccion(mov.Tipo)} | Destino: {mov.CuentaDestino ?? "N/A"}");
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error al obtener movimientos: {ex.Message}");
            }
        }

        static async Task RegistrarMovimiento(MovimientoServiceClient movClient)
        {
            Console.WriteLine("\nRegistrar Movimiento");

            Console.Write("Cuenta origen: ");
            string cuentaOrigen = Console.ReadLine();

            string tipoMovimiento;
            while (true)
            {
                Console.Write("Tipo movimiento (TRANSFERENCIA, DEPOSITO, RETIRO): ");
                tipoMovimiento = Console.ReadLine()?.Trim().ToUpper();

                if (tipoMovimiento == "TRANSFERENCIA" || tipoMovimiento == "DEPOSITO" || tipoMovimiento == "RETIRO")
                    break;

                Console.WriteLine("Tipo inválido. Solo se permiten TRANSFERENCIA, DEPOSITO o RETIRO.");
            }

            Console.Write("Importe: ");
            if (!decimal.TryParse(Console.ReadLine(), out decimal importe) || importe <= 0)
            {
                Console.WriteLine("Importe inválido.");
                return;
            }

            string cuentaDestino = null;
            if (tipoMovimiento == "TRANSFERENCIA")
            {
                Console.Write("Cuenta destino: ");
                cuentaDestino = Console.ReadLine();
            }

            try
            {
                var request = new MovimientoRequest
                {
                    CuentaOrigen = cuentaOrigen,
                    TipoMovimiento = tipoMovimiento,
                    Importe = importe,
                    CuentaDestino = cuentaDestino
                };

                bool resultado = await movClient.RegistrarMovimientoAsync(request);
                if (resultado)
                {
                    Console.WriteLine("Movimiento registrado exitosamente.");
                }
                else
                {
                    Console.WriteLine("Error al registrar movimiento. Verifica datos y estado de cuentas.");
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error al registrar movimiento: {ex.Message}");
            }
        }

        static async Task ConsultarSaldo(MovimientoServiceClient movClient)
        {
            Console.Write("Ingresa código de cuenta: ");
            string cuenta = Console.ReadLine();

            try
            {
                var movimientos = await movClient.GetMovimientosAsync(cuenta);
                if (movimientos == null || movimientos.Length == 0)
                {
                    Console.WriteLine("No se encontraron movimientos para esta cuenta.");
                    return;
                }

                decimal saldo = 0;
                foreach (var mov in movimientos)
                {
                    if (ObtenerAccion(mov.Tipo) == "INGRESO")
                        saldo += mov.Importe;
                    else
                        saldo -= mov.Importe;
                }

                Console.WriteLine($"Saldo estimado de la cuenta {cuenta}: {saldo:C}");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error al consultar saldo: {ex.Message}");
            }
        }

        static string ObtenerAccion(string tipo)
        {
            var ingreso = new[] { "001", "003", "005", "008" };
            if (string.IsNullOrEmpty(tipo))
                return "DESCONOCIDO";

            return ingreso.Contains(tipo.PadLeft(3, '0')) ? "INGRESO" : "SALIDA";
        }
    }
}
