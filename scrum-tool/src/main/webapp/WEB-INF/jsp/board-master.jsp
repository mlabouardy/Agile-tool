<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="container padding-container">
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