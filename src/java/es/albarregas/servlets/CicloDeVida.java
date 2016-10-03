package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samu
 */
@WebServlet(name = "CicloDeVida", urlPatterns = {"/ciclo"})
public class CicloDeVida extends HttpServlet {
 
    /**
    *
    * método de inicialización de un servlet
    */
    @Override
    public void init(){
        System.out.println("INIT()");
    }

    /**
    *
    * método de ejecución de un servlet
    * 
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
    */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("SERVICE()");
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<title>Servlet CicloDeVida</title>");       
            out.println("<link rel=\"stylesheet\" href=\"estilos/estilo.css\" type=\"text/css\"/>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet <span>CicloDeVida</span> desde el contexto de la aplicaci&oacute;n <span>" + request.getContextPath() + "</span></h1>");
            out.println("<h4>Ejecut&aacute;ndose en el m&eacute;todo service(), <span>en la consola de Tomcat de Netbeans</span></h4>");
            out.println("<p><a href='"+request.getContextPath()+"'>Volver atr&aacute;s</a></p>");
            out.println("</body>");
            out.println("</html>");
            
        }
    }

    /**
    *
    * método de destrucción de un servlet
    */
    @Override
    public void destroy(){
        System.out.println("DESTROY()");
    }
}
