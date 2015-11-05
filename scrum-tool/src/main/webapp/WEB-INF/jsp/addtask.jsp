<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container padding-container">
 
	<f:form commandName="task" >

			<div class="container">
				<div class="form-group">
				 	<label for="tag_input">Tag</label>
					<f:input class="form-control" id="tag_input" path="Tag" />
				</div>
				
				<div class="form-group">
				 	<label for="description_input">Description</label>
				 	<f:input class="form-control" id="description_input" path="Description" />
				</div>				
				
				<div class="form-group">
				 	<label for="color_input">Color</label>
				 	<f:input class="form-control" id="color_input" path="color" />
				</div>
				
				<div class="form-group">
					<input class="btn btn-success btn-block" type="submit" value="submit">
				</div>			
			</div>	

	</f:form>	
	 
			<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Description</th>
						</tr>
					</thead>

					<c:forEach var="taskForm"
						items="${taskForms}">
						<tr>
							<td>${taskForm.getId()}</td>
							<td>${taskForm.getDescription()}</td>						
						</tr>
					</c:forEach>
				</table>		
</div>