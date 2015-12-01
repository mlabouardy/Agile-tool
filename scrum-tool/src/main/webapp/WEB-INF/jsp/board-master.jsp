<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="haut.jsp"></jsp:include>

<div class="container padding-container">
	<div class="col-md-12">
		<div class="panel panel-primary">
			<div class="panel-heading panel-dark">List of user stories<a class="btn btn-danger pull-right btn-xs"
					href="<spring:url value="/logout"/>">Sign out</a></div>
			<div class="panel-body">
				
				<jsp:include page="task.jsp"></jsp:include>
			</div>
		</div>
	</div>
	
	<jsp:include page="github-manager.jsp"></jsp:include>
	<jsp:include page="gantt-diagram.jsp"></jsp:include>
	<jsp:include page="scrumteam.jsp"></jsp:include>	
	<jsp:include page="pert.jsp"></jsp:include>
	
	
		<jsp:include page="kanban.jsp"></jsp:include>

	
	<c:if test="${sprint != null}">
	
			<jsp:include page="burndownChart.jsp" ></jsp:include>

	</c:if>
	
	
</div>