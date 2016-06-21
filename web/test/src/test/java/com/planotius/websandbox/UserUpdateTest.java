package com.planotius.websandbox;

import org.junit.AfterClass;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class UserUpdateTest {

    private static Login login;
    private static Menus menu;
    private static UserSearch userSearch;
    private static UserResult userResult;
    private static UserUpdate userUpdate;

    @BeforeClass
    public static void onload() {
        login = new Login().init(Login.class);
        menu = new Menus().init(Menus.class);
        userSearch = new UserSearch().init(UserSearch.class);
        userResult = new UserResult().init(UserResult.class);
        userUpdate = new UserUpdate().init(UserUpdate.class);

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
    public void shouldUpdateUser() {
        userSearch.doSearch("");
        userResult.clickUpdateIconByRow(6);
        userUpdate.fillName("The name was changed");
        userUpdate.clickUpdateButton();
        assertEquals("User succesfuly updated!", userResult.getMessage());
        assertEquals("The name was changed", userResult.getNameFromRowNumber(6));
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
