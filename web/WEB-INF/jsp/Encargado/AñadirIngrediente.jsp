<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ingr" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <div class="panel-heading">Formulario Ingrediente</div>
                    <div class="panel-body">
                <form:form  method="POST" commandName="ingrediente">
                    <h1></h1>
                    <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <div class="form-group">
                    <p><form:label path="nombreIngrediente">Nombre  :</form:label><form:input path="nombreIngrediente" cssClass="form-control"/></p>
                    </div>
            <p></p>
            <p>
                <div class="form-group">
            <form:label path="unidadMedida">Unidad de Medida  :</form:label>
                        <form:select path="unidadMedida" cssClass="form-control">
                            <form:option value="">Seleccione...</form:option>
                                <ingr:forEach items="${unidadMedida}" var="um">   
                                    <form:option value="${um.CODIGO_UNIDAD}">${um.NOMBRE_UNIDAD}</form:option>
                                </ingr:forEach>                            
                        </form:select></p>
                    </div>
                    
            <p></p>
            <form:button class="btn btn-danger" >Enviar</form:button>
        </form:form>
            
            
            <script>
                $('#ingrediente').bootstrapValidator({
                    excluded: ':disabled',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        nombreIngrediente: {
                            validators: {
                                notEmpty: {
                                    message: 'El nombre es requerido y no puede ser vacío'
                                },remote: {
                        type: 'GET',
                        url: 'ValidarIngrediente.do',
                        message: 'El nombre de ingrediente ya existe',
                        delay: 1000
                    }
                                
                            }
                        },
                        unidadMedida: {
                            validators: {
                                notEmpty: {
                                    message: 'La Unidad de medida es requerida y no puede ser vacío'
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
