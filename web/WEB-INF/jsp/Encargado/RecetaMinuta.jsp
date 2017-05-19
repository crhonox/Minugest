<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="rec" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>

        <script type="text/javascript" src="<c:url value="/bower_components/jquery/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/bower_components/jquery/jquery.autocomplete.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/bower_components/moment/min/moment.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/bower_components/bootstrap/dist/js/bootstrap.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"/>"></script>
  <link rel="stylesheet" href="<c:url value="/bower_components/bootstrap/dist/css/bootstrap.min.css"/>" />
  <link rel="stylesheet" href="<c:url value="/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>" />
  <style>
      .autocomplete-suggestions { border: 1px solid #999; background: #FFF; overflow: auto; }
.autocomplete-suggestion { padding: 5px 5px; white-space: nowrap; overflow: hidden; font-size:22px}
.autocomplete-selected { background: #F0F0F0; }
.autocomplete-suggestions strong { font-weight: bold; color: #3399FF; }
  </style>
  
  
   <script type="text/javascript">
     $(document).ready(function() {
    var max_fields      = 10; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID
    
    var x = 1; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div><input type="text" name="Recetas[]" id="w-input-search"/><a href="#" class="remove_field">Remove</a></div>'); //add input box
        }
    });
    
    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).parent('div').remove(); x--;
    })
});
  </script>
  
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
       <form:form  method="POST" commandName="ReMinuta">
                    <h1></h1>
       <form:errors path="*" element="div" cssClass="alert alert-danger" />
                    <p>
                        <form:label path="Nombre_Min">Nombre de Minuta:</form:label>
                        <form:input path="Nombre_Min" cssClass="form-control" readonly="true"/>
                    </p>
            <p></p>
                    <p><form:label path="Codigo_Casi">Casino:</form:label>
                        <form:select path="Codigo_Casi" cssClass="form-control" readonly="true">
                            <form:option value="0">Seleccione...</form:option>
                                <rec:forEach items="${casino}" var="cas">   
                                    <form:option value="${cas.CODIGO_CASINO}"> ${cas.NOMBRE_CASINO}</form:option>
                                </rec:forEach>                            
                        </form:select></p>
        <div class="input_fields_wrap">
    <button class="add_field_button">Add More Fields</button>
    <div><input type="text" name="Recetas[]" id="w-input-search"></div>
</div>
                   
                    <script>
  $(document).ready(function() {

	$('#w-input-search').autocomplete({
		serviceUrl: 'getTags',
		paramName: "tagName",
		delimiter: ",",
	   transformResult: function(response) {

		return {
		  //must convert json to javascript object before process
		  suggestions: $.map($.parseJSON(response), function(item) {

		      return { value: item.tagName, data: item.id };
		   })

		 };

            }

	 });

  });
  </script>

                    
                    
                    <form:hidden path="Codigo_Min" cssClass="form-control" readonly="true"/>
            <form:button class="btn btn-danger" >Enviar</form:button>
        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
