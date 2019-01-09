<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="train.title"/></title>
</head>
<body>
	<div id="train">
		<h2><spring:message code="train.train"/> ${player.name} ${player.surname}</h2>
		<p><spring:message code="team.budget"/> ${team.budget}$</p>

		<div id="skills">

			<p><spring:message code="train.resistance"/></p>
			<div class="barContainer">
				<div class="barContent" style="width: ${chars.resistance}%">${chars.resistance}%</div>
			</div>
			<form action="/MUBA/team/trainResistance.html" method="post">
				<input name="playerId" type="hidden" value="${player.playerId}">
				<c:choose>
					<c:when test="${chars.resistance eq 100}">
						<button type="submit" disabled><spring:message code="train.max"/></button>
					</c:when>
					<c:otherwise>
						<button type="submit">
							<spring:message code="train.for"/>
							<c:set var="result" value="${chars.resistance * 10000}" />${result}$</button>
					</c:otherwise>
				</c:choose>
			</form>

			<p><spring:message code="train.ballControl"/></p>
			<div class="barContainer">
				<div class="barContent" style="width: ${chars.ballControl}%">${chars.ballControl}%</div>
			</div>
			<form action="/MUBA/team/trainBallControl.html" method="post">
				<input name="playerId" type="hidden" value="${player.playerId}">
				<c:choose>
					<c:when test="${chars.ballControl eq 100}">
						<button type="submit" disabled><spring:message code="train.max"/></button>
					</c:when>
					<c:otherwise>
						<button type="submit">
							<spring:message code="train.for"/>
							<c:set var="result" value="${chars.ballControl * 10000}" />${result}$</button>
					</c:otherwise>
				</c:choose>
			</form>

			<p><spring:message code="train.defense"/></p>
			<div class="barContainer">
				<div class="barContent" style="width: ${chars.defense}%">${chars.defense}%</div>
			</div>
			<form action="/MUBA/team/trainDefense.html" method="post">
				<input name="playerId" type="hidden" value="${player.playerId}">
				<c:choose>
					<c:when test="${chars.defense eq 100}">
						<button type="submit" disabled><spring:message code="train.max"/></button>
					</c:when>
					<c:otherwise>
						<button type="submit">
							<spring:message code="train.for"/>
							<c:set var="result" value="${chars.defense * 10000}" />${result}$</button>
					</c:otherwise>
				</c:choose>
			</form>

			<p><spring:message code="train.longShoot"/></p>
			<div class="barContainer">
				<div class="barContent" style="width: ${chars.longShoot}%">${chars.longShoot}%</div>
			</div>
			<form action="/MUBA/team/trainLongShoot.html" method="post">
				<input name="playerId" type="hidden" value="${player.playerId}">
				<c:choose>
					<c:when test="${chars.longShoot eq 100}">
						<button type="submit" disabled><spring:message code="train.max"/></button>
					</c:when>
					<c:otherwise>
						<button type="submit">
							<spring:message code="train.for"/>
							<c:set var="result" value="${chars.longShoot * 10000}" />${result}$</button>
					</c:otherwise>
				</c:choose>
			</form>

			<p><spring:message code="train.shortShoot"/></p>
			<div class="barContainer">
				<div class="barContent" style="width: ${chars.shortShoot}%">${chars.shortShoot}%</div>
			</div>
			<form action="/MUBA/team/trainShortShoot.html" method="post">
				<input name="playerId" type="hidden" value="${player.playerId}">
				<c:choose>
					<c:when test="${chars.shortShoot eq 100}">
						<button type="submit" disabled><spring:message code="train.max"/></button>
					</c:when>
					<c:otherwise>
						<button type="submit">
							<spring:message code="train.for"/>
							<c:set var="result" value="${chars.shortShoot * 10000}" />${result}$</button>
					</c:otherwise>
				</c:choose>
			</form>

		</div>

	</div>
</body>
</html>