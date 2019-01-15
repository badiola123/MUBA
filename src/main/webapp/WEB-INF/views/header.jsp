<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<header>

  <div class="banner">
    <img class="logo"
      src="/MUBA/resources/logo.png"
      alt="MUBA"/>
    
    <div class="loginForm">
      <c:choose>
        <c:when test="${not empty sessUser}">
		  <div class="logout">
		    <h3><spring:message code="header.welcome"/> "${sessUser.username}"</h3>
		    <form action="/MUBA/login/logout.html" method="get">
			  <button type="submit" name="action" value="logout"><spring:message code="action.logout"/></button>
			</form>
		  </div>
        </c:when>
        <c:otherwise>
          <div class="login">
            <form action="/MUBA/login/login.html" method="post">
		      <input id="usernameTxt" type="text" name="username" required placeholder="<spring:message code="header.username"/>"/>
		      <input id="passwordTxt" type="password" name="password" required placeholder="<spring:message code="header.password"/>"/>
		      <button id="loginBut" type="submit" name="action" value="login"><spring:message code="action.login"/></button>
	        </form>
	        <form action="/MUBA/login/register.html" method="get">
	  	      <button id="registerBut" type="submit" name="action" value="new"><spring:message code="action.register"/></button>
	        </form>
          </div>
	    </c:otherwise>
	  </c:choose>
	  
	  <div class="lang">
		<a href="?locale=en"><spring:message code="language.en"/></a>
		<a href="?locale=es_ES"><spring:message code="language.es"/></a>
		<a href="?locale=eu"><spring:message code="language.eu"/></a>
	  </div>
	  
    </div>
  </div>
  
	<nav id="options">
		<a id="homeRef" class="selected" href="/MUBA/login/home.html"><spring:message code="header.home"/></a><!--  
		--><a id="teamRef" class="notSelected" href="/MUBA/team/goToTeam.html"><spring:message code="header.team"/></a><!--
		--><a id="leagueRef" class="notSelected" href="/MUBA/league/goToLeagueList.html"><spring:message code="header.league"/></a><!--
		--><a id="matchRef" class="notSelected" href="/MUBA/match/goToMatch.html"><spring:message code="header.play"/></a><!--
		--><a id="accountRef" class="notSelected" href="/MUBA/account/manage.html"><spring:message code="header.account"/></a>	</nav>
</header>