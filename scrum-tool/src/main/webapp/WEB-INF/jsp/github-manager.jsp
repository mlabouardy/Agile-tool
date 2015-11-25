<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				Github Manager <a class="btn btn-success pull-right btn-xs"
					href="/board/${scrumMaster.getProject().getId()}/github/create.html">Create</a>
			</div>
			<div class="panel-body">

				<c:if test="${scrumMaster.getProject().getGithub()!=null}">
					<div ng-controller="repoInfoCtrl"
				ng-init="init('${scrumMaster.getProject().getGithub().getRepository()}','${scrumMaster.getProject().getGithub().getUsername()}','${scrumMaster.getProject().getGithub().getPassword()}')">
					<div class="row">
						<div class="col-md-12">
							<div class="alert alert-success" role="alert">${scrumMaster.getProject().getGithub().getRepository()}</div>
							<div class="button-group pull-right">
								<a class="btn btn-danger btn-xs">Delete</a>
								<a class="btn btn-primary btn-xs">Edit</a>
							</div>
						</div>
					</div>
						<br /> <br />
						<div class="row">
							<div class="col-md-3">
								<div class="panel panel-primary">
									<div class="panel-heading">
										Owner <a class="btn btn-danger btn-xs pull-right"
											ng-href="{{owner.url}}"><i class="fa fa-github-alt">
												View</i></a>
									</div>
									<div class="panel-body">
										<img class="img-circle" ng-src="{{owner.avatar}}" width="40px" />
										{{owner.login}}<br /> <br />
										<ul class="list-group">
											<li class="list-group-item"><span class="badge">{{owner.followers}}</span>
												<small>Followers</small></li>
											<li class="list-group-item"><span class="badge">{{owner.following}}</span>
												<small>Following</small></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="panel panel-primary">
									<div class="panel-heading">
										Repo info
										<div class="button-group pull-right">
											<a class="btn btn-success btn-xs">{{repo.language}}</a> <a
												class="btn btn-warning btn-xs">{{repo.private_repo}}</a> <a
												class="btn btn-danger btn-xs" ng-href="{{repo.url}}"><i
												class="fa fa-code-fork"> Open</i></a>
										</div>
									</div>
									<div class="panel-body">
										<i class="fa fa-info"></i>: <small>{{repo.description}}</small><br />
										<i class="fa fa-clone"></i>: <small>{{repo.clone}}</small><br />
										<i class="fa fa-calendar-o"></i>: <small>{{repo.created_at}}</small><br />
										<i class="fa fa-calendar-times-o"></i>: <small>{{repo.updated_at}}</small><br />
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="panel panel-primary">
									<div class="panel-heading">Contributors</div>
									<div class="panel-body">
										<ul class="list-group">
											<li class="list-group-item"
												ng-repeat="contributor in contributors"><span
												class="badge">{{contributor.contributions}}</span> <img
												class="img-circle" ng-src="{{contributor.avatar}}"
												width="30px" /> <small>{{contributor.login}}</small></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="panel panel-primary">
								<!-- Default panel contents -->
								<div class="panel-heading">Languages</div>
								<div class="panel-body">
									<canvas id="languagesChart">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="panel panel-primary">
								<!-- Default panel contents -->
								<div class="panel-heading">Commit stats</div>
								<div class="panel-body">
									<canvas id="commitChart">
								</div>
							</div>
						</div>
					</div>
					</div>
				</c:if>
			</div>
		</div>