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
        <div class="logo_gest" style="height: 180px; margin-top: 1px;" >
            <div class="conta">
                <div class="contacto-gest">
                   <ul> 
                       <ul>
                           <br>
                           <br>
                           <a><span class="glyphicons glyphicons-address-book" aria-hidden="true"></span>Contáctenos (+56) 97597708</a>
                       </ul>
                   </ul>
                </div>
                <div class="logo-gest-center" >
                    <center><img src="resources/image/p3.jpg"></center>
                </div>
                <div class="logo-gest-left">
                    <br>
                    <br>
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
                    $(document).ready(function() {
                        var rut_usuario = ${pageContext.request.userPrincipal.name};
                        $.ajax({
                            type:'GET',
                            url:'pendientes.do',
                            data:{rutUser: rut_usuario},

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
                           //$('#Provincia').append($('<option></option>').val(response).html(vartext.ProvinciaName));
                           //$('<option>').val(varvalue.ProvinciaId).html(vartext.ProvinciaName).appendTo(select);
                       });

                            }

                        });
                    });
                </script>
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
                    <ul>
                        <br>
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
        
         <sec:authorize access="!hasAnyRole('AdministradorA','AdministradorB','Supervisor','Encargado')">
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
            
            
            
        </div></sec:authorize>
        
           
        <div id="sidebara" class="nav-collapse">
            <div class="leftside-navigation" style="overflow: hidden; outline: none;" tabinex="4000">
                <ul class="sidebar-menu" id="nav-accordion">
                    <sec:authorize access="hasRole('AdministradorA')">
                    <li><a href="Administracion/cliente.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Clientes</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('AdministradorB')">
                    <li><a href="AdministracionCliente/Usuarios.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Usuarios</a></li>
                    <li><a href="AdministracionCliente/Casinos.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Casinos</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('Supervisor')">
                    <li><a href="Supervisor/Solicitudes.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Solicitudes</a></li>
                    <li><a href="Supervisor/Estadisticas.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Estadisticas</a></li>
                    
                    </sec:authorize>
                    <sec:authorize access="hasRole('Encargado')">
                    <li><a href="Encargado/Minutas.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Planificación de Minutas</a></li>
                    <li><a href="Encargado/receta.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Gestión de Recetas</a></li>
                    <li><a href="Encargado/ingrediente.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;"> Ingredientes </a> </li>
                    <li><a href="Encargado/Solicitudes.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;"> Solicitudes </a> </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
            
        
        <sec:authorize access="hasRole('AdministradorA')">
            
            <div class="menu-conte-wra">
                <center>
                    <table>
                        
                        <td style="width: 350px;">
                        <center>Administrador</center><br>
                        <center>Descripción del Administrador</center>
                        </td>
                        <td><p><br></p></td>
                        <td>
                        <center><img src="resources/image/A1.jpg"></center>
                        </td>
                
                </table>
            </center>
            </div>
            
        </sec:authorize>
         
        <sec:authorize access="hasRole('Supervisor')">
            
            <div class="menu-conte-wra">
                <center>
                    <table>
                        
                        <td style="width: 350px;">
                        <center>Supervisor</center><br>
                        <center>Descripción del Supervisor</center>
                        </td>
                        <td><p><br></p></td>
                        <td>
                        <center><img src="resources/image/A2.jpg"></center>
                        </td>
                
                </table>
            </center>
            </div>
            
        </sec:authorize>
        
        <sec:authorize access="hasRole('Encargado')">
            
            <div class="menu-conte-wra">
                <center>
                    <table>
                        
                        <td style="width: 350px;">
                        <center>Encargado de Minutas</center><br>
                        <center>Descripción del Encargado de Minutas</center>
                        </td>
                        <td><p><br></p></td>
                        <td>
                        <center><img src="resources/image/A3.jpg"></center>
                        </td>
                
                </table>
            </center>
            </div>
            
        </sec:authorize>
    </body>
</html>
