<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="ingr" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
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
        <br>
        <hr/>
        
        <h1>Ingredientes</h1>
        <div class="row">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                <th>N°</th>
                <th>Nombre</th>
                <th>Unidad de Medida</th>
                <th>Acciones</th>

                     </tr>
                </thead>
                <tbody>
                    <ingr:forEach items="${datos}" var="dato" >
                        <tr>
                            <td><ingr:out value="${dato.CODIGO_INGREDIENTE}"/></td>
                            <td><ingr:out value="${dato.NOMBRE_INGREDIENTE}"/></td>
                            <td><ingr:out value="${dato.NOMBRE_UNIDAD}"/></td>
                            <td><a href="editarIngrediente.htm?idIngrediente=${dato.CODIGO_INGREDIENTE}" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>     
                       
                           
                        </tr>
                    </ingr:forEach>
                </tbody>
            </table>
                <a href="AñadirIngrediente.htm" class="btn btn-success">Añadir Ingrediente</a>
            
        </div>
        </div>
    </body>
</html>