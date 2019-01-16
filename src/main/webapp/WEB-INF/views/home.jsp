<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div id="main" class="container-fluid row">
	<div class="jumbotron col-sm">
	  <h1 class="display-4"><spring:message code="home.title"/></h1>
	  <p class="lead"><spring:message code="home.subtitle"/></p>
	  <hr class="my-4">
	  <p> <spring:message code="home.text1"/></p>
	  <p> <spring:message code="home.text2"/></p>
	  <div class="lead">
	    <form id="registerFields" action="/MUBA/login/register.html" method="post">
	    	<button class="btn btn-primary btn-lg" type="submit" name="action" value="new"><spring:message code="action.register"/></button>
	    </form>
	  </div>
	</div>
	<div id="usersChart" class="col-sm"></div>
</div>
