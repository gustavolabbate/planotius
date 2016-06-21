package com.planotius.websandbox;

import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

public class UserSearch extends Controller {

    
    @ElementDiscover("login")
    private Element login;
    
    
    @ElementDiscover("Search")
    private Element searchButton;
    
    
    public void doSearch(String text) {
        if (login.isDisplayed()) {
            login.sendKeys(text);
            searchButton.click();
        }
    }

}
