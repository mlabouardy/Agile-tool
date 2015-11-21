<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container padding-container">
	<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-heading">${name}</div>
		<div class="panel-body">
			<a class="btn btn-danger pull-right" href="<spring:url value="/logout"/>">Sign out</a>
			<h3>Welcome to Scrum Master Dashboard</h3>
			User Story
			<jsp:include page="task.jsp"></jsp:include>
		</div>
	</div>
	</div>
	<div class="col-md-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				Github Manager <a class="btn btn-success pull-right btn-xs"
					href="/board/${scrumMaster.getProject().getId()}/github/create.html">Create</a>
			</div>
			<div class="panel-body">

				<c:if test="${scrumMaster.getProject().getGithub()!=null}">
		  					${scrumMaster.getProject().getGithub().getRepository()}
		  					<div class="button-group pull-right">
						<a class="btn btn-primary">Edit</a>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	
	<div class="col-md-12">
		<div class="panel panel-primary">
			<div class="panel-heading">Scrum Team</div>
			<div class="panel-body">
	  			<jsp:include page="scrumteam.jsp"></jsp:include>		
			</div>
		</div>
	</div>
</div>