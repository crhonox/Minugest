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
  
        <title>Supervisor</title>
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
                <li><a href="<c:url value="Solicitud.htm" />">Solicitudes</a></li>
                <li class="active"> Detalle de Solicitud</li>
                </ol>
                    </div>
                    <div class="panel-body">
                        <table>
                            <c:forEach items="${solicitud}" var="dato" >
                            <tr>
                                <td><label>Asunto: </label></td>
                                <td><c:out value="${dato.ASUNTO}"/></td>
                            </tr>
                            <tr>
                                <td><label>Destinatario: </label></td>
                                <td><c:out value="${dato.Nombre}"/></td>
                            </tr>
                            <tr>
                                <td><label>Contenido: </label></td>
                                <td><c:out value="${dato.CONTENIDO}"/></td>
                            </tr>
                            
                             </c:forEach>
                        </table>
                           
                           
    
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
