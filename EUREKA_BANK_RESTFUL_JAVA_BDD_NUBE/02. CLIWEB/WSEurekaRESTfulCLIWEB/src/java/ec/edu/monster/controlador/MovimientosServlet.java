package ec.edu.monster.controlador;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MovimientosServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String cuenta = request.getParameter("cuenta");
        String url = "http://localhost:8080/WS_EUREKABANK_RESTFULL_JAVA/webresources/coreBancario/movimientos/" + cuenta;

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder resultado = new StringBuilder();
        String linea;
        while ((linea = br.readLine()) != null) {
            resultado.append(linea);
        }
        br.close();

        request.setAttribute("resultado", resultado.toString());
        request.getRequestDispatcher("movimientos.jsp").forward(request, response);
    }
}
