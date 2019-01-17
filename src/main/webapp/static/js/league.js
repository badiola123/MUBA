const leagueButtonIds = ["running","finished","notStarted","available"];

function setLeagueButton(buttonId){
	for(var i=0;i<leagueButtonIds.length;i++){
		var button = $('#'+leagueButtonIds[i]);
		if(buttonId == leagueButtonIds[i]){
			button.attr('class', 'leagueSelected');
		}else{
			button.attr('class', 'leagueNotSelected');
		}
	}
}