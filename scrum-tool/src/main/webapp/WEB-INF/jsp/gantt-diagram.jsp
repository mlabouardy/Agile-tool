<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-12" ng-controller="ganttCtrl">
	<div class="panel panel-primary">
		<div class="panel-heading">Gantt Diagram<a class="btn btn-success btn-xs pull-right" ng-click="refresh()">Refresh</a></div>
		<div class="panel-body">
			<div id="calendar"></div>
		</div>
	</div>
</div>