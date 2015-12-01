<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="haut.jsp"></jsp:include>
<div class="middle-box">
	<form:form commandName="user" id="registrationForm">
		<c:if test="${param.success eq true}">
			<div class="alert alert-success">Registration successful !</div>
		</c:if>
		<div class="form-group">
			<label for="firstname">First name:</label>
			<form:input path="firstname" cssClass="form-control" id="inputFirstName" />
			<form:errors path="firstname"></form:errors>
		</div>
		<div class="form-group">
			<label for="lastname">Last name:</label>
			<form:input path="lastname" cssClass="form-control" id="inputLastName" />
			<form:errors path="lastname"></form:errors>
		</div>
		<div class="form-group">
			<label for="role.id">Job role:</label>
			<form:select path="role.id" cssClass="form-control">
  				<form:option value="1">Product Owner</form:option>
  				<form:option value="2">Scrum Master</form:option>
  				<form:option value="3">Scrum Team</form:option>
			</form:select>
			<form:errors path="role.id"></form:errors>
		</div>
		<div class="form-group">
			<label for="email">Email:</label>
			<form:input path="email" cssClass="form-control" id="inputEmail" />
			<form:errors path="email"></form:errors>
		</div>
		<div class="form-group">
			<label for="password">Password:</label>
			<form:password path="password" cssClass="form-control" id="inputPassword" />
			<form:errors path="password"></form:errors>
		</div>
		<button type="submit" class="btn btn-success btn-block">Create</button>
	</form:form>

	<p class="text-muted text-center">
		<small>Do you have an account?</small>
	</p>
	<a class="btn btn-sm btn-white btn-block" href="login.html">Sign in</a>
</div>