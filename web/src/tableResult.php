<html>
<body>
<?php 

echo "ffff";
echo "<table>";
echo "<td>2</td>";
echo "</table>";

$file_handle = fopen("/var/www/phpTeste/widgets.csv", "r");

while (!feof($file_handle) ) {

$line_of_text = fgetcsv($file_handle, 1024);

echo "<table border=1 bordercolor="black">";
echo "<tr>";
echo "<td>$line_of_text[0]</td>";
echo "<td>$line_of_text[1]</td>";
echo "<td>$line_of_text[2]</td>";
echo "</tr>";
echo "</table>";

}



fclose($file_handle);

?>
</body>
</html>
