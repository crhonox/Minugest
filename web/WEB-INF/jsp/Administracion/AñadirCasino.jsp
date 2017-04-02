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
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="panel panel-primary"> 
                    <div class="panel-heading">Formulario</div>
                    <div class="panel-body">
                <form:form method="POST" commandName="casino">
                    <h1></h1>
                    <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <input id="RutEmpresa" name="RutEmpresa" type="text" value="${rutemp}" hidden="true"/>
                    <p><form:label path="NombreCasino">Nombre  :</form:label><form:input path="NombreCasino" cssClass="form-control"/></p>
                    
            <form:button class="btn btn-danger" >Enviar</form:button>
            
        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>