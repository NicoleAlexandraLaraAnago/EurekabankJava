package ec.edu.monster.controlador;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        String url = "http://localhost:8080/WS_EUREKABANK_RESTFULL_JAVA/webresources/login";
        String params = "usuario=" + URLEncoder.encode(usuario, "UTF-8") +
                        "&contrasena=" + URLEncoder.encode(contrasena, "UTF-8");

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.getOutputStream().write(params.getBytes());

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String respuesta = br.readLine();
        br.close();

        if (respuesta != null && respuesta.contains("\"estado\":1")) {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            response.sendRedirect("menu.jsp");
        } else {
            request.setAttribute("mensaje", "Credenciales incorrectas");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
