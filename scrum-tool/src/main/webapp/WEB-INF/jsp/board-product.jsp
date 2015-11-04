<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div ng-controller="tasksController">

	<div class="row  border-bottom white-bg dashboard-header">
		<div class="col-sm-3">
			<h2>Product Owner</h2>
		</div>
		
	</div>
	<div class="row border-bottom white-bg dashboard-header">
	<div class="col-sm-3">
		<a class="btn btn-success" href="board/create/project.html"><i class="fa fa-plus"></i> Create project</a>
		</div>
	</div>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>List of projects</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">

						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>Name</th>
										<th>Description</th>
										<th>Options</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${projects}" var="project">
										<tr>
											<td>${project.name}</td>
											<td>${project.description}</td>
											<td style="width:95px">
												<div class="button-group">
													<a class="btn btn-success btn-xs" href="board/project/${project.id}.html"><i class="fa fa-eye"></i></a>
													<a class="btn btn-warning btn-xs"><i class="fa fa-pencil"></i></a>
													<a class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i></a>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
				</div>
			</div>
		</div>

	</div>
	
	
<script>

</script>