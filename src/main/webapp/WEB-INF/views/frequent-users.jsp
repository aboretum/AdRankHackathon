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
        <title>User Activity</title>
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
                            
                            <li><a href="#">About Us </a></li>
                            <li class="nav-user dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="./resources/images/user.png" class="nav-avatar" />
                                <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Your Profile</a></li>
                                    <li><a href="#">Edit Profile</a></li>
                                    <li><a href="#">Account Settings</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">Logout</a></li>
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
                                    </b> </a></li>
                                <li><a href="task.html"><i class="menu-icon icon-tasks"></i>Tasks <b class="label orange pull-right">
                                    </b> </a></li>
                            </ul>
                            <!--/.widget-nav-->
                            
                            
                            
                        </div>
                        <!--/.sidebar-->
                    </div>
                    <!--/.span3-->
                    <div class="span9">
                        <div class="content">
                            
                            <div class="module">
                                <div class="module-head">
                                    <h3>
                                        Most active users</h3>
                                </div>
                                <div class="module-body table">
                                    <table cellpadding="0" cellspacing="0" border="0" class="datatable-1 table table-bordered table-striped	 display"
                                        width="100%">
                                        <thead>
                                            <tr>
                                                <th>
                                                    User name
                                                </th>
                                                <th>
                                                    Visited the site
                                                </th>
                                                <th>
                                                    Email Address
                                                </th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach items="${userList}" var="user">
                                            <tr>
                                                <td>
                                                    ${user.userName}  
                                                </td>
                                                <td>
                                                    ${user.loginFrequency} times
                                                </td>
                                                <td>
                                                    ${user.userEmail} 
                                                </td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                        
                                    </table>
                                </div>
                            </div>
                            <!--/.module-->
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
