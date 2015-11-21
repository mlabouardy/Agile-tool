angular.module('scrumApp',[])
.factory('repoFactory', function ($http) {
    var baseUrl="https://api.github.com/repos/";
    // Public API here
    return {
      getInfo: function(user, repo){
      	return $http.get(baseUrl+user+'/'+repo);
      },
      getContributors: function (user, repo) {
        return $http.get(baseUrl+user+'/'+repo+'/contributors');
      },
      getLanguages:function(user, repo){
      	return $http.get(baseUrl+user+'/'+repo+'/languages');
      },
      getContents:function(user, repo){
      	return $http.get(baseUrl+user+'/'+repo+'/contents');
      },
      getCommits:function(repo){
      	return $http.get(baseUrl+repo+'/commits');
      }
    };
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