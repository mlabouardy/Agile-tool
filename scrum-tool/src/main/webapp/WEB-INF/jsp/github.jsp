<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container padding-container">
<form ng-controller="RepositoryCtrl">
  	<div class="form-group">
  		<label>Name:</label>
  		<input type="text" class="form-control" ng-model="repository" placeholder="mlabouardy/Commit-Monitor"/>
  	</div>
    <div class="form-group" required>
      <label>Visibility:</label>
      <select ng-model="visibility" class="form-control">
          <option>Public</option>
          <option>Private</option>
      </select>
    </div>
    <div ng-if="errorVisibility!=undefined">
      <div class="alert alert-danger" role="alert">
        {{errorVisibility}}
      </div>
    </div>
    <div ng-if="visibility=='Private'">
      <div class="form-group">
        <label>Username:</label>
        <input type="text" class="form-control" ng-model="$parent.username"/>
      </div>
      <div class="form-group">
        <label>Password:</label>
        <input type="password" class="form-control" ng-model="$parent.password"/>
      </div>
      </div>
  	<input type="submit" class="btn btn-success btn-block" value="Create"/>
  </form>
  </div>