package com.planotius.websandbox;

import org.junit.AfterClass;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    private final Login login;
    private final Menus menu;

    String INVALID_CREDENTIAL_MESSAGE="Invalid username or password";
    String EMPTY_FIELDS_MESSAGE = "Please, enter a username and a password.";
    
    public LoginTest() {
        login = new Login().init(Login.class);
        menu = new Menus().init(Menus.class);
    }

    @BeforeClass
    public static void onload() {
        Login.accessApp();
    }

    @Test
    public void shouldAccess() {
        login.fillFieldsAndClickSubmit("admin", "admin123");
        login.printScreen("target/shouldAccess.png");
        assertTrue(login.verifyMessage("bla"));
    }

    @Test
    public void shouldNotAccessWithInvalidCredentials() {
        login.fillFieldsAndClickSubmit("bla", "blabla");
        login.printScreen("target/shouldNotAccess-InvalidCredentials.png");
        assertTrue(login.verifyMessage(INVALID_CREDENTIAL_MESSAGE));
    }

    @Test
    public void shouldNotAccessWithEmptyUsername() {
        login.fillFieldsAndClickSubmit("", "admin123");
        login.printScreen("target/shouldNotAccess-EmptyUsername.png");
        assertTrue(login.verifyMessage(EMPTY_FIELDS_MESSAGE));
    }

    @Test
    public void shouldNotAccessWithEmptyPassword() {
        login.fillFieldsAndClickSubmit("admin", "");
        login.printScreen("target/shouldNotAccess-EmptyPassword.png");
        assertTrue(login.verifyMessage(EMPTY_FIELDS_MESSAGE));
    }

    @Test
    public void shouldNotAccessWithEmptyFields() {
        login.fillFieldsAndClickSubmit("", "");
        login.printScreen("target/shouldNotAccess-EmptyFields.png");
        assertTrue(login.verifyMessage(EMPTY_FIELDS_MESSAGE));
    }

    @After
    public void finish() {
        if (menu.doLogout()){
        menu.printScreen("target/logedOut.png");
        }
    }

    @AfterClass
    public static void tearDown() {
        Login.end();
    }

}
