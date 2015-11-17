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
        <?php

        $row = 1;
        $inactive = 0;
        $active = 0;
        $pending = 0;

        if (($handle = fopen("data/user.csv", "r")) !== FALSE) {
            while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {

                $num = count($data);
                for ($c = 0; $c < $num; $c++) {

                    if ($data[$c] == "inactive") {
                        $inactive++;
                    } else if ($data[$c] == "active") {
                        $active++;
                    } else if ($data[$c] == "pending") {
                        $pending++;
                    }
                }
            }
            fclose($handle);
        }
        ?>

        <div class="dashboard">
            <table class="simple" name="dashboard_table">
                <tr>
                    <th class="simple" colspan="2">Users</th> <td colspan="2"></td> <td colspan="2"></td> <td colspan="2"></td>
                    
                </tr>
                <tr>
                    <td rowspan="3" style="width: 1%"><img src="images/user-icon.png" height="100px"/></td>    
                    <td>active: <text class="count_result" id="active_count"><?php echo $active ?></text></td> <td rowspan="3"></td> <td></td> <td rowspan="3"></td> <td></td> <td rowspan="3"></td> <td></td>
                </tr>
                <tr>
                    <td>inactive: <text class="count_result" id="inactive_count"><?php echo $inactive ?></text></td> <td></td> <td></td> <td></td> 
                </tr>
                <tr>
                    <td>pending: <text class="count_result" id="pending_count"><?php echo $pending ?></text</td>    <td></td> <td></td> <td></td> 
                </tr>
            </table>

        </div>
    </body>
</html>
