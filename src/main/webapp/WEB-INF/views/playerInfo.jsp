<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="main" data-customvalueone="${player.playerId}">
	<aside id="playerInfo">		
		<div id="singlePlayer">
			<h4 id="name">${player.name} ${player.surname}</h4>
			<img alt="player photo" src="/MUBA/resources/player.png" />
			<div id="data"><span>Age:&nbsp;</span><span>${chars.age}</span><br>
			<span>Height:&nbsp;</span><span>${chars.height}</span><br></div>
		</div>
		<div id="playerInfo_characteristicsChart"></div>
	</aside>
	<table id="table" class="display">
		<thead>
            <tr>
                <th>Date</th>
                <th>VS</th>
                <th>TimePlayed</th>
                <th>FT</th>
                <th>2P</th>
                <th>3P</th>
                <th>DR</th>
                <th>OR</th>
                <th>S</th>
                <th>B</th>
                <th>OR</th>
                <th>A</th>
                <th>F</th>
                <th>Val</th>
            </tr>
        </thead>
	</table>

</div>