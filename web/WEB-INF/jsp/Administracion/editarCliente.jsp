<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
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

        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/disetest.css"/>"/>
        <title>Minugest</title>
    </head>
    <body>
       <div class="logo_gest">
            <div class="conta">
                <div class="contacto-gest">
                    <a href="/Minugest/home">Volver al inicio</a>
                </div>
                <div class="logo-gest-center">
                    <h1 align="center">MinuGest</h1>
                     <br>
                </div>
                <div class="logo-gest-left">
     <sec:authorize access="hasRole('AdministradorA')">
		<!-- For login user -->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h4>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
                                        
			</h4>
                                        <h4>ESTE ROL ES EL ADMINISTRADOR</h4>
		</c:if>
                </sec:authorize>
                
                <sec:authorize access="hasRole('Supervisor')">
		<!-- For login user -->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h4>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
                                        
			</h4>
                                        <h4>ESTE ROL ES EL Supervisor</h4>
		</c:if>
                

	</sec:authorize>
                
                 
                                        <sec:authorize access="hasRole('Encargado')">
		<!-- For login user -->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h4>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
                                        
			</h4>
                                        <h4>ESTE ROL ES EL ENCARGADO</h4>
                </c:if>
                

	</sec:authorize>
        <sec:authorize access="!hasAnyRole('AdministradorA','Supervisor','Encargado')">
            <div class="icon-login">
                <ul>
                    <ul>
                        <a href="login"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>Iniciar sesión</a>
                    </ul>
                </ul>
            </div>
            </sec:authorize>                                
                </div>
            </div>
       </div>
       
       <div class="barra-contenido">
           <div class="conta">
               <div class="espacio2">
            </div>
                <div class="navbar">
                    <div class="navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="/Minugest/home" style="color: #fff;"> Inicio </a></li>
                            <li><a href="/Minugest/compañia.htm" style="color: #fff;">Compañia</a></li>
                            <li><a href="/Minugest/soluciones.htm" style="color: #fff;">Soluciones</a></li>
                            <li><a href="/Minugest/socios.htm" style="color: #fff;">Socios</a></li>
                        </ul> 
                    </div>
                </div>
            </div>
        </div> 
        
        <div id="sidebara" class="nav-collapse">
            <div class="leftside-navigation" style="overflow: hidden; outline: none;" tabinex="4000">
                <ul class="sidebar-menu" id="nav-accordion">
                    <sec:authorize access="hasRole('AdministradorA')">
                    <li><a href="cliente.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Clientes</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('Supervisor')">
                    <li><a href="Supervisor/Solicitudes.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Solicitudes</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('Encargado')">
                    <li><a href="Encargado/Minuta.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Planificacion de Minutas</a></li>
                    <li><a href="Encargado/receta.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Gestion de Recetas</a></li>
                    <li><a href="Encargado/ingrediente.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;"> Ingredientes </a> </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
        
        
        <div class="menu-conte-wra">
            <ol class="breadcrumb">
                <li><a href="<c:url value="/Administracion/cliente.htm" />">Listado de clientes</a></li>
                <li class="active">Editar</li>
            </ol>
            <div class="panel panel-primary">
                <div class="panel-heading">Editar</div>
                <div class="panel-body">
                   
                        <form:form method="post" commandName="cliente">
                            <h1>Complete el formulario</h1>
                            
                            <form:errors path="*" element="div" cssClass="alert alert-danger" />
                            <p><form:label path="rut">Rut  :</form:label><form:input readonly="true" path="rut" cssClass="form-control"/></p>
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
         
      });

           }

       });
     });
            </script>
            
             <script>
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
    </body>
</html>
