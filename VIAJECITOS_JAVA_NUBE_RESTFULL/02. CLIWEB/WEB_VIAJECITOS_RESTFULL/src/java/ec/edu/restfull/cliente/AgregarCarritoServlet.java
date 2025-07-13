/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.restfull.cliente;

import ec.edu.restfull.servicio.Vuelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "AgregarCarritoServlet", urlPatterns = {"/AgregarCarritoServlet"})
public class AgregarCarritoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<Vuelo> carrito = (List<Vuelo>) session.getAttribute("carrito");
        Map<Integer, Integer> asientosSeleccionados = (Map<Integer, Integer>) session.getAttribute("asientosSeleccionados");

        if (carrito == null) carrito = new ArrayList<>();
        if (asientosSeleccionados == null) asientosSeleccionados = new HashMap<>();

        Vuelo vuelo = new Vuelo();
        vuelo.setId(Integer.parseInt(request.getParameter("vueloId")));
        vuelo.setCiudadOrigen(request.getParameter("origen"));
        vuelo.setCiudadDestino(request.getParameter("destino"));

        try {
            javax.xml.datatype.DatatypeFactory factory = javax.xml.datatype.DatatypeFactory.newInstance();
            javax.xml.datatype.XMLGregorianCalendar fechaXML = factory.newXMLGregorianCalendar(request.getParameter("fecha"));
            vuelo.setFechaSalida(fechaXML);
        } catch (javax.xml.datatype.DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        vuelo.setHoraSalida(request.getParameter("hora"));
        vuelo.setValor(Double.parseDouble(request.getParameter("valor")));
        vuelo.setAsientosDisponibles(Integer.parseInt(request.getParameter("asientos")));

        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        // ✅ Validación de fecha y hora del vuelo contra el momento actual
        try {
            // Convertir XMLGregorianCalendar a LocalDate
            LocalDate fechaVuelo = vuelo.getFechaSalida().toGregorianCalendar().toZonedDateTime().toLocalDate();
            LocalTime horaVuelo = LocalTime.parse(vuelo.getHoraSalida());
            LocalDateTime fechaHoraVuelo = LocalDateTime.of(fechaVuelo, horaVuelo);

            LocalDateTime ahora = LocalDateTime.now();

            if (fechaHoraVuelo.isBefore(ahora)) {
                // No se permite agregar el vuelo
                request.setAttribute("error", "No se puede agregar un vuelo con fecha u hora pasada.");
                request.getRequestDispatcher("buscarVuelos.jsp").forward(request, response);
                return;
            }

            // ✅ Validación para no agregar vuelos a la misma hora
            for (Vuelo v : carrito) {
                // Comparamos las fechas y horas de cada vuelo en el carrito
                LocalDateTime fechaHoraExistente = LocalDateTime.of(
                    v.getFechaSalida().toGregorianCalendar().toZonedDateTime().toLocalDate(),
                    LocalTime.parse(v.getHoraSalida())
                );
                
                if (fechaHoraVuelo.equals(fechaHoraExistente)) {
                    // Si la fecha y hora coinciden, no permitimos agregarlo
                    request.setAttribute("error", "Ya tienes un vuelo a esta misma hora en el carrito.");
                    request.getRequestDispatcher("buscarVuelos.jsp").forward(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al validar la fecha y hora del vuelo.");
            request.getRequestDispatcher("buscarVuelos.jsp").forward(request, response);
            return;
        }

        // Agrega el vuelo al carrito solo si la fecha y hora son válidas
        carrito.add(vuelo);
        asientosSeleccionados.put(vuelo.getId(), cantidad);

        session.setAttribute("carrito", carrito);
        session.setAttribute("asientosSeleccionados", asientosSeleccionados);
        
        
        // Agregar el número de vuelos en el carrito a la sesión
        int totalVuelos = carrito.size();
        session.setAttribute("totalVuelos", totalVuelos);

        response.sendRedirect("buscarVuelos.jsp");
    }
}
