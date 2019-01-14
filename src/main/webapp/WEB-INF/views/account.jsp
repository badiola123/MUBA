<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="changePass">
  <h1><spring:message code="manage.editPass"/></h1>
  <form action="/MUBA/account/manage.html" method="post">
	<input id="oldPass" class="changePassForm" type="text" name="oldPass" required placeholder="<spring:message code="manage.oldPass"/>"/>
	<input id="newPass" class="changePassForm" type="text" name="newPass" required placeholder="<spring:message code="manage.newPass"/>"/>
	<input id="reNewPass" class="changePassForm" type="text" name="reNewPass" required placeholder="<spring:message code="manage.reNewPass"/>"/>
	<button id="changePassBut" class="changePassForm" type="submit" name="action" value="changePass"><spring:message code="action.save"/></button>
  </form>
</div>