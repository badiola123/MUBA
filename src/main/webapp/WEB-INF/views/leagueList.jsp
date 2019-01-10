<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id=leagueListDisplay>
	<div id="optionBar">
		<form action="/MUBA/league/newLeague.html" method="post">
	    	<button id="newLeagueButton" type="submit" name="action" value="newLeague"><fmt:message key="action.new"/></button>
		</form>
	</div>
	<div id="leagueList">
		<c:forEach items="${requestScope.leagues}" var="league">
			
			<div class="leagueItem">
				<h2><a href="goToLeague.html?leagueId=${league.leagueId}"><c:out value="${league.leagueName}"/></a></h2>	
				<p><c:out value="${league.leagueDesc}"/></p>
				<h4>Starting Date: <c:out value="${league.startDate}"/></h4>	
				<h4>Ending Date: <c:out value="${league.endDate}"/></h4>	
			</div>
		</c:forEach>
  </div>
</div>