<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="haut.jsp"></jsp:include>

	<c:if test="${fn:length(scrumMaster.getUserStories()) > 0}">				
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Tag</th>
							<th>Description</th>
							<th>Operations</th>
						</tr>
					</thead>

					<c:forEach var="userStory" items="${scrumMaster.getUserStories()}">

						<tr>
							<td style="width:25px">${userStory.getId()}</td>
							<td>${userStory.getTag()}</td>
							<td>${userStory.getDescription()}</td>
							<td style="width:150px;text-align:center">
								<div class="button-group">
								<a class="btn btn-success btn-xs" href="/addtask/${scrumMaster.getId()}/${userStory.getId()}.html">Add task</a>
								<a class="btn btn-warning btn-xs" href="/board/project/${scrumMaster.getProject().getId()}/listtask/${scrumMaster.getId()}/${userStory.getId()}.html">List task</a>
								</div>
							</td>
						</tr>

					</c:forEach>

				</table>
		</c:if>
