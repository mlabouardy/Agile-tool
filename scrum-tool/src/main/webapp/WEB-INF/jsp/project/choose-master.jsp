<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<div class="container">
	<div
		class="row border-bottom white-bg dashboard-header padding-container">
		<div class="col-sm-3">
			<a class="btn btn-success"
				href="<spring:url value="/board/project/${sessionScope.idproject}/scrum-master.html"/>"><i
				class="fa fa-arrow-left"></i> Previous</a>
		</div>
	</div>

	<div
		class="row  border-bottom white-bg dashboard-header padding-container">
		<div class="col-sm-3">
			<h2>List of Scrum masters</h2>
		</div>
		<div class="ibox-content">

			<div class="row m-b-sm m-t-sm">
				<div class="col-md-1">
					<button type="button" id="loading-example-btn"
						class="btn btn-white btn-sm">
						<i class="fa fa-refresh"></i> Refresh
					</button>
				</div>
				<div class="col-md-11">
					<div class="input-group">
						<input type="text" placeholder="Search"
							class="input-sm form-control"> <span
							class="input-group-btn">
							<button type="button" class="btn btn-sm btn-primary">
								Go!</button>
						</span>
					</div>
				</div>
			</div>

			<div class="project-list">

				<table class="table table-hover">
					<tbody>
						<c:forEach items="${scrummasters}" var="scrummaster">
						<tr>
							<td class="project-people" style="float:center"><a href=""><img alt="image"
									class="img-circle" src="http://webapplayers.com/inspinia_admin-v2.3/img/a3.jpg"></a></td>
							<td class="project-title"><a href="project_detail.html">${scrummaster.firstname} ${scrummaster.lastname}</a> <br /> <small>${scrummaster.email}</small></td>
							<td class="project-actions"><a href="#"
								class="btn btn-white btn-sm"><i class="fa fa-plus"></i>
									Add </a> </td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
		<div class="row border-bottom white-bg dashboard-header"></div>
	</div>

	<script>
$('.selectpicker').selectpicker();
</script>