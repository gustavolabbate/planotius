package com.planotius.websandbox;

import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

public class UserResult extends Controller {

    @ElementDiscover("user_result")
    private Element userResultTable;

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

}
