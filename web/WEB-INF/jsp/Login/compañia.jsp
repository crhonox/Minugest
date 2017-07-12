<%-- 
    Document   : compañia
    Created on : 11-03-2017, 12:15:12 AM
    Author     : Sir Lekxas
--%>

<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/disetest.css"/>"/>
        <title>Minugest</title>
    </head>
    <body>
        <div class="logo_gest" style="height: 150px; margin-top: 1px;">
            <div class="conta">
                <div class="contacto-gest" float="right">
               
                           <center><img src="resources/image/p3b.jpg"></center>
                 
                </div>
                <div class="logo-gest-center">
                    <br>
                    <br>
                    <h1 align="center">Nosotros</h1>
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
                <sec:authorize access="hasRole('AdministradorB')">
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
                                        <h4>ESTE ROL ES EL ADMINISTRADOR CLIENTE</h4>
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
          <sec:authorize access="!hasAnyRole('AdministradorA','AdministradorB','Supervisor','Encargado')">
            <div class="icon-login">
                <ul>
                    <ul><br>
                           <br>
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
                            <li class="active"><a href="home" style="color: #fff;"> Inicio </a></li>
                            <li><a href="compañia.htm" style="color: #fff;">Compañía</a></li>
                            <li><a href="soluciones.htm" style="color: #fff;">Soluciones</a></li>
                            <li><a href="socios.htm" style="color: #fff;">Socios</a></li>
                        </ul> 
                    </div>
                </div>
            </div>
        </div>
        
        <div class="menu-conte-wra" style="width: 1000px;">
            <p>
            <table> <td>
         <div class="row" style="width: 550px;">
             <center><div class="panel panel-primary" style="width: 500px;"> 
             <center><table>
                     <div class="panel-heading" ><center><label>Misión</label></center></div>
                     <div class="panel-body"><center>
            
     InfoQuest es una empresa emergente en la comercialización del producto MinuGest, satisface las necesidades de las empresas del rubro de la alimentación de la Región Metropolitana, brindando un servicio excelente y de calidad. Tiene como objetivo el crecimiento continuo de la empresa y el desarrollo profesional de sus trabajadores.
            </center></div>     
            <br>
            <center><label>Visión</label></center>
            <center>
   InfoQuest busca consolidar el liderazgo de MinuGest en el mercado nacional, a través de la confianza y las buenas prácticas con nuestros clientes, expandiendo el servicio a todas las empresas del rubro de la alimentación, situándonos como una de las empresas con el mejor sistema de gestión y planificación dedicado a minutas de alimentos. 
            </center>
            <br>
            <center><label>Valores</label></center>
            <center>
     Los valores de InfoQuest reflejan la empresa que aspiran ser, estos valores describirán el comportamiento de InfoQuest en el futuro: la valentía de modelar soluciones de distintos rubros, cuidar de los clientes, hablar con la verdad y transparencia en todo momento, responsabilidad y pasión en el trabajo realizado.
            </center>
                </table>
            </center> 
         
         </div></center>
         </div></td>
            </p>
            <p>
            <td>
                <center><img src="resources/image/admi.jpg"></center></td>
            </p></table>
        </div> 

        
           
        
        
        
    </body>
</html>
