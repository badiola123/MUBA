var intervalId = 0;
var myChart = null;

intervalId = setInterval(getDataFromDatabase, 2000);

function getDataFromDatabase(){
	$(document).ready(
			$.ajax({
				type : "GET",
				url : '/MUBA/ajax/homePage_gameData.html',
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
	var myChart = echarts.init(document.getElementById('usersChart'));
	var option = {
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'none'
		        },
		        formatter: function (params) {
		            return params[0].name + ': ' + params[0].value;
		        }
		    },
		    xAxis: {
		        data: ['Leagues', 'Teams', 'Games', 'Players'],
		        axisTick: {show: false},
		        axisLine: {show: false},
		        axisLabel: {
		            textStyle: {
		                color: '#148B95'
		            }
		        }
		    },
		    yAxis: {
		        splitLine: {show: false}
		    },
		    color: ['#148B95'],
		    series: [{
		        name: 'Game data',
		        type: 'pictorialBar',
		        barCategoryGap: '0%',
		        // symbol: 'path://M0,10 L10,10 L5,0 L0,10 z',
		        symbol: 'path://M0,10 L10,10 C5.5,10 5.5,5 5,0 C4.5,5 4.5,10 0,10 z',
		        itemStyle: {
		            normal: {
		                opacity: 0.5
		            },
		            emphasis: {
		                opacity: 1
		            }
		        },
		        data: json.data,
		        z:10
		    }]
		};
	myChart.setOption(option);
}

$(window).on('resize', function(){
    if(myChart != null && myChart != undefined){
    	myChart.resize();
    }
});