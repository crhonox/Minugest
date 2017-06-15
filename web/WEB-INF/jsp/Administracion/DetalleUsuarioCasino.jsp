<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>

        <script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/moment.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js"/>"></script>
  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>" />
  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css"/>" />
  
        <title>Minugest</title>
    </head>
     <body>
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="panel panel-primary"> 
                   
                    <div class="panel-heading"> 
                        <ol class="breadcrumb">
                <li><a href="<c:url value="listaUsuario.htm?rut=${rutEmp}" />">Listado de Usuarios</a></li>
                <li class="active"> Detalle de Usuario</li>
                </ol>
                    </div>
                    <div class="panel-body">
                        <table>
                            <tr>
                                <td><label>Rut Usuario: </label></td>
                                <td><c:out value="${rut}"/></td>
                            </tr>
                            <tr>
                                <td><label>Nombre de Usuario: </label></td>
                                <td><c:out value="${nombre}"/></td>
                            </tr>
                            <tr>
                                <td><label>Apellido de usuario: </label></td>
                                <td><c:out value="${apellido}"/></td>
                            </tr>
                            <tr>
                                <td><label>Perfil: </label></td>
                                <td><c:out value="${perfil}"/></td>
                            </tr>
                            <tr>
                                <td><label>Correo: </label></td>
                                <td><c:out value="${correo}"/></td>
                            </tr>
                            <tr>
                                <td><label>Contraseña: </label></td>
                                <td><c:out value="${pass}"/></td>
                            </tr>
                            
                        </table>
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                <th><label>Casino Asignado </label></th>
                                
                                <th><label>Acciones </label></th>
                                    </tr>
                            </thead>
                            <c:forEach items="${datos2}" var="dato" >
                                <tr>
                                <td><c:out value="${dato.NOMBRE_CASINO}"/></td>
                                
                                <td><a href="DesasignarCasino.htm?RUT=${rut}&COD=${dato.ID_CASINO}" onclick="return confirm('Esta seguro que quiere desasignar este casino?');">Desasignar</a></td>
                                
                            </tr>
                            </c:forEach>
                            </table>
                            <a href="CasinoUsuario.htm?rutUser=${rut}" ><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Asignar Casino</a>
    
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
