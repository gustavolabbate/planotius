<?php
   /* **Step 1:** Include the `fusioncharts.php` file that contains functions to embed the charts. */
   include("libs/fusioncharts.php");
   ?>
<html>
<head>
<script type="text/javascript" src="js/fusioncharts.js"></script>
<script type="text/javascript" src="js/themes/fusioncharts.theme.ocean.js"></script>
</head>
<body>
<div id="menu">
<?php 
include('menu.php');
?>
</div>
<?php

//COUNT STATUS
$row = 1;
$inactive=0;
$active=0;
$pending=0;

if (($handle = fopen("data/user.csv", "r")) !== FALSE) {
	
    while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
	
        $num = count($data);
	for ($c=0; $c < $num; $c++) {
	
	if ($data[$c] == "inactive"){
		$inactive++; 
	}
	else if ($data[$c] == "active"){
		$active++; 
	}
	else if ($data[$c] == "pending"){
		$pending++;
	}
		}
	}
	
    fclose($handle);
}
    	$columnChart = new FusionCharts("pie2d", "myFirstChart" , 600, 300, "chart-1", "json",
		'{
			"chart": {
					"caption": "Users",
					"subCaption": "status",
					"numberPrefix": "",
					"showPercentInTooltip": "0",
					"decimals": "1",
					"useDataPlotColorForLabels": "1",
					"theme": "ocean"               			   
                },
                "data": [{
					label: "active",
					value: "'.$active.'",
					color: "#6D929B"
				},
				{
					label: "inactive",
					value: "'.$inactive.'"
				},
				{
					label: "pending",
					value: "'.$pending.'"
					
				}]
		}'
    );
            /*'{
                "chart": {
                    "caption": "Monthly revenue for last year",
                    "subCaption": "Harry\’s SuperMart",
                    "xAxisName": "Month",
                    "yAxisName": "Revenues (In USD)",
                    "numberPrefix": "$",
                    "paletteColors": "#0075c2",
                    "bgColor": "#ffffff",
                    "borderAlpha": "20",
                    "canvasBorderAlpha": "0",
                    "usePlotGradientColor": "0",
                    "plotBorderAlpha": "10",
                    "placeValuesInside": "1",
                    "rotatevalues": "1",
                    "valueFontColor": "#ffffff",
                    "showXAxisLine": "1",
                    "xAxisLineColor": "#999999",
                    "divlineColor": "#999999",
                    "divLineIsDashed": "1",
                    "showAlternateHGridColor": "0",
                    "subcaptionFontSize": "14",
                    "subcaptionFontBold": "0"
                },
                "data": [{
                    "label": "Jan",
                    "value": "420000"
                }, {
                    "label": "Feb",
                    "value": "810000"
                }, {
                    "label": "Mar",
                    "value": "720000"
                }, {
                    "label": "Apr",
                    "value": "550000"
                }, {
                    "label": "May",
                    "value": "910000"
                }, {
                    "label": "Jun",
                    "value": "510000"
                }, {
                    "label": "Jul",
                    "value": "680000"
                }, {
                    "label": "Aug",
                    "value": "620000"
                }, {
                    "label": "Sep",
                    "value": "610000"
                }, {
                    "label": "Oct",
                    "value": "490000"
                }, {
                    "label": "Nov",
                    "value": "900000"
                }, {
                    "label": "Dec",
                    "value": "730000"
                }]
            }'
    );*/

/* Because we are using JSON to specify chart data, `json` is passed as the value for the data format parameter of the constructor. The actual chart data, in string format, is passed as the value for the data source parameter of the constructor. Alternatively, you can store this string in a variable and pass the variable to the constructor. */

     	/* **Step 4:** Render the chart */
     	$columnChart->render();
		
		
		
		
		
		
		$csvChart = new FusionCharts("pie3d", "myFirstChart" , 600, 300, "chart-2", "csv",'data/user.csv');
		
		$csvChart->render();
		
		
		
		
		
  	?>
  	<div id="chart-1" align="center"><!-- Fusion Charts will render here--></div>
	<div id="chart-2" align="center"><!-- Fusion Charts will render here--></div>

<!--
bla
<div align="center" id="chartContainer">

</div>
blo
-->
</body>
</html>
