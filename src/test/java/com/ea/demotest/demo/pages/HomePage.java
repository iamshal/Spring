package com.ea.demotest.demo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Component;

@Component
public class HomePage {


    private Page page;
    private Locator lnkLogin;
    private Locator lnkEmployeeList;

    public HomePage(Page page) {
        this.page = page;
        lnkLogin = page.locator("text=Login");
        lnkEmployeeList = page.locator("text=Employee List");
    }


    public void clickLogin() {
        lnkLogin.click();
    }

    public void clickEmployeeList(){
        lnkEmployeeList.click();
    }
}
