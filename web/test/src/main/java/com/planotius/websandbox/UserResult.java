package com.planotius.websandbox;

import org.openqa.selenium.By;
import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

public class UserResult extends Controller {

    @ElementDiscover("user_result")
    private Element userResultTable;

    @ElementDiscover("message")
    private Element message;

    public String getMessage() {
        return this.message.getText();
    }

    public String getNameFromStringField(String field) {
        return userResultTable.getCellValueFromTable(field, "name");
    }

    public String getNameFromRowNumber(int row) {
        return userResultTable.getCellValueFromTable(row, "name");
    }

    public String getLoginFromStringField(String field) {
        return userResultTable.getCellValueFromTable(field, "login");
    }

    public String getLoginFromRowNumber(int row) {
        return userResultTable.getCellValueFromTable(row, "login");
    }

    public String getPasswordFromStringField(String field) {
        return userResultTable.getCellValueFromTable(field, "password");
    }

    public String getPasswordFromRowNumber(int row) {
        return userResultTable.getCellValueFromTable(row, "password");
    }

    public String getStatusFromStringField(String field) {
        return userResultTable.getCellValueFromTable(field, "status");
    }

    public String getStatusFromRowNumber(int row) {
        return userResultTable.getCellValueFromTable(row, "status");
    }

    public void clickDeleteIconByRow(int row) {
        this.getElement("delete" + row).click();
    }

    public void clickUpdateIconByRow(int row) {
        this.getElement("update" + row).click();
    }

}
