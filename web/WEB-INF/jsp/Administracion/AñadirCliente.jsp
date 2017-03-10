<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <form:form>
            <p><form:label path="Nombre">Nombre  :</form:label><form:input path="Nombre"/></p>
            <p></p>
            <p><form:label path="Region">Region  :</form:label><form:input path="Region"/></p>
            <p></p>
            <p><form:label path="Comuna">Comuna  :</form:label><form:input path="Comuna"/></p>
            <p></p>
            <p><form:label path="Direccion">Direccion  :</form:label><form:input path="Direccion"/></p>
            <p></p>
            <p><form:label path="rut">Rut  :</form:label><form:input path="rut"/></p>
            <p></p>
            <p><form:label path="email">E-mail  :</form:label><form:input path="email"/></p>
            <p></p>
            <p><form:label path="Telefono">Telefono  :</form:label><form:input path="Telefono"/></p>
            <p></p>
            <form:button>Enviar</form:button>
            
        </form:form>
        
    </body>
</html>
