<%-- 
    Document   : login
    Created on : 10-03-2017, 11:09:54 PM
    Author     : Sir Lekxas
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <body>
        <div> 
            
            <div id="tituloleft"><h1 align="left">Logo</h1></div>
            
            
     <div id="tituloright">
       <ul>
                 <li><a href="home.htm">Inicio</a></li>
     </ul>
     
     </div>
            <div id="titulocenter"><h1 align="center">Minugest</h1></div>
               
        <hr/>       
            
       </div>
        
        <div>
        
            <div class="container">
               <div class="row">
                <div class="panel panel-primary"> 
                    <div class="panel-heading"><center>Inicio de Sesion</center></div>
                    <div class="panel-body">
                    <form:form method="POST" commandName="login">
                    <h1></h1>
                    <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    
            <p><form:label path="rut">Rut  :</form:label><form:input path="rut" cssClass="form-control"/></p>
            <p></p>
            <p><form:label path="pass">Contraseña :</form:label><form:password path="pass" cssClass="form-control"/></p>
            <p></p>
            <form:button class="btn btn-danger" >Ingresar</form:button>
            
        </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
    </body>
</html>
