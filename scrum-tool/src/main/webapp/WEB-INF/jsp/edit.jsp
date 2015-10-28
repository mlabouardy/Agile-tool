<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container padding-container">

	<f:form modelAttribute="userStoryForm" method="post" action="edit.html">
		<div>
		
			<div class="container">
				
				<f:hidden path="id" />
				
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
					 	
					 	<c:forEach var="currentID" items="${userStoryForm.getDependencies()}">

							<c:set var="contains" value="false" />

							<c:forEach var="selectedID" items="${userStoryForm.getSelectedDependencies()}">
								<c:if test="${selectedID eq currentID}">
									<c:set var="contains" value="true" />
								</c:if>
							</c:forEach>

							<c:choose>

								<c:when test="${contains}">
									<div class="checkbox">
										<f:checkbox class="checkbox" path="selectedDependencies" value="${currentID}" checked="checked" />${currentID}
									</div>
								</c:when>

								<c:otherwise>
									<div class="checkbox">
										<f:checkbox class="checkbox" path="selectedDependencies" value="${currentID}" />${currentID}
									</div>
								</c:otherwise>

							</c:choose>

						</c:forEach>
						
					</div>
				</c:if>

				<div class="form-group">
					<input class="btn btn-success btn-block" type="submit" value="submit">
				</div>			
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