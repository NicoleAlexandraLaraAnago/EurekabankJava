using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using CONDOR_Servidor.DTO;

namespace CONDOR_Servidor
{
    public class CondorService : ICondorService
    {
        // Cadena de conexión desde web.config
        private string connStr = ConfigurationManager.ConnectionStrings["ConexionCondorDB"].ConnectionString;

        // Registro de un nuevo usuario (rol fijo: cliente)
        public string RegistrarUsuario(string usuario, string password, string nombre, string apellido)
        {
            try
            {
                string hash = BCrypt.Net.BCrypt.HashPassword(password);
                using (SqlConnection con = new SqlConnection(connStr))
                {
                    string query = "INSERT INTO Usuarios (Usuario, Password, Nombre, Apellido, Rol) VALUES (@usuario, @password, @nombre, @apellido, 'cliente')";
                    SqlCommand cmd = new SqlCommand(query, con);
                    cmd.Parameters.AddWithValue("@usuario", usuario);
                    cmd.Parameters.AddWithValue("@password", hash);
                    cmd.Parameters.AddWithValue("@nombre", nombre);
                    cmd.Parameters.AddWithValue("@apellido", apellido);
                    con.Open();
                    cmd.ExecuteNonQuery();
                    return "Registro exitoso";
                }
            }
            catch (Exception ex)
            {
                return "Error: " + ex.Message;
            }
        }

        // Login de usuario
        public UsuarioDTO Login(string usuario, string password)
        {
            using (SqlConnection con = new SqlConnection(connStr))
            {
                string query = "SELECT * FROM Usuarios WHERE Usuario = @usuario";
                SqlCommand cmd = new SqlCommand(query, con);
                cmd.Parameters.AddWithValue("@usuario", usuario);
                con.Open();
                var reader = cmd.ExecuteReader();
                if (reader.Read())
                {
                    string hash = reader["Password"].ToString();
                    if (BCrypt.Net.BCrypt.Verify(password, hash))
                    {
                        return new UsuarioDTO
                        {
                            Id = (int)reader["Id"],
                            Usuario = reader["Usuario"].ToString(),
                            Nombre = reader["Nombre"].ToString(),
                            Apellido = reader["Apellido"].ToString(),
                            Rol = reader["Rol"].ToString()
                        };
                    }
                }
                return null;
            }
        }

        // Obtener usuario por ID (por ejemplo, luego del login)
        public UsuarioDTO ObtenerUsuarioPorId(int idUsuario)
        {
            using (SqlConnection con = new SqlConnection(connStr))
            {
                string query = "SELECT * FROM Usuarios WHERE Id = @id";
                SqlCommand cmd = new SqlCommand(query, con);
                cmd.Parameters.AddWithValue("@id", idUsuario);
                con.Open();
                var reader = cmd.ExecuteReader();
                if (reader.Read())
                {
                    return new UsuarioDTO
                    {
                        Id = idUsuario,
                        Usuario = reader["Usuario"].ToString(),
                        Nombre = reader["Nombre"].ToString(),
                        Apellido = reader["Apellido"].ToString(),
                        Rol = reader["Rol"].ToString()
                    };
                }
                return null;
            }
        }

        // Buscar el vuelo más caro para una ruta y fecha
        public VueloDTO BuscarVueloMayorValor(string origen, string destino, DateTime fecha)
        {
            using (SqlConnection con = new SqlConnection(connStr))
            {
                string query = @"
             SELECT TOP 1 * FROM Vuelos
             WHERE Ciudad_Origen = @origen AND Ciudad_Destino = @destino
             AND CAST(Hora_Salida AS DATE) = @fecha
             ORDER BY Valor DESC";

                SqlCommand cmd = new SqlCommand(query, con);
                cmd.Parameters.AddWithValue("@origen", origen);
                cmd.Parameters.AddWithValue("@destino", destino);
                cmd.Parameters.AddWithValue("@fecha", fecha.Date);
                con.Open();
                var reader = cmd.ExecuteReader();
                if (reader.Read())
                {
                    return new VueloDTO
                    {
                        Id = (int)reader["Id"],
                        CiudadOrigen = reader["Ciudad_Origen"].ToString(),
                        CiudadDestino = reader["Ciudad_Destino"].ToString(),
                        Valor = (decimal)reader["Valor"],
                        HoraSalida = (DateTime)reader["Hora_Salida"]
                    };
                }
                return null;
            }
        }

