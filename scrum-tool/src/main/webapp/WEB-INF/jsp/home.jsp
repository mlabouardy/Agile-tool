<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<nav class="navbar navbar-default navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Scrum Tool</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#home">Home</a></li>
				<li><a href="#features">Features</a></li>
				<li><a href="#contact">Contact</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<spring:url value="/login.html"/>">Login</a></li>
				<li><a href="<spring:url value="/register.html"/>">Register</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</nav>

  <header class="header">
        <div class="container">
          <div class="row">
            <div class="col-md-5 col-md-offset-1">
                <div class="content">
                  <div class="pull-middle">
                    <h1 class="page-header">Agile Project Management</h1>
                    <p class="lead">The most intuitive Scrum tool you've ever tried. It's lightning fast and a pleasure to use.</p>
                  </div>              
                </div>
            </div>
            <div class="col-md-4 col-md-offset-1 text-center mt-100 mb-100">
                <div class="phone">
                    <img class="img-responsive img-rounded" src="https://www.mountaingoatsoftware.com/uploads/articles/Scrum_256x256.png">
                </div>
            </div>
          </div>
        </div>
    </header>