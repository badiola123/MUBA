<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<input id="current" type="hidden" value="1">

<div id="addSection">
    <p>Chcesz powiększyć swoje stado?</p>
    <form action="/atlasr/myRobaks/addPage.html" method="post">
    <button class="btn btn-success">Dodaj robaka</button>
    </form>
</div>

<c:forEach items="${myRobaks}" var="robak">
    <div>${robak.pic}</div>
    <p>${robak.name}</p>
</c:forEach>

<img src="data:image/png;base64,${pictureString}">