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
        <script type="text/javascript" src="<c:url value="/resources/js/combobox.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/moment.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js"/>"></script>
  
  

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
<tr><td><select id="combobox" name="combobox" class="form-control combobox" style="width:600px"><option value="0">Seleccione...</option><rec:forEach items="${Receta}" var="res"> <option value="${res.CODIGO_RECETA}"> ${res.NOMBRE_RECETA}</option></rec:forEach></select></td><td><a href="#" class="remove_field">Remove</a></td>\n\
<td>&nbsp;</td><td><label for="Cantidad">Porciones:</label> </td><td><input id="Cantidad" name="Cantidad" class="form-control" type="text" value=""/></td></tr></table></div>');//add input box
        }
        
	    $(".combobox").combobox();

    });

    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).closest('div').remove(); x--;
    });
    
});
  </script>
       
                            
                            
                                 
                                    
                                                        
                        </p>
        <title>Encargado</title>
    </head>
     <body>
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="panel panel-primary"> 
                    <div class="panel-heading">Formulario de Minuta</div>
                    <div class="panel-body">
       <form:form  method="POST" commandName="Minuta">
                    <h1></h1>
       <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <p>
                        <form:label path="Codigo_Min">ID de Minuta:</form:label>
                        <form:input path="Codigo_Min" cssClass="form-control" readonly="true"/>
                    </p>
                    
                    <p> <form:label path="combobox">Receta:</form:label>
                         
                        <div class="input_fields_wrap">
                            <table>
                         <button class="add_field_button">Agregar mas recetas</button>
                        
                             <tr>
                                 <td> <form:select path="combobox" cssClass="form-control combobox" cssStyle="width:600px">
                            
                            <form:option value="0">Seleccione...</form:option>
                                <rec:forEach items="${Receta}" var="res">   
                                    <form:option value="${res.CODIGO_RECETA}"> ${res.NOMBRE_RECETA}</form:option>
                                </rec:forEach>                            
                        </form:select></p>
                             </td>
                             <td>       </td>
                             <td><form:label path="Cantidad">Porciones: </td>
                                 <td></form:label><form:input path="Cantidad" cssClass="form-control"/></td>
                             </tr>

                    </table>
                   
                                 </div>


                    
                    
                    
            <form:button class="btn btn-danger" >Enviar</form:button>
        </form:form>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="<c:url value="/resources/js/combobox.js"/>"></script>
        
    </body>
</html>
