/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.restfull.cliente;

import ec.edu.restfull.servicio.Compra;
import ec.edu.restfull.servicio.DetalleCompra;
import ec.edu.restfull.servicio.Usuario;
import ec.edu.restfull.servicio.Vuelo;
import ec.edu.restfull.servicio.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ProcesarPagoServlet", urlPatterns = {"/ProcesarPagoServlet"})
public class ProcesarPagoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Vuelo> carrito = (List<Vuelo>) session.getAttribute("carrito");
        Map<Integer, Integer> cantidades = (Map<Integer, Integer>) session.getAttribute("asientosSeleccionados");

        int metodoPagoId = Integer.parseInt(request.getParameter("metodoPagoId"));
        String tipoPago = request.getParameter("tipoPago");
        String tipoAmortizacion = request.getParameter("tipoAmortizacion");
        int cuotas = request.getParameter("cuotas") != null ? Integer.parseInt(request.getParameter("cuotas")) : 0;
        double tasa = request.getParameter("tasa") != null ? Double.parseDouble(request.getParameter("tasa")) : 0;

        Compra compra = new Compra();
        compra.setUsuarioId(usuario.getId());
        compra.setMetodoPagoId(metodoPagoId);
        compra.setCodigoEmpleado("SISTEMA"); // opcional

        List<DetalleCompra> detalles = new ArrayList<>();
        double subtotal = 0;

        for (Vuelo vuelo : carrito) {
            int cantidad = cantidades.get(vuelo.getId());
            double subtotalVuelo = vuelo.getValor() * cantidad;
            subtotal += subtotalVuelo;

            DetalleCompra detalle = new DetalleCompra();
            detalle.setVueloId(vuelo.getId());
            detalle.setCantidadAsientos(cantidad);
            detalle.setSubtotalVuelo(subtotalVuelo);
            detalles.add(detalle);
        }

        compra.getDetalles().addAll(detalles);
        compra.setSubtotal(subtotal);

        // CONSUMO DEL WEB SERVICE
        ViajecitosService_Service serviceLocator = new ViajecitosService_Service();
        ViajecitosService servicio = serviceLocator.getViajecitosServicePort();

        Factura factura = servicio.comprarYFacturar(compra, tipoAmortizacion, cuotas, tasa);

        session.setAttribute("facturaGenerada", factura);
        session.removeAttribute("carrito");
        session.removeAttribute("asientosSeleccionados");

        response.sendRedirect("compraExitosa.jsp");

    }
}