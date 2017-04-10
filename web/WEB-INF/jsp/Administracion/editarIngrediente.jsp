<%@taglib prefix="ingr" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Minugest</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    </head>
    <body>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="<ingr:url value="/ingrediente.htm" />">Listado de Ingredientes</a></li>
                <li class="active">Editar</li>
            </ol>
            <div class="panel panel-primary">
                <div class="panel-heading">Editar</div>
                <div class="panel-body">
                   
                        <form:form method="post" commandName="ingrediente">
                            <h1>Complete el formulario</h1>
                            
                            <form:errors path="*" element="div" cssClass="alert alert-danger" />
                            <p><form:label path="idIngrediente">ID:</form:label><form:input readonly="true" path="idIngrediente" cssClass="form-control"/></p>
                            <p><form:label path="nombreIngrediente">Nombre  :</form:label><form:input path="nombreIngrediente" cssClass="form-control"/></p>
                            <p><form:label path="unidadMedida">Unidad de Medida  :</form:label>
                               <form:select path="unidadMedida" cssClass="form-control">
                                <form:option value="0">Seleccione...</form:option>
                                <ingr:forEach items="${unidadMedida}" var="um">   
                                    <form:option value="${um.CODIGO_UNIDAD}">${um.NOMBRE_UNIDAD}</form:option>
                                </ingr:forEach>                            
                        </form:select></p>
            
                        
                            <hr />
                            <input type="submit" value="Enviar" class="btn btn-danger" />
                        </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
