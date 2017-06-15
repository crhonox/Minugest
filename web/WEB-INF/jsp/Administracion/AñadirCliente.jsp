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
                <form:form method="POST" commandName="cliente">
                    
                    
                    <div class="form-group">
                    <form:label path="Nombre">Nombre  :</form:label>
                    <form:input path="Nombre" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                    <form:label path="NombreLargo">Razon Social  :</form:label>
                    <form:input path="NombreLargo" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                    <form:label path="Region">Region  :</form:label>
                    <form:select path="Region" cssClass="form-control">
                            <form:option value="">Seleccione...</form:option>
                                <c:forEach items="${regiones}" var="region">   
                                    <form:option value="${region.REGION_ID}">${region.REGION_NOMBRE}</form:option>
                                </c:forEach>                            
                    </form:select>
                    </div>
            <div class="form-group">
            <form:label path="Provincia">Provincia  :</form:label>    
            <form:select class="form-control"  path="Provincia">
                    <form:option value="" label="--Seleccione Provincia--"/>
                </form:select>
                    </div>
            <div class="form-group">
            <form:label path="Comuna">Comuna  :</form:label><form:select path="Comuna" cssClass="form-control">
                            <form:option value="">Seleccione...</form:option>
                            </form:select>
            </div>
            <div class="form-group">
            <form:label path="Direccion">Direccion  :</form:label>
            <form:input path="Direccion" cssClass="form-control"/>
            </div>
            <div class="form-group">
            <form:label path="rut">Rut Empresa :</form:label>
            <form:input path="rut" cssClass="form-control"/></p>
            </div>
            <div class="form-group">
            <form:label path="email">E-mail Representante :</form:label>
               <form:input path="email" cssClass="form-control"/>
            </div>
            <div class="form-group">
            <form:label path="Telefono">Telefono Representante :</form:label>
            <form:input path="Telefono" cssClass="form-control"/>
            </div>
            <form:button class="btn btn-danger" >Enviar</form:button>
            
        </form:form>
            
            <script>
                $(document).ready(function() {
                $("select#Region").change(function(){
                    var region = $("select#Region").val();
       $.ajax({
           type:'GET',
           url:'buscarProvincia.do',
           data:{regionID: region},

            headers: {
             Accept: 'application/json'
            },
           dataType: 'json',

           success:function(response){

            var select = $('#Provincia');
        select.find('option').remove();
        $('#Provincia').html('<option value="">Seleccione</option>');
          $.each(response, function(i,data) {
             var div_data="<option value="+data.ProvinciaId+">"+data.ProvinciaName+"</option>";
            $(div_data).appendTo('#Provincia');
          //$('#Provincia').append($('<option></option>').val(response).html(vartext.ProvinciaName));
          //$('<option>').val(varvalue.ProvinciaId).html(vartext.ProvinciaName).appendTo(select);
      });

           }

       });
     });
      });
            </script>
            
             <script>
                 $(document).ready(function() {
                $("select#Provincia").change(function(){
                    var region = $("select#Provincia").val();
       $.ajax({
           type:'GET',
           url:'buscarComuna.do',
           data:{provinciaID: region},

            headers: {
             Accept: 'application/json'
            },
           dataType: 'json',

           success:function(response){

            var select = $('#Comuna');
        select.find('option').remove();
        $('#Comuna').html('<option value="">Seleccione</option>');
          $.each(response, function(i,data) {
             var div_data="<option value="+data.ComunaId+">"+data.ComunaName+"</option>";
            $(div_data).appendTo('#Comuna');
          //$('#Provincia').append($('<option></option>').val(response).html(vartext.ProvinciaName));
          //$('<option>').val(varvalue.ProvinciaId).html(vartext.ProvinciaName).appendTo(select);
      });

           }

       });
     });
     });
            </script>
            
              <script>
                $('#cliente').bootstrapValidator({
                    excluded: ':disabled',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        Nombre: {
                            validators: {
                                notEmpty: {
                                    message: 'El nombre es requerido'
                                }
                            }
                        },
                        NombreLargo: {
                            validators: {
                                notEmpty: {
                                    message: 'La razon Social es requerida'
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
                                    message: 'El Rut de la empresa es requerido'
                                },
                                stringLength: {
                                    min: 9,
                                    message: 'El Rut debe contener al menos 9 caracteres'
                                }
                            }
                        },
                        Region: {
                            validators: {
                                notEmpty: {
                                    message: 'La Region es requerida y no puede ser vacío'
                                }
                            }
                        },
                        Direccion: {
                            validators: {
                                notEmpty: {
                                    message: 'La dirección es requerida y no puede ser vacío'
                                }
                            }
                        },
                         Provincia: {
                            validators: {
                                notEmpty: {
                                    message: 'La provincia es requerida y no puede ser vacío'
                                }
                            }
                        },
                         Comuna: {
                            validators: {
                                notEmpty: {
                                    message: 'La Comuna es requerida y no puede ser vacío'
                                }
                            }
                        },
                        Telefono: {
                            message: 'El teléfono no es valido',
                            validators: {
                                notEmpty: {
                                    message: 'El teléfono es requerido y no puede ser vacío'
                                },
                                regexp: {
                                    regexp: /^[0-9]+$/,
                                    message: 'El teléfono solo puede contener números'
                                }
                            }
                        },
                        email: {
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
