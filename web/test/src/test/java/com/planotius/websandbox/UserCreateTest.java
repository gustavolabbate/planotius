package com.planotius.websandbox;

import org.junit.AfterClass;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class UserCreateTest {

    private static Login login;
    private static Menus menu;
    private static UserCreate userCreate;
    private static MainDashboard mainDashboard;

    @BeforeClass
    public static void onload() {
        login = new Login().init(Login.class);
        menu = new Menus().init(Menus.class);
        userCreate = new UserCreate().init(UserCreate.class);
        mainDashboard = new MainDashboard().init(MainDashboard.class);

        UserDataRestore restoreUsers = new UserDataRestore();
        restoreUsers.restoreCSVUserData();

        login.accessApp();

    }

    @Before
    public void beforeEachTest() {
        login.fillFieldsAndClickSubmit("admin", "admin123");
        menu.goToCreateUser();
    }

    @Test
    public void shouldCreateUser() {
        userCreate.fillFields("Test Create User", "tstUsrCreated", "tst123", "Active");
        userCreate.clickCreateButton();
        assertEquals("User created!", userCreate.getMessage());
    }

    @Test
    public void shouldCreateUserThatAlreadyExists() {
        userCreate.fillFields("Login already exists", "operator", "tst123", "Active");
        userCreate.clickCreateButton();
        assertEquals("User already exists. Choose a different login.", userCreate.getMessage());
    }

    @Test
    public void shouldCreateUserAndVerifyDashBoard() {
        menu.goToDashboard();
        int activeUsers = mainDashboard.getActiveCount();
        activeUsers++;
        menu.goToCreateUser();
        userCreate.fillFields("Test Create User Dashboard", "tstUsrdash", "tst123", "Active");
        userCreate.clickCreateButton();
        assertEquals("User created!", userCreate.getMessage());
        menu.goToDashboard();
        assertEquals(activeUsers, mainDashboard.getActiveCount());
    }

    @Test
    public void shouldCreateUserEmptyFields() {
        userCreate.clickCreateButton();
        assertEquals("Please enter all info when creating users.", userCreate.getMessage());
    }

    @Test
    public void shouldCreateUserEmptyLoginFields() {
        userCreate.fillFields("Test Empty Login", "", "tst123", "Active");
        userCreate.clickCreateButton();
        assertEquals("Please enter all info when creating users.", userCreate.getMessage());
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
