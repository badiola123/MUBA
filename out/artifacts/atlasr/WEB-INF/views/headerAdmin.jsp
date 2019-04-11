<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<header>
    <div id = "top">
        <h1 class="display-3">Atlas Robaków</h1>
        <div id = "userPanel">
            <p>Użytkownik: <strong>${sessUser.username}</strong></p>
            <form action="/atlasr/login/logout.html" method="post">
            <button class="btn btn-success" id="logout">Wyloguj</button>
            </form>
        </div>
    </div>
    <div id="bot">
        <a href="/atlasr/allUsers/page.html" class="btn btn-success" id="nav1">Użytownicy</a>
        <a href="/atlasr/robaksAdmin/page.html" class="btn btn-success" id="nav2">Robaki</a>
        <a href="/atlasr/settings/page.html" class="btn btn-success" id="nav3">Ustawienia</a>
    </div>
</header>