<%-- 
    Document   : consultaExito
    Created on : 12-03-2017, 02:46:55 AM
    Author     : Sir Lekxas
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
        <h1 align="center">Minugest</h1>
        <ol class="breadcrumb">
                <li><a href="<c:url value="/consultaMinuta.htm" />">Buscar Minuta</a></li>
                <li class="active">Minutas</li>
            </ol>
        <br>
        <hr/>
        
        <h1>Minuta</h1>
        <div class="row">
            
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                <th>Nombre Minuta</th>
                <th>Codigo Minuta</th>
                <th>Codigo Casino</th>
                <th>Codigo Usuario</th>
                <th>Fecha Minuta</th>
                <th>Revisar Minuta</th>
                
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datos}" var="dato" >
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
    </body>
</html>
