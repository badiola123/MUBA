<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:importAttribute name="stylesheets"/>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:getAsString name="title" /></title>
<c:forEach var="css" items="${stylesheets}">
  <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
</c:forEach>
</head>

<body>
    <div class="flex-container">
        <tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
    </div>
</body>
</html>
