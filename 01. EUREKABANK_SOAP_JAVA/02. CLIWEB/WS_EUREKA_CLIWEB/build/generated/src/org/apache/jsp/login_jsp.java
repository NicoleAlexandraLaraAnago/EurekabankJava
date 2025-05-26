package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import ec.edu.monster.controlador.EurekaController;
import javax.servlet.http.HttpSession;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
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
      out.write("\n");

    // Check if login form is submitted
    String usuario = request.getParameter("usuario");
    String contrasena = request.getParameter("contrasena");
    if (usuario != null && contrasena != null) {
        EurekaController controlador = new EurekaController();
        boolean autenticado = controlador.autenticarUsuario(usuario, contrasena);
        
        if (autenticado) {
            // Iniciar sesión y redirigir al dashboard o página principal
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            response.sendRedirect("Menu.jsp");
            return;
        } else {
            // Almacenar el error como un atributo de solicitud
            request.setAttribute("error", "Usuario o contraseña incorrectos.");
        }
    }

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Inicio de Sesión</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"styles.css\">\n");
      out.write("        <style>\n");
      out.write("            .login-container {\n");
      out.write("                width: 600px;\n");
      out.write("                margin: 100px auto;\n");
      out.write("                padding: 20px;\n");
      out.write("                border: 1px solid #ccc;\n");
      out.write("                box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);\n");
      out.write("                display: flex;\n");
      out.write("                align-items: center;\n");
      out.write("                justify-content: space-between;\n");
      out.write("            }\n");
      out.write("            .form-container {\n");
      out.write("                width: 60%;\n");
      out.write("            }\n");
      out.write("            .form-group {\n");
      out.write("                margin-bottom: 15px;\n");
      out.write("                text-align: left;\n");
      out.write("            }\n");
      out.write("            label {\n");
      out.write("                display: block;\n");
      out.write("                margin-bottom: 5px;\n");
      out.write("                font-weight: bold;\n");
      out.write("            }\n");
      out.write("            input[type=\"text\"], input[type=\"password\"] {\n");
      out.write("                width: calc(90% - 20px);\n");
      out.write("                padding: 10px;\n");
      out.write("                margin: 5px 0;\n");
      out.write("                border: 1px solid #ccc;\n");
      out.write("                border-radius: 4px;\n");
      out.write("            }\n");
      out.write("            button[type=\"submit\"] {\n");
      out.write("                width: calc(90% - 20px);\n");
      out.write("                background-color: #3364ff;\n");
      out.write("                color: white;\n");
      out.write("                padding: 10px;\n");
      out.write("                border: none;\n");
      out.write("                border-radius: 4px;\n");
      out.write("                cursor: pointer;\n");
      out.write("            }\n");
      out.write("            button[type=\"submit\"]:hover {\n");
      out.write("                background-color: #3364ff;\n");
      out.write("            }\n");
      out.write("            .error-message {\n");
      out.write("                color: red;\n");
      out.write("                font-weight: bold;\n");
      out.write("            }\n");
      out.write("            .logo-container {\n");
      out.write("                width: 35%;\n");
      out.write("                text-align: center;\n");
      out.write("            }\n");
      out.write("            .logo-container img {\n");
      out.write("                width: 150px;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"login-container\">\n");
      out.write("            <div class=\"form-container\">\n");
      out.write("                <h2>Inicio de Sesión</h2>\n");
      out.write("                <form action=\"login.jsp\" method=\"post\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"usuario\">Usuario:</label>\n");
      out.write("                        <input type=\"text\" name=\"usuario\" id=\"usuario\" required>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"contrasena\">Contraseña:</label>\n");
      out.write("                        <input type=\"password\" name=\"contrasena\" id=\"contrasena\" required>\n");
      out.write("                    </div>\n");
      out.write("                    <button type=\"submit\">Ingresar</button>\n");
      out.write("                    ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"logo-container\">\n");
      out.write("                <img src=\"resources/download.jpg\" alt=\"ESPE Logo\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty requestScope.error}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                        <p class=\"error-message\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.error}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</p>\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }
}
