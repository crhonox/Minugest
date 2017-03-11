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
        <link type="text/css" href="ind.css" rel="stylesheet"/>
        <title>JSP Page</title>
        <%--  ${pageContext.request.contextPath} codigo para conseguir la ruta absoluta del directiorio
              <c:url value="/resources/css/styles.css" />
        --%>
    </head>
    <body>
        <div> 
            
            <div id="tituloleft"><h1 align="left">Logo</h1></div>
            
            
     <div id="tituloright">
         <img src="user-icon.png" width="20" height="20"><ul><a href="login.htm">Iniciar sesión</a></ul></div>
                
            <div id="titulocenter"><h1 align="center">Minugest</h1></div>
               
        <hr/>       
            
       </div>
        
        <div>
        <div id="left">
        <ul>          
            <li><a href="compañia.htm">Compañia</a></li>
            <li><a href="soluciones.htm">Soluciones</a></li>
            <li><a href="socios.htm">Socios</a></li>
            
        </ul>
            </div>
            <div id="center"></div>
            
            
        </div>
    </body>
</html>