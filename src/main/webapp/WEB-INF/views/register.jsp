<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
<form action="/MUBA/login/validateRegister.html" method="post">

  <div class="container-fluid">
    <div class="row justify-content-center">
    
      <div class="loginInfo col-12 col-sm-12 col-md-4">
        <div class="inner">
          <h1><spring:message code="register.personalInfo"/></h1>
          
          <div class="shadow input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text"><spring:message code="register.username"/></span>
            </div>
            <input id="regUsername" class="regUserInfo form-control" type="text" name="regUserInfo[]" required placeholder="<spring:message code="register.username"/>"/>
          </div>
          
          <div class="shadow input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text"><spring:message code="register.password"/></span>
            </div>
            <input id="regPassword" class="regUserInfo form-control" type="password" name="regUserInfo[]" required placeholder="<spring:message code="register.password"/>"/>
          </div>
        </div>
      </div>
      
      <div class="teamInfo col-12 col-sm-12 col-md-4">
        <div class="inner">
          <h1><spring:message code="register.teamInfo"/></h1>
          
          <div class="shadow input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text"><spring:message code="register.teamName"/></span>
            </div>
            <input id="regTeamName" class="regTeamInfo form-control" type="text" name="regTeamName" required placeholder="<spring:message code="register.teamName"/>"/>
          </div>
         
        </div>
      </div>
    
      <div class="collapsable col-12 col-sm-12 col-md-4">
        <div class="inner">
          <p>
            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
              <spring:message code="register.informationTitle"/>
            </button>
          </p>
          <div class="collapse" id="collapseExample">
            <div class="card card-body">
              <spring:message code="register.informationBody"/>
            </div>
          </div>
        </div>
      </div>
    
    </div>
  </div>
  
  <br>
  <hr>
  <br>
  
  <div class="playerInfo">
	<h1><spring:message code="register.teamPlayers"/></h1>
	
	<div class="container-fluid">
	  <div class="row">
	
	  <div class="card playerName d-inline-block col-12 col-sm-6 col-md-4 col-lg-3">
        <h5 class="card-header"><spring:message code="register.playerInfo"/></h5>
        <div class="card-body">
          <input id="regPlayer1Name" class="regPlayerInfo form-control" type="text" name="regPlayer1[]" required placeholder="<spring:message code="register.playerName"/>"/>
	      <input id="regPlayer1Surname" class="regPlayerInfo form-control" type="text" name="regPlayer1[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
        </div>
      </div>
    
      <div class="card playerName d-inline-block col-12 col-sm-6 col-md-4 col-lg-3">
        <h5 class="card-header"><spring:message code="register.playerInfo"/></h5>
        <div class="card-body">
          <input id="regPlayer2Name" class="regPlayerInfo form-control" type="text" name="regPlayer2[]" required placeholder="<spring:message code="register.playerName"/>"/>
	      <input id="regPlayer2Surname" class="regPlayerInfo form-control" type="text" name="regPlayer2[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
        </div>
      </div>
    
      <div class="card playerName d-inline-block col-12 col-sm-6 col-md-4 col-lg-3">
        <h5 class="card-header"><spring:message code="register.playerInfo"/></h5>
        <div class="card-body">
          <input id="regPlayer3Name" class="regPlayerInfo form-control" type="text" name="regPlayer3[]" required placeholder="<spring:message code="register.playerName"/>"/>
	      <input id="regPlayer3Surname" class="regPlayerInfo form-control" type="text" name="regPlayer3[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
        </div>
      </div>
    
      <div class="card playerName d-inline-block col-12 col-sm-6 col-md-4 col-lg-3">
        <h5 class="card-header"><spring:message code="register.playerInfo"/></h5>
        <div class="card-body">
          <input id="regPlayer4Name" class="regPlayerInfo form-control" type="text" name="regPlayer4[]" required placeholder="<spring:message code="register.playerName"/>"/>
	      <input id="regPlayer4Surname" class="regPlayerInfo form-control" type="text" name="regPlayer4[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
        </div>
      </div>
    
      <div class="card playerName d-inline-block col-12 col-sm-6 col-md-4 col-lg-3">
        <h5 class="card-header"><spring:message code="register.playerInfo"/></h5>
        <div class="card-body">
          <input id="regPlayer5Name" class="regPlayerInfo form-control" type="text" name="regPlayer5[]" required placeholder="<spring:message code="register.playerName"/>"/>
	      <input id="regPlayer5Surname" class="regPlayerInfo form-control" type="text" name="regPlayer5[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
        </div>
      </div>
    
      <div class="card playerName d-inline-block col-12 col-sm-6 col-md-4 col-lg-3">
        <h5 class="card-header"><spring:message code="register.playerInfo"/></h5>
        <div class="card-body">
          <input id="regPlayer6Name" class="regPlayerInfo form-control" type="text" name="regPlayer6[]" required placeholder="<spring:message code="register.playerName"/>"/>
	      <input id="regPlayer6Surname" class="regPlayerInfo form-control" type="text" name="regPlayer6[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
        </div>
      </div>
    
      <div class="card playerName d-inline-block col-12 col-sm-6 col-md-4 col-lg-3">
        <h5 class="card-header"><spring:message code="register.playerInfo"/></h5>
        <div class="card-body">
          <input id="regPlayer7Name" class="regPlayerInfo form-control" type="text" name="regPlayer7[]" required placeholder="<spring:message code="register.playerName"/>"/>
	      <input id="regPlayer7Surname" class="regPlayerInfo form-control" type="text" name="regPlayer7[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
        </div>
      </div>
    
      <div class="card playerName d-inline-block col-12 col-sm-6 col-md-4 col-lg-3">
        <h5 class="card-header"><spring:message code="register.playerInfo"/></h5>
        <div class="card-body">
          <input id="regPlayer8Name" class="regPlayerInfo form-control" type="text" name="regPlayer8[]" required placeholder="<spring:message code="register.playerName"/>"/>
	      <input id="regPlayer8Surname" class="regPlayerInfo form-control" type="text" name="regPlayer8[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
        </div>
      </div>
    
      <div class="card playerName d-inline-block col-12 col-sm-6 col-md-4 col-lg-3">
        <h5 class="card-header"><spring:message code="register.playerInfo"/></h5>
        <div class="card-body">
          <input id="regPlayer9Name" class="regPlayerInfo form-control" type="text" name="regPlayer9[]" required placeholder="<spring:message code="register.playerName"/>"/>
	      <input id="regPlayer9Surname" class="regPlayerInfo form-control" type="text" name="regPlayer9[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
        </div>
      </div>
    
      <div class="card playerName d-inline-block col-12 col-sm-6 col-md-4 col-lg-3">
        <h5 class="card-header"><spring:message code="register.playerInfo"/></h5>
        <div class="card-body">
          <input id="regPlayer10Name" class="regPlayerInfo form-control" type="text" name="regPlayer10[]" required placeholder="<spring:message code="register.playerName"/>"/>
	      <input id="regPlayer10Surname" class="regPlayerInfo form-control" type="text" name="regPlayer10[]" required placeholder="<spring:message code="register.playerSurname"/>"/>
        </div>
      </div>
      
      </div>
    </div>
  
  </div>
  
  <br>
  <br>
  
  <button id="regSaveBut" type="submit" name="action" value="register" class="registerForm btn btn-primary btn-lg btn-block"><spring:message code="action.register"/></button>
</form>
</div>