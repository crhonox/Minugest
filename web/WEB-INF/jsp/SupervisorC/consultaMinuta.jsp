<%-- 
    Document   : consultaMinuta
    Created on : 11-03-2017, 12:10:16 AM
    Author     : Sir Lekxas
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"/>
        <script type="text/javascript" src="/path/to/bootstrap-datetimepicker.min.js"></script>
        <link rel="stylesheet" href="/path/to/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />
        
        <script type="text/javascript" src="/bower_components/jquery/jquery.min.js"></script>
  <script type="text/javascript" src="/bower_components/moment/min/moment.min.js"></script>
  <script type="text/javascript" src="/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
  <link rel="stylesheet" href="/bower_components/bootstrap/dist/css/bootstrap.min.css" />
  <link rel="stylesheet" href="/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />
  
  
	<link rel="stylesheet" type="text/css" href="resources/calendario/tcal.css" />
	<script type="text/javascript" src="resources/calendario/tcal.js"></script>
        
        <title>Minugest</title>
    </head>
    <body>
        <h1 align="center">Minugest</h1>
        <br>
        <hr/>       
     
        <div>
        <div id="left">
            <ul class="nav nav-pills nav-stacked">          
            <li><a href="consultaMinuta.htm">Consultar Minuta</a></li>
            <li><a href="minutaDia.htm">Minuta del Día</a></li>
        </ul>
            </div>
            <div class="container">
                <center>
                  <form:form method="POST" commandName="minuta">
       <form:label path="Fecha_Min"></form:label> 
<div class="input-group date" id="datetimepicker1" >
    
    <input path="Fecha_Min" type="text" class="form-control"/></p>

<span class="input-group-addon" >
    <span class="glyphicon glyphicon-calendar" />
</span>
    <script type="text/javascript">
        $(function (){
            $('#datetimepicker1').datetimepicker();
        });
    </script>
</div>         
                     <form:button type="submit" class="btn btn-default">Buscar</form:button>
                  </form:form>
                
                </center>  
            
                
            </div>
            
            
        </div>
    </body>
</html>
