angular.module('scrumApp',['scrumApp.services'])
 .controller('repoInfoCtrl',function($scope, repoFactory, userFactory){
	 $scope.init=function(repository, username, password){
			$scope.repository=repository;
			$scope.username=username;
			$scope.password=password;
			
			repoFactory.getInfo($scope.repository).success(function(data){
		 		$scope.owner={
		 			login:data.owner.login,
		 			avatar:data.owner.avatar_url,
		 			url:data.owner.html_url
		 		};

		 		userFactory.getFollowers($scope.username).success(function(followers){
		 			$scope.owner.followers=followers.length;
		 		});

		 		userFactory.getFollowing($scope.username).success(function(following){
		 			$scope.owner.following=following.length;
		 		});

		 		$scope.repo={
		 			private_repo:data.private,
		 			url:data.html_url,
		 			description:data.description,
		 			created_at:data.created_at,
		 			updated_at:data.updated_at,
		 			language:data.language,
		 			clone:data.clone_url
		 		};

		 		if($scope.repo.private_repo){
		 			$scope.repo.private_repo="Private";
		 		}else{
		 			$scope.repo.private_repo="Public";
		 		}

		 		repoFactory.getContributors($scope.repository).success(function(contributors){
		 			$scope.contributors=[];
		 			$scope.data=[];
		 			for(var i=0;i<contributors.length;i++){
		 				$scope.contributors.push({
		 					login:contributors[i].login,
		 					avatar:contributors[i].avatar_url,
		 					contributions:contributors[i].contributions
		 				});

		 				$scope.data.push({
		 					value: contributors[i].contributions,
		 					label: contributors[i].login,
		 					color: getRandomColor()
		 				});
		 			}
		 			draw('commitChart', $scope.data);
		 		});

		 		repoFactory.getLanguages($scope.repository).success(function(languages){
		 			$scope.data=[];
		 			var keys=Object.keys(languages);
		 			keys.forEach(function(key){
		 				$scope.data.push({
		 					value: languages[key],
		 					label: key,
		 					color: getRandomColor()
		 				});
		 			});
		 			draw('languagesChart', $scope.data);
		 		});
		 		
		 		repoFactory.getContents($scope.repository).success(function(contents){
		 			$scope.contents=[];
		 			for(var i=0;i<contents.length;i++){
		 				$scope.contents.push({
		 					name:contents[i].name,
		 					size: Math.round( ((contents[i].size /1000)) * 10 ) / 10,
		 					download:contents[i].download_url
		 				});
		 			}
		 		});

		 	});
	 }
	 
 })
.controller('ganttCtrl',function($scope, $http){
	
	$scope.refresh=function(){
		$('#calendar').fullCalendar('refresh');
		console.log('refreshed');
	}
	
	$http.get('/tasks.json').success(function(data){
		$scope.tasks=[];
		for(var i=0;i<data.length;i++){
			var date = new Date(data[i].start);
			var year=date.getFullYear()
			var month=('0' + date.getMonth()).slice(-2);
			var day=('0' + date.getDate()).slice(-2);
			var h=('0' + date.getHours()).slice(-2);
			var m=('0' + date.getMinutes()).slice(-2);
			var s=('0' + date.getSeconds()).slice(-2);
			var start=year+"-"+month+"-"+day+"T"+h+":"+m+":"+s;
			var date = new Date(data[i].end);
			var year=date.getFullYear()
			var month=('0' + date.getMonth()).slice(-2);
			var day=('0' + date.getDate()).slice(-2);
			var h=('0' + date.getHours()).slice(-2);
			var m=('0' + date.getMinutes()).slice(-2);
			var s=('0' + date.getSeconds()).slice(-2);
			var end=year+"-"+month+"-"+day+"T"+h+":"+m+":"+s;
			$scope.tasks.push({
				title:data[i].title,
				start:start,
				end:end,
				color:data[i].color
			});
		}
		console.log($scope.tasks);
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			defaultDate: '2015-10-01',
			editable: true,
			events: $scope.tasks
		});
	});
	
})
.controller('commitCtrl',function($scope, repoFactory){
	$scope.init=function(repository, username, password, tag){
		$scope.repository=repository;
		$scope.username=username;
		$scope.password=password;
		$scope.tag=tag;
		
		repoFactory.getCommits($scope.repository).success(function(commits){
			$scope.commits=[];
			$scope.users=[];
			for(var i=0;i<commits.length;i++){
				var message=commits[i].commit.message;
				var taskTag= message.substr(0, message.indexOf(':')); 
				var currentMessage=message.substr(message.indexOf(":") + 1); 
				if(taskTag==$scope.tag){
 				$scope.commits.push({
	 				commiter:commits[i].commit.committer.name,
	 				email:commits[i].commit.committer.email,
	 				date:commits[i].commit.committer.date,
	 				message:currentMessage,
	 				files: "#/repository/"+$scope.repository+"/commits/"+commits[i].commit.tree.sha
 				});
				}
			}
		});
	};
	
	
});