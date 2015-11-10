<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container padding-container">

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