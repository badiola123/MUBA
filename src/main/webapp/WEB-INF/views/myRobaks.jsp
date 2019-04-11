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

<div id="grid">
<c:forEach items="${pageList}" var="robak">
    <div class="card">
        <img class="card-img-top" src="data:image/jpg;base64,${pictures[robak.robakId]}" alt="Card image cap">
        <div class="card-body">
            <div>
            <h5 class="card-title">${robak.name}</h5>
            <p class="card-text">${robak.description}</p>
            </div>
            <form class = "deleteForm" action="/atlasr/myRobaks/delete.html" method="post">
                <input type="hidden" value="${robak.robakId}" name="robakId">
                <input name="page" type="hidden" value=${page}>
                <input name="elements" type="hidden" value=${pageList.size()}>
                <button class="btn btn-success" id="logout">Usuń</button>
            </form>
        </div>
    </div>
</c:forEach>
</div>

<div id = "navigation">
    <form action="/atlasr/allUsers/pages.html" method="get">
        <input name="page" type="hidden" value=${page-1}>
        <input name="limit" type="hidden" value="8">
        <c:if test="${previous}">
            <button id="previous"></button>
        </c:if>
        <c:if test="${!previous}">
            <button id="previousDisabled" disabled></button>
        </c:if>
    </form>
    <p id="page"><c:out value="${page+1}"/></p>
    <form action="/atlasr/allUsers/pages.html" method="get">
        <input name="page" type="hidden" value=${page+1}>
        <input name="limit" type="hidden" value="8">
        <c:if test="${next}">
            <button id="next"></button>
        </c:if>
        <c:if test="${!next}">
            <button id="nextDisabled" disabled></button>
        </c:if>
    </form>
</div>
