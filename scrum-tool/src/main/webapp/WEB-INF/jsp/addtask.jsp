<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="haut.jsp"></jsp:include>
<div class="container padding-container">
 
 <c:if test="${empty taskForm.exception}">
	<f:form commandName="taskForm" >

			<div class="container">
				<div class="form-group">
				 	<div class="col-md-6 col-sm-6 col-xs-12">
				 	<label class="control-label col-md-3 col-sm-3 col-xs-12" for="tag_input">Tag</label>
					<f:input class="form-control" id="tag_input" path="Tag" />
				</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-md-3 col-sm-3 col-xs-12" for="description_input">Description</label>
				 	<div class="col-md-6 col-sm-6 col-xs-12">
				 	<f:input class="form-control" id="description_input" path="Description" />
					</div>
				</div>				
				
				<div class="form-group">
				 	<label class="control-label col-md-3 col-sm-3 col-xs-12" for="color_input">Color</label>
				 	<f:input class="form-control" id="color_input" path="color" />
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12" for="radiobutton_input">Scrum Team</label>
						<c:forEach var="scrumTeam" items="${scrumTeams}">
							<div class="radiobutton">
								<f:radiobutton id="radiobutton" class="radiobutton" path="scrumTeamId" value="${scrumTeam.getId()}"/> ${scrumTeam.getFirstname()} ${scrumTeam.getLastname()}
							</div>
						</c:forEach>
				</div>
			
				<div class="form-group">
					<input class="btn btn-success btn-block" type="submit" value="submit">
				</div>			
			</div>	

	</f:form>	
	</c:if>	
	<c:if test="${not empty taskForm.exception}">
	
		<div>
			${taskForm.exception}
		</div>
	</c:if>		
</div>