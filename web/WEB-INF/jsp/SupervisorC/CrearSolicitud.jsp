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
                    <div class="panel-heading">Solicitud</div>
                    <div class="panel-body">
       <form:form  method="POST" commandName="Solicitud">
                    <h1></h1>
       <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <p>
                        <form:label path="destino">Destino  :</form:label>
                        <form:select path="destino" cssClass="form-control">
                            <form:option value="0">Seleccione...</form:option>
                                <rec:forEach items="${encargado}" var="cat">   
                                    <form:option value="${cat.CODIGO_USUARIO}"> ${cat.Nombre}</form:option>
                                </rec:forEach>                            
                        </form:select></p>
                    </p>
           
         <p><form:label path="asunto">Asunto :</form:label>
                        <form:input path="asunto" cssClass="form-control"/></p>

         <p>
                <form:label path="contenido">Solicitud :</form:label>
                <form:textarea path="contenido" cssClass="form-control"/>
         </p>
         <input type="hidden" value="${usuario}" name="codigo_usuario" id="codigo_usuario"/>
         
            <form:button class="btn btn-danger" >Enviar</form:button>
        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
