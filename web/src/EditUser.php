<html>
<head>
  <link rel="stylesheet" href="css/style_sheet.css">
</head>
<body>
<div id="menu">
<?php 
include('menu.php');
?>


<?php
$userLine = $_GET['r'];
$row = 1;
if (($handle = fopen("data/user.csv", "r")) !== FALSE) {

    while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
        $num = count($data);
	if ($userLine == $row){
	for ($c=0; $c < $num; $c++) {
	
	
	if ($c == 0 ){
		$name = $data[$c];
		
	}else if ($c == 1 ){
		$login = $data[$c];
		
	}else if ($c == 2 ){
		$password = $data[$c];
		
	}else if ($c == 3 ){
		$status = $data[$c];
	}
	}
	}
	$row++;
	}
	 fclose($handle);
}

?>

<div class="create" id="addUser">



<form action="actions/UpdateUserAction.php" method="POST">

<table class="create">
<tr>
<td>Name:</td><td><input type="text" name="name" size="10" value="<?php echo $name ?>"/></td>
</tr><tr>
<td>Login:</td><td><input type="text" name="login" size="10" readonly value="<?php echo $login ?>"/></td>
</tr><tr>
<td>Password:</td><td><input type="text" name="password" size="10" value="<?php echo $password ?>"/></td>
</tr><tr>
<td>Status:</td><td>
<select name="status">
<?php
$options = array("active", "inactive", "pending");

foreach ($options as $value){ 
	if ($value == $status){
			echo "<option selected value='" . $value . "'>" . $value . "</option>";
	}
	else{
			echo "<option value='" . $value . "'>" . $value . "</option>";
	}
}
?>
</select>
</td>
</tr><tr>
<td colspan=2 align="center"><input type="submit" value="Update" /></td>
</tr><tr>

</tr><tr>
<td colspan=2 align="center"><font color="red" size= 2px>
<?php 


if (isset($_GET['e']))
{
	echo $_GET['e'];
}

?></font></td>
</tr>


</table>
<?php
$userLine = $_GET['r'];

echo "<input type='hidden' value='$userLine' name='row'/>";


?>

</form>
</div>

</body>
</html>
