<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->

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
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
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
            <div class="row">
                <div class="panel panel-primary"> 
                    <div class="panel-heading">Formulario</div>
                    <div class="panel-body">
                <form:form method="POST" commandName="casino">
                    
                    <input id="RutEmpresa" name="RutEmpresa" type="text" value="${rutemp}" hidden="true"/>
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
            <form:button class="btn btn-danger" >Enviar</form:button>
            
        </form:form>
                    </div>
                </div>
            </div>
        </div>
        
        <script>
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
            </script>
            
             <script>
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
