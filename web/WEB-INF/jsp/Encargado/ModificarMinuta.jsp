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
   <script type="text/javascript" src="<c:url value="/resources/js/bootstrapValidator.js"/>"></script>
  
        <title>Encargado</title>
    </head>
     <body>
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="panel panel-primary"> 
                    <div class="panel-heading">Formulario de Minuta</div>
                    <div class="panel-body">
       <form:form  method="POST" commandName="Minuta">
                    <h1></h1>
       <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <p><div class="form-group">
                        <form:label path="Nombre_Min">Nombre de Minuta:</form:label>
                        <form:input path="Nombre_Min" cssClass="form-control"/>
                    </div> </p>
            <p></p>
                    <p><div class="form-group">
                        <form:label path="Codigo_Casi">Casino:</form:label>
                        <form:select path="Codigo_Casi" cssClass="form-control">
                            <form:option value="">Seleccione...</form:option>
                                <rec:forEach items="${casino}" var="cas">   
                                    <form:option value="${cas.CODIGO_CASINO}"> ${cas.NOMBRE_CASINO}</form:option>
                                </rec:forEach>                            
                        </form:select></div></p>
         <p><div class="form-group">
         <form:label path="Fecha_Min">Fecha:</form:label> 
<div class="input-group date" id="datetimepicker3" >
    
    <input id="Fecha_Min" name  ="Fecha_Min" type="text" class="form-control" value="" /></p>

<span class="input-group-addon" >
    <span class="glyphicon glyphicon-calendar" />
</span>
    <script type="text/javascript">
            $(function () {
                $('#Fecha_Min').datetimepicker({
                    format: 'YYYY-MM-DD'
                });
            });
        </script>
    </div> 
</div>
    <input id="Codigo_User" name="Codigo_User" class="form-control" type="hidden" value="${pageContext.request.userPrincipal.name}" />
                    
                
            <form:button class="btn btn-danger" >Enviar</form:button>
        </form:form>
             <script>
                $('#Minuta').bootstrapValidator({
                    excluded: ':disabled',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        Nombre_Min: {
                            validators: {
                                notEmpty: {
                                    message: 'El nombre es requerido y no puede ser vacío'
                                }
                                
                            }
                        },
                        Codigo_Casi: {
                            validators: {
                                notEmpty: {
                                    message: 'El casino es requerida y no puede ser vacío'
                                }
                            }
                        },
                        Fecha_Min: {
                            validators: {
                                notEmpty: {
                                    message: 'La fecha es requerida y no puede ser vacío'
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
