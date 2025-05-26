package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.http.HttpSession;

public final class Menu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String usuario = (String) sesion.getAttribute("usuario");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Menú de Opciones</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"styles.css\">\n");
      out.write("        <style>\n");
      out.write("            .menu-container {\n");
      out.write("                width: 400px;\n");
      out.write("                margin: 100px auto;\n");
      out.write("                text-align: center;\n");
      out.write("                padding: 20px;\n");
      out.write("                border: 1px solid #ccc;\n");
      out.write("                box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);\n");
      out.write("            }\n");
      out.write("            .menu-container h2 {\n");
      out.write("                margin-bottom: 20px;\n");
      out.write("            }\n");
      out.write("            .menu-container button {\n");
      out.write("                width: 80%;\n");
      out.write("                padding: 15px;\n");
      out.write("                margin: 10px 0;\n");
      out.write("                border: none;\n");
      out.write("                background-color: #333;\n");
      out.write("                color: white;\n");
      out.write("                font-size: 16px;\n");
      out.write("                cursor: pointer;\n");
      out.write("                border-radius: 4px;\n");
      out.write("            }\n");
      out.write("            .menu-container button:hover {\n");
      out.write("                background-color: #555;\n");
      out.write("            }\n");
      out.write("            .logo-container {\n");
      out.write("                margin-top: 20px;\n");
      out.write("            }\n");
      out.write("            .logo-container img {\n");
      out.write("                width: 150px;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"menu-container\">\n");
      out.write("            <h2>Menú de Opciones</h2>\n");
      out.write("            <form action=\"realizarDeposito.jsp\" method=\"get\">\n");
      out.write("                <button type=\"submit\">Realizar Depósito</button>\n");
      out.write("            </form>\n");
      out.write("            <form action=\"consultarMovimientos.jsp\" method=\"get\">\n");
      out.write("                <button type=\"submit\">Consultar Movimientos</button>\n");
      out.write("            </form>\n");
      out.write("            <form action=\"login.jsp\" method=\"post\">\n");
      out.write("                <button type=\"submit\">Salir</button>\n");
      out.write("            </form>\n");
      out.write("            <div class=\"logo-container\">\n");
      out.write("                <img src=\"resources/download.jpg\" alt=\"ESPE Logo\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
