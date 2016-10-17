package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samu
 */
@WebServlet(name = "ContadorCookies", urlPatterns = {"/contadorCookies"})
public class ContadorCookies extends HttpServlet {

    protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Cookie cookie= null;

            Cookie[] cookies= request.getCookies();
            
            if(cookies!=null){  //Si existen cookies buscamos "contador"
                for(Cookie cookie1: cookies){
                    if(cookie1.getName().equals("contador")){
                        cookie = cookie1;
                        break;
                    }
                }
            }
            
            if(cookie==null) {  //Si "contador" no existe la creamos a 0
                cookie= new Cookie("contador", "0");
            }else if(request.getParameter("limpiar")!=null) {  //Si la borramos la ponemos a 0
                cookie.setValue("0");
            }
            
            int cont= Integer.parseInt(cookie.getValue());
            cookie.setValue(Integer.toString(cont + 1));
            cookie.setMaxAge(60*60*2);  //Dos horas en segundos
            response.addCookie(cookie);

            pintarCabecera(out);
            
            String valor= cookie.getValue();  //Si la cookie vale 1 damos la bienvenida
            if("1".equals(valor)){
                out.println("<h2>Bienvenido por primera vez</h2>");
            }else{
                out.println("<h2>Contador de visitas: " + valor + "</h2>");
            }
            
            out.println("<form action=\"contadorCookies\" method=\"post\">");
            out.println("<input type=\"submit\" id=\"limpiar\" name=\"limpiar\" value=\"Eliminar cookie\"/>");
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
