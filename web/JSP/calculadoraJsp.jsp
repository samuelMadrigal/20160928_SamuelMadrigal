<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculadora</title>
        <link rel="stylesheet" href="../estilos/estilo.css" type="text/css"/>
    </head>
    <body>
        <h2>Calculadora jsp</h2>
        
        <%
        int num1=0;
        int num2=0;
        int res=0;
        Calendar calen;
        Date fecha;
        
        if (request.getParameter("enviar") != null) { //Si viene de enviar se muestra resultado o error
            try{
                num1=Integer.parseInt(request.getParameter("num1"));
                num2=Integer.parseInt(request.getParameter("param2"));
                
                if(request.getParameter("operacion").equals("dividir") && num2==0){  //Control de divison por cero
                %>
                    No se puede dividir por cero
                <%
                }else{
                    switch(request.getParameter("operacion")){
                        case "sumar": res=num1+num2;
                                    break;
                        case "restar": res=num1-num2;
                                    break;
                        case "multiplicar": res=num1*num2;
                                    break;
                        case "dividir": res=num1/num2;
                                    break;
                    }/*switch*/

                calen = Calendar.getInstance();   //Obtener fecha y formatear
                fecha = calen.getTime();
                DateFormat format = DateFormat.getInstance();
                SimpleDateFormat dia=new SimpleDateFormat("EEEE");
                SimpleDateFormat diaN=new SimpleDateFormat("d");
                SimpleDateFormat mes=new SimpleDateFormat("MMMM");
                SimpleDateFormat anio=new SimpleDateFormat("yyyy");
                SimpleDateFormat hora=new SimpleDateFormat("HH:mm");
            %> 
                <p>
                    Cliente: <%=request.getHeader("user-agent")%>
                <br/>
                    Fecha: <%=dia.format(fecha).substring(0,1).toUpperCase() + dia.format(fecha).substring(1)%>, <%=diaN.format(fecha)%> de <%=mes.format(fecha)%> de <%=anio.format(fecha)%>. Son las <%=hora.format(fecha)%>
                </p>
                <br/>
                <p>
                    El resultado de <%=request.getParameter("operacion")%> <%=num1%> y <%=num2%> es igual a <%=res%>
                </p> 
                <br/>
            <%
                }/*ifelse*/
            }catch(NumberFormatException e){
            %> 
                Fallo de operandos
            <%
            }/*catch*/
        }/*ifenviar*/
        %>
        
        <!-- Formulario calculadora se muestra siempre-->
        <div id="contForm">
            <form id="fomulario-datos" action="calculadoraJsp.jsp" method="post"> 
                <p>
                    <label for="num1">Número 1:</label>
                    <input type="text" id="num1" name="num1" size="10" maxlength="9" value="" />&nbsp;&nbsp;&nbsp;&nbsp;
                    <label for="param2">Número 2:</label>
                    <input type="text" id="param2" name="param2" size="10" maxlength="9" value="" />
                </p>
                <p>
                    <label for="operacion"></label>
                    <input type="radio" id="operacion" name="operacion" value="sumar" checked="checked" />Sumar&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="operacion" value="restar" />Restar&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="operacion" value="multiplicar" />Multiplicar&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="operacion" value="dividir" />Dividir
                </p>

                <input type="submit" id="aceptar" name="enviar" value="Calcular" />
                <input type="reset" id="aceptar" name="borrar" value="Limpiar" />
                <input type="button" id="aceptar" name="inicio" value="Volver" onClick="location.href='<%=request.getContextPath()%>'"/>
            </form>
        </div>
    </body>
</html>
