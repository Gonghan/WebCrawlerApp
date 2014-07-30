<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risk Advisor</title>
<%@  include file="./templates/includes.jsp"%></head>
<body>
	<%@  include file="./templates/header.jsp"%>
	<!-- Main part -->
	<div class="container">
		<h3>Show the results of a company in a given year.</h3>
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<div class="col-sm-4">
					<label for="symbol" class="control-label">Stock symbol</label> <input
						type="text" class="form-control" id="symbol"
						placeholder="Input the company symbol you want to search" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4">
					<label for="year" class="control-label">Year</label> <select
						id="year" class="form-control">
						<option>all</option>
						<option>2014</option>
						<option>2013</option>
						<option>2012</option>
						<option>2011</option>
					</select>
				</div>
			</div>
			<button id="searchrecords" class="btn btn-default">Show results!</button>
		</form>
	</div>

	<div id="chartContainer1" style="height: 300px; width: 100%;"></div>
	<div id="chartContainer2" style="height: 300px; width: 100%;"></div>
	<div id="result-output" style="display: none" class="bg-info">
		<table
			class="table table-striped table-hover table-bordered table-striped">
			<thead>
				<tr>
					<td>Symbol</td>
					<td>Year</td>
					<td>Risk Factors</td>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>

	<script>
		$("#searchrecords").click(
				function(event) {
					event.preventDefault();
					var symbol = $("#symbol").val();
					var year = $("#year option:selected").text();
					var crawlurl = "/api/results/" + symbol;
					var categoryurl="/api/category/" + symbol;
					if (year != "all") {
						crawlurl = crawlurl + "/?year=" + year;
						categoryurl=categoryurl+"/?year=" + year;
					}else{
						crawlurl = crawlurl + "/?year=2014";
						categoryurl=categoryurl+"/?year=2014";
					}
					$.ajax({
						url : crawlurl,
						success : function(data) {
							var body = $("#result-output").find("tbody");
							$("#result-output").show();
							body.find('tr').remove();
							$.each(data.records, function(i, v) {
								body.append($('<tr>').append(
										$('<td>').text(v.symbol)).append(
										$('<td>').text(v.year)).append(
										$('<td>').text(v.riskFactor)));
							});
							//var data= '{"keywords":{"stockholder":6,"harm":2,"loss":10,"threat":1,"regulations":10,"disruption":3,"difficult":4,"capital":1,"adverse":8,"affect":14,"compete":1,"acquisition":3,"stock":10,"personnel":5,"fail":8,"interest":3,"raw materials":2,"difficulties":1,"terrorist":1,"litigation":2,"license":2,"interest rate":2,"supplier":5,"ineffective":1,"challenges":2,"political":5,"intellectual property":4,"liquidity risk":1,"international":1,"revenue growth":1,"disrupt":3,"cost":6,"distributors":4,"economic":6,"depends on":1,"product":17,"fluctuations":2,"failure":7,"challenge":2,"tax matter":2,"reporting":2,"competitive":7,"economic conditions":1,"infrastructure":3,"decrease":1,"fluctuation":2,"credit risk":1}}';
							//showData(data.records[0].keywords,"Risk Factors Keywords");
						}
					});
					$.ajax({
						url: categoryurl,
						success : function(data){
							showData(data,"Risk Categories");
						}
					});
				});
		function showData(data,text) {
			//var obj = jQuery.parseJSON(data);
			//var risks=data.records[0].keywords;
			var risks=data;
			//console.log(risks);

			var dataPoints = [];
			for (key in risks) {
				if(risks[key]<=0){
					continue;
				}
				dataPoints.push({
					label : key,
					y : risks[key]
				});
			}
			var chart1 = new CanvasJS.Chart("chartContainer1", {

				title : {
					text : text+" Histogram"
				},
				data : [//array of dataSeries              
				{ //dataSeries object

					/*** Change type "column" to "bar", "area", "line" or "pie"***/
					type : "column",
					dataPoints : dataPoints
				} ]
			});

			var chart2 = new CanvasJS.Chart("chartContainer2", {
				title : {
					text : text+" Components PieChart",
					fontFamily : "Impact",
					fontWeight : "normal"
				},

				legend : {
					verticalAlign : "bottom",
					horizontalAlign : "center"
				},
				data : [ {
					//startAngle: 45,
					indexLabelFontSize : 20,
					indexLabelFontFamily : "Garamond",
					indexLabelFontColor : "darkgrey",
					indexLabelLineColor : "darkgrey",
					indexLabelPlacement : "outside",
					type : "doughnut",
					dataPoints : dataPoints
				} ]
			});
			chart1.render();
			chart2.render();
			//}
		}
	</script>

	<%@  include file="./templates/footer.jsp"%>
</body>
</html>