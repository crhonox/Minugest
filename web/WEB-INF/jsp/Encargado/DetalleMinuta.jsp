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
  
        <title>Encargado</title>
    </head>
     <body>
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="panel panel-primary"> 
                    <div class="panel-heading">Detalle de Minuta</div>
                    <div class="panel-body">
                        <table>
                            <tr>
                                <td><label>Nombre de Minuta: </label></td>
                                <td><c:out value="${nombre}"/></td>
                            </tr>
                            <tr>
                                <td><label>Fecha de Minuta: </label></td>
                                <td><c:out value="${fecha}"/></td>
                            </tr>
                            <tr>
                                <td><label>Casino asociado: </label></td>
                                <td><c:out value="${casino}"/></td>
                            </tr>
                            <tr>
                                <td><label>Creador de Minuta: </label></td>
                                <td><c:out value="${usuario}"/></td>
                            </tr>
                            
                        </table>
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                <th><label>Recetas asignadas </label></th>
                                <th><label>Porciones </label></th>
                                <th><label>Acciones </label></th>
                                    </tr>
                            </thead>
                            <c:forEach items="${recetas}" var="dato" >
                                <tr>
                                <td><c:out value="${dato.NOMBRE_RECETA}"/></td>
                                <td><c:out value="${dato.CANTIDAD_PORCION}"/></td>
                                <td><a href="url_to_delete?COD=${dato.CODIGO_RECETA}" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a></td>
                                
                            </tr>
                            </c:forEach>
                            </table>
            
    
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
