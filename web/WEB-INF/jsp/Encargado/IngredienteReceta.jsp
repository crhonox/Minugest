<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<tr><td><div class="form-group"><select id="combobox" name="combobox" class="form-control combobox" style="width:600px" required><option value="0">Seleccione...</option><rec:forEach items="${ingrediente}" var="ing"> <option value="${ing.CODIGO_INGREDIENTE}"> ${ing.NOMBRE_INGREDIENTE}</option></rec:forEach></select></td><td><a href="#" class="remove_field">Remove</a></div></td>\n\
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
       
    </head>
    <body>
        
         <h1 align="center">Minugest</h1>
        <br>
        <hr/>
        <div class="container">
            
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
                                    <form:option value="${ing.CODIGO_INGREDIENTE}"> ${ing.NOMBRE_INGREDIENTE}</form:option>
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
                            <input type="submit" value="Enviar" class="btn btn-danger" />
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