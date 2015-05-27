package org.planotius.pageobjects;

import org.planotius.controller.Controller;
import org.planotius.controller.functions.Element;
import org.planotius.controller.functions.ElementDiscover;

/**
 *
 * @author ggodoy
 */
public class LocalTable extends Controller {

    //Tables
    @ElementDiscover("//table[@id='table_test']/tbody/tr[2]/td[2]")
    public Element elementFromSecondTD;

    @ElementDiscover(key = "tableHeader")
    public Element tableWithHeader;

    @ElementDiscover(key = "headlessTable")
    public Element headlessTable;

    @ElementDiscover(key = "myNumber1")
    public Element elementFromTDID;

    @ElementDiscover("changedByjs")
    public Element myInputText;

    public String getTextfromTD() {
        return elementFromTDID.getText();
    }

    public String getTextfromSecondWithoutElementDiscoverExtFile() {
        return elementFromSecondTD.getText();
    }

//    public String getValueFromTableWithHeader(String rowValue, String columnValue) {
//        System.out.print(".");
//        return tableWithHeader.getCellValue(rowValue, columnValue);
//    }
//    public String getValueByColumnIndexFromTableWithHeader(String value, int col) {
//        System.out.print("|");
//        return tableWithHeader.getCellValuebyColumnIndex(value, col);
//    }
//    public String getValueByRowIndexFromTableWithHeader(int row, String value) {
//        System.out.print(".");
//        return tableWithHeader.getCellValueByRowIndex(row, value);
//    }
//    public String getValueByRowAndColumnIndexFromTableWithHeader(int rowIndex, int columnIndex) {
//        System.out.print("|");
//        return tableWithHeader.getCellValueByRowAndColumnIndex(rowIndex, columnIndex);
//    }
//    public String getValueFromHeadlessTable(String rowValue, String columnValue) {
//        System.out.print(".");
//        return headlessTable.getCellValue(rowValue, columnValue);
//    }
//    public String getValueByColumnIndexFromHeadlessTable(String value, int col) {
//        System.out.print("|");
//        return headlessTable.getCellValuebyColumnIndex(value, col);
//    }
//    public String getValueByRowIndexFromHeadlessTable(int row, String value) {
//        System.out.print(".");
//        return headlessTable.getCellValueByRowIndex(row, value);
//    }
//    public String getValueByRowAndColumnIndexFromHeadlessTable(int rowIndex, int columnIndex) {
//        System.out.print("|");
//        return headlessTable.getCellValueByRowAndColumnIndex(rowIndex, columnIndex);
//    }
    public String getCellValueFromTableWithHeader(Object row, Object column) {
        System.out.print(".");
        return tableWithHeader.getCellValueFromTable(row, column);
    }

    public String getCellValueFromHeadlessTable(Object row, Object column) {
        System.out.print("|");
        return headlessTable.getCellValueFromTable(row, column);
    }

}
