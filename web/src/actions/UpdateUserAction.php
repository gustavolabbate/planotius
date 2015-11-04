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
  
  
  	//$file = fopen("../data/user.csv","w+");
	
/*		$list = array(
		$name,$login,$password,$status
		);

		$file = fopen("../data/user.csv","a");


		fputcsv($file,$list);

		fclose($file);
	*/


    /*foreach($arr as $line) 
	{ 
	fwrite($file,$line); 
	
	}*/

	
		
header("location: ../CreateUserSuccess.php?name=$name&login=$login&password=$password&status=$status");
	
}

?>	
