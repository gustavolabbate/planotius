package com.planotius.websandbox;

import org.junit.AfterClass;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MainDashboardTest {

    private static Login login;
    private static Menus menu;
    private static MainDashboard mainDashboard;

    @BeforeClass
    public static void onload() {
        login = new Login().init(Login.class);
        menu = new Menus().init(Menus.class);
        mainDashboard = new MainDashboard().init(MainDashboard.class);

        UserDataRestore restoreUsers = new UserDataRestore();
        restoreUsers.restoreCSVUserData();

        login.accessApp();
    }

    @Before
    public void beforeEachTest() {
        login.fillFieldsAndClickSubmit("admin", "admin123");
    }

    @Test
    public void shouldCountUserStatus() {
        assertEquals(5, mainDashboard.getActiveCount());
        assertEquals(3, mainDashboard.getInactiveCount());
        assertEquals(0, mainDashboard.getPendingCount());
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
