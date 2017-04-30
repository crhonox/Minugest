<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ingr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <title>Encargado</title>
    </head>
    <body>
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="panel panel-primary"> 
                    <div class="panel-heading">Formulario Ingrediente</div>
                    <div class="panel-body">
                <form:form  method="POST" commandName="ingrediente">
                    <h1></h1>
                    <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <p><form:label path="nombreIngrediente">Nombre  :</form:label><form:input path="nombreIngrediente" cssClass="form-control"/></p>
            <p></p>
            <p><form:label path="unidadMedida">Unidad de Medida  :</form:label>
                        <form:select path="unidadMedida" cssClass="form-control">
                            <form:option value="0">Seleccione...</form:option>
                                <ingr:forEach items="${unidadMedida}" var="um">   
                                    <form:option value="${um.CODIGO_UNIDAD}">${um.NOMBRE_UNIDAD}</form:option>
                                </ingr:forEach>                            
                        </form:select></p>
                    
                    
            <p></p>
            <form:button class="btn btn-danger" >Enviar</form:button>
        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
