<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript" src='../../resources/js/Chart.js'>
<!--

//-->
</script>

<nav class="navbar navbar-default navbar-custom">
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

<div class="container">
  <div class="row">
    <div class="col-md-5 col-md-offset-1">
      
      <h1>Burdown Chart : </h1>
      <script>
      	var datesBurndown = [];
		var expectedCostsBurndown = [];
		var effetiveCostsBurndown = [];
      </script>
      <c:forEach var="date" items="${dates}">
      	<script>
      		datesBurndown.push("${date}") ;
      	</script>
      </c:forEach>
      <c:forEach var="cost" items="${expectedCosts}">
      	<script>
      		expectedCostsBurndown.push("${cost}") ;
      	</script>
      </c:forEach>
      <c:forEach var="cost" items="${effectiveCosts}">
      	<script>
      		effetiveCostsBurndown.push("${cost}") ;
      	</script>
      </c:forEach>
      <canvas id="myChart" width="400" height="400"></canvas>
      
      
    </div>
  </div>
</div>

<script type="text/javascript">

//alert(${sprint.getId()});
var ctx = document.getElementById("myChart").getContext("2d");
var expectedCosts = ${expectedCosts};
var data = {
    labels: datesBurndown,
    datasets: [
        {
            label: "My First dataset",
            fillColor: "rgba(220,220,220,0.2)",
            strokeColor: "rgba(220,220,220,1)",
            pointColor: "rgba(220,220,220,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(220,220,220,1)",
            data: effetiveCostsBurndown
        },
        {
            label: "My Second dataset",
            fillColor: "rgba(151,187,205,0.2)",
            strokeColor: "rgba(151,187,205,1)",
            pointColor: "rgba(151,187,205,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(151,187,205,1)",
            data: expectedCostsBurndown
        }
    ]
};

var myLineChart = new Chart(ctx).Line(data);

myLineChart.update();




</script>