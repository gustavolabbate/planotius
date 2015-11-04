<html>
<head>
  <link rel="stylesheet" href="css/style_sheet.css">
</head>
<body>
<div id="menu">
<?php 
include('menu.php');
?>
</div>

<div class="message">
User created!
</div>
<div class='container'>
<table>
<tr>
<th>Name</th><th>Login</th><th>Password</th><th>Status</th>
</tr><tr>
<?php 
echo "<td>" . $_GET['name']."</td><td>".$_GET['login']."</td><td>".$_GET['password']."</td><td>".$_GET['status']."</td>";
?>
</tr>
</table>
</div>
</body>
</html>
