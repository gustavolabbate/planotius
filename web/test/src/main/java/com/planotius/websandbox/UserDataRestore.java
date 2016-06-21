package com.planotius.websandbox;

import org.planotius.controller.Controller;

public class UserDataRestore extends Controller {

    public void restoreCSVUserData() {
        
        setUrl(getUrl() + "/actions/RestoreCsv.php");
        openUrl();
    }

}
