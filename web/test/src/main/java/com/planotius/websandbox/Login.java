package com.planotius.websandbox;

import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

public class Login extends Controller {

    @ElementDiscover("username")
    public Element username;
    @ElementDiscover("password")
    public Element password;

    @ElementDiscover("submit")
    public Element enterButton;

    public static void accessApp() {
        Controller se = new Controller() {
        };
        se.openUrl();
    }

    public void fillFieldsAndClickSubmit(String userName, String password) {
        this.username.sendKeys(userName);
        this.password.sendKeys(password);
        this.enterButton.click();
    }

    public static void end() {
        Controller.stop();
    }

}