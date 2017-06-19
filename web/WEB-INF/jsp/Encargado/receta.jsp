<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- Importar funciones de spring jstl -->
<!DOCTYPE html>
<html>
   <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/disetest.css"/>"/>
        <title>Encargado</title>
    </head>
    <body>
      
        <div class="agile_header">
        <div class="contai"> 
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
        
        <div id="sidebar" class="nav-collapse">
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
         <h1>Recetas</h1>
          <div class="row">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                <th>N°</th>
                <th>Nombre</th>
                <th>Categoria</th>
                <th>Descripción</th>
                <th>Porciones</th>
                <th>Acciones</th>
               

                     </tr>
                </thead>
                <tbody>
                    <rec:forEach items="${datos}" var="dato" >
                        <tr>
                            <td><rec:out value="${dato.CODIGO_RECETA}"/></td>
                            <td><a href="DetalleReceta.htm?COD=${dato.CODIGO_RECETA}"><rec:out value="${dato.NOMBRE_RECETA}"/></a></td>
                            <td><rec:out value="${dato.NOMBRE_CATEGORIA}"/></td>
                            <td><rec:out value="${dato.DESCRIPCION_RECETA}"/></td>
                            <td><rec:out value="${dato.CANTIDAD_PORCION}"/></td>
                            <td><a href="editarReceta.htm?idReceta=${dato.CODIGO_RECETA}" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>   
                                <a href="AñadirIngredienteReceta.htm?idReceta=${dato.CODIGO_RECETA}" ><span class="glyphicon glyphicon-import" aria-hidden="true"></span></a>     
                           </td>  
                        </tr>
                    </rec:forEach>
                </tbody>
            </table>
                <a href="AñadirReceta.htm" class="btn btn-success">Añadir Receta</a>
            
        </div>
        </div>
    </body>
</html>
