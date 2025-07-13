/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.restfull.cliente;

import ec.edu.restfull.servicio.Amortizacion;
import ec.edu.restfull.servicio.Factura;
import ec.edu.restfull.servicio.ViajecitosService;
import ec.edu.restfull.servicio.ViajecitosService_Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sebastian
 */
@WebServlet(name = "VerFacturaServlet", urlPatterns = {"/VerFacturaServlet"})
public class VerFacturaServlet extends HttpServlet {
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int facturaId = Integer.parseInt(request.getParameter("facturaId"));

            ViajecitosService servicio = new ViajecitosService_Service().getViajecitosServicePort();
            Factura factura = servicio.obtenerFactura(facturaId);
            List<Amortizacion> amortizacion = servicio.obtenerAmortizacionPorFactura(facturaId);

            HttpSession session = request.getSession();
            session.setAttribute("facturaSeleccionada", factura);
            session.setAttribute("amortizacionFactura", amortizacion);

            response.sendRedirect("verFactura.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("historialFacturas.jsp?error=1");
        }
    }
}