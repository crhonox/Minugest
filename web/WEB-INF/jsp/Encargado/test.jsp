<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
        <title>Minugest</title>
    </head>
    <body>
        <div class="container">
        <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        
        <h1>Calculo de ingrediente para Minuta ${NombreMinuta}</h1>
        <div class="row">
                    <c:forEach items="${REC}" var="recetas" >
                            <label>${recetas.getNombreReceta()}</label>
                            <table class="table table-bordered table-hover">
                            <thead>
                                 <th>Ingrediente</th>
                                 <th>Cantidad</th>
                                 <th>unidad de medida</th>
                            </thead>
                            <tbody>
                            
                            <c:forEach items="${recetas.getIngredientes()}" var="ing">
                            <tr>
                                <td>  <c:out value="${ing.getNombreIngrediente()}" /> </td>
                                <td>  <c:out value="${ing.getCantidadIngrediente()}" /> </td>
                                <td>  <c:out value="${ing.getUnidadMedida()}" /> </td>
                            </tr>
                            </c:forEach>
                               
                                 </tbody>
                            </table>
                    </c:forEach>
         </div>
        </div>
    </body>
</html>