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
        <ol class="breadcrumb">
                <li><a href="<c:url value="cliente.htm" />">Listado de clientes</a></li>
                <li class="active"> Aaaaa</li>
            </ol>
        <br>
        <hr/>
        
        <h1>Casinos</h1>
        <div class="row">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                
                <th>Nombre Casino</th>
                <th>Empresa</th>
                <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datos}" var="dato" >
                        <tr>
                            <td><c:out value="${dato.NOMBRE_CASINO}"/></td>                                                      
                            <td><c:out value="${dato.NOMBRE_EMPRESA}"/></td>
                            <td><a href="editarCasino.htm?cod=${dato.CODIGO_CASINO}" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>     
                            <!--<a href="eliminarCasino.htm?cod=${dato.CODIGO_CASINO}" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>  --> </td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form:form method="post" commandName="cliente">
                <a href="AñadirCasino.htm?rutemp=${cliente.rut}" class="btn btn-success">Añadir Casino</a>
            </form:form>
            </div>
        </div>
    </body>
</html>