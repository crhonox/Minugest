<%-- 
    Document   : minutaDiaError
    Created on : 25-04-2017, 10:26:58 AM
    Author     : Sir Lekxas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        
        <title>Minugest</title>
    </head>
    <body>
        <h1 align="center">Minugest</h1>
        <br>
        <hr/>       
        <div>
            <div class="container">
        <div id="left">
        <ul>          
            <li><a href="consultaMinuta.htm">Consultar Minuta</a></li>
            <li><a href="minutaDia.htm">Minuta del Día</a></li>
        </ul>
            </div>
            
            <center><h1>Minuta del Día</h1></center>
            <center><h7>Fecha de hoy : ${fecha} </h7></center>
            
            <div class="row">                
                <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th><center>No Existen Minutas Este Día</center></th>
                </tr>
                </thead>
                </table>
                </div>
        </div>
            </div>
    </body>
</html>
