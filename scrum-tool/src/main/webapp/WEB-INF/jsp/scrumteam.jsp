<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="haut.jsp"></jsp:include>
<div class="col-md-12">

	<div class="panel panel-primary">
	<div class="panel-heading">Scrum Team</div>
		<div class="panel-body">
	
			<c:if test="${fn:length(devListWithoutTeam) > 0 || fn:length(scrumMaster.getScrumTeamList()) > 0}">
			
				<f:form class="form-horizontal" modelAttribute="scrumTeamForm" method="post" action="/scrumteam.html">
				
					<table class="table table-bordered table-hover">
	
						<thead>
							<tr>
								<th>&nbsp;</th>
								<th>Id</th>
								<th>Firstname</th>
								<th>Lastname</th>
							</tr>
						</thead>
						
						<c:if test="${fn:length(scrumMaster.getScrumTeamList()) > 0}">
							
							<c:forEach var="dev" items="${scrumMaster.getScrumTeamList()}">
								<tr>
									<td>
										<f:checkbox class="checkbox" path="selectedDev" checked="checked" value="${dev.getId()}" />
									</td>
									<td>${dev.getId()}</td>
									<td>${dev.getFirstname()}</td>
									<td>${dev.getLastname()}</td>
								</tr>
							</c:forEach>
							
						</c:if>
						
						<c:if test="${fn:length(devListWithoutTeam) > 0}">
						
							<c:forEach var="devWithoutTeam" items="${devListWithoutTeam}">
								<tr>
									<td>	
										<f:checkbox class="checkbox" path="selectedDev" value="${devWithoutTeam.getId()}" />
									</td>
									<td>${devWithoutTeam.getId()}</td>
									<td>${devWithoutTeam.getFirstname()}</td>
									<td>${devWithoutTeam.getLastname()}</td>
								</tr>
							</c:forEach>
							
						</c:if>
						
					</table>
				
					<button type="submit" class="btn btn-success btn-block">Update ScrumTeam</button>
					
				</f:form>	
				
			</c:if>
	
		</div>
	</div>

</div>
