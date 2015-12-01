<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<jsp:include page="haut.jsp"></jsp:include>
<div class="container padding-container">
<div class="panel panel-primary">
	<div class="panel-heading">User Story</div>
		<div class="panel-body">									
     		<div class="container-fluid">
  				<div class="row-fluid">
	<f:form class="form-horizontal" modelAttribute="userStoryForm" method="post" action="/add.html" >

			<div class="container">
				<div class="form-group">
				 	<label class="control-label col-md-3 col-sm-3 col-xs-12" for="tag_input">Tag</label>
				 	<div class="col-md-6 col-sm-6 col-xs-12">
					<f:input class="form-control" id="tag_input" path="tag" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-md-3 col-sm-3 col-xs-12" for="description_input">Description</label>
				 	<div class="col-md-6 col-sm-6 col-xs-12">
				 	<f:textarea class="form-control" id="description_input" path="description" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-md-3 col-sm-3 col-xs-12" for="priority_input">Priority</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
				 	<f:input class="form-control" id="priority_input" path="priority" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-md-3 col-sm-3 col-xs-12" for="difficulty_input">Difficulty</label>
				 	<div class="col-md-6 col-sm-6 col-xs-12">
				 	<f:input class="form-control" id="difficulty_input" path="difficulty" />
					</div>
				</div>

				<c:if test="${fn:length(userStoryForm.getDependencies()) > 0}">
					<div class="form-group">
					 	<label class="control-label col-md-3 col-sm-3 col-xs-12" for="dependencies_input">Dependencies</label>
					 	<div class="col-md-6 col-sm-6 col-xs-12">
					 	<c:forEach var="userStoryId" items="${userStoryForm.getDependencies()}">
							<div class="checkbox">
								<f:checkbox id="dependencies_input" class="checkbox" path="selectedDependencies" value="${userStoryId}" /> ${userStoryId}
							</div>
						</c:forEach>
					</div>
					</div>
				</c:if>
				
				<c:if test="${fn:length(userStoryForm.getScrumMasterList()) > 0}">
					<div class="form-group">
					 	<label class="control-label col-md-3 col-sm-3 col-xs-12" for="master_input">ScrumMaster</label>
					 <div class="col-md-6 col-sm-6 col-xs-12">
					 	<f:select id="master_input" path="selectedScrumMasterId">
						    <f:options items="${userStoryForm.getScrumMasterList()}" />
						</f:select>
					</div>
					</div>
				</c:if>
				
				
<!-- 				<div class="form-group"> -->
<!-- 					<input class="btn btn-success btn-block" type="submit" value="submit"> -->
<!-- 				</div>			 -->
<!-- 			</div>	 -->
  				<div class="form-group ">
                     <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                        <button type="submit" class="btn btn-primary">Cancel</button>
                     	<button type="submit" class="btn btn-success">Submit</button>
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
	
	<jsp:include page="previous.jsp"></jsp:include>		
	
</div>
</div>
</div>
</div>
</div>