<%-- 
    Document   : home
    Created on : 09-03-2017, 20:38:14
    Author     : crhonox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%--  ${pageContext.request.contextPath} codigo para conseguir la ruta absoluta del directiorio
              <c:url value="/resources/css/styles.css" />
        --%>
    </head>
    <body>
        <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <ul>          
            <li><a href="cliente.htm">Clientes</a></li>
            <li><a href="receta.htm">Recetas</a></li>
            <li><a href="ingrediente.htm">Ingredientes</a></li>
            
        </ul>
    </body>
</html>
