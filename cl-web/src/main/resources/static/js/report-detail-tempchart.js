var chartLabels = [];
var chartData = [];
var tripId = window.location.pathname.split("/")[2];

$.getJSON("http://localhost:8080/report-chart/" + tripId, function(data) {
	$.each(data, function(inx, obj) {
		chartLabels.push(obj.point.toLocaleString());
		chartData.push(obj.proveTemp);
	});
	createChart();
	console.log("create Chart");
	console.log(data);
	console.log("http://localhost:8080/report-chart/" + tripId);
});

var lineChartData = {
	labels : chartLabels,
	datasets : [ {
		label : "int_temp",
		fillColor : "rbga(151,187,205,0.2)",
		strokeColor : "rbga(151,187,205,1)",
		pointColor : "rbga(151,187,205,1)",
		pointStrokeColor : "#fff",
		pointHighlightFill : "#fff",
		pointHighlightStroke : "rbga(151,187,205,1)",
		data : chartData
	} ]
};

function createChart() {
	var ctx = document.getElementById("chart").getContext("2d");
	LineChartDemo = Chart.Line(ctx, {
		data : lineChartData,
		options : {
			scales : {
				xAxes : [ {
					type : 'time',
					time : {
						unit : 'minute',
						displayFormats : {
							'millisecond' : 'hh:mm',
							'second' : 'hh:mm',
							'minute' : 'DD/MM/YYYY hh:mm',
							'hour' : 'hh:mm',
							'day' : 'hh:mm',
							'week' : 'hh:mm',
							'month' : 'hh:mm',
							'quarter' : 'hh:mm',
							'year' : 'hh:mm',
						}
					},
				} ],
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	})
};