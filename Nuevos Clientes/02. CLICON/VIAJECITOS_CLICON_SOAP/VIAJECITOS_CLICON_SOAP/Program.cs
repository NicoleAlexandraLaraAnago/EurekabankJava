using System;
using System.Linq;
using VIAJECITOS_CLICON_SOAP.ViajecitosReference;

namespace ViajecitosClientConsole
{
    class Program
    {
        static ViajecitosServiceClient service = new ViajecitosServiceClient();
        static UsuarioDTO usuarioActual = null;

        static void Main(string[] args)
        {
            bool salir = false;

            while (!salir)
            {
                if (usuarioActual == null)
                {
                    Console.WriteLine("\n--- VIAJECITOS ---");
                    Console.WriteLine("1. Iniciar sesión");
                    Console.WriteLine("2. Registrar usuario");
                    Console.WriteLine("3. Salir");
                    Console.Write("Seleccione una opción: ");
                    string op = Console.ReadLine();

                    switch (op)
                    {
                        case "1": Login(); break;
                        case "2": RegistrarUsuario(); break;
                        case "3": salir = true; break;
                        default: Console.WriteLine("Opción inválida"); break;
                    }
                }
                else
                {
                    if (usuarioActual.Rol == "admin")
                        MenuAdmin();
                    else
                        MenuCliente();
                }
            }

            Console.WriteLine("¡Hasta pronto!");
        }

        static void Login()
        {
            Console.Write("\nUsuario: ");
            string usuario = Console.ReadLine();
            Console.Write("Contraseña: ");
            string password = Console.ReadLine();

            var usuarioDTO = service.Login(usuario, password);
            if (usuarioDTO != null)
            {
                usuarioActual = usuarioDTO;
                Console.WriteLine($"Bienvenido {usuarioDTO.Nombre} ({usuarioDTO.Rol})");
            }
            else
            {
                Console.WriteLine("Credenciales incorrectas");
            }
        }

        static void RegistrarUsuario()
        {
            Console.Write("\nUsuario: ");
            string usuario = Console.ReadLine();
            Console.Write("Contraseña: ");
            string password = Console.ReadLine();
            Console.Write("Nombre: ");
            string nombre = Console.ReadLine();
            Console.Write("Apellido: ");
            string apellido = Console.ReadLine();

            string mensaje = service.RegistrarUsuario(usuario, password, nombre, apellido);
            Console.WriteLine(mensaje);
        }

        static void MenuAdmin()
        {
            Console.WriteLine("\n--- MENÚ ADMIN ---");
            Console.WriteLine("1. Ver todas las compras");
            Console.WriteLine("2. Cerrar sesión");
            Console.Write("Seleccione una opción: ");
            string op = Console.ReadLine();

            switch (op)
            {
                case "1": VerTodasLasCompras(); break;
                case "2": usuarioActual = null; break;
                default: Console.WriteLine("Opción inválida"); break;
            }
        }

        static void MenuCliente()
        {
            Console.WriteLine("\n--- MENÚ CLIENTE ---");
            Console.WriteLine("1. Buscar vuelo más caro");
            Console.WriteLine("2. Comprar vuelo");
            Console.WriteLine("3. Ver mis compras");
            Console.WriteLine("4. Ver mis facturas");
            Console.WriteLine("5. Cerrar sesión");
            Console.Write("Seleccione una opción: ");
            string op = Console.ReadLine();

            switch (op)
            {
                case "1": BuscarVueloMayor(); break;
                case "2": ComprarVuelo(); break;
                case "3": VerMisCompras(); break;
                case "4": VerFacturas(); break;
                case "5": usuarioActual = null; break;
                default: Console.WriteLine("Opción inválida"); break;
            }
        }

        static void VerMisCompras()
        {
            var compras = service.ObtenerComprasPorUsuario(usuarioActual.Id);

            Console.WriteLine("\n--- MIS COMPRAS ---");
            if (compras.Length == 0)
            {
                Console.WriteLine("No tienes compras registradas.");
                return;
            }

            foreach (var c in compras)
            {
                Console.WriteLine($"\nCompra #{c.IdCompra}");
                Console.WriteLine($"Ruta: {c.CiudadOrigen} → {c.CiudadDestino}");
                Console.WriteLine($"Valor: ${c.Valor}");
                Console.WriteLine($"Cantidad boletos: {c.Cantidad}"); // ← NUEVO
                Console.WriteLine($"Salida: {c.HoraSalida}");
                Console.WriteLine($"Fecha de compra: {c.FechaCompra}");
            }
        }


        static string SeleccionarCiudad(string tipo)
        {
            var ciudades = service.ListarCiudades();
            Console.WriteLine($"\n--- Seleccione ciudad de {tipo} ---");
            for (int i = 0; i < ciudades.Length; i++)
            {
                Console.WriteLine($"{i + 1}. {ciudades[i].NombreCiudad}");
            }

            Console.Write("Número: ");
            int index = int.Parse(Console.ReadLine()) - 1;
            return ciudades[index].Codigo;
        }

