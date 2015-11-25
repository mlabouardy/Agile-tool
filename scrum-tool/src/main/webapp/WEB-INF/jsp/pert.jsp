<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:if test="${not empty scrumMaster}">
	
	<div class="col-md-12">
	
		<div class="panel panel-primary">
			<div class="panel-heading">Pert</div>
							
			<div class="panel-body">
				
				<div id="graphviz_svg_div"></div>
				
				<script type="text/javascript">
				
					jQuery(document).ready(function () {
		
					  var svg_div = jQuery('#graphviz_svg_div');
		
					  function UpdateGraphviz(id) {
					    requeteGetJson("/rest/ressources/getCurrentPertFromScrumMaster/"+id);
					  }
		
					  function requeteGetJson(url){
						  
					    $.ajax({
					    	
					      url: url,
					      dataType: "text",
					      async: true,
					      
					      success: function(dot){
					    	  
					    	if (dot == 'null'){
					    		svg_div.attr("class", "alert alert-danger");
					    		svg_div.html("no available task");
					    	}else{
							    svg_div.html("");
							    var svg = Viz(dot, "svg");
							    svg_div.html("<hr>"+svg); 
							    $(svg_div).html($("#graphviz_svg_div").find("svg"));
							    
							    //remove all attribute
							    $("#graphviz_svg_div").find("svg").each(function() {
						    	    var attributes = this.attributes;
						    	    var i = attributes.length;
						    	    while( i-- ){
						    	      this.removeAttributeNode(attributes[i]);
						    	    }
						    	  });
							    
							    
							    $("#graphviz_svg_div").find("svg").attr("style", "width: 100%;");
					    	}
		
		
					      },
					      
					      error: function(jqXHR, textStatus, ex) {
					    	  console.log("null : " +textStatus);
					      }
					      
					    });
					  }
		
					  UpdateGraphviz('${scrumMaster.getId()}');
		
					});
		
				</script>
		
			</div>
		</div>
	</div>
</c:if>
