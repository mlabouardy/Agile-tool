<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="haut.jsp"></jsp:include>
<div class="container padding-container">
<div class="panel panel-primary">
	<div class="panel-heading"></div>
		<div class="panel-body">		
    		 <div class="container-fluid">
  				<div class="row-fluid">
<form:form commandName="github">
<div class="container">
  	<div class="form-group">
  	  <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
  		<label>Repository:</label>
  		<form:input path="repository" type="text" cssClass="form-control" placeholder="mlabouardy/Commit-Monitor"/>
  	  </div>
  	</div>
    
    <div class="form-group">
       <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
        <label>Username:</label>
        <form:input path="username" type="text" cssClass="form-control"/>
       </div>
     </div>
     <div class="form-group">
      <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
         <label>Password:</label>
         <form:password path="password" cssClass="form-control"/>
       </div>
     </div>
     		
     	<div class="form-group">
          <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
            <button type="submit" class="btn btn-primary">Cancel</button>
           	<button type="submit" class="btn btn-success" value="Create">Submit</button>
<!--     <input type="submit" class="btn btn-success btn-block" value="Create"/> -->
            </div>
         </div>
</div>
  	
  </form:form>
  </div>
  
  <jsp:include page="previous.jsp"></jsp:include>	
 </div>
</div>
</div>
</div>