        static void BuscarVueloMayor()
        {
            string origen = SeleccionarCiudad("origen");
            string destino = SeleccionarCiudad("destino");

            Console.Write("Fecha del vuelo (yyyy-mm-dd): ");
            DateTime fecha = DateTime.Parse(Console.ReadLine());

            var vuelo = service.BuscarVueloMayorValor(origen, destino, fecha);

            if (vuelo != null)
            {
                Console.WriteLine("\n--- VUELO MÁS CARO ---");
                Console.WriteLine($"ID: {vuelo.Id}");
                Console.WriteLine($"Ruta: {vuelo.CiudadOrigen} → {vuelo.CiudadDestino}");
                Console.WriteLine($"Hora salida: {vuelo.HoraSalida}");
                Console.WriteLine($"Valor: ${vuelo.Valor}");

                Console.Write("\n¿Deseas comprar este vuelo ahora? (s/n): ");
                string opcion = Console.ReadLine().Trim().ToLower();

                if (opcion == "s")
                {
                    ComprarConFactura(vuelo.Id);
                }
                else
                {
                    Console.WriteLine("Vuelo no comprado.");
                }
            }
            else
            {
                Console.WriteLine("No se encontraron vuelos disponibles.");
            }
        }

        static void ComprarVuelo()
        {
            string origen = SeleccionarCiudad("origen");
            string destino = SeleccionarCiudad("destino");

            Console.Write("Fecha (yyyy-mm-dd): ");
            DateTime fecha = DateTime.Parse(Console.ReadLine());

            var vuelos = service.ListarVuelos(origen, destino, fecha);

            if (vuelos.Length == 0)
            {
                Console.WriteLine("No hay vuelos disponibles.");
                return;
            }

            Console.WriteLine("\n--- VUELOS DISPONIBLES ---");
            foreach (var v in vuelos)
            {
                Console.WriteLine($"ID: {v.Id} | Ruta: {v.CiudadOrigen} → {v.CiudadDestino} | Hora: {v.HoraSalida} | Valor: ${v.Valor} | Cupos: {v.CuposDisponibles}");
            }

            Console.Write("\nIngrese el ID del vuelo que desea comprar: ");
            if (!int.TryParse(Console.ReadLine(), out int idVuelo))
            {
                Console.WriteLine("ID inválido.");
                return;
            }

            var vueloSeleccionado = vuelos.FirstOrDefault(v => v.Id == idVuelo);
            if (vueloSeleccionado == null)
            {
                Console.WriteLine("No se encontró un vuelo con ese ID.");
                return;
            }

            ComprarConFactura(idVuelo);
        }

        static void ComprarConFactura(int idVuelo)
        {
            Console.Write("¿Cuántos boletos desea comprar? (1 a 5): ");
            if (!int.TryParse(Console.ReadLine(), out int cantidad) || cantidad < 1 || cantidad > 5)
            {
                Console.WriteLine("Cantidad inválida.");
                return;
            }

            try
            {
                var factura = service.RegistrarCompraConFactura(idVuelo, usuarioActual.Id, cantidad);
                MostrarFactura(factura, cantidad);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"❌ Error al generar la factura: {ex.Message}");
            }
        }

        static void MostrarFactura(FacturaDTO factura, int cantidad)
        {
            Console.WriteLine("\n--- FACTURA GENERADA ---");
            Console.WriteLine("Vendedor: Viajecitos S.A."); // ← línea nueva
            Console.WriteLine($"Factura N°: {factura.IdFactura}");
            Console.WriteLine($"Nombre: {factura.NombreComprador}");
            Console.WriteLine($"Ruta: {factura.CiudadOrigen} → {factura.CiudadDestino}");
            Console.WriteLine($"Boleto: {factura.NumeroBoleto}");
            Console.WriteLine($"Cantidad boletos: {cantidad}");
            Console.WriteLine($"Valor unitario: ${factura.ValorUnitario:N2}");
            Console.WriteLine($"Subtotal: ${factura.Subtotal:N2}");
            Console.WriteLine($"IVA (12%): ${factura.IVA:N2}");
            Console.WriteLine($"Total a pagar: ${factura.TotalAPagar:N2}");
            Console.WriteLine($"Fecha: {factura.FechaFactura}");
        }


        static void VerTodasLasCompras()
        {
            var compras = service.ObtenerTodasLasCompras();

            Console.WriteLine("\n--- TODAS LAS COMPRAS ---");
            foreach (var c in compras)
            {
                Console.WriteLine($"\nCompra #{c.IdCompra}");
                Console.WriteLine($"Usuario: {c.Usuario} ({c.Nombre} {c.Apellido})");
                Console.WriteLine($"Ruta: {c.CiudadOrigen} → {c.CiudadDestino}");
                Console.WriteLine($"Valor: ${c.Valor}");
                Console.WriteLine($"Salida: {c.HoraSalida}");
                Console.WriteLine($"Fecha de compra: {c.FechaCompra}");
                Console.WriteLine($"Cantidad boletos: {c.Cantidad}");
            }
        }
        static void VerFacturas()
        {
            var facturas = service.ObtenerFacturasPorUsuario(usuarioActual.Id);

            if (facturas.Length == 0)
            {
                Console.WriteLine("No tienes facturas registradas.");
                return;
            }

            Console.WriteLine("\n--- TUS FACTURAS ---");
            foreach (var f in facturas)
            {
                Console.WriteLine($"Factura #{f.IdFactura} | {f.FechaFactura.ToShortDateString()} | Total: ${f.TotalAPagar:N2}");
            }
        }
    }
}
