<?php

if($_SERVER["REQUEST_METHOD"] == "POST")
{
$lineNum=$_POST['row'];
delLineFromFile($lineNum);

}

function delLineFromFile($lineNum){
    $arr = file("../data/user.csv");
	
    $lineToDelete = $lineNum-1;
	
	if ($lineToDelete == 1){
	header("location: ../UserResult.php?msg=Not possible to delete Admin!!");		
		
	}
	
  unset($arr["$lineToDelete"]);

  	$file = fopen("../data/user.csv","w+");
    
    foreach($arr as $line) 
	{ 
	fwrite($file,$line); 
	
	}

	fclose($file);
		
header("location: ../UserResult.php?msg=User succesfuly deleted!");

	
}



?>	
