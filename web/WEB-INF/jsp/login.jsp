<%@page session="true"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->

<html>
<head>
<title>Minugest</title>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/disetest.css"/>"/>
</head>
<body onload='document.loginForm.username.focus();'>

    
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
    
             <div class="menu-conte-wra">
               
                 <div class="row">
                     <center><div class="panel panel-primary" style="width: 400px;"> 
	                     
                              
                    <div class="panel-heading" ><center>Inicio de Sesion</center></div>
                 
                         <center><table> 
                    <div class="panel-body">
		   
                
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
<td> 
		<form name='loginForm'
		  action="<c:url value='/j_spring_security_check' />" method='POST'>
                    
		
                    <label>RUT :</label>
                                <input type='text' name='CODIGO_USUARIO' class="form-control">
			
			
                                <label>Password :</label>
                                <input type='password' name='PASS_USUARIO' class="form-control" />
			<p></p>
				<input name="submit" type="submit" value="Ingresar" class="btn btn-danger" />
			
		  </td>
                    
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
<td>
                        <center>
                            <img src="resources/image/secury.jpg">
                        </center>
                     </td>
		</form>
                    </div>
                        </div>
                        </div>
                        
                    
                 </table></center>
                         </div></center>
</body>
</html> 
