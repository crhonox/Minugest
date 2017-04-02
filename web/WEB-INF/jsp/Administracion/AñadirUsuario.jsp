<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <title>Minugest</title>
    </head>
    <body>
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="panel panel-primary"> 
                    <div class="panel-heading">Formulario</div>
                    <div class="panel-body">
                <form:form method="POST" commandName="usuario">
                    <h1></h1>
                    <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <input id="RutEmpresa" name="RutEmpresa" type="text" value="${rutEmpresa}" hidden="true"/>
                    <p><form:label path="nombre">Nombre  :</form:label><form:input path="nombre" cssClass="form-control"/></p>
                    <p><form:label path="apellido">Apellido  :</form:label><form:input path="apellido" cssClass="form-control"/></p>
                    <p><form:label path="pass">Contraseña  :</form:label><form:password path="pass" cssClass="form-control" /></p>
                    <p></p>
                    <p><form:label path="rut">Rut  :</form:label><form:input path="rut" cssClass="form-control"/></p>
                    <p></p>
                    <p><form:label path="correo">Correo  :</form:label><form:input path="correo" cssClass="form-control"/></p>
                    <p></p>
                    <p><form:label path="cod_perfil">Perfil  :</form:label><form:select path="cod_perfil" cssClass="form-control">
                            <form:option value="0">Seleccione...</form:option>
                                <c:forEach items="${perfiles}" var="perfil">   
                                    <form:option value="${perfil.CODIGO_PERFIL}">${perfil.NOMBE_PERFIL}</form:option>
                                </c:forEach>                            
                        </form:select></p>
                    <p></p>
                    <p><form:label path="cod_casino">Casino  :</form:label><form:select path="cod_casino" cssClass="form-control">
                            <form:option value="0">Seleccione...</form:option>
                        
            <c:forEach items="${casinos}" var="casino">
                <form:option value="${casino.CODIGO_CASINO}">${casino.NOMBRE_CASINO}</form:option>
            </c:forEach>
                            </form:select>
                    </p>
            <p></p>
            
            <form:button class="btn btn-danger" >Enviar</form:button>
            
        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>