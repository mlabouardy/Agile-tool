<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="true"%>
<div class="container">
	<div
		class="row border-bottom white-bg dashboard-header padding-container">
		<div class="col-sm-3">
			<a class="btn btn-success" href="<spring:url value="/board/project/${sessionScope.id}/backlog.html"/>"><i
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
		<form >
			<div class="col-md-6">
			<div class="form-group">
				<label>Tag:</label> 
				<input  class="form-control" placeholder="Enter project tag"/>
			</div>
			</div>
			<div class="col-md-6">
			<div class="form-group">
				<label>Name:</label> 
				<input  class="form-control" placeholder="Enter project name"/>
			</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label>Priority:</label> 
					<select class="form-control">
						<option>High</option>
						<option>Medium</option>
						<option>Low</option>
					</select>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label>Difficulty:</label> 
					<input  class="form-control" placeholder="Enter project difficulty"/>
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
		</form>
	</div>
</div>

<script>
$('.selectpicker').selectpicker();
</script>