<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<security:authorize access="hasRole('ROLE_SCRUM_MASTER')">
	<jsp:include page="board-master.jsp"></jsp:include>
</security:authorize>

<security:authorize access="hasRole('ROLE_SCRUM_TEAM')">
	<jsp:include page="board-team.jsp"></jsp:include>
</security:authorize>

<security:authorize access="hasRole('ROLE_PRODUCT_OWNER')">
	<jsp:include page="board-product.jsp"></jsp:include>
</security:authorize>