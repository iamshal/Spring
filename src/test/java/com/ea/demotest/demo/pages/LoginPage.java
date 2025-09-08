package com.ea.demotest.demo.pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Component;

@Component
public class LoginPage {

    private Locator txtUserName;
    private Locator txtPassword;
    private Locator btnLogin;

    public LoginPage(Page page){
        txtUserName = page.locator("input[name='UserName']");
        txtPassword = page.locator("input[name='Password']");
        btnLogin = page.locator(".btn");
    }


    public void performLogin(String userName, String password) {
        txtUserName.fill(userName);
        txtPassword.fill(password);
        btnLogin.click();
    }
}
