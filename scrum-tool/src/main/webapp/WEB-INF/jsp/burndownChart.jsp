<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript" src='../../resources/js/Chart.js'>
<!--
	
//-->
</script>
<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-heading">Burndown Chart</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-8 col-sm-8 col-lg-8 col-md-offset-1">

					<script>
						var datesBurndown = [];
						var expectedCostsBurndown = [];
						var effetiveCostsBurndown = [];
					</script>
					<c:forEach var="date" items="${dates}">
						<script>
							datesBurndown.push("${date}");
						</script>
					</c:forEach>
					<c:forEach var="cost" items="${expectedCosts}">
						<script>
							expectedCostsBurndown.push("${cost}");
						</script>
					</c:forEach>
					<c:forEach var="cost" items="${effectiveCosts}">
						<script>
							effetiveCostsBurndown.push("${cost}");
						</script>
					</c:forEach>
					<canvas id="myChart" width="400" height="400"></canvas>

				</div>
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript">
		var ctx = document.getElementById("myChart").getContext("2d");
		var data = {
			labels : datesBurndown,
			datasets : [ {
				label : "My First dataset",
				fillColor : "rgba(220,220,220,0.2)",
				strokeColor : "rgba(220,220,220,1)",
				pointColor : "rgba(220,220,220,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(220,220,220,1)",
				data : effetiveCostsBurndown
			}, {
				label : "My Second dataset",
				fillColor : "rgba(151,187,205,0.2)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(151,187,205,1)",
				data : expectedCostsBurndown
			} ]
		};

		var myLineChart = new Chart(ctx).Line(data);

		myLineChart.update();
	</script>