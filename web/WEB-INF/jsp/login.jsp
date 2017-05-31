<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>

</head>
<body onload='document.loginForm.username.focus();'>

	
            <br><a href="home">Volver al inicio</a> </h1>
            <div id="titulocenter"><h1 align="center">Minugest</h1></div>
            
             <div class="container">
                 <div class="row">
                <div class="panel panel-primary"> 
	
                    <div class="panel-heading"><center>Inicio de Sesion</center></div>
                    <div class="panel-body">
		
                
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
		  action="<c:url value='/j_spring_security_check' />" method='POST'>
                    
		
                    <label>RUT :</label>
                                <input type='text' name='CODIGO_USUARIO' class="form-control">
			
			
                                <label>Password :</label>
                                <input type='password' name='PASS_USUARIO' class="form-control" />
			<p></p>
				<input name="submit" type="submit" value="Ingresar" class="btn btn-danger" />
			
		  

		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		</form>
                    </div>
                        </div>
                        </div>
             </div>
</body>
</html> 