<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ page session="true"%>

<div id="wrapper">
	<jsp:include page="sidebar-project.jsp"></jsp:include>

	<div id="page-wrapper" class="gray-bg dashbard-1">

		<%@include file="/resources/components/navbar-top.html"%>
			
			<div
		class="row border-bottom white-bg dashboard-header padding-container">
		<div class="col-lg-10">
			<h2>Project ${sessionScope.idproject}</h2>
			<ol class="breadcrumb">
				<li><a href="<spring:url value="/board.html"/>">Dashboard</a></li>
				<li class="active"><strong>Projects</strong></li>
			</ol>
		</div>

	</div>
	<div class="row">
	<div class="col-lg-3">
                <div class="widget style1 navy-bg">
                    <div class="row">
                        <div class="col-xs-4">
                            <i class="fa fa-user fa-5x"></i>
                        </div>
                        <div class="col-xs-8 text-right">
                            <span> Scrum Masters </span>
                            <h2 class="font-bold">4</h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="widget style1 lazur-bg">
                    <div class="row">
                        <div class="col-xs-4">
                            <i class="fa fa-envelope-o fa-5x"></i>
                        </div>
                        <div class="col-xs-8 text-right">
                            <span> New messages </span>
                            <h2 class="font-bold">260</h2>
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