        // Registrar una compra de vuelo por un usuario
        public string RegistrarCompra(int idVuelo, int idUsuario)
        {
            try
            {
                using (SqlConnection con = new SqlConnection(connStr))
                {
                    con.Open();

                    // Verificar que el vuelo exista
                    string checkQuery = "SELECT COUNT(*) FROM Vuelos WHERE Id = @idVuelo";
                    SqlCommand checkCmd = new SqlCommand(checkQuery, con);
                    checkCmd.Parameters.AddWithValue("@idVuelo", idVuelo);
                    int vueloExiste = (int)checkCmd.ExecuteScalar();

                    if (vueloExiste == 0)
                    {
                        return "Error: El vuelo con ID " + idVuelo + " no existe.";
                    }

                    string query = "INSERT INTO Compras (IdVuelo, IdUsuario, FechaCompra) VALUES (@idVuelo, @idUsuario, GETDATE())";
                    SqlCommand cmd = new SqlCommand(query, con);
                    cmd.Parameters.AddWithValue("@idVuelo", idVuelo);
                    cmd.Parameters.AddWithValue("@idUsuario", idUsuario);
                    cmd.ExecuteNonQuery();
                    return "Compra registrada correctamente";
                }
            }
            catch (Exception ex)
            {
                return "Error al registrar compra: " + ex.Message;
            }
        }

        public List<CompraDetalleDTO> ObtenerTodasLasCompras()
        {
            var lista = new List<CompraDetalleDTO>();

            using (SqlConnection con = new SqlConnection(connStr))
            {
                string query = @"
        SELECT c.Id AS IdCompra, u.Usuario, u.Nombre, u.Apellido,
               v.Ciudad_Origen, v.Ciudad_Destino, v.Valor, v.Hora_Salida,
               c.FechaCompra, c.Cantidad
        FROM Compras c
        JOIN Usuarios u ON c.IdUsuario = u.Id
        JOIN Vuelos v ON c.IdVuelo = v.Id";

                SqlCommand cmd = new SqlCommand(query, con);
                con.Open();
                var reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    lista.Add(new CompraDetalleDTO
                    {
                        IdCompra = (int)reader["IdCompra"],
                        Usuario = reader["Usuario"].ToString(),
                        Nombre = reader["Nombre"].ToString(),
                        Apellido = reader["Apellido"].ToString(),
                        CiudadOrigen = reader["Ciudad_Origen"].ToString(),
                        CiudadDestino = reader["Ciudad_Destino"].ToString(),
                        Valor = (decimal)reader["Valor"],
                        HoraSalida = (DateTime)reader["Hora_Salida"],
                        FechaCompra = (DateTime)reader["FechaCompra"],
                        Cantidad = (int)reader["Cantidad"]
                    });
                }
            }

            return lista;
        }


        public List<VueloDTO> ListarVuelos(string origen, string destino, DateTime fecha)
        {
            List<VueloDTO> lista = new List<VueloDTO>();

            using (SqlConnection con = new SqlConnection(connStr))
            {
                string query = @"
            SELECT * FROM Vuelos
            WHERE Ciudad_Origen = @origen AND Ciudad_Destino = @destino
            AND CAST(Hora_Salida AS DATE) = @fecha";

                SqlCommand cmd = new SqlCommand(query, con);
                cmd.Parameters.AddWithValue("@origen", origen);
                cmd.Parameters.AddWithValue("@destino", destino);
                cmd.Parameters.AddWithValue("@fecha", fecha.Date);
                con.Open();

                var reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    lista.Add(new VueloDTO
                    {
                        Id = (int)reader["Id"],
                        CiudadOrigen = reader["Ciudad_Origen"].ToString(),
                        CiudadDestino = reader["Ciudad_Destino"].ToString(),
                        Valor = (decimal)reader["Valor"],
                        HoraSalida = (DateTime)reader["Hora_Salida"],
                        CuposDisponibles = (int)reader["CuposDisponibles"] // ✅ Agregado
                    });
                }
            }

