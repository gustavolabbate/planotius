<html>
<head>
  <link rel="stylesheet" href="style_sheet.css">
</head>
<body>

<?php
$row = 1;
if (($handle = fopen("user.csv", "r")) !== FALSE) {
echo "<div>";
	echo "<table>";
    while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
	echo "<tr>";
        $num = count($data);
        $row++;
        for ($c=0; $c < $num; $c++) {
	echo "<td>" . $data[$c] . "</td>";
        }
	echo "</tr>";
    }
    fclose($handle);
}
echo "</div>";
?>


</body>
</html>
