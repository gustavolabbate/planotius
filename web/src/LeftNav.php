<html>
<head>
  <link rel="stylesheet" href="css/LeftNav.css">
  <script type="text/javascript" src="js/LeftNav.js"></script>
</head>
<body>
 <div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="SearchUser.php">User</a>
  <a href="#">Charts</a>
  <a href="#">Home</a>
  <a href="#">Contact</a>
</div>

<!-- Use any element to open the sidenav -->
<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; open</span>

<!-- Add all page content inside this div if you want the side nav to push page content to the right (not used if you only want the sidenav to sit on top of the page -->
<div id="main">
  ...
</div>


</body>
</html>
