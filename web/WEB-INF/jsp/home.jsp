<%-- 
    Document   : home
    Created on : 09-03-2017, 20:38:14
    Author     : crhonox
--%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<!DOCTYPE html>
<html>
     <head>
        <meta charset="UTF-8"/>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/disetest.css"/>"/>
        <title>Minugest</title>
    </head>
        <%--  ${pageContext.request.contextPath} codigo para conseguir la ruta absoluta del directiorio
              <c:url value="/resources/css/styles.css" />
        --%>

    <body>
        <div class="agile_header">
        <div class="container2"> 
            <div class="espacio1">
            </div>
            <div class="agile-login">
                
            </div>
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
        
        <div class="logo_gest">
            <div class="conta">
                <div class="contacto-gest">
                   <ul> 
                       <ul>
                           <a><span class="glyphicons glyphicons-address-book" aria-hidden="true"></span>Contactenos (+56) 97597708</a>
                       </ul>
                   </ul>
                </div>
                <div class="logo-gest-center">
                    <h1 align="center">Minugest</h1>
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
                            <li class="active"><a href="home" style="color: #fff;"> Inicio </a></li>
                            <li><a href="compañia.htm" style="color: #fff;">Compañia</a></li>
                            <li><a href="soluciones.htm" style="color: #fff;">Soluciones</a></li>
                            <li><a href="socios.htm" style="color: #fff;">Socios</a></li>
                        </ul> 
                    </div>
                </div>
            </div>
        </div>
        
         <sec:authorize access="!hasAnyRole('AdministradorA','Supervisor','Encargado')">
        <div class="skdslider">
            <ul id="demo1" class="slides">
               <li> <center><img src="resources/image/2s.jpg"></center> </li>
               <li> <center><img src="resources/image/1s.jpg"></center> </li>
               <li> <center><img src="resources/image/3s.jpg"></center> </li>
            </ul>
            <script src="<c:url value="/resources/js/skdslider.min.js"/>" ></script>
<script type="text/javascript">
		jQuery(document).ready(function(){
			jQuery('#demo1').skdslider({'delay':5000, 'animationSpeed': 2000,'showNextPrev':true,'showPlayButton':true,'autoSlide':true,'animationType':'fading'});
						
			jQuery('#responsive').change(function(){
			  $('#responsive_wrapper').width(jQuery(this).val());
			});
			
		});
</script>
            
            <ul class="slide-navs" style="margin-left: -24px">
                <li class="slide-nav-0"></li>
                <li class="slide-nav-1"></li>
                <li class="slide-nav-2"></li>
            </ul>
                <a class="prev"></a>
                <a class="next"></a>
                <a class="play-control pause" style="display: none;"></a>
            
        </div></sec:authorize>
        
        <sec:authorize access="hasRole('AdministradorA')">
            
            
                
            
        </sec:authorize>
        
        
        
        
           
        <div id="sidebar" class="nav-collapse">
            <div class="leftside-navigation" style="overflow: hidden; outline: none;" tabinex="4000">
                <ul class="sidebar-menu" id="nav-accordion">
                    <sec:authorize access="hasRole('AdministradorA')">
                    <li><a href="Administracion/cliente.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Clientes</a></li>
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
            
            
    </body>
</html>