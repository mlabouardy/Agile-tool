
<%@ page session="true"%>
<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav metismenu" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<span> <img alt="image" class="img-circle"
						src="http://webapplayers.com/inspinia_admin-v2.3/img/profile_small.jpg" />
					</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
						class="clear"> <span class="block m-t-xs"> <strong
								class="font-bold">David Williams</strong>
						</span> <span class="text-muted text-xs block">Product Owner <b
								class="caret"></b></span>
					</span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a href="profile.html">Profile</a></li>
						<li class="divider"></li>
						<li><a href="/logout">Logout</a></li>
					</ul>
				</div>
				<div class="logo-element">AT</div>
			</li>
			<li class="active"><a href="/board/project/${sessionScope.idproject}.html"><i
					class="fa fa-th-large"></i> <span class="nav-label">Dashboard</span>
			</a></li>
			<li><a href="/board/project/${sessionScope.idproject}/backlog.html"><i
					class="fa fa-table"></i> <span class="nav-label">Backlog</span></a></li>
			<li><a href="/board/project/${sessionScope.idproject}/scrum-master.html"><i class="fa fa-users"></i> <span
					class="nav-label">Scrum Masters</span></a></li>
			<li><a href="/team.html"><i class="fa fa-bar-chart-o"></i> <span
					class="nav-label">Graphs</span></a></li>
		</ul>

	</div>
</nav>
