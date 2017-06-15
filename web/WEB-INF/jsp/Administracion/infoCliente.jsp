<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <title>Minugest</title>
    </head>
    <body>
        <div class="container">
            
            <ol class="breadcrumb">
                <li><a href="<c:url value="cliente.htm" />">Listado de clientes</a></li>
                <li class="active">${cliente.nombre}</li>
            </ol>
            <div class="panel panel-primary">
                <div class="panel-heading"></div>
                <div class="panel-body">
                   
                        <form:form method="post" commandName="cliente">
                            <h1></h1>
                            
                            <form:errors path="*" element="div" cssClass="alert alert-danger" />
                            <p><form:label path="rut">Rut  : ${cliente.rut}</form:label></p>
                            <p><form:label path="Nombre">Nombre  : ${cliente.nombre}</form:label></p>
                            <p><form:label path="Nombre">Razon Social  : ${cliente.nombreLargo}</form:label></p>
                            <p><form:label path="email">E-mail  : ${cliente.email}</form:label></p>
                            <p><form:label path="Telefono">Telefono  : ${cliente.telefono}</form:label></p>
                            <p><form:label path="Region">Region  : ${cliente.region}</form:label></p>
                            <p><form:label path="Provincia">Provincia  : ${cliente.provincia}</form:label></p>
                            <p><form:label path="Comuna">Comuna  : ${cliente.comuna}</form:label></p>
                        <p><form:label path="Direccion">Direccion  : ${cliente.direccion}</form:label></p>
                            <hr />
                                <a href="listaUsuario.htm?rut=${cliente.rut}" class="btn btn-success">Lista de Usuarios</a> <a href="listaCasino.htm?rut=${cliente.rut}" class="btn btn-success">Lista de Casinos</a>
                        </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
