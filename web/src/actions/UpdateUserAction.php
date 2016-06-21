<?php

if($_SERVER["REQUEST_METHOD"] == "POST")
{
$lineNum=$_POST['row'];
updateUser($lineNum);

}

function updateUser($lineNum){
$name=$_POST['name'];
$login=$_POST['login'];
$password=$_POST['password'];
$status=$_POST['status'];

    $arr = file("../data/user.csv");
	
    $lineToDelete = $lineNum-1;
	
	unset($arr["$lineToDelete"]);
	array_splice($arr, $lineToDelete, 0, array($name, ",",$login, ",",$password, ",",$status, "\n"));
	
	$file = fopen("../data/user.csv","w+");
   
   foreach($arr as $line) 
	{ 
	fwrite($file,$line); 
	
	}


	
	fclose($file);
		
header("location: ../UserResult.php?msg=User succesfuly updated!");
	
	
}

?>	
