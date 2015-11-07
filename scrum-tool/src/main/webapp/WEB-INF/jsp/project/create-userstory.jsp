<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="true"%>
<div class="container">
	<div
		class="row border-bottom white-bg dashboard-header padding-container">
		<div class="col-sm-3">
			<a class="btn btn-success" href="<spring:url value="/board/project/${sessionScope.idproject}/backlog.html"/>"><i
				class="fa fa-arrow-left"></i> Previous</a>
		</div>
	</div>

	<div
		class="row  border-bottom white-bg dashboard-header padding-container">
		<div class="col-sm-3">
			<h2>New user story</h2>
		</div>

	</div>
	<div class="row border-bottom white-bg dashboard-header">
		<form:form commandName="userstory">
			<div class="col-md-6">
			<div class="form-group">
				<label>Tag:</label> 
				<form:input path="tag" cssClass="form-control" placeholder="Enter project tag"/>
			</div>
			</div>
			<div class="col-md-6">
			<div class="form-group">
				<label>Name:</label> 
				<form:input path="name" cssClass="form-control" placeholder="Enter project name"/>
			</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label>Priority:</label> 
					<form:select path="priority" cssClass="form-control">
						<form:option value="1">High</form:option>
						<form:option value="2">Medium</form:option>
						<form:option value="3">Low</form:option>
					</form:select>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label>Difficulty:</label> 
					<form:input path="difficulty" cssClass="form-control" placeholder="Enter project difficulty"/>
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group">
					<label>Dependancies:</label> 
					<select class="selectpicker form-control" data-style="btn-primary" multiple data-selected-text-format="count>3">
						<option>High</option>
						<option>Medium</option>
						<option>Low</option>
					</select>
				</div>
			</div>
			<div class="col-md-6">
				<button type="submit" class="btn btn-success">Create</button>
			</div>
		</form:form>
	</div>
</div>

<script>
$('.selectpicker').selectpicker();
</script>