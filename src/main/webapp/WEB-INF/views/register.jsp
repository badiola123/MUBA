<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form action="/MUBA/login/validateRegister.html" method="post">

  <div class="generalInfo">
    <div class="loginInfo">
      <h1><spring:message code="register.personalInfo"/></h1>
	  <input id="regUsername" class="regUserInfo" type="text" name="regUserInfo[]" required placeholder="<spring:message code="register.username"/>"/>
	  <input id="regPassword" class="regUserInfo" type="password" name="regUserInfo[]" required placeholder="<spring:message code="register.password"/>"/>
    </div>
    <div class="teamInfo">
      <h1><spring:message code="register.teamInfo"/></h1>
	  <input id="regTeamName" class="regTeamInfo" type="text" name="regTeamName" required placeholder="<spring:message code="register.teamName"/>"/>
    </div>
  </div>
  
  <br>
  <hr>
  <br>
  
  <div class="playerInfo">
	<h1><spring:message code="register.teamPlayers"/></h1>
	
	<div class="playerName">
	  <h3><spring:message code="register.playerInfo"/></h3>
	  <input id="regPlayer1Name" class="regPlayerInfo" type="text" name="regPlayer1[]" required placeholder="<spring:message code="register.playerName"/>"/>
	  <input id="regPlayer1Surname" class="regPlayerInfo" type="text" name="regPlayer1[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
	</div>
	<br>
	<div class="playerName">
	  <h3><spring:message code="register.playerInfo"/></h3>
	  <input id="regPlayer2Name" class="regPlayerInfo" type="text" name="regPlayer2[]" required placeholder="<spring:message code="register.playerName"/>"/>
	  <input id="regPlayer2Surname" class="regPlayerInfo" type="text" name="regPlayer2[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
	</div>
	<br>
	<div class="playerName">
	  <h3><spring:message code="register.playerInfo"/></h3>
	  <input id="regPlayer3Name" class="regPlayerInfo" type="text" name="regPlayer3[]" required placeholder="<spring:message code="register.playerName"/>"/>
	  <input id="regPlayer3Surname" class="regPlayerInfo" type="text" name="regPlayer3[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
	</div>
	<br>
	<div class="playerName">
	  <h3><spring:message code="register.playerInfo"/></h3>
	  <input id="regPlayer4Name" class="regPlayerInfo" type="text" name="regPlayer4[]" required placeholder="<spring:message code="register.playerName"/>"/>
	  <input id="regPlayer4Surname" class="regPlayerInfo" type="text" name="regPlayer4[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
	</div>
	<br>
	<div class="playerName">
	  <h3><spring:message code="register.playerInfo"/></h3>
	  <input id="regPlayer5Name" class="regPlayerInfo" type="text" name="regPlayer5[]" required placeholder="<spring:message code="register.playerName"/>"/>
	  <input id="regPlayer5Surname" class="regPlayerInfo" type="text" name="regPlayer5[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
	</div>
	<br>
	<div class="playerName">
	  <h3><spring:message code="register.playerInfo"/></h3>
	  <input id="regPlayer5Name" class="regPlayerInfo" type="text" name="regPlayer6[]" required placeholder="<spring:message code="register.playerName"/>"/>
	  <input id="regPlayer5Surname" class="regPlayerInfo" type="text" name="regPlayer6[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
	</div>
	<br>
	<div class="playerName">
	  <h3><spring:message code="register.playerInfo"/></h3>
	  <input id="regPlayer5Name" class="regPlayerInfo" type="text" name="regPlayer7[]" required placeholder="<spring:message code="register.playerName"/>"/>
	  <input id="regPlayer5Surname" class="regPlayerInfo" type="text" name="regPlayer7[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
	</div>
	<br>
	<div class="playerName">
	  <h3><spring:message code="register.playerInfo"/></h3>
	  <input id="regPlayer5Name" class="regPlayerInfo" type="text" name="regPlayer8[]" required placeholder="<spring:message code="register.playerName"/>"/>
	  <input id="regPlayer5Surname" class="regPlayerInfo" type="text" name="regPlayer8[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
	</div>
	<br>
	<div class="playerName">
	  <h3><spring:message code="register.playerInfo"/></h3>
	  <input id="regPlayer5Name" class="regPlayerInfo" type="text" name="regPlayer9[]" required placeholder="<spring:message code="register.playerName"/>"/>
	  <input id="regPlayer5Surname" class="regPlayerInfo" type="text" name="regPlayer9[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
	</div>
	<br>
	<div class="playerName">
	  <h3><spring:message code="register.playerInfo"/></h3>
	  <input id="regPlayer5Name" class="regPlayerInfo" type="text" name="regPlayer10[]" required placeholder="<spring:message code="register.playerName"/>"/>
	  <input id="regPlayer5Surname" class="regPlayerInfo" type="text" name="regPlayer10[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
	</div>
  </div>
  
  <button id="registerBut" class="registerForm" type="submit" name="action" value="register"><spring:message code="action.register"/></button>
</form>