<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">
	<div
		class="row border-bottom white-bg dashboard-header padding-container">
		<div class="col-sm-3">
			<a class="btn btn-success" href="<spring:url value="/board.html"/>"><i
				class="fa fa-arrow-left"></i> Previous</a>
		</div>
	</div>

	<div
		class="row  border-bottom white-bg dashboard-header padding-container">
		<div class="col-sm-3">
			<h2>Create Project</h2>
		</div>

	</div>
	<div class="row border-bottom white-bg dashboard-header">
		<form:form commandName="project">
			<div class="form-group">
				<label>Name:</label> 
				<form:input path="name" cssClass="form-control" placeholder="Enter project name"/>
			</div>
			<div class="form-group">
				<label>Description:</label>
				<form:textarea path="description" cssClass="form-control" placeholder="Enter description" rows="3"/>
			</div>
			<button type="submit" class="btn btn-success">Create</button>
		</form:form>
	</div>
</div>
