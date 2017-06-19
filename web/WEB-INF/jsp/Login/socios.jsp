<%-- 
    Document   : socios
    Created on : 11-03-2017, 12:16:42 AM
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/disetest.css"/>"/>
        <title>Minugest</title>
    </head>
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
                    <h1 align="center">Socios</h1>
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
        
        <div class="menu-conte-wra">
            <p>
            <center> Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla <br>
                 Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla <br>
                  Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla <br>
                  Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla <br>
                  Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla <br>
            </center>
            </p>
            <p>
                <center><img src="resources/image/soci.jpg"></center>
            </p>
        </div> 
    </body>
</html>
