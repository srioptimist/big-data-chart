$(document).ready(function() { 
	
	var attritionMap;
	
	$.getJSON( "rest/attrition", function( data ) {
		   
		attritionMap = data;
		
		FusionCharts.ready(function(){
		    var fusioncharts = new FusionCharts({
		    type: 'column2d',
		    renderAt: 'chart-container',
		    width: '500',
		    height: '500',
		    dataFormat: 'json',
		    dataSource: {
		        "chart": {
		            "caption": "Top 5 prime indicators for attrition",
		            "subCaption": "Last year",
		            "xAxisName": "Indicators",
		            "yAxisName": "Count",
		            "numberPrefix": "",
		            "theme": "fint"
		        },

		        "data":attritionMap
		    }
		}
		);
		    fusioncharts.render();
		});
		   
	});
	

});