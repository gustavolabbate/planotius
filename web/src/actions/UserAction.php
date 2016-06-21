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

function checkUnique($username)
{
$handle = fopen("../data/user.csv", "r");

while (($data = fgetcsv($handle)) !== FALSE) {
    if ($data[1] == $username) {
        return 1;
        break;
    }
}

fclose($handle);

return  0;
}


if($_SERVER["REQUEST_METHOD"] == "POST")
{
$name=$_POST['name'];
$login=$_POST['login'];
$password=$_POST['password'];
$status=$_POST['status'];

	if (empty($name) || empty($login) || empty($password) || empty($status) )
	{
		header("location: ../CreateUser.php?e=Please enter all info when creating users.&name=$name&login=$login&password=$password&status=$status");
	}
	
	else if (checkUnique($login) == 1)
	{
		header("location: ../CreateUser.php?e=User already exists. Choose a different login.");
	}
	else
	{

		$list = array(
		$name,$login,$password,$status
		);

		$file = fopen("../data/user.csv","a");


		fputcsv($file,$list);

		fclose($file);

		header("location: ../CreateUserSuccess.php?name=$name&login=$login&password=$password&status=$status");
	}
}
?>	
