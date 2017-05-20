<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
        <title>Minugest</title>
    </head>
    <body>
        <div class="container">
        <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        
        <h1>Lista De Minutas</h1>
        <div class="row">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                <th>Nombre de Minuta</th>
                <th>Casino</th>
                <th>Usuario</th>
                <th>Fecha</th>
                <th>Acciones</th>
                
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${minutas}" var="dato" >
                        <tr>
                            <td><c:out value="${dato.NOMBRE_MINUTA}"/></td>
                            <td><c:out value="${dato.NOMBRE_CASINO}"/></td>
                            <td><c:out value="${dato.CODIGO_USUARIO}"/></td>
                            <td><c:out value="${dato.FECHA_MINUTA}"/></td>
                            <td><a href="RecetaMinuta.htm?COD=${dato.CODIGO_MINUTA}" ><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>     
                            <a href="ModificarMinuta.htm?COD=${dato.CODIGO_MINUTA}" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>     
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
                <a href="AñadirMinuta.htm" class="btn btn-success">Crear Minuta</a>
            </div>
        </div>
    </body>
</html>