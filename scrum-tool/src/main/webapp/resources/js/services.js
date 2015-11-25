angular.module('scrumApp.services',[])
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
  });