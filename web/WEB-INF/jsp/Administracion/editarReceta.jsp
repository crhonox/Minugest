<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core"   %>
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
                <li><a href="<rec:url value="/receta.htm" />">Listado de Recetas</a></li>
                <li class="active">Editar</li>
            </ol>
            <div class="panel panel-primary">
                <div class="panel-heading">Editar</div>
                <div class="panel-body">
                   
                        <form:form method="post" commandName="receta">
                            <h1>Complete el formulario</h1>
                            
                            <form:errors path="*" element="div" cssClass="alert alert-danger" />
                            <p><form:label path="idReceta">ID:</form:label><form:input readonly="true" path="idReceta" cssClass="form-control"/></p>
                            <p><form:label path="nombreReceta">Nombre  :</form:label><form:input path="nombreReceta" cssClass="form-control"/></p>
                            <p><form:label path="idCategoria">Categoria:</form:label>
                        <form:select path="idCategoria" cssClass="form-control">
                            <form:option value="0">Seleccione...</form:option>
                                <rec:forEach items="${idCategoria}" var="cat">   
                                    <form:option value="${cat.CODIGO_CATEGORIA}"> ${cat.NOMBRE_CATEGORIA}</form:option>
                                </rec:forEach>                            
                        </form:select></p>
             <p><form:label path="descripcionReceta">Descripción  :</form:label><form:input path="descripcionReceta" cssClass="form-control"/></p>
                        
                            <hr />
                            <input type="submit" value="Enviar" class="btn btn-danger" />
                        </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
