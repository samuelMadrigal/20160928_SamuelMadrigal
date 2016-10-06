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
@WebServlet(name = "FormularioCorrecto", urlPatterns = {"/formularioCorrecto"})
public class FormularioCorrecto extends HttpServlet {

    String user="";
    String clave="";


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
            if(request.getParameter("enviar")!=null){
              
                bodyCabecera(out);
                
                out.println("<form id=\"fomulario-oculto\" action=\"formularioCorrecto\">");
                
                Enumeration<String> parametros=request.getParameterNames();
                
                while(parametros.hasMoreElements()){
                    String elemento=parametros.nextElement();
                    String[] valores=request.getParameterValues(elemento);
                    if(!"enviar".equals(elemento)){
                        out.println("<p id=\"sec\"><span id=\"neg\">"+elemento+" - </span>");
                        for (int i = 0; i < valores.length; i++) {
                            out.println(" "+valores[i]);
                            out.println("<input type=\"hidden\" name=\"" + elemento + "\" value=\"" + valores[i] + "\" />");
                        }/*for*/
                        out.println("</p>");
                    }/*if*/
                    
                }/*while*/
                
                user=request.getParameter("nombre");
                clave=request.getParameter("password");
                
                out.println("<input type=\"hidden\" name=\"nombre\" value=\"" + user + "\" />");
                out.println("<input type=\"hidden\" name=\"password\" value=\"" + clave + "\" />");
                out.println("<p><input type=\"submit\" name=\"confirmar\" value=\"Confirmar\" />");
                out.println("<p><input type=\"submit\" name=\"cambiar\" value=\"Cambiar datos\" />");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
                
            }else{
                
                if(request.getParameter("confirmar")!=null){
                    
                    procesoCorrecto(out);
                  
                }else{
                    
                    bodyCabecera(out);

                    out.println("<h1>Formulario de datos</h1>");
                    out.println("<form id=\"fomulario-datos\" action=\"formularioCorrecto\" method=\"post\">" );
                    out.println("<fieldset> ");
                    out.println("<legend><em><strong>Datos</strong></em></legend>");
                    out.println("<p><label for=\"nombre\">Usuario:</label><br />");
                    out.println("<input type=\"text\" id=\"nombre\" name=\"nombre\" size=\"20\" maxlength=\"15\" value=\""+user+"\" /></p>");
                    out.println("<p><label for=\"clave\">Pasword:</label><br />");
                    out.println("<input type=\"password\" id=\"password\" name=\"password\" size=\"20\" maxlength=\"15\" value=\""+clave+"\" /></p>");
                    out.println("<p><label for=\"sexo\">Sexo:</label>&nbsp;&nbsp;&nbsp;&nbsp;");
                    if(request.getParameter("sexo")==null || "Hombre".equals(request.getParameter("sexo"))){
                        out.println("<input type=\"radio\" id=\"sexo\" name=\"sexo\" value=\"Hombre\" checked=\"checked\" />Hombre&nbsp;&nbsp;&nbsp;&nbsp;");
                        out.println("<input type=\"radio\" name=\"sexo\" value=\"Mujer\" />Mujer&nbsp;&nbsp;&nbsp;&nbsp;");
                    }else{
                        out.println("<input type=\"radio\" id=\"sexo\" name=\"sexo\" value=\"Hombre\"/>Hombre&nbsp;&nbsp;&nbsp;&nbsp;");
                        out.println("<input type=\"radio\" name=\"sexo\" value=\"Mujer\" checked=\"checked\"/>Mujer&nbsp;&nbsp;&nbsp;&nbsp;");
                    }
                    out.println("</p>");
                    out.println("<p><label id=\"aficiones\">Preferencias:</label></p>");
                    out.println("<table summary=\"\" >");
                    out.println("<tr>");
                    if(request.getParameter("aficiones")==null){
                        out.println("<td><input type=\"checkbox\" name=\"aficiones\" value=\"Deportes\" />Deportes&nbsp;&nbsp;&nbsp;</td>");
                        out.println("<td><input type=\"checkbox\" name=\"aficiones\" value=\"Viajes\" />Viajes&nbsp;&nbsp;&nbsp;</td>");
                        out.println("<td><input type=\"checkbox\" name=\"aficiones\" value=\"Tiendas\" />Tiendas&nbsp;&nbsp;&nbsp;</td>");
                        out.println("<td><input type=\"checkbox\" name=\"aficiones\" value=\"Juegos\" />Juegos</td>");
                    }else{
                        //aficiones desde cambiar
                        out.println("Proceso de devolver aficiones");
                    }
                    out.println("</tr>");
                    out.println("</table>");
                    out.println("</fieldset>");
                    out.println("<p><input type=\"submit\" name=\"enviar\" value=\"Enviar\" />");
                    out.println("<p><input type=\"reset\" name=\"borrar\" value=\"Limpiar\" />");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                }/*if confirmar*/ 
            }/*if enviar*/
        }/*try*/
    }/*doPost*/

    
    /**
     * método para pintar cabecera
     * 
     * @param out 
     */
    public void bodyCabecera(PrintWriter out) {
        out.println("<html lang=\"es\">");
        out.println("<head>");
        out.println("<title>Formulario con regreso</title>");
        out.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=ISO-8859-1\" />");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos/estilo.css\"/>");
        out.println("</head>");
        out.println("<body>");
    }/*bodyCabecera*/
    
    
    /**
     * método para formulario correcto
     * 
     * @param out 
     */
    public void procesoCorrecto(PrintWriter out) {
        
            bodyCabecera(out);
        
            out.println("<h1>Los datos se enviaron a nuestar base de datos</h1>");
            out.println("</body>");
            out.println("</html>");
    }/*procesoCorrecto*/
    
    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
