<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="panel panel-success">
	<div class="panel-body">
	<c:if test="${fn:length(scrumMaster.getUserStories()) > 0}">				
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Tag</th>
							<th>Description</th>
							<th>Add Task</th>
							<th>List Task</th>
						</tr>
					</thead>

					<c:forEach var="userStory" items="${scrumMaster.getUserStories()}">

						<tr>
							<td>${userStory.getId()}</td>
							<td>${userStory.getTag()}</td>
							<td>${userStory.getDescription()}</td>
							<td>
								<a class="btn btn-success" href="/addtask/${scrumMaster.getId()}/${userStory.getId()}.html">Add task</a>
							</td>
							<td>
								<a class="btn btn-success" href="/listtask/${scrumMaster.getId()}/${userStory.getId()}.html">List task</a>
							</td>
						</tr>

					</c:forEach>

				</table>
		</c:if>
	</div>
</div>

