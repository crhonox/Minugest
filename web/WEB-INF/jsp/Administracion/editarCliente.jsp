<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
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
                <li><a href="<c:url value="/cliente.htm" />">Listado de clientes</a></li>
                <li class="active">Editar</li>
            </ol>
            <div class="panel panel-primary">
                <div class="panel-heading">Editar</div>
                <div class="panel-body">
                   
                        <form:form method="post" commandName="cliente">
                            <h1>Complete el formulario</h1>
                            
                            <form:errors path="*" element="div" cssClass="alert alert-danger" />
                            <p><form:label path="rut">Rut  :</form:label><form:input readonly="true" path="rut" cssClass="form-control"/></p>
                            <p><form:label path="Nombre">Nombre  :</form:label><form:input path="Nombre" cssClass="form-control"/></p>
                            <p><form:label path="email">E-mail  :</form:label><form:input path="email" cssClass="form-control"/></p>
                            <p><form:label path="Telefono">Telefono  :</form:label><form:input path="Telefono" cssClass="form-control"/></p>
                            <p><form:label path="Region">Region  :</form:label><form:select path="Region" cssClass="form-control">
                            <%--  <c:forEach items="${clicoms}" var="clicom" ><form:option value="${clicom.REGION_ID}">${clicom.REGION_NOMBRE}</form:option></c:forEach>--%>
                                    
                                <c:forEach items="${regiones}" var="region">   
                                    <form:option value="${region.REGION_ID}" onclick="">${region.REGION_NOMBRE}</form:option>
                                </c:forEach>                            
                        </form:select></p>
                            <p><form:label path="Comuna">Comuna  :</form:label>
                            <form:select path="Comuna" cssClass="form-control">
                           <%-- <c:forEach items="${clicoms}" var="clicom" ><form:option value="${clicom.COMUNA_ID}">${clicom.COMUNA_NOMBRE}</form:option></c:forEach>--%>
                        <c:forEach items="${comunas}" var="comuna">
                            <form:option value="${comuna.COMUNA_ID}">${comuna.COMUNA_NOMBRE}</form:option>
                        </c:forEach>
                            </form:select>
            <p></p>
            
                        <p><form:label path="Direccion">Direccion  :</form:label><form:input path="Direccion" cssClass="form-control"/></p>
            
                            <hr />
                            <input type="submit" value="Enviar" class="btn btn-danger" />
                        </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
