<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
        <title>Minugest</title>
    </head>
    <body>
        <a href="/Minugest/home">Volver al inicio</a> 
        <div class="container">
        <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        
        <h1>Clientes</h1>
        <div class="row">
            <form:form  method="POST" modelAttribute="cliente">
                    <h1></h1>
                     <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    
                    <p><form:label path="Nombre">Nombre  :</form:label>
                    <form:input path="Nombre" cssClass="form-control"/></p>

            <form:button class="btn btn-danger" > Buscar</form:button>
            <a href="cliente.htm" class="btn btn-success">Mostrar todo</a>
            
            <hr>
        </form:form>
            
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                <th>Rut</th>
                <th>Nombre</th>
                <th>Razon Social</th>
                <th>Telefono</th>
                <th>Correo</th>
                <th>Region</th>
                <th>Provincia</th>
                <th>Comuna</th>
                <th>Direccion</th>
                <th>Acciones</th>
                
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datos}" var="dato" >
                        <tr>
                            <td><c:out value="${dato.RUT_EMPRESA}"/></td>
                            <td><a href="infoCliente.htm?rut=${dato.RUT_EMPRESA}"><c:out value="${dato.NOMBRE_EMPRESA}"/></a></td>
                            <td><c:out value="${dato.RAZON_SOCIAL}"/></td>
                            <td><c:out value="${dato.TELEFONO_EMPRESA}"/></td>
                            <td><c:out value="${dato.CORREO_EMPRESA}"/></td>
                            <td><c:out value="${dato.REGION_NOMBRE}"/></td>
                            <td><c:out value="${dato.PROVINCIA_NOMBRE}"/></td>
                            <td><c:out value="${dato.COMUNA_NOMBRE}"/></td>
                            <td><c:out value="${dato.Direccion_EMPRESA}"/></td>
                            <td><a href="editarCliente.htm?rut=${dato.RUT_EMPRESA}" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>     
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
                <a href="AñadirCliente.htm" class="btn btn-success">Añadir cliente</a>
            </div>
        </div>
    </body>
</html>
