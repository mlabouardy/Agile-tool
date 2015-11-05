<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="panel panel-success">
	<div class="panel-body">
	 
	
			<c:if test="${fn:length(backLogForm.getUserStoryFormList()) > 0}">
 
				<table class="table table-bordered table-hover">

					<thead>
						<tr>
							<th>Id</th>
							<th>Tag</th>
							<th>Description</th>
							<th>Add Task</th>
						</tr>
					</thead>

					<c:forEach var="userStoryForm"
						items="${backLogForm.getUserStoryFormList()}">

						<tr>
							<td>${userStoryForm.getId()}</td>
							<td>${userStoryForm.getTag()}</td>
							<td>${userStoryForm.getDescription()}</td>
							<td>
								<a class="btn btn-success" href="addtask/${userStoryForm.getId()}.html">Add task</a>
							</td>
						</tr>

					</c:forEach>

				</table>
				 
		</c:if>
		
		
		<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Description</th>
						</tr>
					</thead>

					<c:forEach var="taskForm"
						items="${taskForms}">
						<tr>
							<td>${taskForm.getId()}</td>
							<td>${taskForm.getDescription()}</td>							
						</tr>
					</c:forEach>
				</table>
			 	
	</div>
</div>

