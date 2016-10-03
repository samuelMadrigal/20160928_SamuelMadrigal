/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(name = "FormularioC", urlPatterns = {"/formComp"})
public class FormularioCompleto extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<title>Formulario Completo</title>");       
            out.println("<link rel=\"stylesheet\" href=\"estilos/estilo.css\" type=\"text/css\"/>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Formulario completo</h1>");
            out.println("<h4>Ha accedido desde el m&eacute;todo doGet()</h4>");
            out.println("<p><a href='"+request.getContextPath()+"'>Volver atr&aacute;s</a></p>");
            out.println("</body>");
            out.println("</html>");
            
        }
        
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
            
            Enumeration<String> parametros=request.getParameterNames();
            
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<title>Formulario</title>");       
            out.println("<link rel=\"stylesheet\" href=\"estilos/estilo.css\" type=\"text/css\"/>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Formulario completo</h1>");
            
            out.println("<p id=\"sec\"><span id=\"neg\">Usuario:  </span>");
            String user=request.getParameter("nombre");
            out.println(user+"</p>");
            out.println("<br>");
            out.println("<p id=\"sec\"><span id=\"neg\">Password:  </span>");         
            String clave=request.getParameter("password");
            out.println(clave+"</p>");
            out.println("<br>");
            out.println("<p id=\"sec\"><span id=\"neg\">Edad:  </span>");         
            String edad=request.getParameter("edad");
            out.println(edad+"</p>");
            out.println("<br>");
            out.println("<br>");
            
            out.println("<p id=\"sec\"><span id=\"neg\">Estado civil:  </span>");         
            String estado=request.getParameter("estado");
            out.println(estado+"</p>");
            out.println("<br>");
            out.println("<p id=\"sec\"><span id=\"neg\">Aficiones:  </span></p><ul>");         
            String[] aficiones=request.getParameterValues("aficiones");
            if(aficiones!=null){
              for(int i=0;i<aficiones.length;i++) {
                  out.println("<li id=\"li\">" + aficiones[i]);
              } 
              out.println("</ul>");
            }else{
                out.println("<p id=\"li\">No tiene aficiones</p>");
                out.println("</ul>");
            }
            out.println("<br>");
            out.println("<p id=\"sec\"><span id=\"neg\">Comentario:  </span>");
            String comen=request.getParameter("comentario");
            out.println(comen+"</p>");
            out.println("<br>");
            out.println("<br>");
            
            out.println("<p id=\"sec\"><span id=\"neg\">Dispone de internet:  </span>");
            String internet=request.getParameter("internet");
            out.println(internet+"</p>");
            out.println("<br>");
            out.println("<p id=\"sec\"><span id=\"neg\">Sistema operativo:  </span>");
            String sistema=request.getParameter("sistema");
            out.println(sistema+"</p>");
            out.println("<br>");
            
            out.println("<br>");
            out.println("<br>");
            
            out.println("<p><a id=\"nar\" href=\"" + request.getContextPath() + "\">Volver al menu</a>&nbsp;&nbsp;&nbsp;&nbsp;");
            out.println("<a href=\"HTML/formularioCompleto.html\">Volver atr&aacute;s</a></p>");
            out.println("</body>");
            out.println("</html>");
            
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
