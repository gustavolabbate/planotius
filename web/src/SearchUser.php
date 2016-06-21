<html>
<head>
  <link rel="stylesheet" href="css/style_sheet.css">
</head>
<body>
<div id="menu">
<?php 
include('menu.php');
?>

<div class="create" id="addUser">



<form action="UserResult.php" method="POST">

<table class="create">
<!-- <tr>
 <td>Name:</td><td><input type="text" name="name" size="10" disabled="true"/></td> 
</tr>-->
<tr>
<td>Login:</td><td><input type="text" name="login" size="10"/></td>
<td><input type="submit" value="Search" id="Search" /></td>
</tr>
<!--<tr>
<td>Status:</td><td>

<select name="status" disabled="true">
	<option value="active">Active</option>
	<option value="inactive">Inactive</option>
	<option value="waiting">Waiting Approval</option>
</select>
</td>
</tr>
-->
<!-- 
<tr>
<td colspan=2 align="center"><input type="submit" value="Search" /></td>
</tr><tr>

</tr>
-->
<tr>
<td colspan=3 align="center"><font color="red" size= 2px>
<?php 


if (isset($_GET['e']))
{
	echo $_GET['e'];
}

?></font></td>
</tr>


</table>
</form>
</div>

</body>
</html>
