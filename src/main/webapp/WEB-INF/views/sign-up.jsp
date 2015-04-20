<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Sign up</title>
	<link type="text/css" href="./resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link type="text/css" href="./resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link type="text/css" href="./resources/css/theme.css" rel="stylesheet">
	<link type="text/css" href="./resources/images/icons/css/font-awesome.css" rel="stylesheet">
	<link type="text/css" href='./resources/http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600' rel='stylesheet'>
</head>
<body>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-inverse-collapse">
					<i class="icon-reorder shaded"></i>
				</a>

			  	<a class="brand" href="index.html">
			  		AdRank
			  	</a>

				<div class="nav-collapse collapse navbar-inverse-collapse">
				
					<ul class="nav pull-right">

		
						
					</ul>
				</div><!-- /.nav-collapse -->
			</div>
		</div><!-- /navbar-inner -->
	</div><!-- /navbar -->



	<div class="wrapper">
		<div class="container">
			<div class="row">
				<div class="module module-login span4 offset4">
					<form action="AddUser" class="form-vertical" method="post">
						<div class="module-head">
							<h3>Fill out this form to sign up</h3>
						</div>
						<div class="module-body">
							<div class="control-group">
								<div class="controls row-fluid">
									<input class="span12" type="text" id="inputEmail" name="username" placeholder="User name">
								</div>
							</div>
							<div class="control-group">
								<div class="controls row-fluid">
									<input class="span12" type="text" id="inputEmail" name="email" placeholder="Email">
								</div>
							</div>
							<div class="control-group">
								<div class="controls row-fluid">
									<input class="span12" type="password" id="inputPassword" name="password" placeholder="Password">
								</div>
							</div>
						</div>
						<div class="module-foot">
							<div class="control-group">
								<div class="controls clearfix">
									<button type="submit" class="btn btn-primary pull-right">Sign me up</button>
									
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div><!--/.wrapper-->

	
	<script src="./resources/scripts/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="./resources/scripts/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
	<script src="./resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</body>
