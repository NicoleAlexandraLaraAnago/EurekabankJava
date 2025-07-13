/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.restfull.cliente;

import ec.edu.restfull.servicio.ViajecitosService;
import ec.edu.restfull.servicio.ViajecitosService_Service;
import ec.edu.restfull.servicio.Vuelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Sebastian
 */
@WebServlet(name = "BuscarVuelosServlet", urlPatterns = {"/BuscarVuelosServlet"})
public class BuscarVuelosServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.removeAttribute("resultadosBusqueda"); // ✅ Limpia resultados anteriores

        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        String fechaStr = request.getParameter("fecha");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaStr);

            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            XMLGregorianCalendar xmlFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

            ViajecitosService servicio = new ViajecitosService_Service().getViajecitosServicePort();
            List<Vuelo> vuelos = servicio.buscarVuelos(usuarioId, origen, destino, xmlFecha);

            session.setAttribute("resultadosBusqueda", vuelos); // ✅ Guardar en sesión
        } catch (Exception e) {
            session.setAttribute("error", "Error al buscar vuelos: " + e.getMessage());
        }

        response.sendRedirect("buscarVuelos.jsp"); // ✅ Redirige en vez de reenviar
    }
}