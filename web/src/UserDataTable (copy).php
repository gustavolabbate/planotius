<html>
<head>
  <link rel="stylesheet" href="style_sheet.css">
</head>
<body>
<div class="container" id="resultTable">
<?php
$row = 1;
if (($handle = fopen("user.csv", "r")) !== FALSE) {
	echo "<table>";
    while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
	echo "<tr>";
        $num = count($data);
	for ($c=0; $c < $num; $c++) {
	
	if ($row ==1 ){
	echo "<th>" . $data[$c] . "</th>";
	}
	else{
	echo "<td>" . $data[$c] . "</td>";
        }
	}
	echo "</tr>";
	$row++;
    }
    fclose($handle);
}
?>
</div>

</body>
</html>
