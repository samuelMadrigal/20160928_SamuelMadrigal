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
    Enumeration<String> parametros;
    private final String[] preferencias = {"Deportes", "Viajes", "Tiendas", "Juegos"};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
            if(request.getParameter("enviar")!=null){  //Si viene de enviar se muestran parametros en formulario oculto
              
                pintarCabecera(out);
                
                out.println("<form id=\"fomulario-oculto\" action=\"formularioCorrecto\" method=\"post\">");
                
                pintarParametrosOcultos(out, request);
                
                out.println("<p><input type=\"submit\" id=\"aceptar\" name=\"confirmar\" value=\"Confirmar\"/>");
                out.println("<input type=\"submit\" id=\"borrar\" name=\"cambiar\" value=\"Cambiar datos\"/></p>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
                
            }else{  //Si NO viene de enviar
                
                if(request.getParameter("confirmar")!=null){  //Si viene de confirmar se procede al registro
                    
                    procesoCorrecto(out, request);
                  
                }else{  //Si viene de primeras, cambiar o limpiar se muestra formulario
                    
                    if(request.getParameter("cambiar")!=null){ //Si viene de cambiar se devuelven los parametros
                        user=request.getParameter("nombre");
                        clave=request.getParameter("password");
                    }else{
                        user="";
                        clave="";
                    }
                    
                    pintarCabecera(out);

                    pintarFormulario(out, request);
                    
                }/*if confirmar*/ 
            }/*if enviar*/
        }/*try*/
    }/*doPost*/

    
    
    /**
     * método para pintar cabecera
     * 
     * @param out 
     */
    public void pintarCabecera(PrintWriter out) {
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
     * @param request 
     */
    public void procesoCorrecto(PrintWriter out, HttpServletRequest request) {
        
            pintarCabecera(out);
        
            out.println("<h1>Los datos se enviaron a nuestar base de datos</h1>");
            
            pintarParametros(out, request);
            
            out.println("</p><p><a href=\"./formularioCorrecto\">Volver al formulario</a></p>");
            out.println("<p><a id=\"nar\" href=\".\">Volver al menu principal</a></p>");
            out.println("</body>");
            out.println("</html>");
    }/*procesoCorrecto*/
    
    /**
     * método para pintar parametros ocultos
     * 
     * @param out 
     * @param request 
     */
    public void pintarParametrosOcultos(PrintWriter out, HttpServletRequest request) {
            parametros=request.getParameterNames(); //Se obtienen los parametros de la peticion
            int num=0; //Variable para el control de aficiones
            while(parametros.hasMoreElements()){
                    String elemento=parametros.nextElement();
                    String[] valores=request.getParameterValues(elemento);
                    if(!"enviar".equals(elemento)){ //Para no mostrar los botones
                        if(!elemento.startsWith("afi")){
                            out.println("<p id=\"sec\"><span id=\"neg\">"+elemento+" - </span>");
                        }else if(num==0){
                            out.println("<p id=\"sec\"><span id=\"neg\">Preferencias - </span>");
                            num++;
                        }
                        for (int i = 0; i < valores.length; i++) {
                            out.println(" "+valores[i]);
                            out.println("<input type=\"hidden\" name=\"" + elemento + "\" value=\"" + valores[i] + "\" />"); //Creacion de campos ocultos con los parametro
                        }/*for*/
                        if(num==0){ //Cerrar parrafos hasta que llega la primera aficion
                            out.println("</p>");
                        }
                    }/*if*/
                }/*while*/
    }/*pintarParametrosOcultos*/
    
    /**
     * método para pintar parametros
     * 
     * @param out 
     * @param request 
     */
    public void pintarParametros(PrintWriter out, HttpServletRequest request) {
            parametros=request.getParameterNames(); //Se obtienen los parametros de la peticion
            int num=0; //Variable para el control de aficiones
            while(parametros.hasMoreElements()){
                    String elemento=parametros.nextElement();
                    String[] valores=request.getParameterValues(elemento);
                    if(!"confirmar".equals(elemento) && !"enviar".equals(elemento) && !"cambiar".equals(elemento)){ //Para no mostrar los botones
                        if(!elemento.startsWith("afi")){
                            out.println("<p id=\"sec\"><span id=\"neg\">"+elemento+" - </span>");
                        }else if(num==0){
                            out.println("<p id=\"sec\"><span id=\"neg\">Preferencias - </span>");
                            num++;
                        }
                        for (int i = 0; i < valores.length; i++) {
                            out.println(" "+valores[i]);
                        }/*for*/
                        if(num==0){ //Cerrar parrafos hasta que llega la primera aficion
                            out.println("</p>");
                        }
                    }/*if*/
                }/*while*/
    }/*pintarParametros*/
    
    /**
     * método para pintar formulario
     * 
     * @param out 
     * @param request 
     */
    public void pintarFormulario(PrintWriter out, HttpServletRequest request) {
        out.println("<h1>Formulario de datos</h1>");
                    out.println("<form id=\"fomulario-datos\" action=\"formularioCorrecto\" method=\"post\">" );
                    out.println("<fieldset> ");
                    out.println("<legend><em><strong>Datos</strong></em></legend>");
                    out.println("<p><label for=\"nombre\">Usuario:</label><br />");
                    out.println("<input type=\"text\" id=\"nombre\" name=\"nombre\" size=\"20\" maxlength=\"15\" value=\""+user+"\" /></p>");
                    out.println("<p><label for=\"clave\">Pasword:</label><br />");
                    out.println("<input type=\"password\" id=\"password\" name=\"password\" size=\"20\" maxlength=\"15\" value=\""+clave+"\" /></p>");
                    out.println("<p><label for=\"sexo\">Sexo:</label>&nbsp;&nbsp;&nbsp;&nbsp;");
                    //Para devolver el sexo
                    //Si viene de primeras, o igual a hombre, o viene de limpiar
                    if(request.getParameter("sexo")==null || "Hombre".equals(request.getParameter("sexo")) || request.getParameter("borrar")!=null){
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
                    if(request.getParameter("cambiar")==null){  //Para devolver el checkbox de aficiones vacio si NO viene de cambiar
                        out.println("<td><input type=\"checkbox\" name=\"aficiones0\" value=\"Deportes\" />Deportes&nbsp;&nbsp;&nbsp;</td>");
                        out.println("<td><input type=\"checkbox\" name=\"aficiones1\" value=\"Viajes\" />Viajes&nbsp;&nbsp;&nbsp;</td>");
                        out.println("<td><input type=\"checkbox\" name=\"aficiones2\" value=\"Tiendas\" />Tiendas&nbsp;&nbsp;&nbsp;</td>");
                        out.println("<td><input type=\"checkbox\" name=\"aficiones3\" value=\"Juegos\" />Juegos</td>");
                    }else{  //Para devolver el checkbox de aficiones cargado si viene de cambiar
                        StringBuilder cadena = new StringBuilder();
                        String valor;
                            for (int i = 0; i < 4; i++) {
                                String aficion = "aficiones" + i;
                                valor = (request.getParameter(aficion) != null) ? "checked=\"checked\"" : "";
                                cadena.append("<input type=\"checkbox\" name=\"aficiones").append(i).append("\" " + "value=\"").append(preferencias[i]).append("\" ").append(valor).append(" />").append(preferencias[i]).append("&nbsp;&nbsp;&nbsp;");
                            }
                        out.println(cadena.toString());
                    }
                    out.println("</tr>");
                    out.println("</table>");
                    out.println("</fieldset>");
                    out.println("<p><input type=\"submit\" id=\"aceptar\" name=\"enviar\" value=\"Enviar\"/>");
                    out.println("<input type=\"submit\" id=\"borrar\" name=\"borrar\" value=\"Limpiar\"/></p>");
                    out.println("</form>");
                    out.println("<a href=\".\">Volver al menu principal</a>");
                    out.println("</body>");
                    out.println("</html>");
    }/*pintarFormulario*/
    
    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }/*getServletInfo*/
    
}//FormularioCorrecto servlet
