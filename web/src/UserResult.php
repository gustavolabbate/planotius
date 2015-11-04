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
<?php 

if (isset($_GET['msg']))
{
	echo $_GET['msg'] ;
}

?>
</div>


<div class="container" id="resultTable">
<?php
$username = $_POST['name'];
$login = $_POST['login'];
$status = $_POST['status'];

$validRow = NULL;

if (!empty($login)){
	if (($handle = fopen("data/user.csv", "r")) !== FALSE) {
		while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
			$num = count($data);
			for ($c=0; $c < $num; $c++) {
				
				if($login == $data[$c]){
				$validRow = $c+1;
				}
			}
		}
	}
}
else{
	$validRow = -1;
}

if (empty($validRow)){
	header("location: SearchUser.php?e=User not found!");
}	

$row = 1;
if (($handle = fopen("data/user.csv", "r")) !== FALSE) {
	echo "<table name='user_result'>";
    while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
	echo "<tr>";
        $num = count($data);
	for ($c=0; $c < $num; $c++) {
	
	if ($row ==1 ){
	echo "<th>" . $data[$c] . "</th>";
	}
	else{

	//if(!empty($validRow)){
	if($row == $validRow || $validRow == -1 ){
		echo "<td>" . $data[$c] . "</td>";
	}
	}
	}
	
	if ($row ==1 ){
		echo "<th></th><th></th>";
	}
	
	elseif ($row ==2 || $row ==3 || $row ==4|| $row ==5){
		
		if($row == $validRow || $validRow == -1){
		echo "<td></td><td></td>";	
		}
	}
	else{
		if($row == $validRow || $validRow == -1){
	echo "<td align='center' width='5px'><a href='DeleteUser.php?r=$row'><img src='images/del_icon.png' width='15px' /> </a></td>";	
	echo "<td align='center' width='5px'><a href='EditUser.php?r=$row'><img src='images/edit_icon.png' width='20px' /></a></td>";	
		}
	}
	echo "</tr>";
	
	$row++;
    }
	echo "<tr><td colspan='6' style='background-color:#6D929B'> </td></tr>" ;
	echo "</table>";
    fclose($handle);
	}




?>
</div>
</body>
</html>
