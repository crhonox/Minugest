<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <title>Minugest</title>
    </head>
    <body>
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="panel panel-primary"> 
                    <div class="panel-heading">Formulario</div>
                    <div class="panel-body">
                <form:form method="POST" commandName="usuario">
                    
                    <input id="RutEmpresa" name="RutEmpresa" type="text" value="${rutEmpresa}" hidden="true"/>
                     <div class="form-group">
                    <form:label path="nombre">Nombre  :</form:label>
                    <form:input path="nombre" cssClass="form-control"/>
                     </div>
                      <div class="form-group">
                    <form:label path="apellido">Apellido  :</form:label>
                     <form:input path="apellido" cssClass="form-control"/>
                      </div>
                       <div class="form-group">
                    <form:label path="pass">Contraseña  :</form:label>
                    <form:password path="pass" cssClass="form-control" />
                       </div>
                        <div class="form-group">
                    <form:label path="rut">Rut  :</form:label>
                    <form:input path="rut" cssClass="form-control"/>
                        </div>
                     <div class="form-group">
                    <form:label path="correo">Correo  :</form:label>
                    <form:input path="correo" cssClass="form-control"/>
                     </div>
                     <div class="form-group">
                    <form:label path="cod_perfil">Perfil  :</form:label>
                    <form:select path="cod_perfil" cssClass="form-control">
                            <form:option value="">Seleccione...</form:option>
                                <c:forEach items="${perfiles}" var="perfil">   
                                    <form:option value="${perfil.CODIGO_PERFIL}">${perfil.NOMBE_PERFIL}</form:option>
                                </c:forEach>                            
                    </form:select>
                     </div>
            
            
            <form:button class="btn btn-danger" >Enviar</form:button>
            
        </form:form>
            
             <script>
                $('#usuario').bootstrapValidator({
                    excluded: ':disabled',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        nombre: {
                            validators: {
                                notEmpty: {
                                    message: 'El nombre es requerido'
                                }
                            }
                        },
                        apellido: {
                            validators: {
                                notEmpty: {
                                    message: 'El apellido es requerido'
                                }
                            }
                        },
                        rut: {
                            validators: {
                                id: {
                                    country: 'CL',
                                    message: 'El Rut Es Invalido'
                                },
                                notEmpty: {
                                    message: 'El Rut es requerido'
                                },
                                stringLength: {
                                    min: 9,
                                    message: 'El Rut debe contener al menos 9 caracteres'
                                }
                            }
                        },
                        pass: {
                            validators: {
                                notEmpty: {
                                    message: 'La Contraseña es requerida y no puede ser vacío'
                                }
                            }
                        },
                       
                         
                        cod_perfil: {
                            message: 'El perfil no es valido',
                            validators: {
                                notEmpty: {
                                    message: 'El perfil es requerido y no puede ser vacío'
                                }
                            }
                        },
                        correo: {
                            message: 'El correo no es valido',
                            validators: {
                                notEmpty: {
                                    message: 'El correo es requerido y no puede ser vacío'
                                },
                                emailAddress: {
                        message: 'El correo no es valido'
                    }
                            }
                        }
                    }
                });
        </script>
     
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>