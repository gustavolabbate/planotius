package com.planotius.websandbox;

import org.junit.AfterClass;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class UserDeleteTest {

    private static Login login;
    private static Menus menu;
    private static UserSearch userSearch;
    private static UserResult userResult;
    private static UserDelete userDelete;

    @BeforeClass
    public static void onload() {
        login = new Login().init(Login.class);
        menu = new Menus().init(Menus.class);
        userSearch = new UserSearch().init(UserSearch.class);
        userResult = new UserResult().init(UserResult.class);
        userDelete = new UserDelete().init(UserDelete.class);

        UserDataRestore restoreUsers = new UserDataRestore();
        restoreUsers.restoreCSVUserData();

        login.accessApp();

    }

    @Before
    public void beforeEachTest() {
        login.fillFieldsAndClickSubmit("admin", "admin123");
        menu.goToSearchUser();
    }

    @Test
    public void shouldDeleteUser() {
        userSearch.doSearch("");
        userResult.clickDeleteIconByRow(6);
        userDelete.clickConfirmButton();
        assertEquals("User succesfuly deleted!", userResult.getMessage());
    }

    @After
    public void afterEachTest() {
        menu.doLogout();
    }

    @AfterClass
    public static void tearDown() {
        Login.end();
    }

}
