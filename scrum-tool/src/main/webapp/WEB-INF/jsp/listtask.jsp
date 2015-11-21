<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container padding-container">

	<c:choose>
	
		<c:when test="${fn:length(tasks) > 0}">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Description</th>
						<th>Commits</th>
					</tr>
				</thead>
		
				<c:forEach var="task" items="${tasks}">
					<tr>
						<td>${task.getId()}</td>
						<td>${task.getDescription()}</td>	
						<td><a class="btn btn-success" href="/board/project/id/task/${task.getId()}/commits.html">View</a></td>					
					</tr>
				</c:forEach>
			</table>	
		</c:when>
	
		<c:otherwise>
			<p>Task list is empty</p>
		</c:otherwise>
	
	</c:choose>

</div>