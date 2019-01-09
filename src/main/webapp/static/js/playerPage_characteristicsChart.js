var intervalId = 0;
var myChart = null;
intervalId = setInterval(getDataFromDatabase, 1000);

function getDataFromDatabase(){
	var jsonCharacteristics = "{ data : [54, 53, 67, 89, 76] }"
	var playerId
	$(document).ready(
			$.ajax({
				type : "GET",
				url : '/MUBA/ajax/playerInfo_charactChart.html?playerId=' + $("#main").attr("data-customvalueone"),
			    contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				success : function(json){
					console.log(json)
					drawChart(json);
				},
				error : function(e){
					console.log("Error -> " + e);
				}
			})
		)
}

function drawChart(json){
	myChart = echarts.init(document.getElementById('playerInfo_characteristicsChart'));
	option = {
	    title: {
	        //text: 'Player Characteristics'
	    },
	    tooltip: {},
	    legend: {},
	    radar: {
	        // shape: 'circle',
	        name: {
	            textStyle: {
	                color: '#fff',
	                backgroundColor: '#999',
	                borderRadius: 3,
	                padding: [3, 5]
	           }
	        },
	        indicator: [
	           { name: 'Resistance', max: 100},
	           { name: 'BallControll', max: 100},
	           { name: 'Defense', max: 100},
	           { name: 'LongShoot', max: 100},
	           { name: 'ShortShoot', max: 100}
	        ]
	    },
	    series: [{
	    	name: 'Player Characteristics',
            type: 'radar',
            data:[ {
            		value : json.data
            	}]
	    }]
	};
	myChart.setOption(option);
}

$(window).on('resize', function(){
    if(myChart != null && myChart != undefined){
    	myChart.resize();
    }
});
$(document).ready( function () {
    $('#table').DataTable();
} );
