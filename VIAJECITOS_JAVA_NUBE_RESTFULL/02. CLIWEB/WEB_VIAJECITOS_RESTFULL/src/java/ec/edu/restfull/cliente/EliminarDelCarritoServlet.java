/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.restfull.cliente;

import ec.edu.restfull.servicio.Vuelo;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EliminarDelCarritoServlet", urlPatterns = {"/EliminarDelCarritoServlet"})
public class EliminarDelCarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int vueloId = Integer.parseInt(request.getParameter("vueloId"));
        HttpSession session = request.getSession();

        List<Vuelo> carrito = (List<Vuelo>) session.getAttribute("carrito");
        Map<Integer, Integer> asientosSeleccionados = (Map<Integer, Integer>) session.getAttribute("asientosSeleccionados");

        if (carrito != null) {
            // Eliminar el vuelo del carrito
            carrito.removeIf(v -> v.getId() == vueloId);
        }

        if (asientosSeleccionados != null) {
            // Eliminar los asientos seleccionados del carrito
            asientosSeleccionados.remove(vueloId);
        }

        // Actualizar el contador en la sesión
        int totalVuelos = carrito.size();
        session.setAttribute("totalVuelos", totalVuelos);

        // Redirigir a la página del carrito
        response.sendRedirect("carrito.jsp");
    }
}
