<%-- 
    Document   : AñadirReceta
    Created on : 13-04-2017, 19:21:44
    Author     : Bayron Cruz C
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="container">
            <div class="row">
                <div class="panel panel-primary"> 
                    <div class="panel-heading">Formulario Receta</div>
                    <div class="panel-body">
       <form:form  method="POST" commandName="receta">
                    <h1></h1>
       <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <p>
                        <form:label path="nombreReceta">Nombre  :</form:label>
                        <form:input path="nombreReceta" cssClass="form-control"/>
                    </p>
            <p></p>
            <p><form:label path="idCategoria">Categoria:</form:label>
                        <form:select path="idCategoria" cssClass="form-control">
                            <form:option value="0">Seleccione...</form:option>
                                <rec:forEach items="${idCategoria}" var="cat">   
                                    <form:option value="${cat.CODIGO_CATEGORIA}"> ${cat.NOMBRE_CATEGORIA}</form:option>
                                </rec:forEach>                            
                        </form:select></p>
         <p><form:label path="descripcionReceta">Descripción Receta :</form:label>
                        <form:input path="descripcionReceta" cssClass="form-control"/></p>

            <p></p>
            <form:button class="btn btn-danger" >Enviar</form:button>
        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
