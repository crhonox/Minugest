<%-- 
    Document   : consultaMinuta
    Created on : 11-03-2017, 12:10:16 AM
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
        <div id="left">
            <ul class="nav nav-pills nav-stacked">          
            <li><a href="consultaMinuta.htm">Consultar Minuta</a></li>
            <li><a href="minutaDia.htm">Minuta del Día</a></li>
        </ul>
            </div>
            <div class="container">
               <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                    <input type="text" class="form-control" placeholder="Fecha de minuta">
                     </div>
                     <button type="submit" class="btn btn-default">Buscar</button>
                </form>   
            </div>
            
            
        </div>
    </body>
</html>
