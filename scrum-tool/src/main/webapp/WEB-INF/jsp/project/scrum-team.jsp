<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<div id="wrapper">
	<jsp:include page="sidebar-project.jsp"></jsp:include>

	<div id="page-wrapper" class="gray-bg dashbard-1">

		<%@include file="/resources/components/navbar-top.html"%>

		<div
			class="row border-bottom white-bg dashboard-header padding-container">
			<div class="col-lg-10">
				<h2>Scrum Masters</h2>
			</div>

		</div>
		<div class="row border-bottom white-bg dashboard-header">
			<div class="col-sm-3">
				<a class="btn btn-success"
					href="/board/project/${sessionScope.idproject}/scrum-master/create.html"><i
					class="fa fa-plus"></i> New member</a>
			</div>
		</div>

		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="row">
				<div class="col-lg-4">
					<div class="contact-box">
						<a href="profile.html">
							<div class="col-sm-4">
								<div class="text-center">
									<img alt="image" class="img-circle m-t-xs img-responsive"
										src="http://webapplayers.com/inspinia_admin-v2.3/img/a3.jpg">
									<div class="m-t-xs font-bold">Graphics designer</div>
								</div>
							</div>
							<div class="col-sm-8">
								<h3>
									<strong>Mohamed Labouardy</strong>
								</h3>
								<p>
									<i class="fa fa-map-marker"></i> Riviera State 32/106
								</p>
								<address>
									<strong>Twitter, Inc.</strong><br> 795 Folsom Ave, Suite
									600<br> San Francisco, CA 94107<br> <abbr
										title="Phone">P:</abbr> (123) 456-7890
								</address>
							</div>
							<div class="clearfix"></div>
						</a>
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