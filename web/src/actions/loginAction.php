<?php
include("UserAction.php");

if($_SERVER["REQUEST_METHOD"] == "POST")
{

$myusername=$_POST['username'];
$mypassword=$_POST['password']; 


$resultLogin=checkUser($myusername, $mypassword);
if ($resultLogin==0){
header("location: ../main.php");
}
else{
header("location: ../login.php?e=$resultLogin");

}
}

?>
