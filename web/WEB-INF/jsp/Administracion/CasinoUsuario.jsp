<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>

        <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js"/>"></script>

  <script type="text/javascript" src="<c:url value="/resources/js/moment.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js"/>"></script>
  
  

  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>" />
  <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>" />

  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css"/>" />
  
 

        
  
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
<tr><td><select id="combobox" name="combobox" class="form-control" style="width:600px"><option value="0">Seleccione...</option><rec:forEach items="${Casino}" var="cas"> <option value="${cas.CODIGO_CASINO}"> ${cas.NOMBRE_CASINO}</option></rec:forEach></select></td><td><a href="#" class="remove_field">Remove</a></td>\n\
<td>&nbsp;</td></tr></table></div>');//add input box
        }
        

    });

    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).closest('div').remove(); x--;
    });
    
});
  </script>
       
                            
                            
                                 
                                    
                                                        
                        </p>
        <title>Minugest</title>
    </head>
     <body>
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="panel panel-primary"> 
                    <div class="panel-heading">Agregar usuario a casino</div>
                    <div class="panel-body">
       <form:form  method="POST" commandName="Usuario">
                    <h1></h1>
       <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <p>
                        <form:label path="rut">Rut de usuario:</form:label>
                        <form:input path="rut" cssClass="form-control" readonly="true"/>
                        <form:hidden path="RutEmpresa" cssClass="form-control" readonly="true"/>
                    </p>
                    
                    <p> <form:label path="combobox">Casino:</form:label>
                         
                        <div class="input_fields_wrap">
                           
                         <button class="add_field_button">Agregar a mas casinos</button>
                         <p></p>
                              <select id="combobox" name="combobox" class="form-control" style="width:600px"  >
                            
                            <option value="0">Seleccione...</option>
                                <rec:forEach items="${Casino}" var="cas">   
                                    <option value="${cas.CODIGO_CASINO}"> ${cas.NOMBRE_CASINO}</option>
                                </rec:forEach>                            
                        </select></p>
                             
                   
                                 </div>


                    
                    
                    
            <form:button class="btn btn-danger" >Enviar</form:button>
        </form:form>
                    </div>
                </div>
            </div>
        </div>
        
        
    </body>
</html>
