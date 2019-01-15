<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="newLeague">
  <h1><spring:message code="leagueList.newLeague"/></h1>
  <form action="/MUBA/league/confirmLeague.html" method="post">
	<input id="leagueName" class="textInput" type="text" name="leagueName" required placeholder="<spring:message code="league.name"/>"/>
	<input id="leagueName" class="textInput" type="text" name="leagueDesc" required placeholder="<spring:message code="league.desc"/>"/>
	<input id="4teamRadio" class="rButton" type="radio" name="teamAmount" checked="checked" value="4"/>4 Teams
	<input id="8teamRadio" class="rbutton" type="radio" name="teamAmount" value="8"/>8 Teams
	<button id="confirmButton" class="button" type="submit" name="action" value="confirmLeague"><spring:message code="action.save"/></button>
 	<button id="cancelButton" class="button" type="submit" name="action" value="goToLeagueList"><spring:message code="action.cancel"/></button>
 
  </form>
</div>
