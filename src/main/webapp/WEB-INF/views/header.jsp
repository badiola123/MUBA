<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<fmt:bundle basename="resources.View">
<title><fmt:message key="header.appName"/></title>
</fmt:bundle>
</head>
<body>
<fmt:bundle basename="edu.eskola.muba.resources.View">
<header>

  <div class="banner">
    <img class="logo"
      src="/MUBA/resources/logo.png"
      alt="MUBA"/>
    
    <div class="loginForm">
      <c:choose>
        <c:when test="${not empty sessUser}">
		  <div class="logout">
		    <h3>Welcome "${sessUser.username}"</h3>
		    <form action="/MUBA/login/logout.html" method="get">
			  <button type="submit" name="action" value="logout"><fmt:message key="action.logout"/></button>
			</form>
		  </div>
        </c:when>
        <c:otherwise>
          <div class="login">
            <form action="/MUBA/login/login.html" method="post">
		      <input id="usernameTxt" type="text" name="username" required value="<c:out value="${requestScope.username}" default=""/>" placeholder="Username"/>
		      <input id="passwordTxt" type="password" name="password" required value="<c:out value="${requestScope.password}" default=""/>" placeholder="Password"/>
		      <button id="loginBut" type="submit" name="action" value="login"><fmt:message key="action.login"/></button>
	        </form>
	        <form action="Register" method="get">
	  	      <button id="registerBut" type="submit" name="action" value="new"><fmt:message key="action.register"/></button>
	        </form>
          </div>
	    </c:otherwise>
	  </c:choose>
      
	  <div class="options">
	    <!-- Enable language changing -->
	    <div class="lang">
		  <form action="Locale" method="post">
		    <button type="submit" name="langTag" value="en"><fmt:message key="language.en"/></button>
		    <button type="submit" name="langTag" value="es-ES"><fmt:message key="language.es"/></button>
		    <button type="submit" name="langTag" value="eu"><fmt:message key="language.eu"/></button>
		  </form>
		</div>
	  </div>
	</div>
  </div>
  
	<nav id="options">
		<a href="/MUBA/login/home.html"><fmt:message key="header.home"/></a><!--  
		--><a href="team"><fmt:message key="header.team"/></a><!--
		--><a href="league"><fmt:message key="header.league"/></a><!--
		--><a href="/MUBA/match/goToMatch.html"><fmt:message key="header.match"/></a><!--
		--><a href="account"><fmt:message key="header.account"/></a>
	</nav>
</header>
</fmt:bundle>
<jsp:include page="messages_and_errors.jsp"></jsp:include>