<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Match</title>
</head>
<body>
	<div id="match">
		<header>
			<div class="teamName">${yourTeam.teamName}</div>
			<div class="score">${score}</div>
			<div class="teamName">${enemyTeam.teamName}</div>
		</header>
		<div class="main">
			<div class="pitch">
				<img alt="basketball pitch" src="/MUBA/resources/pitch.png" />
				<div id="player0">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${yourPlayers[0].name} ${yourPlayers[0].surname}</p>
				</div>
				<div id="player1">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${yourPlayers[1].name} ${yourPlayers[1].surname}</p>
				</div>
				<div id="player2">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${yourPlayers[2].name} ${yourPlayers[2].surname}</p>
				</div>
				<div id="player3">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${yourPlayers[3].name} ${yourPlayers[3].surname}</p>
				</div>
				<div id="player4">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${yourPlayers[4].name} ${yourPlayers[4].surname}</p>
				</div>
				<div id="player5">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${enemyPlayers[0].name} ${enemyPlayers[0].surname}</p>
				</div>
				<div id="player6">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${enemyPlayers[1].name} ${enemyPlayers[1].surname}</p>
				</div>
				<div id="player7">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${enemyPlayers[2].name} ${enemyPlayers[2].surname}</p>
				</div>
				<div id="player8">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${enemyPlayers[3].name} ${enemyPlayers[3].surname}</p>
				</div>
				<div id="player9">
					<img alt="player photo" src="/MUBA/resources/player.png" />
					<p>${enemyPlayers[4].name} ${enemyPlayers[4].surname}</p>
				</div>
			</div>
			<div class="dataLog"></div>
			<form action="/MUBA/match/play.html" method="get">
			  <button id="playButton" type="submit" name="action" value="start">PLAY</button>
			</form>
		</div>
	</div>
</body>
</html>