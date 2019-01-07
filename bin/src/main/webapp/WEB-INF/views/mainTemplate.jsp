<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:getAsString name="title" /></title>
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
</head>

<body>
    <div class="flex-container">
        <tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
    </div>
</body>
</html>
