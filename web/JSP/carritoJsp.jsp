<%-- 
    Document   : carritoJsp
    Created on : 17-oct-2016, 18:18:07
    Author     : Samu
--%>

<%@page import="java.lang.Exception"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.albarregas.beans.Libro"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito con sesión</title>
        <link rel="stylesheet" href="../estilos/estilo2.css" type="text/css"/>
    </head>
    <body>
        <%
        HttpSession sesion=request.getSession();  // Obtenemos sesion
        ArrayList<Libro> libros;
        
        if(sesion.getAttribute("libros")==null){  // Obtenemos o creamos array libros
            libros=new ArrayList();
        }else{
            libros=(ArrayList<Libro>)sesion.getAttribute("libros");
        }/*ifelse(sesion.getAttribute("libros")==null)*/
            
        if (request.getParameter("finalizar")!=null){  // Si venimos de "finalizar" mostramos carrito
            if(libros.isEmpty()){  // Si carrito vacio
            %>
                <h1>Carrito vac&iacute;o</h1>
            <%
            }else{  // Si carrito NO vacio
            %>   
                <img id="peq" src="../imagenes/carro.jpg" alt="carrito"/>
                <h1>Gracias por su compra</h1>
                <table>
                    <caption>PEDIDO</caption>
                    <tr><th>Libro</th><th>Cantidad</th></tr>
                    <%
                    for(Libro l:libros){
                    %>
                        <tr><td> <%=l.getTitulo()%> </td><td> <%=l.getCantidad()%></td></tr>
                    <%
                    }/*for(Libro l:libros)*/   
                    sesion.invalidate();  // Al finalizar compra invalidamos sesion
                    %>  
                </table>
            <%
            }/*ifelse(libros.isEmpty()*/  
            %>
            <p><a href="../HTML/carritoJsp.html">Volver a la tienda</a></p>
            <%    
        }else{  // Si NO venimos de "finalizar" mostramos formulario
        %>
            <img id="peq" src="../imagenes/carro.jpg" alt="carrito"/>
            <h1>Bienvenido a nuestra tienda</h1>
            <form action="carritoJsp.jsp" method="post">
            <%
            if(request.getParameter("aniadir")!=null){  // Si venimos de "aniadir" comprobamos y/o mostramos registro o error
                int cantidad=0;
                
                if(request.getParameter("select")==null){  // Si "select" vacio
                %>  
                    <h3 id="error">Elige un libro</h3>
                <%   
                }else{  // Si "select" NO vacio comprobamos error en la cantidad
                    try{
                        cantidad=Integer.parseInt(request.getParameter("cantidad"));
                        if(cantidad==0){throw new Exception();}  //  Si cantidad es 0 salimos del try

                        String unidad="";
                        if(cantidad>1){
                            unidad="unidades";
                        }else{
                            unidad="unidad";
                        }/*ifelse(cantidad>1)*/
                        %>  
                        <h3>Has añadido <%=request.getParameter("cantidad")%> <%=unidad%> del libro <%=request.getParameter("select")%></h3>
                        <%
                        Libro nuevo=new Libro();  //  Se crea libro
                        nuevo.setCantidad(cantidad);
                        nuevo.setTitulo(request.getParameter("select"));
                        boolean existe=false;
                        for(Libro l:libros){  //  Se busca si estaba ya en el array
                            if(l.getTitulo().equals(request.getParameter("select"))){
                                l.setCantidad(cantidad+l.getCantidad());  //  Si estaba se modifica la cantidad
                                existe=true;
                            }/*if(l.getTitulo().equals(request.getParameter("select"))*/
                        }/*for(Libro l:libros)*/
                        if(!existe){libros.add(nuevo);}  //  Si NO estaba se añade
                        sesion.setAttribute("libros", libros);  //  Si añade array a la sesion
                    }catch(Exception e){
                        %>
                        <h3 id="error">Introduce un n&uacute;mero v&aacute;lido</h3>
                        <%
                    }/*trycatch*/ 
                }/*ifelse(request.getParameter("select")==null)*/
            }/*if(request.getParameter("aniadir")!=null)*/
            %>
                <select id="select" name="select" size="6">
                    <option value="HARRY POTTER Y EL LEGADO MALDITO">HARRY POTTER Y EL LEGADO MALDITO</option>
                    <option value="LOS HEREDEROS DE LA TIERRA">LOS HEREDEROS DE LA TIERRA</option>
                    <option value="FUNDAMENTOS DE PSICOBIOLOGIA 2016">FUNDAMENTOS DE PSICOBIOLOGIA 2016</option>
                    <option value="PATRIA">PATRIA</option>
                    <option value="UN MONSTRUO VIENE A VERME">UN MONSTRUO VIENE A VERME</option>
                    <option value="LA CHICA DEL TREN">LA CHICA DEL TREN</option>
                    <option value="EL PRINCIPITO">EL PRINCIPITO</option>
                    <option value="EL UNIVERSO EN TU MANO">EL UNIVERSO EN TU MANO</option>
                    <option value="14. LA AUTOBIOGRAFÍA">14. LA AUTOBIOGRAFÍA</option>
                    <option value="LA CARNE">LA CARNE</option>
                </select>
                <p>
                    <h4><label for="cantidad">Marque una cantidad:</label></h4>
                    <input type="text" id="cantidad" name="cantidad" size="20" maxlength="15" value="" />
                </p>
                <p>
                    <input type="submit" id="aniadir" name="aniadir" value="Añadir al carro"/>
                    <input type="reset" id="limpiar" name="limpiar" value="Limpiar"/>
                    <input type="submit" id="finalizar" name="finalizar" value="Finalizar la compra"/>
                </p>
            </form>
        <%
        }/*ifelse(request.getParameter("enviar")!=null)*/
        %>
        <p><a id="nar" href="<%=request.getContextPath()%>">Volver al menu principal</a><p>
    </body>
</html>
