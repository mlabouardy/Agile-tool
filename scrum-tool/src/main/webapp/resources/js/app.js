var agilApp=angular.module('agilApp',[]);

agilApp.controller('tasksController',function($scope){
	$scope.tasks=[
	              {name:"Model",dev:"Mohamed",start:"25-10-2015",end:"25-10-2015"},
	              {name:"View",dev:"Mohamed",start:"25-10-2015",end:"25-10-2015"},
	              {name:"Controller",dev:"Mohamed",start:"25-10-2015",end:"25-10-2015"},
	              {name:"BD",dev:"Mohamed",start:"25-10-2015",end:"25-10-2015"}
	              ];
	
	$scope.newTask=function(){
		$scope.tasks.push({
			name: $scope.name,
			dev: $scope.dev,
			start: $scope.start,
			end: $scope.end
		});
	}
	
});