            return lista;
        }


        public List<CiudadDTO> ListarCiudades()
        {
            var lista = new List<CiudadDTO>();
            using (SqlConnection con = new SqlConnection(connStr))
            {
                string query = "SELECT Codigo, NombreCiudad FROM Ciudades ORDER BY NombreCiudad";
                SqlCommand cmd = new SqlCommand(query, con);
                con.Open();
                var reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    lista.Add(new CiudadDTO
                    {
                        Codigo = reader["Codigo"].ToString(),
                        NombreCiudad = reader["NombreCiudad"].ToString()
                    });
                }
            }
            return lista;
        }

        public FacturaDTO RegistrarCompraConFactura(int idVuelo, int idUsuario, int cantidad)
        {
            if (cantidad < 1 || cantidad > 5)
                return null;

            using (SqlConnection con = new SqlConnection(connStr))
            {
                con.Open();
                SqlTransaction tx = con.BeginTransaction();

                try
                {
                    // 1. Obtener vuelo y usuario
                    SqlCommand getVuelo = new SqlCommand(@"
                SELECT v.Ciudad_Origen, v.Ciudad_Destino, v.Valor, v.Hora_Salida, v.CuposDisponibles,
                       u.Nombre + ' ' + u.Apellido AS NombreCompleto
                FROM Vuelos v, Usuarios u
                WHERE v.Id = @idVuelo AND u.Id = @idUsuario", con, tx);
                    getVuelo.Parameters.AddWithValue("@idVuelo", idVuelo);
                    getVuelo.Parameters.AddWithValue("@idUsuario", idUsuario);
                    var reader = getVuelo.ExecuteReader();

                    if (!reader.Read())
                    {
                        reader.Close();
                        tx.Rollback();
                        return null;
                    }

                    string ciudadOrigen = reader["Ciudad_Origen"].ToString();
                    string ciudadDestino = reader["Ciudad_Destino"].ToString();
                    decimal valorUnitario = (decimal)reader["Valor"];
                    int cuposDisponibles = (int)reader["CuposDisponibles"];
                    string nombreComprador = reader["NombreCompleto"].ToString();
                    reader.Close();

                    if (cuposDisponibles < cantidad)
                    {
                        tx.Rollback();
                        return null;
                    }

                    // 2. Insertar compra (AGREGADO: columna Cantidad)
                    SqlCommand insertCompra = new SqlCommand(@"
    INSERT INTO Compras (IdVuelo, IdUsuario, FechaCompra, Cantidad)
    OUTPUT INSERTED.Id
    VALUES (@idVuelo, @idUsuario, GETDATE(), @cantidad)", con, tx);

                    insertCompra.Parameters.AddWithValue("@idVuelo", idVuelo);
                    insertCompra.Parameters.AddWithValue("@idUsuario", idUsuario);
                    insertCompra.Parameters.AddWithValue("@cantidad", cantidad); // NUEVO
                    int idCompra = (int)insertCompra.ExecuteScalar();

                    // 3. Actualizar cupos
                    SqlCommand updateCupos = new SqlCommand(@"
                UPDATE Vuelos SET CuposDisponibles = CuposDisponibles - @cantidad
                WHERE Id = @idVuelo", con, tx);
                    updateCupos.Parameters.AddWithValue("@cantidad", cantidad);
                    updateCupos.Parameters.AddWithValue("@idVuelo", idVuelo);
                    updateCupos.ExecuteNonQuery();

                    // 4. Insertar factura
                    decimal subtotal = valorUnitario * cantidad;
                    decimal iva = Math.Round(subtotal * 0.12m, 2);
                    decimal total = subtotal + iva;
                    string numeroBoleto = "BOL" + DateTime.Now.Ticks.ToString().Substring(8);

                    SqlCommand insertFactura = new SqlCommand(@"
                INSERT INTO Facturas (IdCompra, FechaFactura, NombreComprador,
                    CiudadOrigen, CiudadDestino, NumeroBoleto,
                    ValorUnitario, Subtotal, IVA, TotalAPagar)
                VALUES (@idCompra, GETDATE(), @nombre, @origen, @destino, @boleto,
                        @unitario, @subtotal, @iva, @total)", con, tx);

                    insertFactura.Parameters.AddWithValue("@idCompra", idCompra);
                    insertFactura.Parameters.AddWithValue("@nombre", nombreComprador);
                    insertFactura.Parameters.AddWithValue("@origen", ciudadOrigen);
                    insertFactura.Parameters.AddWithValue("@destino", ciudadDestino);
                    insertFactura.Parameters.AddWithValue("@boleto", numeroBoleto);
                    insertFactura.Parameters.AddWithValue("@unitario", valorUnitario);
                    insertFactura.Parameters.AddWithValue("@subtotal", subtotal);
                    insertFactura.Parameters.AddWithValue("@iva", iva);
                    insertFactura.Parameters.AddWithValue("@total", total);
                    insertFactura.ExecuteNonQuery();

                    var factura = new FacturaDTO
                    {
                        IdCompra = idCompra,
                        FechaFactura = DateTime.Now,
                        NombreComprador = nombreComprador,
                        CiudadOrigen = ciudadOrigen,
                        CiudadDestino = ciudadDestino,
                        NumeroBoleto = numeroBoleto,
                        ValorUnitario = valorUnitario,
                        Subtotal = subtotal,
                        IVA = iva,
                        TotalAPagar = total
                    };

                    tx.Commit();
                    return factura;
                }
                catch (Exception)
                {
                    tx.Rollback();
                    return null;
                }
            }
        }


        public List<CompraDetalleDTO> ObtenerComprasPorUsuario(int idUsuario)
        {
            var lista = new List<CompraDetalleDTO>();

            using (SqlConnection con = new SqlConnection(connStr))
            {
                string query = @"
        SELECT c.Id AS IdCompra, u.Usuario, u.Nombre, u.Apellido,
               v.Ciudad_Origen, v.Ciudad_Destino, v.Valor, v.Hora_Salida,
               c.FechaCompra, c.Cantidad
        FROM Compras c
        JOIN Usuarios u ON c.IdUsuario = u.Id
        JOIN Vuelos v ON c.IdVuelo = v.Id
        WHERE c.IdUsuario = @idUsuario";

                SqlCommand cmd = new SqlCommand(query, con);
                cmd.Parameters.AddWithValue("@idUsuario", idUsuario);
                con.Open();
                var reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    lista.Add(new CompraDetalleDTO
                    {
                        IdCompra = (int)reader["IdCompra"],
                        Usuario = reader["Usuario"].ToString(),
                        Nombre = reader["Nombre"].ToString(),
                        Apellido = reader["Apellido"].ToString(),
                        CiudadOrigen = reader["Ciudad_Origen"].ToString(),
                        CiudadDestino = reader["Ciudad_Destino"].ToString(),
                        Valor = (decimal)reader["Valor"],
                        HoraSalida = (DateTime)reader["Hora_Salida"],
                        FechaCompra = (DateTime)reader["FechaCompra"],
                        Cantidad = (int)reader["Cantidad"]
                    });
                }
            }

            return lista;
        }
        public List<FacturaDTO> ObtenerFacturasPorUsuario(int idUsuario)
        {
            var lista = new List<FacturaDTO>();

            using (SqlConnection con = new SqlConnection(connStr))
            {
                string query = @"
    SELECT f.*
    FROM Facturas f
    INNER JOIN Compras c ON f.IdCompra = c.Id
    WHERE c.IdUsuario = @idUsuario";

                SqlCommand cmd = new SqlCommand(query, con);
                cmd.Parameters.AddWithValue("@idUsuario", idUsuario);

                con.Open();
                var reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    lista.Add(new FacturaDTO
                    {
                        IdFactura = (int)reader["IdFactura"],
                        IdCompra = (int)reader["IdCompra"],
                        FechaFactura = (DateTime)reader["FechaFactura"],
                        NombreComprador = reader["NombreComprador"].ToString(),
                        CiudadOrigen = reader["CiudadOrigen"].ToString(),
                        CiudadDestino = reader["CiudadDestino"].ToString(),
                        NumeroBoleto = reader["NumeroBoleto"].ToString(),
                        ValorUnitario = (decimal)reader["ValorUnitario"],
                        Subtotal = (decimal)reader["Subtotal"],
                        IVA = (decimal)reader["IVA"],
                        TotalAPagar = (decimal)reader["TotalAPagar"]
                    });
                }
            }

            return lista;
        }

    }
}
