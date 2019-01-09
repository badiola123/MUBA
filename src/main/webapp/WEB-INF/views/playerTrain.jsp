<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Player info</title>
</head>
<body>
	<div id="train">
		<h2>Train ${player.name} ${player.surname}</h2>
		<p>Budget: ${team.budget}$</p>
		
		<div id="skills">
		
			<p>Resistance:</p>
			<div class="barContainer">
				<div class="barContent" style="width: ${chars.resistance}%">${chars.resistance}%</div>
			</div>
			<form action="/MUBA/team/trainResistance.html" method="post">
				<input name="playerId" type="hidden" value="${player.playerId}">
				<c:choose>
				<c:when test="${chars.resistance eq 100}">
				<button type="submit" disabled>Maximum skill</button>
				</c:when>
				<c:otherwise>
				<button type="submit">Train for <c:set var="result" value="${chars.resistance * 10000}" />${result}$</button>
				</c:otherwise>
				</c:choose>
			</form>
			
			<p>Ball control:</p>
			<div class="barContainer">
				<div class="barContent" style="width: ${chars.ballControl}%">${chars.ballControl}%</div>
			</div>
			<form action="/MUBA/team/trainBallControl.html" method="post">
				<input name="playerId" type="hidden" value="${player.playerId}">
				<button type="submit">
					Train for <c:set var="result" value="${chars.ballControl * 10000}" />${result}$</button>
			</form>
			
			<p>Defense:</p>
			<div class="barContainer">
				<div class="barContent" style="width: ${chars.defense}%">${chars.defense}%</div>
			</div>
			<form action="/MUBA/team/trainDefense.html" method="post">
				<input name="playerId" type="hidden" value="${player.playerId}">
				<button type="submit">
					Train for <c:set var="result" value="${chars.defense * 10000}" />${result}$</button>
			</form>
			
			<p>Long shoot:</p>
			<div class="barContainer">
				<div class="barContent" style="width: ${chars.longShoot}%">${chars.longShoot}%</div>
			</div>
			<form action="/MUBA/team/trainLongShoot.html" method="post">
				<input name="playerId" type="hidden" value="${player.playerId}">
				<button type="submit">
					Train for <c:set var="result" value="${chars.longShoot * 10000}" />${result}$</button>
			</form>
			
			<p>Short shoot:</p>
			<div class="barContainer">
				<div class="barContent" style="width: ${chars.shortShoot}%">${chars.shortShoot}%</div>
			</div>
			<form action="/MUBA/team/trainShortShoot.html" method="post">
				<input name="playerId" type="hidden" value="${player.playerId}">
				<button type="submit">
					Train for <c:set var="result" value="${chars.shortShoot * 10000}" />${result}$</button>
			</form>			
			
		</div>

	</div>
</body>
</html>