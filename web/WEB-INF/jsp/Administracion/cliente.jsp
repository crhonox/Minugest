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
        <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        
        <h1>Clientes</h1>
        <div class="row">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                <th>Rut</th>
                <th>Nombre</th>
                <th>Telefono</th>
                <th>Correo</th>
                <th>Region</th>
                <th>Comuna</th>
                <th>Direccion</th>
                <th>Acciones</th>
                
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datos}" var="dato" >
                        <tr>
                            <td><c:out value="${dato.RUT_EMPRESA}"/></td>
                            <td><c:out value="${dato.NOMBRE_EMPRESA}"/></td>
                            <td><c:out value="${dato.TELEFONO_EMPRESA}"/></td>
                            <td><c:out value="${dato.CORREO_EMPRESA}"/></td>
                            <td><c:out value="${dato.REGION_NOMBRE}"/></td>
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
