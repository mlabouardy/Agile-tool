angular.module('scrumApp',[])
.factory('repoFactory', function ($http) {
    var baseUrl="https://api.github.com/repos/";
    // Public API here
    return {
      getInfo: function(repo){
      	return $http.get(baseUrl+repo);
      },
      getContributors: function (repo) {
        return $http.get(baseUrl+repo+'/contributors');
      },
      getLanguages:function(repo){
      	return $http.get(baseUrl+repo+'/languages');
      },
      getContents:function(repo){
      	return $http.get(baseUrl+repo+'/contents');
      },
      getCommits:function(repo){
      	return $http.get(baseUrl+repo+'/commits');
      }
    };
  })
  .factory('userFactory', function ($http) {
     var baseUrl="https://api.github.com/users/";
    
    return {
      getFollowers: function(user){
        return $http.get(baseUrl+user+'/followers');
      },
      getFollowing: function (user) {
        return $http.get(baseUrl+user+'/following');
      }
    };
  })
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