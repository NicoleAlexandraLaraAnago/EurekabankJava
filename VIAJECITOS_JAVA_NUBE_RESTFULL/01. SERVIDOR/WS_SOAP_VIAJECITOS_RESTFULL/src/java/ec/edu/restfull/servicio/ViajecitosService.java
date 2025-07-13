package ec.edu.restfull.servicio;

import ec.edu.restfull.modelo.Amortizacion;
import ec.edu.restfull.modelo.HistorialBusqueda;
import ec.edu.restfull.modelo.Compra;
import ec.edu.restfull.modelo.Usuario;
import ec.edu.restfull.modelo.Factura;
import ec.edu.restfull.modelo.Vuelo;
import ec.edu.restfull.controlador.UsuarioControlador;
import ec.edu.restfull.controlador.FacturaControlador;
import ec.edu.restfull.controlador.CompraControlador;
import ec.edu.restfull.controlador.VueloControlador;
import ec.edu.restfull.db.ConexionDB;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@WebService(serviceName = "ViajecitosService")
public class ViajecitosService {

    private final UsuarioControlador usuarioControlador = new UsuarioControlador();
    private final VueloControlador vueloControlador = new VueloControlador();
    private final CompraControlador compraControlador = new CompraControlador();
    private final FacturaControlador facturaControlador = new FacturaControlador();
    private int usuarioAutenticadoId = -1;

    @WebMethod(operationName = "login")
    public Usuario login(@WebParam(name = "usuario") String usuario,
                         @WebParam(name = "clave") String clave) {
        return usuarioControlador.login(usuario, clave);
    }


    @WebMethod(operationName = "buscarVuelos")
    public List<Vuelo> buscarVuelos(
            @WebParam(name = "usuarioId") int usuarioId,
            @WebParam(name = "origen") String origen,
            @WebParam(name = "destino") String destino,
            @WebParam(name = "fecha") Date fecha) {

        List<Vuelo> vuelos = vueloControlador.buscarVuelos(origen, destino, fecha);

        // Insertar historial si hay resultados
        if (!vuelos.isEmpty()) {
            vueloControlador.guardarHistorialBusqueda(usuarioId, origen, destino, fecha);
        }

        return vuelos;
    }


    @WebMethod(operationName = "comprarYFacturar")
    public Factura comprarYFacturar(
            @WebParam(name = "compra") Compra compra,
            @WebParam(name = "tipoAmortizacion") String tipoAmortizacion,
            @WebParam(name = "cuotas") int cuotas,
            @WebParam(name = "tasaAnual") double tasaAnual
    ) {
        // 1. Registrar compra
        int idCompra = compraControlador.registrarCompra(compra);

        // 2. Generar factura
        int idFactura = facturaControlador.generarFactura(idCompra);

        // 3. Obtener todos los datos completos de la factura
        Factura factura = facturaControlador.obtenerFacturaCompleta(idFactura);

        // 4. Generar amortización si aplica
        if ("Crédito".equalsIgnoreCase(factura.getMetodoPago().getTipoPago())) {
            if ("Francesa".equalsIgnoreCase(tipoAmortizacion)) {
                facturaControlador.generarAmortizacionFrancesa(idFactura, cuotas, tasaAnual);
            } else if ("Alemana".equalsIgnoreCase(tipoAmortizacion)) {
                facturaControlador.generarAmortizacionAlemana(idFactura, cuotas, tasaAnual);
            }
        }

        // 5. Retornar la factura completa con toda la información
        return factura;
    }


    @WebMethod(operationName = "obtenerFactura")
    public Factura obtenerFactura(@WebParam(name = "facturaId") int facturaId) {
        return facturaControlador.obtenerFacturaCompleta(facturaId);
    }

    @WebMethod(operationName = "obtenerAmortizacionPorFactura")
    public List<Amortizacion> obtenerAmortizacionPorFactura(@WebParam(name = "facturaId") int facturaId) {
        return facturaControlador.obtenerAmortizacion(facturaId);
    }

    @WebMethod(operationName = "historialFacturasPorUsuario")
    public List<Factura> historialFacturasPorUsuario(@WebParam(name = "usuarioId") int usuarioId) {
        return facturaControlador.listarFacturasPorUsuario(usuarioId);
    }

    @WebMethod(operationName = "verHistorialBusquedas")
    public List<HistorialBusqueda> verHistorialBusquedas(@WebParam(name = "usuarioId") int usuarioId) {
        return vueloControlador.obtenerHistorial(usuarioId);
    }
    
    @WebMethod(operationName = "buscarVueloPorId")
    public Vuelo buscarVueloPorId(@WebParam(name = "vueloId") int vueloId) {
        return vueloControlador.buscarVueloPorId(vueloId);
    }


    
}
