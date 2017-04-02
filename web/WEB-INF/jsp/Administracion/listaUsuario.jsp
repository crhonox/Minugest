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
                <li><a href="<c:url value="/cliente.htm" />">Listado de clientes</a></li>
                <li class="active"> Empresa</li>
            </ol>
        <br>
        <hr/>
        
        <h1>Usuarios</h1>
        <div class="row">
            
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                <th>Rut</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Casino</th>
                <th>Perfil</th>
                <th>Correo</th>
                <th>Contraseña</th>
                <th>Acciones</th>
                
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${datos}" var="dato" >
                        <tr>
                            <td><c:out value="${dato.CODIGO_USUARIO}"/></td>                                                      
                            <td><c:out value="${dato.NOMBRE_USUARIO}"/></td>
                            <td><c:out value="${dato.APELLIDO_USUARIO}"/></td>
                            <td><c:out value="${dato.NOMBRE_CASINO}"/></td>
                            <td><c:out value="${dato.NOMBE_PERFIL}"/></td>
                            <td><c:out value="${dato.CORREO_USUARIO}"/></td>
                            <td><c:out value="${dato.PASS_USUARIO}"/></td>
                            <td><a href="editarUsuario.htm?rutUser=${dato.CODIGO_USUARIO}" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td> 
                            
                        </tr>
                    </c:forEach>      
                </tbody> 
            </table>
            <form:form method="post" commandName="cliente">
                <a href="AñadirUsuario.htm?rutEmpresa=${cliente.rut}" class="btn btn-success">Añadir Usuario</a>
            </form:form>
             
            </div>
        </div>
    </body>
</html>
