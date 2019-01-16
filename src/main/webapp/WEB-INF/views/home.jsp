<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div id="main" class="container-fluid row">
	<div class="jumbotron col-sm">
	  <h1 class="display-4">Hello!</h1>
	  <p class="lead">Wellcome to Mondragon Unibertsitatea Basketball Association online game</p>
	  <hr class="my-4">
	  <p> Create and manage your own basketball team, train the players and good luck in the games!</p>
	  <p> If you still don't have an account register and have fun!</p>
	  <div class="lead">
	    <form id="registerFields" action="/MUBA/login/register.html" method="post">
	    	<button class="btn btn-primary btn-lg" type="submit" name="action" value="new"><spring:message code="action.register"/></button>
	    </form>
	  </div>
	</div>
	<div id="usersChart" class="col-sm"></div>
</div>
