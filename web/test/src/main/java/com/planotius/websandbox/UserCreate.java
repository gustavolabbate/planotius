package com.planotius.websandbox;

import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

public class UserCreate extends Controller {

    @ElementDiscover("name")
    private Element name;

    @ElementDiscover("login")
    private Element login;

    @ElementDiscover("password")
    private Element password;

    @ElementDiscover("status")
    private Element status;

    @ElementDiscover("Create")
    private Element createButton;

    @ElementDiscover("message")
    private Element message;

    public void fillFields(String name, String login, String password, Object status) {
        this.name.sendKeys(name);
        this.login.sendKeys(login);
        this.password.sendKeys(password);
        this.status.selectOnlist(status);
    }

    public void clickCreateButton() {
        this.createButton.click();
    }

    public String getMessage(){
        return this.message.getText();
    }
    
}
