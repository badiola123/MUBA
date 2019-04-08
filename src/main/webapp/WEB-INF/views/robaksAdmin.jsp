<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<input id="current" type="hidden" value="2">

<form id="search" action="/atlasr/robaksAdmin/search.html" method="post">
    <input type="text" name="key" placeholder="Wpisz nazwę robaka lub właściciela">
    <button class="btn btn-success">Szukaj</button>
</form>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Nazwa robaka</th>
        <th scope="col">Nazwa właściciela</th>
        <th scope="col" id="action">Akcja</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageRobakList}" var="robak" varStatus="loop">
        <tr>
            <td>${robak.robakId}</td>
            <td>${robak.name}</td>
            <td>${pageOwnerList[loop.index].username}</td>
            <td>
                <form action="/atlasr/robaksAdmin/delete.html" method="post">
                    <input type="hidden" value="${robak.robakId}" name="robakId">
                    <input name="page" type="hidden" value=${page}>
                    <input name="elements" type="hidden" value=${pageRobakList.size()}>
                    <button class="btn btn-success" id="logout">Usuń</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id = "navigation">
    <form action="/atlasr/robaksAdmin/pages.html" method="get">
        <input name="page" type="hidden" value=${page-1}>
        <input name="limit" type="hidden" value="10">
        <c:if test="${previous}">
            <button id="previous"></button>
        </c:if>
        <c:if test="${!previous}">
            <button id="previousDisabled" disabled></button>
        </c:if>
    </form>
    <p id="page"><c:out value="${page+1}"/></p>
    <form action="/atlasr/robaksAdmin/pages.html" method="get">
        <input name="page" type="hidden" value=${page+1}>
        <input name="limit" type="hidden" value="10">
        <c:if test="${next}">
            <button id="next"></button>
        </c:if>
        <c:if test="${!next}">
            <button id="nextDisabled" disabled></button>
        </c:if>
    </form>
</div>