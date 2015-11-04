<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>


<div id="wrapper">
	<%@include file="/resources/components/sidebar-left.html"%>

	<div id="page-wrapper" class="gray-bg dashbard-1">

		<%@include file="/resources/components/navbar-top.html"%>

		<security:authorize access="hasRole('ROLE_SCRUM_MASTER')">
			<jsp:include page="board-master.jsp"></jsp:include>
		</security:authorize>

		<security:authorize access="hasRole('ROLE_SCRUM_TEAM')">
			<jsp:include page="board-team.jsp"></jsp:include>
		</security:authorize>

		<security:authorize access="hasRole('ROLE_PRODUCT_OWNER')">
			<jsp:include page="board-product.jsp"></jsp:include>
		</security:authorize>

		
	</div>
	<div class="footer">
			<div class="pull-right">
				Mohamed <strong>Labouardy</strong>.
			</div>
			<div>
				<strong>Copyright</strong> University of Bordeaux &copy; 2015-2016
			</div>
		</div>
</div>
