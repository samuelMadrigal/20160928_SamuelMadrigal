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
@WebServlet(name = "FormularioError", urlPatterns = {"/formularioError"})
public class FormularioError extends HttpServlet {

    private final String[] preferencias = {"Deportes", "Lectura", "Cine", "Viajes"};
    private Enumeration<String> parametros;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }/*doGet*/
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        bodyCabecera(out);
        
        // Comprobamos si vienen errores
            boolean error = false;
            parametros = request.getParameterNames();
            while (parametros.hasMoreElements()) {
                String nombre = parametros.nextElement();
                if (request.getParameter("nombre").equals("") || request.getParameter("user").equals("") || request.getParameter("password").equals("") || !fechaCorrecta(request.getParameter("dia"), request.getParameter("mes"), request.getParameter("anio"))){
                    error = true;
                }
            }

            
            if (!error) {
                
                bodyCabecera(out);
                out.println("<h1>Los datos se enviaron a nuestar base de datos</h1>");
                out.println("<a href=\"./\">Volver al menu principal</a>");
                out.println("</body>");
                out.println("</html>");
                
            } else {
                
                bodyCabecera(out);
                bodyCuerpoError(request, response, out);
                
            }
        }
        
    }/*doPost*/

    
    
    
    
    /**
     * método para pintar cabecera
     * 
     * @param out 
     */
    private void bodyCabecera(PrintWriter out) {
        out.println("<html lang=\"es\">");
        out.println("<head>");
        out.println("<title>Formulario con errores</title>");
        out.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=ISO-8859-1\" />");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos/estilo.css\"/>");
        out.println("</head>");
        out.println("<body>");
    }/*bodyCabecera*/
    /**
     * método para pintar cuerpo de formulario con errores
     *  
    */
    private void bodyCuerpoError(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
            throws ServletException, IOException {
        
        String valor = null;
        
        out.println("<div id=\"contForm\">");
        out.println("<h1 id=\"titulo\">FORMULARIO ERRORES</h1>");
        out.println("<form action=\"formularioError\">");
        
        out.println("<fieldset>");
        out.println("<legend><em><strong>Datos Personales</strong></em></legend>");
        valor=(request.getParameter("nombre") != null) ? request.getParameter("nombre") : "";
        out.println("<p>"
                +   "<label for=\"nombre\">*Nombre:</label>&nbsp;&nbsp;&nbsp;&nbsp;"
                +   "<input type=\"text\" id=\"nombre\" name=\"nombre\" size=\"20\" maxlength=\"15\" value=\""+valor+"\"/>");
        if("".equals(request.getParameter("nombre"))){
            out.println("&nbsp;&nbsp;&nbsp;&nbsp;<span id=\"error\">Nombre obligatorio</span>");
        }
        out.println("</p>");
        valor=(request.getParameter("apellidos") != null) ? request.getParameter("apellidos") : "";
        out.println("<p>"
                +   "<label for=\"apellidos\">Apellidos:</label>&nbsp;&nbsp;&nbsp;&nbsp;"
                +   "<input type=\"text\" id=\"apellidos\" name=\"apellidos\" size=\"20\" maxlength=\"20\" value=\""+valor+"\"/>"
                + "</p>");
        out.println("<p>"
                +   "<label for=\"sexo\">Sexo:</label>&nbsp;&nbsp;&nbsp;&nbsp;");
                    if(request.getParameter("sexo")==null || "hombre".equals(request.getParameter("sexo"))){
                        out.println("<input type=\"radio\" id=\"sexo\" name=\"sexo\" value=\"hombre\" checked=\"checked\"/>Hombre&nbsp;&nbsp;&nbsp;&nbsp;");
                        out.println("<input type=\"radio\" name=\"sexo\" value=\"mujer\"/>Mujer");
                    }else{
                        out.println("<input type=\"radio\" id=\"sexo\" name=\"sexo\" value=\"hombre\"/>Hombre&nbsp;&nbsp;&nbsp;&nbsp;");
                        out.println("<input type=\"radio\" name=\"sexo\" value=\"mujer\" checked=\"checked\"/>Mujer");
                    }
        out.println("</p>");
        out.println("<p>"
                +   "<label>Fecha de nacimiento:</label>&nbsp;&nbsp;&nbsp;&nbsp;");
        out.println("<select id=\"dia\" name=\"dia\">");
        int dia = Integer.parseInt(request.getParameter("dia"));
        for (int i = 1; i < 32; i++) {
            valor = (dia == i) ? "selected=\"selected\"" : "";
            out.println("<option value=\"" + i + "\"" + valor + ">" + i + "</option>");
        }
        out.println("</select>&nbsp;&nbsp;/&nbsp;&nbsp;");
        out.println("<select id=\"mes\" name=\"mes\">");
        int mes = Integer.parseInt(request.getParameter("mes"));
        for (int i = 1; i < 13; i++) {
            valor = (mes == i) ? "selected=\"selected\"" : "";
            out.println("<option value=\"" + i + "\"" + valor + ">" + i + "</option>");
        }
        out.println("</select>&nbsp;&nbsp;/&nbsp;&nbsp;");
        out.println("<select id=\"anio\" name=\"anio\">");
        int anio = Integer.parseInt(request.getParameter("anio"));
        for (int i = 1988; i < 2008; i++) {
            valor = (anio == i) ? "selected=\"selected\"" : "";
            out.println("<option value=\"" + i + "\"" + valor + ">" + i + "</option>");
        }
        out.println("</select>");
        if(!fechaCorrecta(request.getParameter("dia"), request.getParameter("mes"), request.getParameter("anio"))){
            out.println("&nbsp;&nbsp;&nbsp;&nbsp;<span id=\"error\">Fecha incorrecta</span>");
        }
        out.println("</p>");
        out.println("</fieldset>");
        
        out.println("<fieldset>");
        out.println("<legend><em><strong>Datos de Acceso</strong></em></legend>");
        valor=(request.getParameter("user") != null) ? request.getParameter("user") : "";
        out.println("<p>"
                +   "<label for=\"user\">*Usuario:</label>&nbsp;&nbsp;&nbsp;&nbsp;"
                +   "<input type=\"text\" id=\"user\" name=\"user\" size=\"20\" maxlength=\"15\" value=\""+valor+"\" />");
        if("".equals(request.getParameter("user"))){
            out.println("&nbsp;&nbsp;&nbsp;&nbsp;<span id=\"error\">Usuario obligatorio</span>");
        }
        out.println("</p>");
        valor=(request.getParameter("password") != null) ? request.getParameter("password") : "";
        out.println("<p>"
                +   "<label for=\"password\">*Contraseña:</label>&nbsp;&nbsp;&nbsp;&nbsp;"
                +   "<input type=\"password\" id=\"password\" name=\"password\" size=\"20\" maxlength=\"20\" value=\""+valor+"\" />");
        if("".equals(request.getParameter("password"))){
            out.println("&nbsp;&nbsp;&nbsp;&nbsp;<span id=\"error\">Contraseña obligatoria</span>");
        }
        out.println("</p>");
        out.println("</fieldset>");
        
        out.println("<fieldset>");
        out.println("<legend><em><strong>Informaci&oacute;n general</strong></em></legend>");
        
        out.println("<p>"
                +   "<label>Preferencias:</label>&nbsp;&nbsp;&nbsp;&nbsp;"
                + "</p>");
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String aficion = "aficiones" + i;
            valor = (request.getParameter(aficion) != null) ? "checked=\"checked\"" : "";
            cadena.append("<input type=\"checkbox\" name=\"aficiones").append(i).append("\" " + "value=\"").append(preferencias[i]).append("\" ").append(valor).append(" />").append(preferencias[i]).append("&nbsp;&nbsp;&nbsp;");
        }
        out.println(cadena.toString());
        out.println("</fieldset>");
        
        out.println("<input type=\"submit\" id=\"aceptar\" name=\"aceptar\" value=\"Enviar\"/>");
        out.println("<input type=\"button\" id=\"borrar\" name=\"borrar\" value=\"Limpiar\" onClick=\"location.href='HTML/formularioError.html';\"/>");
        out.println("</form>");
        out.println("<a href=\".\">Volver al menu principal</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    /**
     * método para fecha correcta
     *
    */
    private boolean fechaCorrecta(String d, String m, String a) {

        boolean correcto = true;
        int dia = Integer.parseInt(d);
        int mes = Integer.parseInt(m);
        int anio = Integer.parseInt(a);
        int mesDias = 0;
        int bisiesto = 0;
        int[] numDiasMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (anioBisiesto(anio) && mes == 2) {
            bisiesto = 1;
        }
        mesDias = numDiasMes[mes - 1] + bisiesto;
        if (dia > mesDias) {
            correcto = false;
        }

        return correcto;
        
    }

    /**
     * método para año bisiesto
     *
    */
    private boolean anioBisiesto(int anio) {
        
        boolean anioBisiesto = false;
        if ((anio % 100 != 0 || anio % 400 == 0) && anio % 4 == 0) {
            anioBisiesto = true;
        }
        return anioBisiesto;
        
    }
    
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
    
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }/*getServletInfo*/

}
