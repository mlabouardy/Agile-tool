<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container padding-container">

	<f:form class="form-horizontal" modelAttribute="userStoryForm" method="post" action="/add.html" >

			<div class="container">
				<div class="form-group">
				 	<label for="tag_input">Tag</label>
					<f:input class="form-control" id="tag_input" path="tag" />
				</div>
				
				<div class="form-group">
				 	<label for="description_input">Description</label>
				 	<f:textarea class="form-control" id="description_input" path="description" />
				</div>
				
				<div class="form-group">
				 	<label for="priority_input">Priority</label>
				 	<f:input class="form-control" id="priority_input" path="priority" />
				</div>
				
				<div class="form-group">
				 	<label for="difficulty_input">Difficulty</label>
				 	<f:input class="form-control" id="difficulty_input" path="difficulty" />
				</div>

				<c:if test="${fn:length(userStoryForm.getDependencies()) > 0}">
					<div class="form-group">
					 	<label for="dependencies_input">Dependencies</label>
					 	<c:forEach var="userStoryId" items="${userStoryForm.getDependencies()}">
							<div class="checkbox">
								<f:checkbox id="dependencies_input" class="checkbox" path="selectedDependencies" value="${userStoryId}" /> ${userStoryId}
							</div>
						</c:forEach>
					</div>
				</c:if>
				
				<c:if test="${fn:length(userStoryForm.getScrumMasterList()) > 0}">
					<div class="form-group">
					 	<label for="master_input">ScrumMaster</label>
					 	<f:select id="master_input" path="selectedScrumMasterId">
						    <f:options items="${userStoryForm.getScrumMasterList()}" />
						</f:select>
					</div>
				</c:if>
				
				
				<div class="form-group">
					<input class="btn btn-success btn-block" type="submit" value="submit">
				</div>			
			</div>	

	</f:form>

	<c:if test="${not empty userStoryForm.getException()}">
		<div class="alert alert-danger">
			<table>
				<tr>
					<td>Exception :</td>
					<td>${userStoryForm.getException()}</td>
				</tr>
			</table>
		</div>
	</c:if>
	
</div>