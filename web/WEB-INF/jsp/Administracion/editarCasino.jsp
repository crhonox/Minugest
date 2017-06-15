<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Minugest</title>
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
    </head>
    <body>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="<c:url value="listaCasino.htm?rut=${COD}" />">Listado de casinos</a></li>
                <li class="active">Editar</li>
            </ol>
            <div class="panel panel-primary">
                <div class="panel-heading">Editar</div>
                <div class="panel-body">
                   
                        <form:form method="post" commandName="casino">
                            <h1>Complete el formulario</h1>
                            
                            <form:errors path="*" element="div" cssClass="alert alert-danger" />
                            <p><form:label path="RutEmpresa">Rut EMPRESA :</form:label><form:input readonly="true" path="RutEmpresa" cssClass="form-control"/></p>
                            <div class="form-group">
                    <form:label path="NombreCasino">Nombre  :</form:label>
                    <form:input path="NombreCasino" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                    <form:label path="RegionCasino">Region  :</form:label>
                    <form:select path="RegionCasino" cssClass="form-control">
                            <form:option value="">Seleccione...</form:option>
                                <c:forEach items="${regiones}" var="region">   
                                    <form:option value="${region.REGION_ID}">${region.REGION_NOMBRE}</form:option>
                                </c:forEach>                            
                        </form:select>
                    </div>
                            
            <div class="form-group">
            <form:label path="ProvinciaCasino">Provincia  :</form:label>    
            <form:select class="form-control"  path="ProvinciaCasino">
                    <form:option value="" label="--Seleccione Provincia--"/>
                </form:select>
                    </div>
            <div class="form-group">
            <form:label path="ComunaCasino">Comuna  :</form:label>
            <form:select path="ComunaCasino" cssClass="form-control">
                            <form:option value="">Seleccione...</form:option>
                            </form:select>
                    </div>
            <div class="form-group">
              <form:label path="DireccionCasino">Direccion  :</form:label>
              <form:input path="DireccionCasino" cssClass="form-control"/>
            </div>       
                            <input type="submit" value="Enviar" class="btn btn-danger" />
                        </form:form>
                </div>
            </div>
        </div>
                <script>
                    $(document).ready(function() {
                $("select#RegionCasino").change(function(){
                    var region = $("select#RegionCasino").val();
       $.ajax({
           type:'GET',
           url:'buscarProvincia.do',
           data:{regionID: region},

            headers: {
             Accept: 'application/json'
            },
           dataType: 'json',

           success:function(response){

            var select = $('#ProvinciaCasino');
        select.find('option').remove();
        $('#ProvinciaCasino').html('<option value="">Seleccione</option>');
          $.each(response, function(i,data) {
             var div_data="<option value="+data.ProvinciaId+">"+data.ProvinciaName+"</option>";
            $(div_data).appendTo('#ProvinciaCasino');
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
                $("select#ProvinciaCasino").change(function(){
                    var region = $("select#ProvinciaCasino").val();
       $.ajax({
           type:'GET',
           url:'buscarComuna.do',
           data:{provinciaID: region},

            headers: {
             Accept: 'application/json'
            },
           dataType: 'json',

           success:function(response){

            var select = $('#ComunaCasino');
        select.find('option').remove();
        $('#ComunaCasino').html('<option value="">Seleccione</option>');
          $.each(response, function(i,data) {
             var div_data="<option value="+data.ComunaId+">"+data.ComunaName+"</option>";
            $(div_data).appendTo('#ComunaCasino');
          //$('#Provincia').append($('<option></option>').val(response).html(vartext.ProvinciaName));
          //$('<option>').val(varvalue.ProvinciaId).html(vartext.ProvinciaName).appendTo(select);
      });

           }

       });
     });
      });
            </script>
            
            
            <script>
                $('#casino').bootstrapValidator({
                    excluded: ':disabled',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        NombreCasino: {
                            validators: {
                                notEmpty: {
                                    message: 'El nombre es requerido'
                                }
                            }
                        },
                        
                        
                        RegionCasino: {
                            validators: {
                                notEmpty: {
                                    message: 'La Region es requerida y no puede ser vacío'
                                }
                            }
                        },
                        DireccionCasino: {
                            validators: {
                                notEmpty: {
                                    message: 'La dirección es requerida y no puede ser vacío'
                                }
                            }
                        },
                         ProvinciaCasino: {
                            validators: {
                                notEmpty: {
                                    message: 'La provincia es requerida y no puede ser vacío'
                                }
                            }
                        },
                         ComunaCasino: {
                            validators: {
                                notEmpty: {
                                    message: 'La Comuna es requerida y no puede ser vacío'
                                }
                            }
                        }
                    }
                });
        </script>
     
    </body>
</html>
