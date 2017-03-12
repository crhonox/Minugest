<%-- 
    Document   : exito
    Created on : 10-03-2017, 0:52:55
    Author     : crhonox
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <title>Minugest</title>
    </head>
    <body>
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <p>Resultados del Formulario</p>
        <ul>
            <li>Nombre  :<c:out value="${Nombre}"/></li>
            <li>Direccion  :<c:out value="${Direccion}"/></li>
            <li>Comuna  :<c:out value="${Comuna}"/></li>
            <li>Region  :<c:out value="${Region}"/></li>
            <li>Rut  :<c:out value="${Rut}"/></li>
            <li>Telefono  :<c:out value="${Telefono}"/></li>
            <li>E-mail  :<c:out value="${email}"/></li>
            
        </ul>
    </body>
</html>
