<html>
<head>
  <link rel="stylesheet" href="css/style_sheet.css">
</head>
<body>
<div id="menu">
<?php 
include('menu.php');
?>

</div>

<div class="message"> 
Delete user?
</div>
<div class="container" >
<?php
$userLine = $_GET['r'];
$row = 1;
if (($handle = fopen("data/user.csv", "r")) !== FALSE) {
	echo "<form action='actions/DeleteUserAction.php' method='POST'>";
	echo "<table name='user_delete'>";
    while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
	echo "<tr>";
        $num = count($data);
	for ($c=0; $c < $num; $c++) {
	
	if ($row == 1 ){
	echo "<th>" . $data[$c] . "</th>";
	}
	else{
		if ($row == $userLine ) {
			echo "<td>" . $data[$c] . "</td>";
		}
		
		}
	}
	
	echo "</tr>";
	$row++;
    }
	echo "</table>";
	echo "<input type='hidden' value='$userLine' name='row'/>";
	echo "<input type='submit' value='Confirm' name='Confirm'/>";
	echo "</form>";
	
    fclose($handle);
}

?>




</div>
</body>
</html>
