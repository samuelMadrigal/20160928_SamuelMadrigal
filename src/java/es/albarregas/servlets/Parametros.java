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
@WebServlet(name = "Parametros", urlPatterns = {"/param"})
public class Parametros extends HttpServlet {

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
            
            Enumeration<String> parametros=request.getParameterNames();
            
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<title>Servlet Parametros</title>");       
            out.println("<link rel=\"stylesheet\" href=\"estilos/estilo.css\" type=\"text/css\"/>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Par&aacute;metros en la petici&oacute;n</h2>");
            out.println("<h4>Par&aacute;metro - Valor</h4>");
            
            while(parametros.hasMoreElements()){
                String elemento=parametros.nextElement();
                String valor=request.getParameter(elemento);
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
