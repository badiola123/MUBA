<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id=leagueListDisplay>
	<div id="optionBar">
		<form action="/MUBA/league/newLeague.html" method="post">
	    	<button id="newLeagueButton" type="submit" name="action" value="newLeague"><spring:message code="leagueList.newLeague"/></button>
		</form>
		<form action="/MUBA/league/goToLeagueList.html" method="get">
	    	<button id="runningBut" type="submit" name="category" value="running"><spring:message code="leagueList.running"/></button>
			<button id="finishedBut" type="submit" name="category" value="finished"><spring:message code="leagueList.finished"/></button>
			<button id="notStartedBut" type="submit" name="category" value="notStarted"><spring:message code="leagueList.notStarted"/></button>
			<button id="availableBut" type="submit" name="category" value="available"><spring:message code="leagueList.available"/></button>
		</form>
		
		
	</div>
	<div id="leagueList">
		<c:forEach items="${requestScope.leagues}" var="league">
			<c:set var="leagueTeamsNumber" value="4"/>
			<c:if test="${league.stages == 3}">
 			 	<c:set var="leagueTeamsNumber" value="8"/>
			</c:if>
			<div class="leagueItem">
			<c:choose>
				<c:when test="${requestScope.category eq 'running' || requestScope.category eq 'finished' }">
					<h2><a href="goToLeague.html?leagueId=${league.leagueId}"><c:out value="${league.leagueName}"/></a></h2>	
					<p><c:out value="${league.leagueDesc}"/></p>
					<h4>Starting Date: <c:out value="${league.startDate}"/></h4>	
					<h4>Ending Date: <c:out value="${league.endDate}"/></h4>	
				</c:when>
				<c:when test="${requestScope.category eq 'notStarted'}">
					<h2><c:out value="${league.leagueName}"/></h2>	
					<p><c:out value="${league.leagueDesc}"/></p>
					<h4>Teams registered: <c:out value="${requestScope.joinedTeamsMap[league.leagueId]}"/>/<c:out value="${leagueTeamsNumber}"/></h4>	
					<form action="/MUBA/league/leagueActions.html" method="get">
	    				<input name="leagueId" type="hidden" value="${league.leagueId}">
	    				<button id="leaveButton" type="submit" name="action" value="leave"><spring:message code="action.leave"/></button>
					</form>
				</c:when>
				<c:when test="${requestScope.category eq 'available'}">
					<h2><c:out value="${league.leagueName}"/></h2>	
					<p><c:out value="${league.leagueDesc}"/></p>
					<h4>Teams registered: <c:out value="${requestScope.joinedTeamsMap[league.leagueId]}"/>/<c:out value="${leagueTeamsNumber}"/></h4>	
					<form action="/MUBA/league/leagueActions.html" method="get">
						<input name="leagueId" type="hidden" value="${league.leagueId}">
	    				<button id="joinButton" type="submit" name="action" value="join"><spring:message code="action.join"/></button>
					</form>
				</c:when>
				

			</c:choose>
			</div>
		</c:forEach>
  </div>
</div>