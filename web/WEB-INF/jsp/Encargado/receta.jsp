<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
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
          <div class="row">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                <th>N°</th>
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
                            <td><rec:out value="${dato.CODIGO_RECETA}"/></td>
                            <td><rec:out value="${dato.NOMBRE_RECETA}"/></td>
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
                <a href="AñadirReceta.htm" class="btn btn-success">Añadir Receta</a>
            
        </div>
        </div>
    </body>
</html>
