package com.planotius.websandbox;

import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

public class UserDelete extends Controller {

    @ElementDiscover("user_delete")
    private Element userDeleteInfoTable;

    @ElementDiscover("Confirm")
    private Element confirmButton;

    public void clickConfirmButton() {
        confirmButton.click();
    }

    public String getNameFromStringField(String field) {
        return userDeleteInfoTable.getCellValueFromTable(field, "name");
    }

    public String getNameFromRowNumber(int row) {
        return userDeleteInfoTable.getCellValueFromTable(row, "name");
    }

    public String getLoginFromStringField(String field) {
        return userDeleteInfoTable.getCellValueFromTable(field, "login");
    }

    public String getLoginFromRowNumber(int row) {
        return userDeleteInfoTable.getCellValueFromTable(row, "login");
    }

    public String getPasswordFromStringField(String field) {
        return userDeleteInfoTable.getCellValueFromTable(field, "password");
    }

    public String getPasswordFromRowNumber(int row) {
        return userDeleteInfoTable.getCellValueFromTable(row, "password");
    }

    public String getStatusFromStringField(String field) {
        return userDeleteInfoTable.getCellValueFromTable(field, "status");
    }

    public String getStatusFromRowNumber(int row) {
        return userDeleteInfoTable.getCellValueFromTable(row, "status");
    }

}
