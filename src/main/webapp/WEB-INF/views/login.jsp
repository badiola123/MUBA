<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="javascripts"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:getAsString name="title"/></title>
    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
    </c:forEach>

    <c:forEach var="script" items="${javascripts}">
        <script src="<c:url value="${script}"/>"></script>
    </c:forEach>
</head>

<body>
<tiles:insertAttribute name="alerts"/>
<img src="/atlasr/resources/th.jpg" id="theme">

<img src="/atlasr/resources/eng.png" class="flag">

<div id="main">
    <h1 class="display-3"><spring:message code="websiteName"/></h1>
    <p>Dziel się swoimi robakami ze społecznością online.</p>
    <form id="loginFields" action="/atlasr/login/login.html" method="post">
        <input type="text" class="form-control" id="login" name = "username" placeholder="Login">
        <input type="password" class="form-control" id="password" name = "password" placeholder="Hasło">
    </form>
    <div id="buttons">
        <button class="btn btn-success" id="logButton">Zaloguj</button>
        <a href="/atlasr/login/registerForm.html" class="alert-link">Nie masz konta? Kliknij tutaj.</a>
    </div>
</div>
</body>


</html>
