<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
   <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <title>Encargado</title>
    </head>
    <body>
      
        <div class="container">
        <h1 align="center">Minugest</h1>
        <br>
        <hr/>
         <h1>Recetas</h1>
         <h1></h1>
         <form:form commandName="receta" method="POST">
             <p> <form:label path="nombreReceta">Buscar Receta</form:label></p>
             <form:input path="nombreReceta" cssClass="form-control"/>
             <p></p>
             <form:button class="btn btn-danger">Buscar</form:button> <a href="receta.htm" class="btn btn-success">Mostrar Todo</a> <a href="AñadirReceta.htm" class="btn btn-success">Añadir Receta</a>
         </form:form>
             <p></p>
             
          <div class="row">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                
                <th>Nombre</th>
                <th>Categoria</th>
                <th>Descripción</th>
                <th>Porciones</th>
                <th>Acciones</th>
               

                     </tr>
                </thead>
                <tbody>
                    <rec:forEach items="${datos}" var="dato" >
                        <tr>
                            
                            <td><a href="DetalleReceta.htm?COD=${dato.CODIGO_RECETA}"><rec:out value="${dato.NOMBRE_RECETA}"/></a></td>
                            <td><rec:out value="${dato.NOMBRE_CATEGORIA}"/></td>
                            <td><rec:out value="${dato.DESCRIPCION_RECETA}"/></td>
                            <td><rec:out value="${dato.CANTIDAD_PORCION}"/></td>
                            <td><a href="editarReceta.htm?idReceta=${dato.CODIGO_RECETA}" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>   
                                <a href="AñadirIngredienteReceta.htm?idReceta=${dato.CODIGO_RECETA}" ><span class="glyphicon glyphicon-import" aria-hidden="true"></span></a>     
                           </td>  
                        </tr>
                    </rec:forEach>
                </tbody>
            </table>
               
            
        </div>
        </div>
    </body>
</html>
