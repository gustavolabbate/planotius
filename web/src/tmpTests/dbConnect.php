<?php
$username = "root";
$password = "admin";
$hostname = "10.35.102.122"; 

//connection to the database
$dbhandle = mysql_connect($hostname, $username, $password)
  or die("Unable to connect to MySQL");
echo "Connected to MySQL<br>";
?>