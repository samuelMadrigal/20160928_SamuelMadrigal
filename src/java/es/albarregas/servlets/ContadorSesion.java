package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Samu
 */
@WebServlet(name = "ContadorSesion", urlPatterns = {"/contadorSesion"})
public class ContadorSesion extends HttpServlet {

    protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession sesion=request.getSession(true);
            int cont=1;
            String valor="1";
            
            pintarCabecera(out);
            
            if(request.getParameter("limpiar")!=null) {  //Si venimos de limpiar eliminamos sesion
                sesion.invalidate();
                out.println("<p>Sesi&oacute;n finalizada, recargar para crearla de nuevo</p>");
            }else if(sesion.getAttribute("contador")==null) {  //Si "contador" no existe la creamos a 1
                sesion.setAttribute("contador",cont);
                calcularValor(out, valor);
            }else{  //Si "contador" si existe la aumentamos en 1
                cont=Integer.parseInt(String.valueOf(sesion.getAttribute("contador")));
                cont++;
                sesion.setAttribute("contador", cont);
                valor=String.valueOf(sesion.getAttribute("contador"));
                calcularValor(out, valor);
            }
            
            out.println("<form action=\"contadorSesion\" method=\"post\">");
            out.println("<input type=\"checkbox\" id=\"limpiar\" name=\"limpiar\"/>Eliminar sesi&oacute;n");
            out.println("<input type=\"submit\" id=\"recargar\" name=\"recargar\" value=\"Recargar\"/>");
            out.println("</form>");
            out.println("<a href=\"" + request.getContextPath() + "\">Volver al menu principal</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * método para pintar cabecera
     * 
     * @param out 
     */
    private void pintarCabecera(PrintWriter out) {
        out.println("<html lang=\"es\">");
        out.println("<head>");
        out.println("<title>Contador de visitas con cookies</title>");
        out.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=ISO-8859-1\" />");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos/estilo.css\"/>");
        out.println("</head>");
        out.println("<body>");
    }/*pintarCabecera*/
    
    /**
     * método para calcular valor
     * 
     * @param out 
     * @param valor 
     */
    private void calcularValor(PrintWriter out, String valor) {
        if("1".equals(valor)){  //Si "contador" vale 1 damos la bienvenida
            out.println("<h2>Bienvenido por primera vez</h2>");
        }else{
            out.println("<h2>Contador de visitas: " + valor + "</h2>");
        }
    }/*pintarCabecera*/
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesarPeticion(request, response);
    }/*doGet*/

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesarPeticion(request, response);
    }/*doPost*/

    @Override
    public String getServletInfo() {
        return "Short description";
    }/*getServletInfo*/

}
