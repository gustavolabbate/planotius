<?php
function checkUser($username, $password)
{

if (!strlen($username) || !strlen($password)) {
	return 2;
}

$success = 1;

$handle = fopen("../data/user.csv", "r");

while (($data = fgetcsv($handle)) !== FALSE) {
    if ($data[1] == $username && $data[2] == $password) {
        return 0;
        break;
    }
}

fclose($handle);

return  1;
}

if($_SERVER["REQUEST_METHOD"] == "POST")
{

$myusername=$_POST['username'];
$mypassword=$_POST['password']; 


$resultLogin=checkUser($myusername, $mypassword);
if ($resultLogin==0){
header("location: ../main.php");
}
else{
header("location: ../index.php?e=$resultLogin");

}
}

?>
