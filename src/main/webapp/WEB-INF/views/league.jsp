<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="leagueDisplay">
		<div id="leagueHeader">
			<form action="/MUBA/league/goToLeagueList.html" method="get">
     			<button id="toLeagueListButton" type="submit" name="action" value="return">Return to League List</button>
			</form>
			<h2><c:out value="${requestScope.league.leagueName}"/></h2>
		</div>
		<div id="schedule">
			<c:choose>
    			<c:when test="${requestScope.league.stages == '2'}">
       				<img src="/MUBA/resources/4league.png" alt="League Schedule of 2 stages"/>
       				<c:set var="leagueType" value="2stages" scope="page"/>
    			</c:when>
   				<c:otherwise>
       			 	<img src="/MUBA/resources/8league.png" alt="League Schedule of 3 stages"/>
       			 	<c:set var="leagueType" value="3stages" scope="page"/>
    			</c:otherwise>
			</c:choose>
			<c:set var="teamCounter" value="1" scope="page" />
			<c:forEach items="${requestScope.listStageTeamList}" var="stageTeamList">
				<c:forEach items="${stageTeamList}" var="stageTeam">
					<div class="teamCell${leagueType}" id="teamCell${teamCounter}">
						<c:if test="${stageTeam.teamId != '-1'}"> 
							<p><c:out value="${stageTeam.teamName}"/></p>
						</c:if>
				   	 <c:set var="teamCounter" value="${teamCounter + 1}" scope="page"/>
				   	 </div>
				</c:forEach>
			</c:forEach>
			<div class="teamCell${leagueType}" id="teamWinner">
				<c:if test="${leagueWinner.teamId != '-1'}"> 
					<p><c:out value="${leagueWinner.teamName}"/></p>
				</c:if>
			</div>
		
		</div>
		<div id="gameResults">
			<h3 id="gameResultsTitle"><c:out value="Game Results"/></h3>
			<c:set var="gameCounter" value="1" scope="page" />
				<c:forEach items="${gameList}" var="game">					
					<c:if test="${game.localTeamId != '-1' && game.visitorTeamId != '-1'}">
						<form action="/MUBA/match/showGame.html" method="post"> <!--CHANGE MATCH REDIRECTION LINK-->
							<input name="gameId" type="hidden" value="${game.gameId}">
			   				<button class="gameResultCell" id="gameResultCell${gameCounter}" type="submit" name="action" value=game.gameId>
								<c:set var="localKeyString" value="${game.localTeamId}"  scope="page"></c:set>
								<c:set var="visitorKeyString" value="${game.visitorTeamId}"  scope="page"></c:set>
								<p><c:out value="${teamMap[localKeyString]}"/></p> <p class="points"><c:out value="${game.localTeamResult}"/> : <c:out value="${game.visitorTeamResult}"/> </p><p> <c:out value="${teamMap[visitorKeyString]}"/></p>
					   	 	</button>
		       			</form>
				   	</c:if>
				    <c:set var="gameCounter" value="${gameCounter + 1}" scope="page"/>
				</c:forEach>
		</div>
</div>

