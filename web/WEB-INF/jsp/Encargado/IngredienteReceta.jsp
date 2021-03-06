<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Encargado</title>
         <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/combobox.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/moment.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js"/>"></script>
 
   <script type="text/javascript" src="<c:url value="/resources/js/validator.js"/>"></script>
 
  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>" />
  <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>" />
  <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.combobox.css"/>" />
  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css"/>" />
 
 <style>
  .ui-combobox {
  position: relative;
  display: inline-block;
  }
  .ui-button {
  position: absolute;
  top: 0;
  bottom: 0;
  margin-left: -1px;
  padding: 0;
  /* adjust styles for IE 6/7 */
  *height: 1.7em;
  *top: 0.1em;
  }
  .ui-autocomplete-input {
  margin: 0;
  padding: 0.3em;
  }
 </style>
 
       
 
   <script type="text/javascript">
     $(document).ready(function() {
    var max_fields      = 20; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID
   
    var x = 1; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
           x++; //text box increment
           $(wrapper).append('<div><table>\n\
<tr><td><div class="form-group"><select id="combobox" name="combobox" class="form-control combobox" style="width:600px" required><option value="0">Seleccione...</option><rec:forEach items="${ingrediente}" var="ing"> <option value="${ing.CODIGO_INGREDIENTE}"> ${ing.NOMBRE_INGREDIENTE} (${ing.NOMBRE_UNIDAD})</option></rec:forEach></select></td><td><a href="#" class="remove_field">Remove</a></div></td>\n\
<td>&nbsp;</td><td><div class="form-group"><label for="Cantidad">Cantidad :</label> </td><td><input id="Cantidad" name="Cantidad" class="form-control" type="text" value="" required/></div></td></tr></table></div>');//add input box
        }
       
        $(".combobox").combobox();
            $('#receta').validator('update');
 
    });
 
    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).closest('div').remove(); x--;
        $('#receta').validator('update');
    });
   
});
  </script>
  
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/disetest.css"/>"/>
        <title>Minugest</title>
       
    </head>
    <body> 
       <div class="logo_gest" style="height: 150px; margin-top: 1px;">
            <div class="conta">
                <div class="contacto-gest" float="right">
               
                           <center><img src="../resources/image/p3b.jpg"></center>
                 
                </div>
                <div class="logo-gest-center">
                    <br>
                    <br>
                    <center><img src="../resources/image/p3c.jpg"></center>
                     <br>
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
                    <li><a href="Solicitudes.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Solicitudes</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('Encargado')">
                    <li><a href="Minutas.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Planificación de Minutas</a></li>
                    <li><a href="receta.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;">Gestión de Recetas</a></li>
                    <li><a href="ingrediente.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;"> Ingredientes </a> </li>
                    <li><a href="Solicitudes.htm" class="btn btn-success btn-sm" style="width: 140px; height: 30px; margin-bottom: 0;padding-left: 0;"> Solicitudes </a> </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
        
        <div class="menu-conte-wra">
        <div class="panel panel-primary">
                <div class="panel-heading"><ol class="breadcrumb">
                <li><a href="<rec:url value="receta.htm" />">Listado de Recetas</a></li>
                <li class="active">Agregar Ingredientes a Receta</li>
            </ol></div>
                <div class="panel-body">
                   
                        <form:form method="post" commandName="receta" data-toggle="validator">
                           
                           
                            <form:errors path="*" element="div" cssClass="alert alert-danger" />
                            <p>
                                <form:label path="idReceta">ID: ${idReceta}</form:label>
                            </p>
                            <p>
                                <form:label path="nombreReceta">Nombre de Receta : ${nombre}</form:label>
                            </p>
                        <p>
                            <form:label path="porcionReceta">Cantidad de Porción  : ${porcion}</form:label>
                        </p>
                        <p> <form:label path="combobox">Ingredientes:</form:label>
                         
                        <div class="input_fields_wrap">
                            <table>
                         <button class="add_field_button">Agregar mas ingredientes</button>
                       
                             <tr>
                                 <td>
                                     <div class="form-group">
                           <form:select path="combobox" cssClass="form-control combobox" cssStyle="width:600px" multiple="false" required="required" >
                            <form:option value="">Seleccione...</form:option>
                                <rec:forEach items="${ingrediente}" var="ing">  
                                    <form:option value="${ing.CODIGO_INGREDIENTE}"> ${ing.NOMBRE_INGREDIENTE} (${ing.NOMBRE_UNIDAD})</form:option>
                                </rec:forEach>                            
                           </form:select></p></div>
                                     <div class="help-block with-errors"></div>
                             </td>
                             <td>       </td>
                             
                             <td>  
                             <form:label path="Cantidad">Cantidad   : </td></form:label>
                             <td><form:input path="Cantidad" cssClass="form-control" required="required"/></td>
                             
                                 </div>
                             </tr>
 
                    </table>
                   
                                 </div>
 
                            <hr />
                            <input type="submit" value="Enviar" class="btn btn-success" />
                            <a href="receta.htm" class="btn btn-danger">Volver</a>
                        </form:form>
                           
                            <script>
                $('#receta').validator({
                    excluded: ':disabled',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        combobox: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe agregar uno o mas ingredientes '
                                }
                               
                            }
                        },
                        Cantidad: {
                            validators: {
                                notEmpty: {
                                    message: 'La cantidad es requerida y no puede ser vacío'
                                }
                            }
                        }
                    },
                       
                });
        </script>
                </div>
            </div>
        </div>
    </body>
</html>
