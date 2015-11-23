package com.planotius.websandbox;

import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

public class UserUpdate extends Controller {

    @ElementDiscover("name")
    private Element name;

    @ElementDiscover("login")
    private Element login;

    @ElementDiscover("password")
    private Element password;

    @ElementDiscover("status")
    private Element status;

    @ElementDiscover("Update")
    private Element updateButton;

    public void clickUpdateButton() {
        updateButton.click();
    }

    public void fillName(String name) {
        this.name.clear();
        this.name.sendKeys(name);
    }

    public void fillPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void changeStatus(String status) {
        this.status.selectOnlist(status);
    }
}
