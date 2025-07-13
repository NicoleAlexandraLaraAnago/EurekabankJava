/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.restfull.cliente;

import ec.edu.restfull.servicio.Factura;
import ec.edu.restfull.servicio.Usuario;
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
@WebServlet(name = "HistorialFacturasServlet", urlPatterns = {"/HistorialFacturasServlet"})
public class HistorialFacturasServlet extends HttpServlet {

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Crear cliente del web service
            ViajecitosService_Service service = new ViajecitosService_Service();
            ViajecitosService port = service.getViajecitosServicePort();

            // Llamar al método real
            List<Factura> facturas = port.historialFacturasPorUsuario(usuario.getId());

            // Guardar en sesión y redirigir
            session.setAttribute("historialFacturas", facturas);
            response.sendRedirect("historialFacturas.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("historialFacturas", null);
            response.sendRedirect("historialFacturas.jsp");
        }
    }
}