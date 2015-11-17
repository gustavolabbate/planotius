<html>
    <head>
        <link rel="stylesheet" href="css/style_sheet.css">
    </head>
    <body style="background-image: url('http://localhost:8888/images/bg1.jpg')">

        <div class="login">
            <div style="float: left; width: 60%;">
                <table class="login" >
                    <tr>
                        <th class="login" colspan="3">SandBox</th>                        
                    </tr>
                    <tr>
                        <td><img src="images/sandbox.jpg" height="110px"/></td>
                        <td><h4>Welcome to the test automation sandbox.</h4>
                            Here you are invited to explore all features on you test framework tool.
                        </td><td rowspan="4" align="middle">&nbsp</td>
                    </tr><tr>
                        <td>&nbsp</td><td>&nbsp</td>
                    </tr><tr>
                        <td align="center" colspan=2>&nbsp</td>
                    </tr>
                    <tr>
                        <td colspan=2 align="center"></td>
                    </tr>

                </table>
            </div>
            <div style="float: left; width: 5%;">&nbsp</div>
            <div style="float: left; width: 20%;">
                <form action="actions/loginAction.php" method="POST">
                    <table class="login">
                        <tr>
                            <th class="login" colspan="3">Login</th>                        
                        </tr>
                        <tr>
                            <td>username</td><td><input type="text" name="username" size=30/></td><td rowspan="4" align="middle"><img src="images/keys.png" height="50px" /></td>
                        </tr><tr>
                            <td>password</td><td><input type="password" name="password" size=30/></td>
                        </tr><tr>
                            <td align="center" colspan=2><input type="submit" id="submit" value="enter"/></td>
                        </tr><tr>
                            <td colspan=2 align="center"><font color="red" size= 2px>
                                <?php
                                if (isset($_GET['e'])) {
                                    if ($_GET['e'] == 1) {
                                        echo "Invalid username or password";
                                    }

                                    if ($_GET['e'] == 2) {
                                        echo "Please, enter a username and a password.";
                                    }
                                }
                                ?></font></td>
                        </tr>

                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
