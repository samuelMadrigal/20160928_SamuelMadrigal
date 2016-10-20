package es.albarregas.servlets;

import es.albarregas.beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "BeansSesion", urlPatterns = {"/beansSesion"})
public class BeansSesion extends HttpServlet {

    protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession sesion=request.getSession(true);
            
            pintarCabecera(out);
            
            if(request.getParameter("enviar")==null && request.getParameter("cerrar")==null) {  //Si venimos de primeras mostramos formulario
                out.println("<h1>Formulario de datos</h1>");
                    out.println("<form id=\"fomulario-datos\" action=\"beansSesion\" method=\"post\">");
                    out.println("<fieldset");
                    out.println("<legend><em><strong>Usuario 1</strong></em></legend>");
                    out.println("<p><label for=\"id1\">Id:</label><br/>");
                    out.println("<input type=\"text\" id=\"id1\" name=\"id1\" size=\"20\" maxlength=\"15\" value=\"\"/></p>");
                    out.println("<p><label for=\"nombre1\">Nombre:</label><br/>");
                    out.println("<input type=\"text\" id=\"nombre1\" name=\"nombre1\" size=\"20\" maxlength=\"15\" value=\"\"/></p>");
                    out.println("<p><label for=\"sueldo1\">Sueldo:</label><br/>");
                    out.println("<input type=\"text\" id=\"sueldo1\" name=\"sueldo1\" size=\"20\" maxlength=\"15\" value=\"\"/></p>");
                    out.println("</fieldset>");
                    out.println("<fieldset");
                    out.println("<legend><em><strong>Usuario 2</strong></em></legend>");
                    out.println("<p><label for=\"id2\">Id:</label><br/>");
                    out.println("<input type=\"text\" id=\"id2\" name=\"id2\" size=\"20\" maxlength=\"15\" value=\"\"/></p>");
                    out.println("<p><label for=\"nombre2\">Nombre:</label><br/>");
                    out.println("<input type=\"text\" id=\"nombre2\" name=\"nombre2\" size=\"20\" maxlength=\"15\" value=\"\"/></p>");
                    out.println("<p><label for=\"sueldo2\">Sueldo:</label><br/>");
                    out.println("<input type=\"text\" id=\"sueldo2\" name=\"sueldo2\" size=\"20\" maxlength=\"15\" value=\"\"/></p>");
                    out.println("</fieldset>");
                    out.println("<fieldset");
                    out.println("<legend><em><strong>Usuario 3</strong></em></legend>");
                    out.println("<p><label for=\"id3\">Id:</label><br/>");
                    out.println("<input type=\"text\" id=\"id3\" name=\"id3\" size=\"20\" maxlength=\"15\" value=\"\"/></p>");
                    out.println("<p><label for=\"nombre3\">Nombre:</label><br/>");
                    out.println("<input type=\"text\" id=\"nombre3\" name=\"nombre3\" size=\"20\" maxlength=\"15\" value=\"\"/></p>");
                    out.println("<p><label for=\"sueldo3\">Sueldo:</label><br/>");
                    out.println("<input type=\"text\" id=\"sueldo3\" name=\"sueldo3\" size=\"20\" maxlength=\"15\" value=\"\"/></p>");
                    out.println("</fieldset>");
                    out.println("<p><input type=\"submit\" id=\"enviar\" name=\"enviar\" value=\"Enviar\"/></p>");
                    out.println("</form>");
            }else if(request.getParameter("cerrar")==null) {  //Si venimos de enviar guardamos parametros en sesion
                out.println("<h2>Datos almacenados en sesi&oacute;n</h2>");
                
                Usuario user1=new Usuario();
                Usuario user2=new Usuario();
                Usuario user3=new Usuario();
                ArrayList<Usuario> usuarios=new ArrayList();
                
                user1.setId(Integer.parseInt(request.getParameter("id1")));
                user1.setNombre(request.getParameter("nombre1"));
                user1.setSueldo(Integer.parseInt(request.getParameter("sueldo1")));
                usuarios.add(user1);
                user2.setId(Integer.parseInt(request.getParameter("id2")));
                user2.setNombre(request.getParameter("nombre2"));
                user2.setSueldo(Integer.parseInt(request.getParameter("sueldo2")));
                usuarios.add(user2);
                user3.setId(Integer.parseInt(request.getParameter("id3")));
                user3.setNombre(request.getParameter("nombre3"));
                user3.setSueldo(Integer.parseInt(request.getParameter("sueldo3")));
                usuarios.add(user3);
                
                sesion.setAttribute("users", usuarios);
                
                out.println("<form id=\"fomulario-datos\" action=\"beansSesion\" method=\"post\">");
                out.println("<p><input type=\"submit\" id=\"cerrar\" name=\"cerrar\" value=\"Cerrar\"/></p>");
                out.println("</form>");
            }else{  //Si venimos de cerrar mostramos resultados
                sesion=request.getSession(true);
                
                out.println("<h3>Lista de usuarios:</h3>");
                out.println("</br>");
                
                for (Usuario u: (ArrayList<Usuario>)sesion.getAttribute("users")) {
                    out.println("</p>Id: "+u.getId()+"</p>");
                    out.println("</p>Nombre: "+u.getNombre()+"</p>");
                    out.println("</p>Sueldo: "+u.getSueldo()+"</p>");
                    out.println("</br>");
                }
                
                sesion.invalidate();
            }
            
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
        out.println("<title>Beans con Sesion</title>");
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
