package com.planotius.websandbox;

import org.junit.AfterClass;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class UserResultTest {

    private static Login login;
    private static Menus menu;
    private static UserSearch userSearch;
    private static UserResult userResult;

    @BeforeClass
    public static void onload() {
        login = new Login().init(Login.class);
        menu = new Menus().init(Menus.class);
        userSearch = new UserSearch().init(UserSearch.class);
        userResult = new UserResult().init(UserResult.class);

        login.accessApp();

    }

    @Before
    public void beforeEachTest() {
        login.fillFieldsAndClickSubmit("admin", "admin123");
        menu.goToSearchUser();
    }

    @Test
    public void shouldSearchForAdminUser() {
        userSearch.doSearch("admin");

        userSearch.printScreen("target/SearchAdmin.png");
        assertTrue(login.verifyMessage("System administrator"));
        assertFalse(login.verifyMessage("System operator"));
    }

    @Test
    public void shouldSearchForAllUsers() {
        userSearch.doSearch("");

        userSearch.printScreen("target/SearchAllUsers.png");
        assertTrue(login.verifyMessage("System administrator"));
        assertTrue(login.verifyMessage("System operator"));
        assertTrue(login.verifyMessage("System user"));
        assertTrue(login.verifyMessage("System guest"));
    }

    @Test
    public void shouldVerifyLoginByName() {
        userSearch.doSearch("");
        assertEquals("admin", userResult.getLoginFromStringField("System administrator"));
    }

    @Test
    public void shouldVerifyIfAdminIsTheFirst() {
        userSearch.doSearch("");
        assertEquals("admin", userResult.getLoginFromRowNumber(1));
    }

    @Test
    public void shouldVerifyIfAdminIsActive() {
        userSearch.doSearch("");
        assertEquals("active", userResult.getStatusFromStringField("System administrator"));
    }

    @Test
    public void shouldVerifyIfAdminHasItsDefaultPassword() {
        userSearch.doSearch("");
        assertEquals("admin123", userResult.getPasswordFromStringField("admin"));
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
