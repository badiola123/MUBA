<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Match</title>
</head>
<body>
	<div id="team">
		<h2>Initial Five Selection</h2>
		<div id="initial">
			<img alt="Half of the basketball pitch"
				src="/MUBA/resources/initial.png">
			<form action="/MUBA/team/save.html" method="post">



				<div class="playerPosition">

					<div class="singlePosition">
						<spring:bind path="players">
							<label>Position 1</label>
							<form:select path="players" name="position1">
								<c:forEach items="${players}" var="player" varStatus="status">
									<c:choose>
										<c:when test="${player.key eq position1}">
											<option value="${player}" selected="true">${player.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${player}">${player.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select>
						</spring:bind>
					</div>

					<div class="singlePosition">
						<spring:bind path="players">
							<label>Position 2</label>
							<form:select path="players" name="position2">
								<c:forEach items="${players}" var="player" varStatus="status">
									<c:choose>
										<c:when test="${player.key eq position2}">
											<option value="${player}" selected="true">${player.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${player}">${player.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select>
						</spring:bind>
					</div>

					<div class="singlePosition">
						<spring:bind path="players">
							<label>Position 3</label>
							<form:select path="players" name="position3">
								<c:forEach items="${players}" var="player" varStatus="status">
									<c:choose>
										<c:when test="${player.key eq position3}">
											<option value="${player}" selected="true">${player.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${player}">${player.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select>
						</spring:bind>
					</div>

					<div class="singlePosition">
						<spring:bind path="players">
							<label>Position 4</label>
							<form:select path="players" name="position4">
								<c:forEach items="${players}" var="player" varStatus="status">
									<c:choose>
										<c:when test="${player.key eq position4}">
											<option value="${player}" selected="true">${player.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${player}">${player.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select>
						</spring:bind>
					</div>

					<div class="singlePosition">
						<spring:bind path="players">
							<label>Position 5</label>
							<form:select path="players" name="position5">
								<c:forEach items="${players}" var="player" varStatus="status">
									<c:choose>
										<c:when test="${player.key eq position5}">
											<option value="${player}" selected="true">${player.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${player}">${player.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select>
						</spring:bind>
					</div>

				</div>
				<button id="saveButton" type="submit">Save
					changes</button>
			</form>

		</div>

	</div>
</body>
</html>