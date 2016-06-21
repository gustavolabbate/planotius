<?php
$file = "../data/user.csv";

$contents = "name,login,password,status\n"
        . "System administrator,admin,admin123,active\n"
        . "System operator,operator,ope123,active\n"
        . "System user,user,usr123,active\n"
        . "System guest,guest,gst123,inactive\n"
        . "Testing user number 1,test1,tst123,active\n"
        . "Testing user number 2,test2,tst123,active\n"
        . "Testing user number 3,test3,tst123,inactive\n"
        . "Testing user number 4,test4,tst123,inactive\n"
;
file_put_contents($file, $contents);



header("location: ../UserResult.php");
?>

/*name,login,password,status
System administrator,admin,admin123,active
System operator,operator,ope123,active
System user,user,usr123,active
System guest,guest,gst123,inactive
sixth,123,123,active
oitavo,123,123,active
"mais um",logg,1234,active
este,tambem,3414,active
mais,um,dasda,active
das,das,dasd,active
789,798,79,active
Gustavo,ggodoy,g123,active
*/