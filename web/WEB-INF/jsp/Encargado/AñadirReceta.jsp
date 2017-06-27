<%-- 
    Document   : AñadirReceta
    Created on : 13-04-2017, 19:21:44
    Author     : Bayron Cruz C
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
       <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/combobox.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/moment.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/bootstrapValidator.js"/>"></script>

  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrapValidator.css"/>" />
  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>" />
  <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>" />
  <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.combobox.css"/>" />
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
                    <div class="panel-heading">Formulario Receta</div>
                    <div class="panel-body">
       <form:form  method="POST" commandName="receta">
                    <h1></h1>
       <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <p>
                        <div class="form-group">
                        <form:label path="nombreReceta">Nombre  :</form:label>
                        <form:input path="nombreReceta" cssClass="form-control"/>
                        </div>
                    </p>
            <p></p>
            <div class="form-group">
            <p><form:label path="idCategoria">Categoria:</form:label>
                        <form:select path="idCategoria" cssClass="form-control">
                            <form:option value="">Seleccione...</form:option>
                                <rec:forEach items="${idCategoria}" var="cat">   
                                    <form:option value="${cat.CODIGO_CATEGORIA}"> ${cat.NOMBRE_CATEGORIA}</form:option>
                                </rec:forEach>                            
                        </form:select></p>
            </div>
         <p><form:label path="descripcionReceta">Descripción Receta :</form:label>
                        <form:input path="descripcionReceta" cssClass="form-control"/></p>

         <p><div class="form-group">
                <form:label path="porcionReceta">Cantidad de Porción  :</form:label>
                        <input type="number" id="porcionReceta" name="porcionReceta" Class="form-control"/>
         </div>
         </p>
         
            <form:button class="btn btn-danger" >Enviar</form:button>
        </form:form>
            
            <script>
                $('#receta').bootstrapValidator({
                    excluded: ':disabled',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        nombreReceta: {
                            validators: {
                                notEmpty: {
                                    message: 'El nombre es requerido y no puede ser vacío'
                                },remote: {
                        type: 'GET',
                        url: 'ValidarReceta.do',
                        message: 'El nombre de receta ya existe',
                        delay: 1000
                    }
                                
                            }
                        },
                        idCategoria: {
                            validators: {
                                notEmpty: {
                                    message: 'La categoria es requerida y no puede ser vacío'
                                }
                            }
                        },
                        porcionReceta: {
                            validators: {
                                notEmpty: {
                                    message: 'La cantidad es requerida y no puede ser vacío'
                                }
                            }
                        }
                    }, 
                        
                });
        </script>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
