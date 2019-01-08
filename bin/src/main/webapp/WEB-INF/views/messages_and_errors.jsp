<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- Errors -->
<fmt:bundle basename="resources.Errors">
<!-- This takes the errors from the dispatcher -->
<c:if test="${not empty requestScope.error}">
	<p class="error"><fmt:message key="${requestScope.error}"/></p>
</c:if>
<!-- This takes the errors from the URL (redirect) -->
<c:if test="${not empty param.error}">
	<p class="error"><fmt:message key="${param.error}"/></p>
</c:if>
</fmt:bundle>

<!-- Messages -->
<fmt:bundle basename="resources.Messages">
<!-- This takes the messages from the dispatcher -->
<c:if test="${not empty requestScope.message}">
	<p class="message"><fmt:message key="${requestScope.message}"/></p>
</c:if>
<!-- This takes the messages from the URL (redirect) -->
<c:if test="${not empty param.message}">
	<p class="message"><fmt:message key="${param.message}"/></p>
</c:if>
</fmt:bundle>