function getDataFromDatabase(){
	var jsonCharacteristics = "{ data : [54, 53, 67, 89, 76] }"
	
	$(document).ready(
			$.ajax({
				type : "GET",
				url : '/MUBA/playerStatistics/chart',
			    contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				success : function(json){
					console.log(json)
					var myChart = echarts.init(document.getElementById('chart'));

					option = {
					    title: {
					        text: 'Player Characteristics'
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
				},
				error : function(e){
					console.log("Error");
				}
			})
		)
}

var intervalId = 0;
intervalId = setInterval(getDataFromDatabase, 1000);

