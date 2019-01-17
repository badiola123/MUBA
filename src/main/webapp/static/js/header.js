const buttonIds = ["homeRef","teamRef","leagueRef","matchRef", "accountRef"];

function setButton(buttonId){
	for(var i=0;i<buttonIds.length;i++){
		var button = $('#'+buttonIds[i]);
		if(buttonId == buttonIds[i]){
			button.attr('class', 'selected');
		}else{
			button.attr('class', 'notSelected');
		}
	}
}