<div class="container container-fluid">	
	<div class="kanban row" style="margin-top: 20px">
	
		<div class="task col-md-4 col-sm-4" style="background-color: @gray-lighter;">
		
		<dl>
			<dt> description  </dt>
			<dd> description de la t�che
			<dt> priorit� de la t�che </dt>
			<dd> num </dd>
			<dt> difficult� de la t�che </dt>
			<dd> diff </dd>
			<dt> d�veloppeur associ� </dt>
			<dd> � attribuer </dd>
		</dl>	
		
		</div>
	
	
	</div>
	
</div>	
	
	
	<script>
		
		var datas;
		
		function getTasksFromSprint(id){
			var datas;
			$.ajax({
	  			url: "http://localhost:8085/rest/ressources/getTasksFromSprint/" + id,
	  			dataType: "json",
	  			async: false,
				success: function(data){
					datas = data;
	
				},
				error: function(jqXHR, textStatus, ex) {
	        		alert(textStatus + "," + ex + "," + jqXHR.responseText);
	    		}
			});
			
			return datas;
			
		}
		
		function addTasksInKanban(datas){
			
			for(var i = 0; i < datas.length ; i++){
				
				$(".kanban").append("<div class=\"col-md-12 col-sm-12 \" > " + datas[i].description + "</div>");

			}

		}
		
		

	
	</script>