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
		<div id="skills">
			<div class="w3-light-grey w3-round">
				<div class="w3-container w3-round w3-blue" style="width: ${chars}%">25%</div>
			</div>
			<p>Resistance</p>
		</div>

	</div>
</body>
</html>