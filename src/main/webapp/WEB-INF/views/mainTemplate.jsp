<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="javascripts"/>

<!DOCTYPE html><html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:getAsString name="title" /></title>
    <c:forEach var="css" items="${stylesheets}">
      <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
    </c:forEach>

    <c:forEach var="script" items="${javascripts}">
      <script src="<c:url value="${script}"/>"></script>
    </c:forEach>
  </head>

  <body>
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="alerts" />
    <tiles:insertAttribute name="body" />
  </body>
</html>
