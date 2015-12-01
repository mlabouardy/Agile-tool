<jsp:include page="haut.jsp"></jsp:include>
<div class="container-fluid padding-container" ng-controller="commitCtrl"
	ng-init="init('${github.getRepository()}','${github.getUsername()}','${github.getPassword()}','${tag}')">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-success">
				<div class="panel-heading">List of commits of task {{task}}</div>
				<div class="panel-body">
					<div class="col-md-12">
						<div class="panel panel-primary">
							<!-- Default panel contents -->
							<div class="panel-heading">List of commits</div>
							<div class="panel-body">
								<div class="input-group">
									<span class="input-group-addon" id="search-icon"><i
										class="fa fa-search"></i></span> <input type="text"
										placeholder="Search something" class="form-control"
										aria-describedby="search-icon" ng-model="search" />
								</div>
								<br />
								<table class="table table-hover table-bordered">
									<thead>
										<tr>
											<td>Files</td>
											<td>Commiter</td>
											<td>Email</td>
											<td>Date</td>
											<td>Message</td>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="commit in commits | filter:search">
											<td><a ng-href="{{commit.files}}"
												class="btn btn-success btn-sm">View</a></td>
											<td>{{commit.commiter}}</td>
											<td>{{commit.email}}</td>
											<td>{{commit.date}}</td>
											<td><small>{{commit.message}}</small></td>
										</tr>
									<tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>