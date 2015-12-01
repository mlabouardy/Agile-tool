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
 
 <c:if test="${empty taskForm.exception}">
	<f:form commandName="taskForm" >

			<div class="container">
				<div class="form-group">
				<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
				 	<label for="tag_input">Tag</label>
					<f:input class="form-control" id="tag_input" path="Tag" />
				</div>
				</div>
				
				<div class="form-group">
				<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
				 	<label for="description_input">Description</label>
				 	<f:input class="form-control" id="description_input" path="Description" />
				</div>
				</div>				
				
				<div class="form-group">
				<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
				 	<label for="color_input">Color</label>
				 	<f:input class="form-control" id="color_input" path="color" />
				</div>
				</div>
				
				<div class="form-group">
				<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
					<label for="radiobutton_input">Scrum Team</label>
						<c:forEach var="scrumTeam" items="${scrumTeams}">
							<div class="radiobutton">
								<f:radiobutton id="radiobutton" class="radiobutton" path="scrumTeamId" value="${scrumTeam.getId()}"/> ${scrumTeam.getFirstname()} ${scrumTeam.getLastname()}
							</div>
						</c:forEach>
				</div>
				</div>
			
<!-- 				<div class="form-group"> -->
<!-- 					<input class="btn btn-success btn-block" type="submit" value="submit"> -->
<!-- 				</div>			 -->
				<div class="form-group ">
                     <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                        <button type="submit" class="btn btn-primary">Cancel</button>
                     	<button type="submit" class="btn btn-success">Submit</button>
                     </div>
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

	<jsp:include page="previous.jsp"></jsp:include>		
	
</div>
</div>
</div>
</div>