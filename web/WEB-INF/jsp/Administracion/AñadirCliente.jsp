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
                <form:form method="POST" commandName="cliente">
                    <h1></h1>
                    <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <p><form:label path="Nombre">Nombre  :</form:label><form:input path="Nombre" cssClass="form-control"/></p>
            <p></p>
            <p><form:label path="Region">Region  :</form:label><form:select path="Region" cssClass="form-control">
                            <form:option value="0">Seleccione...</form:option>
                                <c:forEach items="${regiones}" var="region">   
                                    <form:option value="${region.REGION_ID}">${region.REGION_NOMBRE}</form:option>
                                </c:forEach>                            
                        </form:select></p>
            <p></p>
            <p><form:label path="Comuna">Comuna  :</form:label><form:select path="Comuna" cssClass="form-control">
                            <form:option value="0">Seleccione...</form:option>
                        
            <c:forEach items="${comunas}" var="comuna">
                <form:option value="${comuna.COMUNA_ID}">${comuna.COMUNA_NOMBRE}</form:option>
            </c:forEach>
                            </form:select>
                    </p>
            <p></p>
            <p><form:label path="Direccion">Direccion  :</form:label><form:input path="Direccion" cssClass="form-control"/></p>
            <p></p>
            <p><form:label path="rut">Rut  :</form:label><form:input path="rut" cssClass="form-control"/></p>
            <p></p>
            <p><form:label path="email">E-mail  :</form:label><form:input path="email" cssClass="form-control"/></p>
            <p></p>
            <p><form:label path="Telefono">Telefono  :</form:label><form:input path="Telefono" cssClass="form-control"/></p>
            <p></p>
            <form:button class="btn btn-danger" >Enviar</form:button>
            
        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
