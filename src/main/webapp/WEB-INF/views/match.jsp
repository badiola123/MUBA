<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="match.title" /></title>
</head>
<body>
	<div id="match">
		<header>
			<div class="teamName">${localTeam.teamName}</div>
			<div class="score">${score}</div>
			<div class="teamName">${visitorTeam.teamName}</div>
		</header>
		<c:choose>
			<c:when test="${nextMatch eq 'noGame'}">
				<p id="nextMatch">
					<spring:message code="match.noNext" />
				</p>
			</c:when>
			<c:otherwise>
				<c:if test="${not empty nextMatch}">
					<p id="nextMatch">
						<spring:message code="match.next" />
						${nextMatch}
					</p>
				</c:if>
			</c:otherwise>
		</c:choose>

		<div class="main">
			<div class="pitch">
				<img alt="basketball pitch" src="/MUBA/resources/pitch.png" />
				<div id="player0">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${localPlayers[0].name} ${localPlayers[0].surname}</p>
				</div>
				<div id="player1">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${localPlayers[1].name} ${localPlayers[1].surname}</p>
				</div>
				<div id="player2">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${localPlayers[2].name} ${localPlayers[2].surname}</p>
				</div>
				<div id="player3">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${localPlayers[3].name} ${localPlayers[3].surname}</p>
				</div>
				<div id="player4">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${localPlayers[4].name} ${localPlayers[4].surname}</p>
				</div>
				<div id="player5">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${visitorPlayers[0].name} ${visitorPlayers[0].surname}</p>
				</div>
				<div id="player6">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${visitorPlayers[1].name} ${visitorPlayers[1].surname}</p>
				</div>
				<div id="player7">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${visitorPlayers[2].name} ${visitorPlayers[2].surname}</p>
				</div>
				<div id="player8">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${visitorPlayers[3].name} ${visitorPlayers[3].surname}</p>
				</div>
				<div id="player9">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${visitorPlayers[4].name} ${visitorPlayers[4].surname}</p>
				</div>
			</div>
			<div class="dataLog">${matchLogs}</div>
		</div>
	</div>
</body>
</html>