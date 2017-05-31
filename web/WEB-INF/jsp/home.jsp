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
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
        <title>Minugest</title>
    </head>
        <%--  ${pageContext.request.contextPath} codigo para conseguir la ruta absoluta del directiorio
              <c:url value="/resources/css/styles.css" />
        --%>

    <body>
        <div> 
            
            <div id="tituloleft"><h1 align="left">Logo</h1>
            <a href="login"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>Iniciar sesión</a>
            </div>
            
            
     
            <div id="titulocenter"><h1 align="center">Minugest</h1></div>
               <div id="tituloright">
         
                   
     </div>
        <hr/>       
            
       </div>
        
        <div>
        <div id="left">
           
        <ul>          
            <li><a href="compañia.htm">Compañia</a></li>
            <li><a href="soluciones.htm">Soluciones</a></li>
            <li><a href="socios.htm">Socios</a></li>
            
            
            
            
        </ul>
            </div>
            <div id="center">
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
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
                                        
			</h2>
                                        <h1>ESTE ROL ES EL ADMINISTRADOR</h1>
                <ul>          
            <li><a href="Administracion/cliente.htm">Clientes</a></li>
            
     
             </ul>
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
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
                                        
			</h2>
                                        <h1>ESTE ROL ES EL ENCARGADO</h1>
                <ul>          
            <li><a href="Encargado/Minuta.htm">Planificacion de Minutas</a></li>
            <li><a href="Encargado/receta.htm">Gestion de Recetas</a></li>
            <li><a href="Encargado/ingrediente.htm"> Ingredientes </a> </li>
     
             </ul>
		</c:if>
                

	</sec:authorize>
            </div>
            
            
        </div>
    </body>
</html>