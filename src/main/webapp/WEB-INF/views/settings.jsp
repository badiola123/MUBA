<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<input id="current" type="hidden" value="3">
<div id="main">
<p>Zmień hasło</p>
<form id="passFields" action="/atlasr/settings/password.html" method="post">
    <input type="password" class="form-control" id="oldPassword" name = "oldPassword" placeholder="Aktualne hasło">
    <input type="password" class="form-control" id="newPassword" name = "newPassword" placeholder="Nowe hasło">
    <input type="password" class="form-control" id="password2" name = "password2" placeholder="Powtórz nowe hasło">
</form>
<button id="changePassword" class="btn btn-success">Zmień hasło</button>
</div>
