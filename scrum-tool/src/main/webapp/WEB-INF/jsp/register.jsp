<div class="middle-box">
	<form action="/j_spring_security_check" method="post">
		<div class="form-group">
			<label for="name">First name:</label> <input type="text"
				class="form-control" name='j_username' />
		</div>
		<div class="form-group">
			<label for="name">Last name:</label> <input type="text"
				class="form-control" name='j_username' />
		</div>
		<div class="form-group">
		<label for="name">Job role:</label>
			<div class="dropdown">
				<button class="btn btn-success btn-block dropdown-toggle" type="button"
					id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="true">
					Choose a role<span class="caret pull-right"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="#">Product Owner</a></li>
					<li><a href="#">Scrum Master</a></li>
					<li><a href="#">Scrum team</a></li>
				</ul>
			</div>
		</div>
		<div class="form-group">
			<label for="name">Email:</label> <input type="text"
				class="form-control" name='j_username' />
		</div>
		<div class="form-group">
			<label for="name">Password:</label> <input type="password"
				class="form-control" name='j_password' />
		</div>
		<input type="submit"
			class="btn btn-success btn-block btn-custom-color" value="Sign up" />
	</form>
	<p class="text-muted text-center">
		<small>Do you have an account?</small>
	</p>
	<a class="btn btn-sm btn-white btn-block" href="login.html">Sign in</a>
</div>