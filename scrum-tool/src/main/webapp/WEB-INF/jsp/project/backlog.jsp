<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true"%>

<div id="wrapper">
	<jsp:include page="sidebar-project.jsp"></jsp:include>

	<div id="page-wrapper" class="gray-bg dashbard-1">

		<%@include file="/resources/components/navbar-top.html"%>
			
			<div
		class="row border-bottom white-bg dashboard-header padding-container">
		<div class="col-lg-10">
			<h2>Backlog</h2>
		</div>

	</div>
	<div class="row border-bottom white-bg dashboard-header">
	<div class="col-sm-3">
		<a class="btn btn-success" href="/board/project/${sessionScope.idproject}/backlog/userstory/create.html"><i class="fa fa-plus"></i> Create User Story</a>
		</div>
	</div>
	
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>User stories</h5>
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
										<th>Tag</th>
										<th>Name</th>
										<th>Priority</th>
										<th>Difficulty</th>
										<th>Dependancies</th>
										<th>Operations</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${userstories}" var="userstory">
										<tr>
											<td>${userstory.tag}</td>
											<td>${userstory.name}</td>
											<td>${userstory.priority}</td>
											<td>${userstory.difficulty}</td>
											<td>${userstory.dependancies}</td>
											<td style="width:95px">
												<div class="button-group">
													<a class="btn btn-success btn-xs"><i class="fa fa-folder-open-o"></i></a>
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
	<div class="footer">
			<div class="pull-right">
				Mohamed <strong>Labouardy</strong>.
			</div>
			<div>
				<strong>Copyright</strong> University of Bordeaux &copy; 2015-2016
			</div>
		</div>
</div>