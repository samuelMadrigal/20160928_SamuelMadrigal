package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samu
 */
@WebServlet(name = "Cabeceras", urlPatterns = {"/cabe"})
public class Cabeceras extends HttpServlet {

    /**
    * 
    * método de proceso de respuesta de un servlet
    * 
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Enumeration<String> cabeceras=request.getHeaderNames();
            
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<title>Servlet Cabeceras</title>");       
            out.println("<link rel=\"stylesheet\" href=\"estilos/estilo.css\" type=\"text/css\"/>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Cabeceras de la petici&oacute;n</h2>");
            out.println("<h4>Cabecera - Valor</h4>");
            
            while(cabeceras.hasMoreElements()){
                String elemento=cabeceras.nextElement();
                String valor=request.getHeader(elemento);
                out.println("<p id=\"sec\"><span id=\"neg\">"+elemento+" - </span>"+valor+"</p>");
            }
            
            out.println("<p><a href='"+request.getContextPath()+"'>Volver atr&aacute;s</a></p>");
            out.println("</body>");
            out.println("</html>");
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
