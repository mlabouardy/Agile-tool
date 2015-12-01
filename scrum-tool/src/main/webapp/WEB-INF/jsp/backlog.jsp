<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="haut.jsp"></jsp:include>

<div class="panel panel-success">

	<div class="panel-heading">
		<f:form modelAttribute="backLogForm" method="post" action="product.html">
			<f:hidden path="typeForm.type" value="add" />
			<input class="btn btn-success" type="submit" value="Add new user story" />
		</f:form>
	</div>
	
	<div class="panel-body">

		<c:if test="${fn:length(backLogForm.getUserStoryFormList()) > 0}">

				<table class="table table-bordered table-hover">

					<thead>
						<tr>
							<th colspan="2">Options</th>
							<th>Id</th>
							<th>Tag</th>
							<th>Description</th>
							<th>Priority</th>
							<th>Difficulty</th>
							<th>Dependencies</th>
							<th>Scrum Master</th>
						</tr>
					</thead>

					<c:forEach var="userStoryForm" items="${backLogForm.getUserStoryFormList()}">

						<tr>

							<td><f:form modelAttribute="backLogForm" method="post" action="product.html">
									<f:hidden path="typeForm.type" value="remove" />
									<f:hidden path="typeForm.id" value="${userStoryForm.getId()}" />
									<input class="btn btn-danger btn-block" type="submit" value="Remove" />
								</f:form>
							</td>

							<td>
								<f:form modelAttribute="backLogForm" method="post" action="product.html">
									<f:hidden path="typeForm.type" value="edit" />
									<f:hidden path="typeForm.id" value="${userStoryForm.getId()}" />
									<input class="btn btn-info btn-block" type="submit" value="Edit" />
								</f:form>
							</td>
							<td>${userStoryForm.getId()}</td>
							<td>${userStoryForm.getTag()}</td>
							<td>${userStoryForm.getDescription()}</td>
							<td>${userStoryForm.getPriority()}</td>
							<td>${userStoryForm.getDifficulty()}</td>
							<td>${userStoryForm.getSelectedDependencies()}</td>
							<td>${userStoryForm.getSelectedScrumMasterName()}</td>
						</tr>

					</c:forEach>

				</table>
				
		</c:if>

		<c:if test="${not empty backLogForm.getException()}">
			<div class="alert alert-danger">
				<table>
					<tr>
						<td>Exception :</td>
						<td>${backLogForm.getException()}</td>
					</tr>
				</table>
			</div>
		</c:if>
	
	</div>
</div>