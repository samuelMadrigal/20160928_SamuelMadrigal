<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalTime"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saludo</title>
        <link rel="stylesheet" href="../estilos/estilo.css" type="text/css"/>
    </head>
    <body>
       
        <%
        String tratamiento= request.getParameter("sexo").equals("Hombre") ? "señor" : "señora";
        String nombre=request.getParameter("nombre");
        String saludo=null;
        
        int hora=LocalTime.now().getHour();
        if (hora>=6 && hora<13){
            saludo="Hola buenos d&iacute;as";
        }else if(hora>=13 && hora<21){
            saludo="Hola buenas tardes";
        }else{
            saludo="Hola buenas noches";
        }
        %>
        
        <h1><%=saludo%> <%=tratamiento%> <%=nombre%></h1>
        
        <p><a href="../HTML/saludoJsp.html">Volver atr&aacute;s</a></p>
        <p><a id="nar" href="<%=request.getContextPath()%>">Volver al menu principal</a><p>
    </body>
</html>
