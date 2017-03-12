<%-- 
    Document   : login
    Created on : 10-03-2017, 11:09:54 PM
    Author     : Sir Lekxas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <title>Minugest</title>
    </head>
    <body>
        <body>
        <div> 
            
            <div id="tituloleft"><h1 align="left">Logo</h1></div>
            
            
     <div id="tituloright">
         
         <%-- 
             <img src="<c:url value="/public/icons/user-icons.png"/>" />
             --%>
             
             <ul>
                 <li><a href="login.htm"><span class="glyphicons glyphicons-user" aria-hidden="true"></span>Iniciar sesión</a></li>
                 <li><a href="home.htm">Inicio</a></li>
     </ul>
     
     </div>
            <div id="titulocenter"><h1 align="center">Minugest</h1></div>
               
        <hr/>       
            
       </div>
        
        <div>
        
            <div class="container">
                <center><form>
                        <table>
                            
                            <tr><center><h1>Inicio de sesion</h1></center></tr>
                            <tr>
                               <div class="input-group">
  <span class="input-group-addon" id="sizing-addon2">Nombre de usuario</span>
  <input type="text" class="form-control" placeholder="Nombre" aria-describedby="sizing-addon1">
</div>
                            </tr>
                            <tr>
                               <div class="input-group">
  <span class="input-group-addon" id="sizing-addon2">Contraseña</span>
  <input type="text" class="form-control" placeholder="Password" aria-describedby="sizing-addon1">
</div> 
                            </tr>
                        </table>
                        
                    
                    </form></center>
                
            </div>
            
            
        </div>
    </body>
</html>
