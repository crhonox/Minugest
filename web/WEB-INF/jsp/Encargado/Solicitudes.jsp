<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
        <title>Encargado</title>
    </head>
    <body>
        <a href="/Minugest/home">Volver al inicio</a> 
        <div class="container">
        <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        
        <h1>Solicitudes</h1>
        <div class="row">
            
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                <th>Asunto</th>
                <th>Destinatario</th>
                <th>Fecha</th>
                <th>Contenido</th>
                <th>Estado</th>
                <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${solicitud}" var="dato" >
                        <tr>
                            <td><a href="DetalleSolicitud.htm?idSolicitud=${dato.idSOLICITUD}"><c:out value="${dato.ASUNTO}"/></a></td>
                            <td><c:out value="${dato.Nombre}"/></td>
                            <td><c:out value="${dato.TIEMPO}"/></td>
                            <td><c:out value="${dato.CONTENIDO}"/></td>
                            <td><c:out value="${dato.ESTADO}"/></td>
                                
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
                
            </div>
        </div>
    </body>
</html>