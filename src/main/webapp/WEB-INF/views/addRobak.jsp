<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<input id="current" type="hidden" value="1">
<p id="pageName" class="display-4">Dodaj robaka</p>
<form action="/atlasr/myRobaks/addRobak.html" method="post" id="form" enctype="multipart/form-data">
    <input class="form-control" type="text" name="name" placeholder="Imię robaka" required>
    <textarea class="form-control" rows="5" name="description" placeholder="Opis Twojego robaka" id = "description" required></textarea>
    <input type="file" name="pic" class="form-control-file" accept="image/jpg"  required>
    <button class="btn btn-success">Dodaj robaka</button>
</form>