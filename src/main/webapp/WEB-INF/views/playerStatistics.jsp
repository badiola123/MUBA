<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h1>Hello, Player statistics showing</h1>
<form id="getData" action="${pageContext.request.contextPath}/playerStatistics/characteristicsChartData">

	<h2>Player "${requestScope.player.name}"</h2>
	<div id="chart" style="width:600px; height:400px;"></div>
</form>
<script type="text/javascript">
	//based on prepared DOM, initialize echarts instance
	/*var myChart = echarts.init(document.getElementById('chart'));
	
	// specify chart configuration item and data
	var option = {
	    title: {
	        text: 'ECharts entry example'
	    },
	    tooltip: {},
	    legend: {
	        data:['Sales']
	    },
	    xAxis: {
	        data: ["shirt","cardign","chiffon shirt","pants","heels","socks"]
	    },
	    yAxis: {},
	    series: [{
	        name: 'Sales',
	        type: 'bar',
	        data: [5, 20, 36, 10, 10, 20]
	    }]
	};
	
	// use configuration item and data specified to show chart
	myChart.setOption(option);*/
</script>
