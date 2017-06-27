<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
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
  <script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/disetest.css"/>"/>
        
  
        <title>Encargado</title>
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
                    <li><a href="Minuta.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Planificacion de Minutas</a></li>
                    <li><a href="receta.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Gestion de Recetas</a></li>
                    <li><a href="ingrediente.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;"> Ingredientes </a> </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
         
         <div class="menu-conte-wra">
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
            <form:button class="btn btn-success" >Enviar</form:button>
            <a href="Minutas.htm" class="btn btn-danger">Cancelar</a>
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
