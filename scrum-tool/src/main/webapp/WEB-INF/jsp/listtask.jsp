<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="haut.jsp"></jsp:include>
<div class="container padding-container">
 <div class="panel panel-primary">
	<div class="panel-heading">Task</div>
		<div class="panel-body">		
     		<div class="container-fluid">
  				<div class="row-fluid">
	<c:choose>
	
		<c:when test="${fn:length(tasks) > 0}">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Description</th>
						<th>ScrumTeam</th>
						<th>Commits</th>
					</tr>
				</thead>
		
				<c:forEach var="task" items="${tasks}">
					<tr>
						<td>${task.getId()}</td>
						<td>${task.getDescription()}</td>
						<td>Null</td>	
						<td><a class="btn btn-success" href="/board/project/${id_project}/task/${task.getId()}/commits.html">View</a></td>					
					</tr>
				</c:forEach>
			</table>	
		</c:when>
	
		<c:otherwise>
			<p>Task list is empty</p>
		</c:otherwise>
	
	</c:choose>

</div>
</div>
</div>
</div>
</div>