<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h1>Hello, Player statistics showing</h1>

<h2>Player "${requestScope.player.name}"</h2>
<div id="chart" style="width:600px; height:400px;"></div>


