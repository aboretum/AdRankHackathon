<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>AdRank</title>
        <link type="text/css" href="./resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" href="./resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
        <link type="text/css" href="./resources/css/theme.css" rel="stylesheet">
        <link type="text/css" href="./resources/images/icons/css/font-awesome.css" rel="stylesheet">
        <link type="text/css" href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600'
            rel='stylesheet'>
    </head>
    <body>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-inverse-collapse">
                        <i class="icon-reorder shaded"></i></a><a class="brand" href="/app/">AdRank </a>
                    <div class="nav-collapse collapse navbar-inverse-collapse">
                        <ul class="nav nav-icons">
                            <li class="active"><a href="#"><i class="icon-envelope"></i></a></li>
                            <li><a href="#"><i class="icon-eye-open"></i></a></li>
                            <li><a href="#"><i class="icon-bar-chart"></i></a></li>
                        </ul>
                        <form class="navbar-search pull-left input-append" action="SearchKeyWord" method="post">
                        <input type="text" class="span5" name="keyword" placeholder="Search for videos">
                        <button class="btn" type="submit">
                            <i class="icon-search"></i>
                        </button>
                        </form>
                        <ul class="nav pull-right">
                            
                            <li><a href="#">About Us</a></li>
                            <li class="nav-user dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="./resources/images/user.png" class="nav-avatar" />
                                <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Your Profile</a></li>
                                    <li><a href="#">Edit Profile</a></li>
                                    <li><a href="#">Account Settings</a></li>
                                    <li class="divider"></li>
                                    <li><a href="Logout">Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!-- /.nav-collapse -->
                </div>
            </div>
            <!-- /navbar-inner -->
        </div>
        <!-- /navbar -->
        <div class="wrapper">
            <div class="container">
                <div class="row">
                    <div class="span3">
                        <div class="sidebar">
                            <ul class="widget widget-menu unstyled">
                                <li class="active"><a href="/app/Main"><i class="menu-icon icon-dashboard"></i>Dashboard
                                </a></li>
                                <li><a href="activity.html"><i class="menu-icon icon-bullhorn"></i>News Feed </a>
                                </li>
                                <li><a href="message.html"><i class="menu-icon icon-inbox"></i>Inbox <b class="label green pull-right">
                                    11</b> </a></li>
                                <li><a href="task.html"><i class="menu-icon icon-tasks"></i>Tasks <b class="label orange pull-right">
                                    19</b> </a></li>
                            </ul>
                            <!--/.widget-nav-->
                            
                            
                           
                        </div>
                        <!--/.sidebar-->
                    </div>
                    <!--/.span3-->
                    <div class="span9">
                        <div class="content">
                            <div class="btn-controls">
                                <div class="btn-box-row row-fluid">
                                    <a href="RecentSearch" class="btn-box big span4"><i class=" icon-random"></i><b>500</b>
                                        <p class="text-muted">
                                            Hot Searches</p>
                                    </a><a href="FrequentUsers" class="btn-box big span4"><i class="icon-user"></i><b>10</b>
                                        <p class="text-muted">
                                            Users</p>
                                    </a><a href="#" class="btn-box big span4"><i class="icon-money"></i><b>15,152</b>
                                        <p class="text-muted">
                                            Profit</p>
                                    </a>
                                </div>
                                
                            </div>
                            <!--/#btn-controls-->
                            <div class="btn-controls">
                                <div class="row-fluid">
                                    <div class="span9">
                                        <h3>Video Search result</h3>
                                    </div>
                                        <div class="btn-group btn-group-lg span3">
  												<button type="button" class="btn btn-default btn-align-right dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
   													 <span class="glyphicon glyphicon-cog"></span>Arrange Videos
  												</button>
  													<ul class="dropdown-menu" role="menu">
    													<li><a href="SortBySpecifics?orderSpecifics=vd_views">Sort by views</a></li>
    													<li><a href="SortBySpecifics?orderSpecifics=vd_categoryId">Sort by category</a></li>
    													<li><a href="SortBySpecifics?orderSpecifics=vd_date">Sort by upload date</a></li>
  													</ul>
										</div>
                                </div>
                                
                            </div>
                            
                           
                           <c:forEach items="${videoList}" var="video">	
                            <div class="module">
                                <div class="module-head">
                                    <h3>
                                        ${video.title}</h3>
                                </div>
                                <div class="module-body">
                                <div class="row-fluid">
                                	<div class="span8">
                                    <iframe width="560" height="315" src="https://www.youtube.com/embed/${video.url}"  frameborder="0" allowfullscreen></iframe>
                                    </div>
                                    <div class ="span4">
                                    		<ul  class="unstyled">
    											<li><strong>Author</strong>: ${video.author}</li>
    											<li><strong>Uploaded On</strong>:${video.publishDate} </li>
    											<li><strong>View</strong>: ${video.total_views} times</li>
    											<li><strong>Category</strong>: ${video.category} </li>
    											<li><strong>Description</strong>: ${video.description} </li>
  											</ul>
                                    </div>
                                    </div>
                                </div>
                            </div>
                           </c:forEach>
                            <!--/.module-->
                            
                            <div class="module hide">
                                <div class="module-head">
                                    <h3>
                                        Adjust Budget Range</h3>
                                </div>
                                <div class="module-body">
                                    <div class="form-inline clearfix">
                                        <a href="#" class="btn pull-right">Update</a>
                                        <label for="amount">
                                            Price range:</label>
                                        &nbsp;
                                        <input type="text" id="amount" class="input-" />
                                    </div>
                                    <hr />
                                    <div class="slider-range">
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                        <!--/.content-->
                    </div>
                    <!--/.span9-->
                </div>
            </div>
            <!--/.container-->
        </div>
        <!--/.wrapper-->
       
        <script src="./resources/scripts/jquery-1.9.1.min.js" type="text/javascript"></script>
        <script src="./resources/scripts/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
        <script src="./resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="./resources/scripts/flot/jquery.flot.js" type="text/javascript"></script>
        <script src="./resources/scripts/flot/jquery.flot.resize.js" type="text/javascript"></script>
        <script src="./resources/scripts/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="./resources/scripts/common.js" type="text/javascript"></script>
      
    </body>
