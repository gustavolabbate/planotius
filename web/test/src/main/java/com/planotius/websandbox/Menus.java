package com.planotius.websandbox;

import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

public class Menus extends Controller {

    //User
    @ElementDiscover("User")
    private Element userDropDown;

    @ElementDiscover("Create")
    private Element createUser;
    
    @ElementDiscover("Search")
    private Element searchUser;
    
    //Chart
    @ElementDiscover("Charts")
    private Element chartsDropDown;
    
    @ElementDiscover("Pie")
    private Element pieChart;
    
    @ElementDiscover("Bar")
    private Element barChart;
    
    //Home
    @ElementDiscover("Home")
    private Element homeDropDown;
    
    @ElementDiscover("Help")
    private Element help;
    
    @ElementDiscover("About")
    private Element about;
    
    @ElementDiscover("Logout")
    private Element logOut;
    
    
    public void goToDashboard() {
        homeDropDown.click();
    }

    public void goToSearchUser() {
        userDropDown.click();
        searchUser.click();
    }

    public void goToCreateUser() {
        userDropDown.click();
        createUser.click();
    }
    
    public void goToPieCharts(){
        chartsDropDown.click();
        pieChart.click();
    }
    
    public void goToBarCharts(){
        chartsDropDown.click();
        barChart.click();
    }

    public boolean doLogout(){
        if(homeDropDown.isDisplayed()){
        homeDropDown.click();
        logOut.click();
        return true;
        }
        return false;
        
    }

}
