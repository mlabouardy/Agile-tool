<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container padding-container">
<form:form commandName="github">
  	<div class="form-group">
  		<label>Repository:</label>
  		<form:input path="repository" type="text" cssClass="form-control" placeholder="mlabouardy/Commit-Monitor"/>
  	</div>
    <div>
    <div class="form-group">
        <label>Username:</label>
        <form:input path="username" type="text" cssClass="form-control"/>
     </div>
     <div class="form-group">
        <label>Password:</label>
        <form:password path="password" cssClass="form-control"/>
     </div>
     </div>
  	<input type="submit" class="btn btn-success btn-block" value="Create"/>
  </form:form>
  </div>