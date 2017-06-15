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
                <li><a href="<c:url value="listaCasino.htm?rut=${rutEmp}" />">Listado de Casinos</a></li>
                <li class="active"> Detalle de casino</li>
                </ol>
                    </div>
                    <div class="panel-body">
                        <table>
                            <tr>
                                <td><label>Nombre Casino: </label></td>
                                <td><c:out value="${nomcas}"/></td>
                            </tr>
                            <tr>
                                <td><label>Region: </label></td>
                                <td><c:out value="${casreg}"/></td>
                            </tr>
                            <tr>
                                <td><label>Provincia: </label></td>
                                <td><c:out value="${caspro}"/></td>
                            </tr>
                            <tr>
                                <td><label>Comuna: </label></td>
                                <td><c:out value="${cascom}"/></td>
                            </tr>
                            <tr>
                                <td><label>Direccion: </label></td>
                                <td><c:out value="${casdir}"/></td>
                            </tr>
                            
                            
                        </table>
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                <th><label>Rut Usuario</label></th>
                                <th><label>Nombre Usuario</label></th>
                                <th><label>Correo Usuario</label></th>
                                <th><label>Perfil </label></th>
                                
                                <th><label>Acciones </label></th>
                                    </tr>
                            </thead>
                            <c:forEach items="${usuarios}" var="dato" >
                                <tr>
                                <td><c:out value="${dato.RUT_USUARIO}"/></td>
                                <td><c:out value="${dato.Nombre}"/></td>
                                <td><c:out value="${dato.CORREO_USUARIO}"/></td>
                                <td><c:out value="${dato.NOMBE_PERFIL}"/></td>
                                
                                <td><a href="DesasignarUsuario.htm?RUT=${dato.RUT_USUARIO}&COD=${dato.ID_CASINO}" onclick="return confirm('Esta seguro que quiere desasignar este Usuario?');">Desasignar</a></td>
                                
                            </tr>
                            </c:forEach>
                            </table>
                            <a href="UsuarioCasino.htm?cod=${cod}" ><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Asignar Usuarios</a>
    
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
