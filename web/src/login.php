<html>
<head>
  <link rel="stylesheet" href="css/style_sheet.css">
</head>
<body>

<div class="login" id="login">
<form action="actions/loginAction.php" method="POST">
<table class="login">
<tr>
<td>username</td><td><input type="text" name="username" size=10/></td><td rowspan="2" align="middle"><img src="images/keys.png" height="40px" /></td>
</tr><tr>
<td>password</td><td><input type="password" name="password" size=10/></td>
</tr><tr>
<td align="center" colspan=2><input type="submit" id="submit" value="enter"/></td>
</tr><tr>
<td colspan=2 align="center"><font color="red" size= 2px>
<?php 


if (isset($_GET['e']))
{
	if ($_GET['e']==1){
		echo "Invalid username or password";
	}    

	if ($_GET['e']==2){
		echo "Please, enter a username and a password.";
	} 




}

?></font></td>
</tr>

</table>
</form>



</div>
</body>
</html>
