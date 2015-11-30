<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<script src="<spring:url value="/resources/js/funcRest.js"/>"></script>

<script src="<spring:url value="/resources/js/jquery-ui.min.js"/>" > </script>
<div class="col-md-12">
	
		<div class="panel panel-primary">
			<div class="panel-heading">Kanban</div>
							
			<div class="panel-body">
					<div class="kanban row" style="margin-top: 20px">
						<div id="selectKanbanParameters" class="row col-md-8 col-sm-8 col-lg-8"> 
							
								<div class="col-md-4 col-sm-4 col-lg-4">
									<select id="selectProject" class="form-control" onchange="changeProjectSelection()">
				
									</select>
								</div>
							
						</div>
						<div class="kanban_dragdrop col-md-12 col-sm-12 col-lg-12"> 
							<div id="tasks" class="row"> 
								
							
							</div>
							
						</div>
					</div>
			</div>
		</div>
</div>	
	
	
	<script>
		
		var datas;
		
		var baseUrl = "/rest/ressources/";
		
		$(".kanban_dragdrop").height($("body").height()*0.6 + "px");
	
		function changeFormatDate(date){
			if(typeof (date.getDate) != "function" )
				return "";
			return date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();			
		}
		
		// gestion du paramètre projet
		
		function getProjects(){
			return requeteGetJson(baseUrl + "getProjects");
		}
		
		function addInputProject(projects){
			
			for(var i=0; i < projects.length ; i++){
				$("#selectProject").append("<option value=\" " + projects[i].id + "\">" + "Projet : " + projects[i].name + "</option>");
			}			
		}
		
		
		// gestion du paramètre ScrumMaster
		
		function getScrumMastersFromProject(project_id){
			return requeteGetJson(baseUrl + "getScrumMastersFromProject/" + project_id);
		}
		
		function addSelectScrumMasters(){
			$("#selectKanbanParameters").append("<div class=\"col-md-4 col-sm-4 col-lg-4\">\
					<select style=\"display: none;\" id=\"selectScrumMaster\" class=\"form-control\" onchange=\"changeScrumMasterSelection()\">\
					</select>\
					</div>");
			
			$("#selectScrumMaster").fadeIn();
			
		}
		
		function addInputScrumMaster(scrumMasters){
			for(var i=0; i < scrumMasters.length ; i++){
				$("#selectScrumMaster").append("<option value=\" " + scrumMasters[i].id + "\">" + "Responsable : " + scrumMasters[i].firstname + " " + scrumMasters[i].lastname + "</option>");
			}	
		}
		
		// gestion du paramètre Sprint
		function getSprintsFromScrumMaster(scrumMaster_id){
			return requeteGetJson(baseUrl + "getSprintsFromScrumMaster/" + scrumMaster_id);
		}
		
		function addSelectSprints(){
			$("#selectKanbanParameters").append("<div class=\"col-md-4 col-sm-4 col-lg-4\">\
					<select style=\"display: none;\" id=\"selectSprint\" class=\"form-control\" onchange=\"changeSprintSelection()\">\
					</select>\
					</div>");
			
			$("#selectSprint").fadeIn();
			
		}
		
		function addInputSprint(sprints){
			for(var i=0; i < sprints.length ; i++){
				var beginning = new Date(sprints[i].beginning);
				var end = new Date(sprints[i].end);
				if( end != null && beginning != null)
					$("#selectSprint").append("<option value=\" " + sprints[i].id + "\">" + "Debut : " + changeFormatDate(beginning) + " - Fin : " + changeFormatDate(end)  + "</option>");
				else
					$("#selectSprint").append("<option value=\" " + sprints[i].id + "\">" + "Debut : " +  + " - Fin : " +  + "</option>");
			}	
		}
		

		// Recupération des tâches
		
		function getTasksFromSprint(sprint_id){
			return requeteGetJson(baseUrl + "getTasksFromSprint/" + sprint_id);
		}
		
		function addTasks(tasks , JqueryId){
			
			for(var i=0; i < tasks.length ; i++){
				$(JqueryId).append("<div id=\"task_" + tasks[i].id +"\" class=\"post-it col-md-12 col-sm-12 col-lg-12\">\
						<dl>\
							<dt> description  </dt\
							<dd>" + tasks[i].description + " </dd>\
							<dt> priorité de la tâche </dt>\
							<dd>"+ tasks[i].priority +"</dd>\
							<dt> difficulté de la tâche </dt>\
							<dd>"+ tasks[i].difficulty +"</dd>\
							<dt> développeur associé </dt>\
							<dd> à attribuer </dd>\
						</dl>\
						</div>");
				
				$("#task_" + tasks[i].id).draggable({
					revert: "invalid",
					cursor: "move"
				});
				
				$("#task_" + tasks[i].id).data("id" , {id: tasks[i].id});
			}
			
			
		}
		
		function changeSprintSelection(){
			$(".kanban_dragdrop").empty();
			createKanban($("#selectSprint").val());
			
		}
		
		
		function changeScrumMasterSelection(){
			$(".kanban_dragdrop").empty();
			$("#selectSprint").parent().remove();
			$("#tasks").empty();
			var scrumMaster_id = $("#selectScrumMaster").val();
			var sprints = getSprintsFromScrumMaster(scrumMaster_id);
			if(sprints.length > 0)
				addSelectSprints();
			else
				return;
			addInputSprint(sprints);
			changeSprintSelection();
		}
		
		function changeProjectSelection(){
			$(".kanban_dragdrop").empty();
			$("#tasks").empty();
			$("#selectSprint").parent().remove();	
			$("#selectScrumMaster").parent().remove();
			var idProject = $("#selectProject").val();
			var scrumMasters = getScrumMastersFromProject(idProject);
			if(scrumMasters.length > 0)
				addSelectScrumMasters();
			else
				return;
			addInputScrumMaster(scrumMasters);	
			changeScrumMasterSelection();			
			
		}
		
		// FIN RECUPERATION DONNEES NECESSAIRES A L'OBTENTION DES DONNEES SUR LES TACHES
		
		function getStatusOfTasks(){
			return requeteGetJson(baseUrl + "getStatusTasks/");
		}
		
		function getTasksByStatusAndSprint(statusId , sprintId){
			return requeteGetJson(baseUrl + "getTasksByStatusAndSprint/" + statusId + "/" + sprintId);
		}
		
		function setStatusToTask(status , task){
			
			$.ajax({
				  type: "POST",
				  url: baseUrl + "setStatusToTask/"+ status + "/" + task,
				  success: function(msg){
				  }
				});
		}
		
		function dropTask(event, ui) {
			  var draggableId = ui.draggable.attr("id");
			  var droppableId = $(event.target).attr('id');
			  $("#"+draggableId).detach().appendTo("#" + droppableId);
			  $("#"+draggableId).removeAttr( 'style' );
			  setStatusToTask($("#" + droppableId).data("idStatus").status, $("#"+draggableId).data("id").id);
		}
		
		function createKanban(sprintId){
			var status = getStatusOfTasks();
			var tasks;
			if(status.length > 0){
				var sizeDiv = 12/status.length;
				$(".kanban_dragdrop").append("<div id=\"titles_status\" class=\"col-sm-12 col-md-12 col-lg-12 row \" > </div>"  );
				$(".kanban_dragdrop").append("<div id=\"tasks\" class=\"col-sm-12 col-md-12 col-lg-12 row \" > </div>"  );
				for(var i = 0; i < status.length ; i++){
					$("#titles_status").append("<div id=\"title_status_" + status[i].id + "\" class=\"title_status row " + " col-md-"+ sizeDiv +" col-sm-"+ sizeDiv + " col-lg-" + sizeDiv + "\">" +"\
							<div id=\"title_status_" + status[i].id +"\" > <h1>" + status[i].name + "</h1></div>" + "\
							</div>" );
					$("#tasks").append("<div id=\"status_" + status[i].id + "\" class=\"task_list row " + " col-md-"+ sizeDiv +" col-sm-"+ sizeDiv + " col-lg-" + sizeDiv + "\"></div>");
					$("#status_" + status[i].id).data("idStatus" , {status: status[i].id});
					$("#status_" + status[i].id).droppable({
						drop:dropTask
					});
					$("#status_" + status[i].id).sortable();
					$("#status_" + status[i].id).css("height", $(".kanban_dragdrop").height() - ($("#titles_status").height() + 10)  + "px");
					tasks = getTasksByStatusAndSprint(status[i].id , sprintId);
					if(tasks.length > 0){	
						addTasks(tasks , "#status_" + status[i].id);
					}
				}
			}
		}
		
		var projects = getProjects();
		addInputProject(projects);
		changeProjectSelection();
		

	
	</script>