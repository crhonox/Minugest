<%-- 
    Document   : minutaDia
    Created on : 11-03-2017, 12:10:03 AM
    Author     : Sir Lekxas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        
        <title>Minugest</title>
    </head>
    <body>
        <h1 align="center">Minugest</h1>
        <br>
        <hr/>       
        
        
        
        <div>
            <div class="container">
        <div id="left">
        <ul>          
            <li><a href="consultaMinuta.htm">Consultar Minuta</a></li>
            <li><a href="minutaDia.htm">Minuta del Día</a></li>
        </ul>
            </div>
            
            <center><h1>Minuta del Día</h1></center>
            <div class="row">
                <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                <th>Nombre Minuta</th>
                <th>Codigo</th>
                <th>Casino</th>
                <th>Usuario</th>
                <th>Fecha</th>
                </tr>
                </thead>    
                <tbody>
                    <c:forEach items="${datos}" var="dato">
                        <tr>
                            <td><c:out value="${dato.NOMBRE_MINUTA}"/></td>
                            <td><c:out value="${dato.CODIGO_MINUTA}"/></td>
                            <td><c:out value="${dato.CODIGO_CASINO}"/></td>
                            <td><c:out value="${dato.CODIGO_USUARIO}"/></td>
                            <td><c:out value="${dato.FECHA_MINUTA}"/></td>
                        </tr>
                    </c:forEach>
              </tbody>
                
                
                
                </table>
                </div>
            
        </div>
            </div>
    </body>
</html>
