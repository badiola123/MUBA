<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty success}">
  <div class="alert alert-success alert-dismissible fade show" role="alert" style="position:fixed; width:100%;">
    <spring:message code="${success}"/>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
</c:if>

<c:if test="${not empty info}">
  <div class="alert alert-info alert-dismissible fade show" role="alert" style="position:fixed; width:100%;">
    <spring:message code="${info}"/>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
</c:if>


<c:if test="${not empty warning}">
  <div class="alert alert-warning alert-dismissible fade show" role="alert" style="position:fixed; width:100%;">
    <spring:message code="${warning}"/>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
</c:if>

<c:if test="${not empty error}">
  <div class="alert alert-danger alert-dismissible fade show" role="alert" style="position:fixed; width:100%;">
    huj
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
</c:if>