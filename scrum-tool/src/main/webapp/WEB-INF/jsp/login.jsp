<jsp:include page="haut.jsp"></jsp:include>
<div class="middle-box">
	<form  action="/j_spring_security_check" method="post">
		<div class="form-group">
			<label for="email">Email:</label>
			<input type="email" class="form-control" name='j_username'/>
		</div>
		<div class="form-group">
			<label for="password">Password:</label>
			<input type="password" class="form-control" name='j_password'/>
		</div>
		<input type="submit" class="btn btn-success btn-block btn-custom-color"  value="Login"/>
	</form>
	<a class="btn btn-sm btn-white btn-block" href="register.html">Forgot password ?</a>
	<p class="text-muted text-center"><small>Do not have an account?</small></p>
	<a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
</div>